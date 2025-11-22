package Practice01.Bai11;

public class CongNhan implements Comparable<CongNhan> {
    private String ma;
    private String ten;
    private String vao;
    private String ra;
    private int gioLam;
    private String trangThai;

    public CongNhan(String ma, String ten, String vao, String ra) {
        this.ma = ma;
        this.ten = ten;
        this.vao = vao;
        this.ra = ra;
        this.gioLam = tinhGioLam();
        this.trangThai = (gioLam >= 8 * 60 ? "DU" : "THIEU");
    }

    private int tinhGioLam() {
        String[] in = vao.split(":");
        String[] out = ra.split(":");
        int h1 = Integer.parseInt(in[0]);
        int m1 = Integer.parseInt(in[1]);
        int h2 = Integer.parseInt(out[0]);
        int m2 = Integer.parseInt(out[1]);

        int minutesIn = h1 * 60 + m1;
        int minutesOut = h2 * 60 + m2;

        return (minutesOut - minutesIn) - 60;
    }

    public int getGioLam() {
        return gioLam;
    }

    public String getMa() {
        return ma;
    }

    @Override
    public int compareTo(CongNhan other) {
        if (this.gioLam != other.gioLam) {
            return other.gioLam - this.gioLam;
        }
        return this.ma.compareTo(other.ma);
    }

    @Override
    public String toString() {
        int h = gioLam / 60;
        int m = gioLam % 60;
        return ma + " " + ten + " " + h + " gio " + m + " phut " + " " + trangThai;
    }
}

