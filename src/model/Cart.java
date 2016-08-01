package model;
import java.io.Serializable;
/**
 * カート内のユーザーごとの商品情報を格納するBeans
 * @author Masateru Iwata
 *
 */
public class Cart implements Serializable {
    
    /**
     * カートID
     */
    private int cartId;
    
    /**
     * ユーザーID
     */
    private int id;
    
    /**
     * 商品ID
     */
    private int itemId;
    
    /**
     * 合計数量
     */
    private int sumOfNumberOfItems;
    
    /**
     * 数量
     */
    private int numberOfItems;
    /**
     * @return cartId
     */
    public int getCartId() {
        return cartId;
    }
    /**
     * @param cartId セットする cartId
     */
    public void setCartId(int cartId) {
        this.cartId = cartId;
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
     * @return itemId
     */
    public int getItemId() {
        return itemId;
    }
    /**
     * @param itemId セットする itemId
     */
    public void setItemId(int itemId) {
        this.itemId = itemId;
    }
    /**
     * @return numberOfItems
     */
    public int getNumberOfItems() {
        return numberOfItems;
    }
    /**
     * @param numberOfItems セットする numberOfItems
     */
    public void setNumberOfItems(int numberOfItems) {
        this.numberOfItems = numberOfItems;
    }
    /**
     * @return sumOfNumberOfItems
     */
    public int getSumOfNumberOfItems() {
        return sumOfNumberOfItems;
    }
    /**
     * @param sumOfNumberOfItems セットする sumOfNumberOfItems
     */
    public void setSumOfNumberOfItems(int sumOfNumberOfItems) {
        this.sumOfNumberOfItems = sumOfNumberOfItems;
    }
    
    

}
