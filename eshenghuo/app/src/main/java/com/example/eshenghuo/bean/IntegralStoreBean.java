package com.example.eshenghuo.bean;

/**
 * Created by Administrator on 2016/6/28.
 */
public class IntegralStoreBean extends BaseBean {
    private String  imgUrl;
    private String title;
    private String  integralNumber;
    private int purchaseNumber;
    private String soldNumber;


    public int getPurchaseNumber() {
        return purchaseNumber;
    }

    public void setPurchaseNumber(int purchaseNumber) {
        this.purchaseNumber = purchaseNumber;
    }


    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIntegralNumber() {
        return integralNumber;
    }

    public void setIntegralNumber(String integralNumber) {
        this.integralNumber = integralNumber;
    }

    public String getSoldNumber() {
        return soldNumber;
    }

    public void setSoldNumber(String soldNumber) {
        this.soldNumber = soldNumber;
    }

}
