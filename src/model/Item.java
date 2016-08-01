    package model;
import java.io.Serializable;

public class Item implements Serializable{
    
    /**
     * 商品ID
     */
    private int itemId;
    
    /**
     * 商品名
     */
    private String itemName;
    
    /**
     * 値段
     */
    private int price;
    
    /**
     * 商品説明
     */
    private String explanation;
    
    /**
     * 画像パス
     */
    private String imgPath;
    
    /**
     * 数量
     */
    private int numberOfItems;
    
    /**
     * 合計金額 (商品ごと)
     */
    private int sumOfPrice;
    
    
    /**
     * コンストラクタ
     */
    public Item(){
        
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
     * @return itemName
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * @param itemName セットする itemName
     */
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    /**
     * @return price
     */
    public int getPrice() {
        return price;
    }

    /**
     * @param price セットする price
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * @return explanation
     */
    public String getExplanation() {
        return explanation;
    }

    /**
     * @param explanation セットする explanation
     */
    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    /**
     * @return imgPath
     */
    public String getImgPath() {
        return imgPath;
    }

    /**
     * @param imgPath セットする imgPath
     */
    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
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
     * @return sumOfPrice
     */
    public int getSumOfPrice() {
        return sumOfPrice;
    }


    /**
     * @param sumOfPrice セットする sumOfPrice
     */
    public void setSumOfPrice(int sumOfPrice) {
        this.sumOfPrice = sumOfPrice;
    }
    
    

}
