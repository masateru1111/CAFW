package logic;
import model.User;
import dao.UserDAO;

public class CreateUserCompletionLogic {
    
    private int id;
    /**
     * 新規登録処理を行うメソッド
     * @param user　ユーザー情報
     * @return res 新規登録ができればtrueを返す
     */
    public boolean execute(User user){
        boolean res = false;
        UserDAO dao = new UserDAO();
        res = dao.insert(user);
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
