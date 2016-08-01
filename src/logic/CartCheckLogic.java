package logic;
import model.User;
import model.Item;
import java.util.ArrayList;
import dao.CartDAO;

public class CartCheckLogic {
    
    /**
     * 総額
     */
    int sumOfPrice = 0; 

    /**
     * カート内の情報を取得するメソッド
     * @param user
     * @return　itemList　商品リスト
     */
    public ArrayList<Item> execute(User user){
        ArrayList<Item> itemList = new ArrayList<>();
        CartDAO dao = new CartDAO();
        itemList = dao.searchCartFee(user);
        for(Item item : itemList){
            sumOfPrice += item.getSumOfPrice();
        }
        return itemList;
    }

    /**
     * @return sumOfPrice
     */
    public int getSumOfPrice() {
        return sumOfPrice;
    }

    /**
     * @param sumOfPrice セットする sumOfPrice
     */
    public void setSumOfPrice(int sumOfPrice) {
        this.sumOfPrice = sumOfPrice;
    }
    
}
