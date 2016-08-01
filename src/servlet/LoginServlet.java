package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.User;
import logic.LoginLogic;
/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
/**
 * ログインできるか確認するクラス
 * @author Masateru Iwata
 * @version 1.0
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	    RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
	    dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    
	    RequestDispatcher dispatcher = null;
	    //フォーム情報取得
	    String userName = request.getParameter("userName");
	    String password = request.getParameter("password");
	    
	    boolean res = false;
	    
	    //ユーザーbeansインスタンス
	    User user = new User();
	    user.setUserName(userName);
	    user.setPassword(password);
	    
	    //LoginLogic生成　データベースにuserName,passwordがあればtrue
	    LoginLogic logic = new LoginLogic();
	    res = logic.execute(user);
	    
	    if(res){
	        //trueならidのみ取得してセッションへ格納。CartServletへ遷移。
	        user.setId(logic.getId());
	        user.setUserName("null");
	        user.setPassword("null");
	        HttpSession session = request.getSession();
	        session.setAttribute("user", user);
	        
	        ServletContext sc = request.getServletContext();
	        dispatcher = sc.getRequestDispatcher("/CartServlet");
	        dispatcher.forward(request, response);
	    }else{
	        //falseならerrorMsgをリクエストスコープに格納。login.jspへ遷移。
	        request.setAttribute("errorMsg", "ログインできませんでした");
	        dispatcher = request.getRequestDispatcher("/login.jsp");
	        dispatcher.forward(request, response);
	    }
	    
	}

}
