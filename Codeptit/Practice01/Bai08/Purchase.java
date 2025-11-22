package src.core.ThucHanh2.BaiG;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

class Purchase implements Comparable<Purchase> {
    String customerCode, customerName, address, productCode;
    int quantity, total;
    LocalDate buyDate, expireDate;
    static final DateTimeFormatter F = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    Purchase(int id, String customerName, String address,
             String productCode, int quantity, String buyDateStr,
             Map<String, SanPham> products) {
        this.customerCode = String.format("KH%02d", id);
        this.customerName = customerName.trim();
        this.address = address.trim();
        this.productCode = productCode.trim();
        this.quantity = quantity;

        this.buyDate = LocalDate.parse(buyDateStr.trim(), F);
        SanPham p = products.get(this.productCode);
        this.total = p.getPrice() * quantity;
        this.expireDate = this.buyDate.plusMonths(p.getWarrantyMonths());
    }

    @Override
    public int compareTo(Purchase o) {
        int c = this.expireDate.compareTo(o.expireDate);
        if (c == 0) c = this.customerCode.compareTo(o.customerCode);
        return c;
    }

    @Override
    public String toString() {
        return customerCode + " " + customerName + " " + address + " " +
                productCode + " " + total + " " + expireDate.format(F);
    }
}
