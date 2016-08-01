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
import logic.CartCheckLogic;
import java.util.ArrayList;
import model.Item;

/**
 * Servlet implementation class CartCheckServlet
 */
@WebServlet("/CartCheckServlet")
public class CartCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartCheckServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	    
	    //セッションからユーザー情報取得
	    HttpSession session = request.getSession();
	    User user = (User) session.getAttribute("user");
	    
	    //カート内情報を取得
	    ArrayList<Item> itemList = new ArrayList<>();
	    CartCheckLogic logic = new CartCheckLogic();
	    itemList = logic.execute(user);
	    int sumOfPrice = logic.getSumOfPrice();

	    //リクエストスコープに総額を格納
	    request.setAttribute("sumOfPrice", sumOfPrice);
	    
	    //セッションにitemListを格納してフォワード
        session.setAttribute("itemList", itemList);
	    RequestDispatcher dispatcher = request.getRequestDispatcher("/cart_check.jsp");
	    dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
