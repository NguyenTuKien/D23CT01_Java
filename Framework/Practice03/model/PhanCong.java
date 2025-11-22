package model;

class PhanCong {
    private SinhVien sv;
    private BaiTap bt;
    private int soNgayThamGia;
    private String viTri;

    public PhanCong(SinhVien sv, BaiTap bt, int soNgayThamGia, String viTri) {
        this.sv = sv;
        this.bt = bt;
        this.soNgayThamGia = soNgayThamGia;
        this.viTri = viTri;
    }

    public SinhVien getSv() { return sv; }
    public BaiTap getBt() { return bt; }
    public int getSoNgayThamGia() { return soNgayThamGia; }
    public String getViTri() { return viTri; }
}