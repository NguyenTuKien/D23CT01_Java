package model;

public class BaiTap {
    private int maBT;
    private String tenBT;
    private String kieuBT;
    private int tongThoiGian;

    public BaiTap(int maBT, String tenBT, String kieuBT, int tongThoiGian) {
        this.maBT = maBT;
        this.tenBT = tenBT;
        this.kieuBT = kieuBT;
        this.tongThoiGian = tongThoiGian;
    }

    public int getMaBT() { return maBT; }
    public String getTenBT() { return tenBT; }
    public String getKieuBT() { return kieuBT; }
    public int getTongThoiGian() { return tongThoiGian; }

    @Override
    public String toString() {
        return maBT + " - " + tenBT;
    }
}