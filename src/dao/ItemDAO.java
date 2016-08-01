package dao;
import java.sql.*;
import model.Item;
import util.MysqlConnector;
import java.util.ArrayList;
import java.util.List;


/**
 * itemsデータベースとの連携を行うクラス
 * @author Masateru Iwata
 * @version 1.0
 */
public class ItemDAO {

    /**
     * 商品リストを返すメソッド
     * @return itemList　商品リスト
     */
    public ArrayList<Item> findAll() {
        ArrayList<Item> itemList = new ArrayList<>();
        Connection connection = MysqlConnector.getConnection("items");
        try{
            String sql = "select * from item";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Item item = new Item();
                item.setItemId(rs.getInt("item_id"));
                item.setItemName(rs.getString("item_name"));
                item.setPrice(rs.getInt("price"));
                item.setExplanation(rs.getString("explanation"));
                item.setImgPath(rs.getString("img_path"));
                itemList.add(item);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return itemList;
    }
    
}
