package logic;
import model.User;
import dao.UserDAO;

public class UpdateUserCompletionLogic {

    /**
     * ユーザー情報を変更するメソッド
     * @param user
     * @return res
     */
    public boolean execute(User user){
        boolean res = false;
        UserDAO dao = new UserDAO();
        int count = dao.updateUser(user);
        if(count != 0){
            res = true;
        }
        return res;
    }
}
