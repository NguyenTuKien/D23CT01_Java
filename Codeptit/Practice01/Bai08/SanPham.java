package src.core.ThucHanh2.BaiG;

public class SanPham {
    private String code, name;
    private int price, warrantyMonths;

    public SanPham (String code, String name, int price, int warrantyMonths) {
        this.code = code;
        this.name = name;
        this.price = price;
        this.warrantyMonths = warrantyMonths;
    }
    public int getPrice() {
        return price;
    }
    public int getWarrantyMonths() {
        return warrantyMonths;
    }
}
