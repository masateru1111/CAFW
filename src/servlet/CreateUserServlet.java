package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oracle.jrockit.jfr.RequestableEvent;

import model.User;
import logic.CreateUserLogic;

/**
 * Servlet implementation class CreateUserServlet
 */
@WebServlet("/CreateUserServlet")
/**
 * 新規登録入力から確認画面へ遷移するクラス
 * @author Masateru Iwata
 * @version 1.0
 */
public class CreateUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	  //セッションスコープにユーザー情報があれば削除する。（確認ボタンを押すと、セッションに登録されていく。確認押して戻る作業を繰り返されると、セッションにゴミがたある）
	  HttpSession session = request.getSession();
	  session.invalidate();
	  
	  RequestDispatcher dispatcher = request.getRequestDispatcher("/create_user.jsp");
	  dispatcher.forward(request, response);
	    
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	    //入力項目を格納
	    String userName = request.getParameter("userName");
        String userKana =request.getParameter("userKana");
        String password =request.getParameter("password");
        String confirmPass =request.getParameter("confirmPass");
        String email =request.getParameter("email");
        String confirmEmail =request.getParameter("confirmEmail");
        String cellphone =request.getParameter("cellphone");
        String postalCode =request.getParameter("postalCode");
        String address =request.getParameter("address");
        RequestDispatcher dispatcher = null;
        boolean res = true;
        
        
        //入力に不備があれば新規登録画面へ繊維
        if(userName==null||userName.length()==0
                ||userKana==null||userKana.length()==0
                ||password==null||password.length()==0
                ||confirmPass==null||confirmPass.length()==0
                ||email==null||email.length()==0
                ||confirmEmail==null||confirmEmail.length()==0
                ||cellphone==null||cellphone.length()==0
                ||postalCode==null||postalCode.length()==0
                ||address==null||address.length()==0){
       
            request.setAttribute("vacantMsg", "入力項目に不備があります");
            dispatcher = request.getRequestDispatcher("/create_user.jsp");
            dispatcher.forward(request, response);
            
        }else{
            
            //ユーザービーンズに格納
            User user = new User();
            user.setUserName(userName);
            user.setUserKana(userKana);
            user.setPassword(password);
            user.setEmail(email);
            user.setCellphone(cellphone);
            user.setPostalCode(postalCode);
            user.setAddress(address);
            
            //リクエストスコープに一旦格納（入力ミスした時に、値を新規画面に引き渡すため）
            request.setAttribute("userName", userName);
            request.setAttribute("userKana", userKana);
            request.setAttribute("email", email);
            request.setAttribute("confirmEmail", confirmEmail);
            request.setAttribute("cellphone", cellphone);
            request.setAttribute("postalCode", postalCode);
            request.setAttribute("address", address);
            
            //電話番号、パスワード、メールアドレスチェック
            CreateUserLogic logic = new CreateUserLogic();
            res = logic.execute(user);
            if(res){
                
                //データベースに同じものが登録されている場合は、新規画面に返す
                request.setAttribute("errorMsg", logic.getErrorMsg());
                dispatcher = request.getRequestDispatcher("/create_user.jsp");
                dispatcher.forward(request, response);               
            }
            
            //セッションスコープにユーザー情報を格納し、確認画面に遷移
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            dispatcher = request.getRequestDispatcher("/create_user_check.jsp");
            dispatcher.forward(request, response);        
        }
	}
}
