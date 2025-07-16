package org.sysco.models;

public class Data {
    private String shop_page;
    private String product_index;
    private String campaign;


    public Data() {
    }

    public Data(String shop_page, String product_index, String campaign) {
        this.shop_page = shop_page;
        this.product_index = product_index;
        this.campaign = campaign;
    }

    public String getShop_page() {
        return shop_page;
    }

    public void setShop_page(String shop_page) {
        this.shop_page = shop_page;
    }

    public String getProduct_index() {
        return product_index;
    }

    public void setProduct_index(String product_index) {
        this.product_index = product_index;
    }

    public String getCampaign() {
        return campaign;
    }

    public void setCampaign(String campaign) {
        this.campaign = campaign;
    }

    @Override
    public String toString() {
        return "Data{" +
                "shop_page='" + shop_page + '\'' +
                ", product_index='" + product_index + '\'' +
                ", campaign='" + campaign + '\'' +
                '}';
    }
}
