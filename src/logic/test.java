package logic;
import model.User;
import dao.UserDAO;

public class test {
    
    public static void main(String[] args){
        User user = new User();
        user.setCellphone("0123456789");
        user.setPassword("00000000");
        user.setEmail("stu@gmail.com");
        boolean res = execute(user);
        System.out.println(res);
    }
    public static boolean execute(User user){
        boolean res = false;
        UserDAO dao = new UserDAO();

        return res;
    }
}
