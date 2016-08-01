    package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Cart;
import model.Item;
import model.User;
import util.MysqlConnector;

/**
 * カートテーブルを更新するクラス
 * @author Masateru Iwata
 * @version 1.0
 */
public class CartDAO {

    /**
     * カート内に同じ商品IDがあるか検索するメソッド
     * @param cart
     */
    public boolean searchItemId(Cart cart,User user){
        boolean res = false;
        Connection connection = MysqlConnector.getConnection("items");
        try{
            String sql = "select item_id from cart where item_id = ? and id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, cart.getItemId());
            ps.setInt(2, user.getId());
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                res = true;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return res;
    }
    
    /**
     * カート内を更新するクラス
     * @param cart
     */
    public void update(Cart cart){
        Connection connection = MysqlConnector.getConnection("items");
        try{
            String sql ="update cart set number_of_items =?  where item_id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, cart.getNumberOfItems());
            ps.setInt(2, cart.getItemId());
            ps.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            try{
                connection.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
    }
    
    /**
     * あるユーザーのカート内情報を追加するメソッド
     * @param cart
     */
    public void insert(Cart cart){
        Connection connection = MysqlConnector.getConnection("items");
        try{
            String sql = "insert into cart(id,item_id,number_of_items)value(?,?,?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, cart.getId());
            ps.setInt(2, cart.getItemId());
            ps.setInt(3, cart.getNumberOfItems());
            ps.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            try{
                connection.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
    }

    /**
     * カート内の合計数量を返すメソッド
     * @param cart
     * @return numberOfItems
     */
    public Cart searchItems(Cart cart){
        int sumOfNumberOfItems = 0;
        Connection connection = MysqlConnector.getConnection("items");
        try{
            String sql = "select number_of_items from cart where id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, cart.getId());
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                sumOfNumberOfItems += rs.getInt("number_of_items");
                cart.setSumOfNumberOfItems(sumOfNumberOfItems);
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
        return cart;
    }




    /**
     * カート内の情報を取得するメソッド
     * @param user
     * @return　itemList
     */
    public ArrayList<Item> searchCartFee(User user){
        Connection connection = MysqlConnector.getConnection("items");
        ArrayList<Item> itemList = new ArrayList<>();
        try{
            String sql = "select img_path,item_name,price*number_of_items,number_of_items from cart,item where id = ? and item.item_id = cart.item_id";
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
