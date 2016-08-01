package logic;
import model.Cart;
import model.User;
import dao.CartDAO;

public class CartLogic {

    /**
     * カート内の商品を更新し、合計数量を返すメソッド
     * @param cart
     * @return numberOfItems　数量
     */
    public Cart execute(Cart cart, User user){
        CartDAO dao = new CartDAO();
        boolean res = false;
        cart = dao.searchItems(cart);
        return cart;
    }

}
