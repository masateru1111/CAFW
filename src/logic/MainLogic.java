package logic;
import java.util.ArrayList;
import model.Item;
import dao.ItemDAO;

public class MainLogic {

    /**
     * 商品一覧をメイン画面に返すメソッド
     * @return itemList
     */
    public ArrayList<Item> execute(){
        ArrayList<Item> itemList = new ArrayList<>();
        ItemDAO dao = new ItemDAO();
        itemList = dao.findAll();
        return itemList;
    }
}
