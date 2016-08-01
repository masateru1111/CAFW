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
import logic.UpdateLogic;

/**
 * Servlet implementation class UpdateUserServlet
 */
@WebServlet("/UpdateUserServlet")
/**
 * ユーザー情報変更から確認へ遷移するクラス
 * @author Masateru Iwata
 * @version 1.0
 */
public class UpdateUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher dispatcher = null;

        //セッションスコープからユーザー情報取得
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");

        //ユーザーがログイン状態でなければ、ログイン画面に返す。あれば、情報変更画面へ
        if(user != null){

            //ユーザー情報を取得する
            UpdateLogic logic = new UpdateLogic();
            user = logic.find(user);
            session.setAttribute("user", user);

            dispatcher = request.getRequestDispatcher("/update_user.jsp");
            dispatcher.forward(request, response);
        }else{
            request.setAttribute("errorMsg", "ログインしなおしてください");
            dispatcher = request.getRequestDispatcher("/login.jsp");
            dispatcher.forward(request, response);

        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher dispatcher = null;
        //入力項目を格納
        String password =request.getParameter("password");
        String email =request.getParameter("email");
        String cellphone =request.getParameter("cellphone");
        String postalCode =request.getParameter("postalCode");
        String address =request.getParameter("address");
        boolean res = true;
        boolean isResult = false;
        boolean check = true;

        //セッションスコープからユーザー情報を取得
        HttpSession session = request.getSession();  
        User user =(User) session.getAttribute("user");

        //入力項目チェック。変更がなければエラーを返す
        if(password.equals(user.getPassword())
                && email.equals(user.getEmail())
                && cellphone.equals(user.getCellphone())
                && postalCode.equals(user.getPostalCode())
                && address.equals(user.getAddress())){
            request.setAttribute("errorMsg", "どれか更新してください");
            dispatcher = request.getRequestDispatcher("/update_user.jsp");
            dispatcher.forward(request, response);
        }else{

            //リクエストスコープに一旦格納（入力ミスによる値を引き渡し）
            request.setAttribute("email", email);
            request.setAttribute("confirmEmail", email);
            request.setAttribute("cellphone", cellphone);
            request.setAttribute("postalCode", postalCode);
            request.setAttribute("address", address);
            
            UpdateLogic logic = new UpdateLogic();

            //電話番号が変更できるか確認する。
            res = cellphone.equals(user.getCellphone());   
            if(!res){
                user.setCellphone(cellphone);               //検証するためにuserビーンズにcellphone格納
                isResult = logic.searchCellphone(user);
                if(isResult){
                    request.setAttribute("errorMsg", logic.getErrorMsg());
                    check = false;
                }
            }

            //パスワードが変更できるか確認する
            res = password.equals(user.getPassword());
            if(!res){
                user.setPassword(password);                 //検証するためにuserビーンズに格納
                isResult = logic.searchPass(user);
                if(isResult){
                    request.setAttribute("errorMsg", logic.getErrorMsg());
                    check=false;
                }
            }

            //メールアドレスが変更できるか確認する
            res = email.equals(user.getEmail());
            if(!res){
                user.setEmail(email);                       //検証するためにuserビーンズに格納
                isResult = logic.searchEmail(user);
                if(isResult){
                    request.setAttribute("errorMsg", logic.getErrorMsg());
                    check = false;
                }
            }
            
            //電話番号、アドレス、パスワードが更新できなければ元の画面に繊維
            if(!check){
                request.setAttribute("errorMsg", logic.getErrorMsg());
                dispatcher = request.getRequestDispatcher("/update_user.jsp");
                dispatcher.forward(request, response);
                
            }else{

                //上記、すべてユーザービーンズに格納
                user.setPassword(password);
                user.setEmail(email);
                user.setCellphone(cellphone);
                user.setPostalCode(postalCode);
                user.setAddress(address);

                //セッションスコープにユーザー情報を格納
                session = request.getSession();
                session.setAttribute("user", user);
                dispatcher = request.getRequestDispatcher("/update_user_check.jsp");
                dispatcher.forward(request, response);    
            }
        }
    }
}


