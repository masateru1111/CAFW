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
import com.internousdev.util.CreditCard;

/**
 * Servlet implementation class UpdateCardServlet
 */
@WebServlet("/UpdateCardServlet")
/**
 * クレジットカード情報入力から確認へ遷移するクラス
 * @author Masateru Iwata
 * @version 1.0
 */
public class UpdateCardServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateCardServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher dispatcher = null;
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        
        if(user != null){
            dispatcher = request.getRequestDispatcher("/update_card.jsp");
            dispatcher.forward(request, response); 
        }else{
            dispatcher = request.getRequestDispatcher("/login.jsp");
            dispatcher.forward(request, response);
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        RequestDispatcher dispatcher = null;
        boolean res = false;
        boolean isResult = false;

        //セッションスコープ内のユーザー情報を取得
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");

        //パラメータ取得
        String cardVariety = request.getParameter("cardVariety");
        String cardNumber = request.getParameter("cardNumber");
        String cardMonth = request.getParameter("cardMonth");
        String cardYear = request.getParameter("cardYear");
        String cardHolder = request.getParameter("cardHolder");
        String cardSecurity = request.getParameter("cardSecurity");
        String card4=null;
        String token=null;
        
        
        
        if(cardVariety==null
                || cardVariety.length()==0
                || cardNumber==null
                || cardNumber.length()==0
                || cardMonth==null
                || cardMonth.length()==0
                || cardYear==null
                || cardYear.length()==0
                || cardSecurity==null
                || cardSecurity.length()==0
                || cardHolder==null
                || cardHolder.length()==0){
            request.setAttribute("errorMsg", "入力項目に不備があります");
            dispatcher = request.getRequestDispatcher("/update_card.jsp");
            dispatcher.forward(request, response);  
            
        }else{
            
            //ユーザー情報を格納
            user.setCardVariety(cardVariety);
            user.setCardNumber(cardNumber);
            user.setExpirationDate(cardMonth+"/"+cardYear);
            user.setCardHolder(cardHolder);
            user.setCardSecurity(cardSecurity);

            //リクエストスコープに格納（ミスした時にもどす）
            request.setAttribute("cardSecurity", cardSecurity);
            request.setAttribute("cardHolder", cardHolder);
            
            
            CreditCard card = new CreditCard();
            isResult = card.isExists(cardNumber);
            if(isResult){
                card.add(cardNumber, cardHolder, user.getExpirationDate(), user.getCardSecurity(), user.getId());
                card4 = card.getCreditCardNumber();
                token = card.getToken();
                if(card4 != null
                        && card4.length() != 0
                        && token != null
                        && token.length() != 0){
                    res = true;
                    user.setCard4(card4);
                    user.setToken(token);
                }else{
                    request.setAttribute("errorMsg", "クレジットカード登録に失敗しました");
                }
            }else{
                request.setAttribute("errorMsg", "無効な番号です");
            }
        
            if(res){
                session.setAttribute("user", user);
                dispatcher = request.getRequestDispatcher("/update_card_check.jsp");
                dispatcher.forward(request, response);
            }else{
                dispatcher = request.getRequestDispatcher("/update_card.jsp");
                dispatcher.forward(request, response);
            }
        }
    }

}
