package model;
import java.io.Serializable;

/**
 * ユーザー情報を格納するクラス
 * @author Masateru Iwata
 *
 */
public class User implements Serializable{
    
    private int id;
    private String userName;
    private String userKana;
    private String password;
    private String email;
    private String cellphone;
    private String postalCode;
    private String address;
    private String cardVariety;
    private String cardNumber;
    private String expirationDate;
    private String cardHolder;
    private String cardSecurity;
    private String card4;
    private String token;
    
    public User(){
        
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

    /**
     * @return userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName セットする userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return userKana
     */
    public String getUserKana() {
        return userKana;
    }

    /**
     * @param userKana セットする userKana
     */
    public void setUserKana(String userKana) {
        this.userKana = userKana;
    }

    /**
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password セットする password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email セットする email
     */
    public void setEmail(String email) {
        this.email = email;
    }


    
    /**
     * @return cellphone
     */
    public String getCellphone() {
        return cellphone;
    }

    /**
     * @param cellphone セットする cellphone
     */
    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    /**
     * @return postalCode
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * @param postalCode セットする postalCode
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /**
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address セットする address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return cardVariety
     */
    public String getCardVariety() {
        return cardVariety;
    }

    /**
     * @param cardVariety セットする cardVariety
     */
    public void setCardVariety(String cardVariety) {
        this.cardVariety = cardVariety;
    }

    /**
     * @return cardNumber
     */
    public String getCardNumber() {
        return cardNumber;
    }

    /**
     * @param cardNumber セットする cardNumber
     */
    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    /**
     * @return expirationDate
     */
    public String getExpirationDate() {
        return expirationDate;
    }

    /**
     * @param expirationDate セットする expirationDate
     */
    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    /**
     * @return cardHolder
     */
    public String getCardHolder() {
        return cardHolder;
    }

    /**
     * @param cardHolder セットする cardHolder
     */
    public void setCardHolder(String cardHolder) {
        this.cardHolder = cardHolder;
    }

    /**
     * @return cardSecurity
     */
    public String getCardSecurity() {
        return cardSecurity;
    }

    /**
     * @param cardSecurity セットする cardSecurity
     */
    public void setCardSecurity(String cardSecurity) {
        this.cardSecurity = cardSecurity;
    }

    /**
     * @return card4
     */
    public String getCard4() {
        return card4;
    }

    /**
     * @param card4 セットする card4
     */
    public void setCard4(String card4) {
        this.card4 = card4;
    }

    /**
     * @return token
     */
    public String getToken() {
        return token;
    }

    /**
     * @param token セットする token
     */
    public void setToken(String token) {
        this.token = token;
    }
    
    

}
