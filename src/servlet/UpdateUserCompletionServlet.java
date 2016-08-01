package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.User;
import logic.UpdateUserCompletionLogic;;
/**
 * Servlet implementation class UpdateUserCompletionServlet
 */
@WebServlet("/UpdateUserCompletionServlet")
/**
 * ユーザーの情報変更を行うクラス
 * @author Masateru Iwata
 * @version 1.0
 */
public class UpdateUserCompletionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateUserCompletionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	    boolean res = false;
	    RequestDispatcher dispatcher = null;
	    
	    //セッションスコープからユーザー情報を取得
	    HttpSession session = request.getSession();
	    User user = (User) session.getAttribute("user");
	    
	    //データベース更新
	    UpdateUserCompletionLogic logic = new UpdateUserCompletionLogic();
	    res = logic.execute(user);
	    
	    //更新できたらメインへ、失敗なら更新画面へ
	    if(res){
	        User user2 = new User();
	        user2.setId(user.getId());
	        session.setAttribute("user", user2);
	       
	        dispatcher = request.getRequestDispatcher("/update_user_completion.jsp");
	        dispatcher.forward(request, response);  
	    }else{
	        request.setAttribute("errorMsg", "更新できませんでした");
	        dispatcher = request.getRequestDispatcher("update_user.jsp");
	        dispatcher.forward(request, response);
	    }

	}


}
