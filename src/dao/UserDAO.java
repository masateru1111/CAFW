package dao;
import model.User;
import util.MysqlConnector;
import java.sql.*;
import com.internousdev.util.CreditCard;
import com.sun.org.apache.bcel.internal.generic.Select;

/**
 * openconnectデータベースのuserテーブルにアクセスするクラス
 * @author Masateru Iwata
 *
 */
public class UserDAO {
    
    private int id;
    boolean res;
    
    /**
     * 電話番号が登録されているか確認するメソッド
     * @param Masateru Iwata
     * @return res 登録されているならtrueを返す
     */
    public boolean searchCellphone(User user){
        res = false;
        Connection connection = MysqlConnector.getConnection("openconnect");
        try{
            String sql = "select cell_number from user where cell_number=? ";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, user.getCellphone());
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
     * メールアドレスが登録されているか確認するメソッド
     * @param Masateru Iwata
     * @return res 登録されているならtrueを返す
     */
    public boolean searchEmail(User user){
        res = false;
        Connection connection = MysqlConnector.getConnection("openconnect");
        try{
            String sql = "select email from user where email=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, user.getEmail());
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
     * パスワードが登録されているか確認するメソッド
     * @param Masateru Iwata
     * @return res 登録されているならtrueを返す
     */
    public boolean searchPass(User user){
        res = false;
        Connection connection = MysqlConnector.getConnection("openconnect");
        try{
            String sql = "select password from user where password=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, user.getPassword());
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
     * ユーザー情報をopenconnectデータベースに格納するメソッド
     * @param user　ユーザーBeans
     * @return res　格納してidまで取得できたらtrueを返す
     */
    public boolean insert(User user){
        res = false;
        Connection connection = MysqlConnector.getConnection("openconnect");
        try{
            //トランザクション準備
            connection.setAutoCommit(false);
            
            //userビーンズの情報と、登録日付をuserテーブルに格納する
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
            
            //登録できたら、登録した電話番号とemailからユーザーidをテーブルから取得する
            if(count != 0){
                sql="select id from user where cell_number=? and email=?";
                ps = connection.prepareStatement(sql);
                ps.setString(1, user.getCellphone());
                ps.setString(2, user.getEmail());
                ResultSet rs = ps.executeQuery();
                
                //取得できたら、フィールド内のidに格納する。　resにtrueを格納する
                if(rs.next()){
                    id = rs.getInt("id");
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

    /**
     * userテーブルに名前とパスワード情報があるか照合するメソッド
     * @param user
     * @return　res 情報が登録されていればtrueを返す
     */
    public boolean isLogin(User user){
        res = false;
        Connection connection = MysqlConnector.getConnection("openconnect");
        try{
            String sql = "select id from user where user_name = ? and password = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, user.getUserName());
            ps.setString(2, user.getPassword());
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                id = rs.getInt("id");
                res = true;
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
        return res;
    }

    /**
     * ユーザー情報を取得するメソッド
     * @param user
     * @return user
     */
    public User find(User user){
        Connection connection = MysqlConnector.getConnection("openconnect");
        try{
            String sql = "select user_name,user_kana,password,email,cell_number,postal_code,address from user where id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, user.getId());
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                user.setUserName(rs.getString("user_name"));
                user.setUserKana(rs.getString("user_kana"));
                user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));
                user.setCellphone(rs.getString("cell_number"));
                user.setPostalCode(rs.getString("postal_code"));
                user.setAddress(rs.getString("address"));
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
        return user;
    }
    /**
     * userテーブルのユーザー情報を変更するメソッド
     * @param user
     * @return count 更新件数を返す
     */
    public int updateUser(User user){
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
    
    /**
     * クレジットカードを登録するメソッド
     * @param user
     * @return　res 登録できたらtrueを返す
     */
    public boolean updateCard(User user){
        res = false;
        Connection connection = MysqlConnector.getConnection("openconnect");
        try{
            String sql = "update user set credit_number=?,credit_token=? where id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, user.getCard4());
            ps.setString(2, user.getToken());
            ps.setInt(3, user.getId());
            int count = ps.executeUpdate();
            if(count != 0){
                res = true;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return res;
    }
    /**
     * @return id
     */
    public int getId() {
        return id;
    }
    /**
     * @param id セットする id
     */
    public void setId(int id) {
        this.id = id;
    }    
}
