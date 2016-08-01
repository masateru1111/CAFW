package util;
import java.sql.*;

/**
 * mysqlへの接続情報を返すクラス
 * @author Masateru Iwata
 *
 */
public class MysqlConnector {

    
    public static Connection getConnection(String database){
        Connection connection = null;
        String driverName="com.mysql.jdbc.Driver";
        String user="root";
        String pass = "mysql";
        String url="jdbc:mysql://localhost/"+database;
        try{
            Class.forName(driverName);
            connection = DriverManager.getConnection(url,user,pass);        
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

}
