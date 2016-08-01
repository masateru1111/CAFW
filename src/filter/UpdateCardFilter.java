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
@WebFilter("/UpdateCardServlet")
public class UpdateCardFilter implements Filter {

    /**
     * Default constructor. 
     */
    public UpdateCardFilter() {
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
        boolean res = true;
        boolean isResult = false;
        
        //バリデーションする値を取得
        String cardNumber = request.getParameter("cardNumber");
        String cardSecurity = request.getParameter("cardSecurity");
        String cardHolder = request.getParameter("cardHolder");
        
        //リクエストスコープに格納（ミスした時にもどす）
        request.setAttribute("cardSecurity", cardSecurity);
        request.setAttribute("cardHolder", cardHolder);
        
        if(cardNumber != null && cardNumber.length() != 0){

            //クレジットカード番号のバリデーション
            if(cardNumber.length() != 0){
                isResult = cardNumber.matches("^[0-9]{16}$");
                if(!isResult){
                    request.setAttribute("cardNumError", "半角数字16桁で入力してください");
                    res = false;
                }
            }

            //名義のバリデーション          
            if(cardHolder.length() != 0){
                isResult = cardHolder.matches("^[A-Z]* [A-Z]*$");
                if(!isResult){
                    request.setAttribute("cardHolderError", "半角英字で間に半角スペースをいれて入力してください");
                    res = false;
                }
            }

            //セキュリティコードのバリデーション
            if(cardSecurity.length() != 0){
                isResult = cardSecurity.matches("^[0-9]{3,4}$");
                if(!isResult){
                    request.setAttribute("cardSecurityError", "半角数字3~4桁で入力してください");
                    res = false;
                }
            }
        }

        if(!res){
            dispatcher = request.getRequestDispatcher("/update_card.jsp");
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
