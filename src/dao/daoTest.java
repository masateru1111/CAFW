package dao;
import model.Item;
import model.User;
import util.MysqlConnector;
import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import model.Cart;
import com.internousdev.util.CreditCard;


/**
 * openconnectデータベースのuserテーブルにアクセスするクラス
 * @author Masateru Iwata
 *
 */
public class daoTest {
    
    public static void main(String[] args){

        User user = new User();
        user.setId(61);
        ArrayList<Item> itemList = new ArrayList<>();
        itemList = searchCartFee(user);
        for(Item item:itemList){
            System.out.println(item.getItemName());
        }
//        Cart cart = new Cart();
//        cart.setId(20);
//        cart.setItemId(10);
//        cart.setNumberOfItems(5);
//        
//        int numberOfItems = 0;
//        numberOfItems = search(cart);
//        System.out.println(numberOfItems);
        
//        ArrayList<Item> itemList = new ArrayList<>();
//        itemList = findAll();
//        Iterator<Item> it = itemList.iterator();
//        while(it.hasNext()){
//            Item item = it.next();
//            System.out.println(item.getItemName());
//        }
    }
    public static boolean search(User user){
        boolean res = false;
        Connection connection = MysqlConnector.getConnection("openconnect");
        try{
            String sql = "select cell_number,password,email from user where cell_number=? and password=? and email=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, user.getCellphone());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getEmail());
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                res = true;
            }            
        }catch(SQLException e){
            e.printStackTrace();
        }
        return res;
    }
    public static boolean insert(User user){
        boolean res = false;
        Connection connection = MysqlConnector.getConnection("openconnect");
        try{
            connection.setAutoCommit(false);
            String sql = "insert into user(user_name,user_kana,password,"
                    + "email,cell_number,postal_code,address,registration_date)value(?,?,?,?,?,?,?,now())";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, user.getUserName());
            ps.setString(2, user.getUserKana());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getEmail());
            ps.setString(5, user.getCellphone());
            ps.setString(6, user.getPostalCode());
            ps.setString(7, user.getAddress());
            int count = ps.executeUpdate();
            if(count != 0){
                sql="select id from user where cell_number=? and email=?";
                ps = connection.prepareStatement(sql);
                ps.setString(1, user.getCellphone());
                ps.setString(2, user.getEmail());
                ResultSet rs = ps.executeQuery();
                if(rs.next()){
                    user.setId(rs.getInt("id"));
                    res=true;
                }
            }
            connection.commit();
        }catch(SQLException e){
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return res;
    }
    public static int update(User user){
        int count = 0;
        Connection connection = MysqlConnector.getConnection("openconnect");
        try{
            String sql = "update user set password=?,email=?,cell_number=?,postal_code=?,address=?,updated_date=now() where id=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, user.getPassword());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getCellphone());
            ps.setString(4, user.getPostalCode());
            ps.setString(5, user.getAddress());
            ps.setInt(6, user.getId());
            count = ps.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            try{
                connection.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return count;
    }

        public static ArrayList<Item> findAll() {
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
        
            /**
             * あるユーザーのカート内情報を更新し、合計数量を返すメソッド
             * @param cart
             * @return numberOfItems
             */
            public static int search(Cart cart){
                
                int numberOfItems = 0;
                Connection connection = MysqlConnector.getConnection("items");
                try{
                    connection.setAutoCommit(false);
                    String sql = "insert into cart(id,item_id,number_of_items)value(?,?,?)";
                    PreparedStatement ps = connection.prepareStatement(sql);
                    ps.setInt(1, cart.getId());
                    ps.setInt(2, cart.getItemId());
                    ps.setInt(3, cart.getNumberOfItems());
                    int count = ps.executeUpdate();
                    if(count != 0){
                        sql = "select number_of_items from cart where id = ?";
                        ps = connection.prepareStatement(sql);
                        ps.setInt(1, cart.getId());
                        ResultSet rs = ps.executeQuery();
                        while(rs.next()){
                            numberOfItems += rs.getInt("number_of_items");
                        }         
                    }
                    connection.commit();
                }catch(SQLException e){
                    try {
                        connection.rollback();
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                }finally{
                    try{
                        connection.close();
                    }catch(SQLException e){
                        e.printStackTrace();
                    }
                }
                return numberOfItems;
            }
        
            /**
             * カート内の情報を取得するメソッド
             * @param user
             * @return　itemList
             */
            public static ArrayList<Item> searchCartFee(User user){
                Connection connection = MysqlConnector.getConnection("items");
                ArrayList<Item> itemList = new ArrayList<>();
                try{
                    String sql = "select img_path,item_name,price*number_of_items"
                            + ",number_of_items from cart,item where id = ? and item.item_id = cart.item_id";
                    PreparedStatement ps = connection.prepareStatement(sql);
                    ps.setInt(1, user.getId());
                    ResultSet rs = ps.executeQuery();
                    while(rs.next()){
                        Item item = new Item();
                        item.setImgPath(rs.getString("img_path"));
                        item.setSumOfPrice(rs.getInt("price*number_of_items"));
                        item.setNumberOfItems(rs.getInt("number_of_items"));
                        item.setItemName(rs.getString("item_name"));
                        itemList.add(item);
                    }
                }catch(SQLException e){
                    e.printStackTrace();
                }finally{
                    try{
                        connection.close();
                    }catch(SQLException e){
                        e.printStackTrace();
                    }
                }
                return itemList;
            }
    

}
