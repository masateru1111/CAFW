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
import logic.UpdateCardLogic;;
/**
 * Servlet implementation class UpdateCardCompletionServlet
 */
@WebServlet("/UpdateCardCompletionServlet")
/**
 * クレジットカードを更新するクラス
 * @author Masateru Iwata
 * @version 1.0
 */
public class UpdateCardCompletionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateCardCompletionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	    RequestDispatcher dispatcher = null;
	    HttpSession session = request.getSession();
	    User user = (User) session.getAttribute("user");
	    boolean res = false;
	    
	    //カード情報を更新できるか確認する
	    UpdateCardLogic logic = new UpdateCardLogic();
	    res = logic.execute(user);
	    if(res){
	        User user2 = new User();
	        user2.setId(user.getId());
	        dispatcher = request.getRequestDispatcher("/update_card_completion.jsp");
	        dispatcher.forward(request, response);
	    }else{
	        request.setAttribute("errorMsg", "登録できませんでした");
	        dispatcher = request.getRequestDispatcher("/update_card.jsp");
	        dispatcher.forward(request, response);
	    }
	}


}
