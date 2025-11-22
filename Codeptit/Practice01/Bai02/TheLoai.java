package Practice01.Bai02;

public class TheLoai {
    private String maTL, tenTL;

    public TheLoai(int id, String ten) {
        this.tenTL = ten;
        this.maTL = String.format("TL%03d", id);
    }
    public String getMa(){
        return maTL;
    }
    public String getTenTL(){
        return tenTL;
    }
}
