package logic;
import model.User;
import dao.UserDAO;

public class UpdateLogic {
    
    private String errorMsg;
    
    /**
     * ユーザー情報を変更するメソッド
     * @param user
     * @return　res
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
    /**
     * UpdateUserServletのdoGetメソッドで使用
     * ユーザー情報をopenconnectデータベースから取得するメソッド
     * @param user
     * @return　user　ユーザー情報を返す
     */
    public User find(User user){
        UserDAO dao = new UserDAO();
        user = dao.find(user);
        return user;      
    }

    /**
     * 電話番号が登録できるか確認するメソッド
     * @param user
     * @return
     */
    public boolean searchCellphone(User user){
        boolean res = false;
        UserDAO dao = new UserDAO();
        res = dao.searchCellphone(user);
        if(!res){
            errorMsg = "すでに電話番号は使用されています";
        }
        return res;
    }
    /**
     * パスワードが登録できるか確認するメソッド
     * @param user
     * @return
     */
    public boolean searchPass(User user){
        boolean res = false;
        UserDAO dao = new UserDAO();
        res = dao.searchPass(user);
        if(res){
            errorMsg = "そのパスワードはすでに使用されています";
        }
        return res;
    }
    /**
     * メールアドレスが登録できるか確認するメソッド
     * @param user
     * @return
     */
    public boolean searchEmail(User user){
        boolean res = false;
        UserDAO dao = new UserDAO();
        res = dao.searchEmail(user);
        if(res){
            errorMsg = "そのメールアドレスはすでに使用されています";
        }
        return res;
    }
    /**
     * @return errorMsg
     */
    public String getErrorMsg() {
        return errorMsg;
    }
    /**
     * @param errorMsg セットする errorMsg
     */
    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
    
}
