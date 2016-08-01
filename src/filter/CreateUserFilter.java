package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 * Servlet Filter implementation class InputFilter
 */
@WebFilter("/CreateUserServlet")
public class CreateUserFilter implements Filter {

    /**
     * Default constructor. 
     */
    public CreateUserFilter() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @see Filter#destroy()
     */
    public void destroy() {
        // TODO Auto-generated method stub
    }

    /**
     * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
     */
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // TODO Auto-generated method stub
        // place your code here

        request.setCharacterEncoding("UTF-8");
        RequestDispatcher dispatcher = null;
        String email = request.getParameter("email");
        String userName = request.getParameter("userName");
        String userKana =request.getParameter("userKana");
        String password =request.getParameter("password");
        String confirmPass =request.getParameter("confirmPass");
        String confirmEmail =request.getParameter("confirmEmail");
        String cellphone =request.getParameter("cellphone");
        String postalCode =request.getParameter("postalCode");
        String address =request.getParameter("address");
        //最終的なバリデーション判定
        boolean res = true;
        //個々のバリデーション判定
        boolean isResult = false;

        //リクエストスコープに一旦格納（入力ミスによる値を引き渡し）
        request.setAttribute("userName", userName);
        request.setAttribute("userKana", userKana);
        request.setAttribute("email", email);
        request.setAttribute("confirmEmail", confirmEmail);
        request.setAttribute("cellphone", cellphone);
        request.setAttribute("postalCode", postalCode);
        request.setAttribute("address", address);
        
        //メールアドレスに値があれば処理
        if( email != null && email.length() != 0){
            
            //メールアドレスの入力バリデーション
            String matchFormat="^[a-zA-Z0-9!#$%&'_`/=~\\*\\+\\-\\?\\^\\{\\|\\}]+(\\.[a-zA-Z0-9!#$%&'_`/=~\\*\\+\\-\\?\\^\\{\\|\\}]+)*+(.*)@[a-zA-Z0-9][a-zA-Z0-9\\-]*(\\.[a-zA-Z0-9\\-]+)+$";
            isResult = email.matches(matchFormat);
            if(!isResult){
                request.setAttribute("emailError", "認められない形式です");
                res = false;
            }
            
            //名前のバリデーション
            matchFormat = "^[ぁ-んァ-ヶ一-龠一-十]+$";
            isResult = userName.matches(matchFormat);
            if(!isResult){
                request.setAttribute("nameError", "アルファベットと記号以外で入力してください");
                res = false;
            }
            
            //フリガナのバリデーション
            matchFormat = "^[ア-ン]{1,50}$";
            isResult = userKana.matches(matchFormat);
            if(!isResult){
                request.setAttribute("kanaError", "カタカナで入力してください");
                res = false;
            }
            
            //パスワードのバリデーション
            matchFormat = "^[a-zA-Z0-9]{8,16}$";
            isResult = password.matches(matchFormat);
            if(!isResult){
                request.setAttribute("passError", "半角英数字8~16文字で入力してください");
                res = false;
            }
            
            //郵便番号のバリデーション
            matchFormat = "^[0-9]{7}$";
            isResult = postalCode.matches(matchFormat);
            if(!isResult){
                request.setAttribute("postalCodeError", "半角数字７字ハイフンなしで入力してください");
                res = false;
            }
            
            //2回目のアドレスと照合
            if(!email.equals(confirmEmail)){
                request.setAttribute("confirmEmailError", "メールアドレスを確かめてください");
                res=false;
            }
            
            //2回目のパスワード照合
            if(!password.equals(confirmPass)){
                request.setAttribute("confirmPassError", "パスワードを確かめてください");
                res=false;
            }
            
            //電話番号の入力方法照合
            if(!cellphone.matches("[0-9]{10,11}")){
                request.setAttribute("cellphoneError", "電話番号を確認してください");
                res=false;
            }
            
        }

        if(!res){
            dispatcher = request.getRequestDispatcher("/create_user.jsp");
            dispatcher.forward(request, response);
        }
        
        // pass the request along the filter chain
        chain.doFilter(request, response);
    }

    /**
     * @see Filter#init(FilterConfig)
     */
    public void init(FilterConfig fConfig) throws ServletException {
        // TODO Auto-generated method stub
    }

}
