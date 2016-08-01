package logic;
import model.User;
import dao.UserDAO;

public class UpdateCardLogic {
    
    /**
     * クレジットカードを更新するメソッド
     * @param user
     * @return res 
     */
    public boolean execute(User user){
        boolean res = false;
        UserDAO dao = new UserDAO();
        res = dao.updateCard(user);
        return res;
    }

}
