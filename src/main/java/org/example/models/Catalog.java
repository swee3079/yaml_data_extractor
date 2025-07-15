package org.example.models;

public class Catalog {
    private String warehouse;
    private String name;
    private String db;
    private String shop_page_table;
    private String product_index_table;
    private String campaign_table;

    public Catalog() {
    }

    public Catalog(String warehouse, String name, String db, String shop_page_table, String product_index_table, String campaign_table) {
        this.warehouse = warehouse;
        this.name = name;
        this.db = db;
        this.shop_page_table = shop_page_table;
        this.product_index_table = product_index_table;
        this.campaign_table = campaign_table;
    }

    public String getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(String warehouse) {
        this.warehouse = warehouse;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDb() {
        return db;
    }

    public void setDb(String db) {
        this.db = db;
    }

    public String getShop_page_table() {
        return shop_page_table;
    }

    public void setShop_page_table(String shop_page_table) {
        this.shop_page_table = shop_page_table;
    }

    public String getProduct_index_table() {
        return product_index_table;
    }

    public void setProduct_index_table(String product_index_table) {
        this.product_index_table = product_index_table;
    }

    public String getCampaign_table() {
        return campaign_table;
    }

    public void setCampaign_table(String campaign_table) {
        this.campaign_table = campaign_table;
    }

    @Override
    public String toString() {
        return "Catalog{" +
                "warehouse='" + warehouse + '\'' +
                ", name='" + name + '\'' +
                ", db='" + db + '\'' +
                ", shop_page_table='" + shop_page_table + '\'' +
                ", product_index_table='" + product_index_table + '\'' +
                ", campaign_table='" + campaign_table + '\'' +
                '}';
    }
}
