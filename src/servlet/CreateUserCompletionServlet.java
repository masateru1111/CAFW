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
import logic.CreateUserCompletionLogic;

/**
 * Servlet implementation class CreateUserCompletionServlet
 */
@WebServlet("/CreateUserCompletionServlet")
/**
 * 新規登録を完了させるクラス
 * @author Masateru Iwata
 * @version 1.0
 */
public class CreateUserCompletionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateUserCompletionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	    RequestDispatcher dispatcher = null;
	    
	    //セッション情報取得
	    HttpSession session = request.getSession();
	    User user = (User)session.getAttribute("user");
	    
	    //ユーザー登録できるか確認
	    CreateUserCompletionLogic logic = new CreateUserCompletionLogic();
	    boolean res = logic.execute(user);
	    if(res){
	        User user2 = new User();
	        user2.setId(logic.getId());
	        
	        //idだけを格納したuser2をセッションスコープに上書き
	        session.setAttribute("user", user2);
	        dispatcher = request.getRequestDispatcher("/create_user_completion.jsp");
	        dispatcher.forward(request, response);
	    }else{
	        request.setAttribute("errorMsg", "登録できませんでした");
	        dispatcher = request.getRequestDispatcher("/create_user.jsp");
	        dispatcher.forward(request, response);
	    }
	    
	}



}
