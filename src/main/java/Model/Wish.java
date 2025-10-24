package Model;

public class Wish {
    private int wish_id;
    private String wish_name;
    private double wish_price;
    private String wish_link;

    public Wish(int wish_id, String wish_name, double wish_price, String wish_link) {
        this.wish_id = wish_id;
        this.wish_name = wish_name;
        this.wish_price = wish_price;
        this.wish_link = wish_link;
    }

    public int getWish_id() {
        return wish_id;
    }

    public void setWish_id(int wish_id) {
        this.wish_id = wish_id;
    }

    public String getWish_name() {
        return wish_name;
    }

    public void setWish_name(String wish_name) {
        this.wish_name = wish_name;
    }

    public double getWish_price() {
        return wish_price;
    }

    public void setWish_price(double wish_price) {
        this.wish_price = wish_price;
    }

    public String getWish_link() {
        return wish_link;
    }

    public void setWish_link(String wish_link) {
        this.wish_link = wish_link;
    }
}
