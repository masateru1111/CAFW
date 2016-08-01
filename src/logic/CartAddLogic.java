package logic;
import model.Cart;
import model.User;
import dao.CartDAO;

public class CartAddLogic {

    /**
     * カート内の商品を更新し、合計数量を返すメソッド
     * @param cart
     * @return numberOfItems　数量
     */
    public Cart execute(Cart cart, User user){
        CartDAO dao = new CartDAO();
        boolean res = false;
        
        //カート内にある商品を選択しているか確認
        res = dao.searchItemId(cart,user);

        if(res){
            //選択しているなら更新
            dao.update(cart);
        }else{
            //新規なら挿入
            dao.insert(cart);
        }
        cart = dao.searchItems(cart);
        return cart;
    }

}
