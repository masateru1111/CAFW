package logic;
import model.User;
import dao.UserDAO;

public class LoginLogic {
    
    private int id = 0;
    /**
     * ログインできるか確かめるメソッド
     * @param user
     * @return res　ログインできればtrueを返す
     */
    public boolean execute(User user){
        boolean res = false;
        UserDAO dao = new UserDAO();
        res = dao.isLogin(user);
        if(res){
            id = dao.getId();
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
