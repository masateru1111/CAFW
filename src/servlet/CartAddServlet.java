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
import model.Cart;
import logic.CartAddLogic;

/**
 * Servlet implementation class CartAddServlet
 */
@WebServlet("/CartAddServlet")
public class CartAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartAddServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int itemId = 0;
        int numberOfItems = 0;
        
        //セッションからユーザー情報取得
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        
        //パラメータ取得
        String itemIdString = request.getParameter("itemId");
        String numberOfItemsString = request.getParameter("numberOfItems");
    
        //数字変換
        try{
            itemId = Integer.parseInt(itemIdString);
            numberOfItems = Integer.parseInt(numberOfItemsString);
        }catch(Exception e){
            e.printStackTrace();
        }
        
        //カートBeans内にセット
        Cart cart = new Cart();
        cart.setId(user.getId());
        cart.setItemId(itemId);
        cart.setNumberOfItems(numberOfItems);
        
        //カート内更新
        CartAddLogic logic = new CartAddLogic();
        cart = logic.execute(cart,user);
        
        //セッションスコープに数量格納
        session.setAttribute("cart", cart);
        
        ServletContext sc = request.getServletContext();
        RequestDispatcher dispatcher = sc.getRequestDispatcher("/MainServlet");
        dispatcher.forward(request, response);
        
        
	}

}
