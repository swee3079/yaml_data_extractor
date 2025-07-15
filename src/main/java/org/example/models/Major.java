package org.example.models;

public class Major {
    private Catalog catalog;
    private Data data;


    public Major() {
    }

    public Major(Catalog catalog, Data data) {
        this.catalog = catalog;
        this.data = data;
    }


    public Catalog getCatalog() {
        return catalog;
    }

    public void setCatalog(Catalog catalog) {
        this.catalog = catalog;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Major{" +
                "catalog=" + catalog +
                ", data=" + data +
                '}';
    }
}
