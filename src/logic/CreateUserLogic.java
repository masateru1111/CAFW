package logic;
import model.User;

import org.omg.CORBA.PRIVATE_MEMBER;

import dao.UserDAO;

public class CreateUserLogic {

    private String errorMsg;

    /**
     * パスワード、アドレス、電話番号が有効か確認するメソッド
     * @param user
     * @return　res
     */
    public boolean execute(User user){

        boolean res = false;
        UserDAO dao = new UserDAO();
        res = dao.searchPass(user);
        if(res){
            errorMsg="同じパスワードが使用されています";
            return res;
        }else{
            res = dao.searchEmail(user);
            if(res){
                errorMsg="同じメールアドレスが使用されています";
                return res;
            }else{
                res=dao.searchCellphone(user);
                if(res){
                    errorMsg="同じ電話番号が使用されています";
                    return res;
                }
            }
        }
        return res;
    }
    public String getErrorMsg(){
        return errorMsg;
    }
}
