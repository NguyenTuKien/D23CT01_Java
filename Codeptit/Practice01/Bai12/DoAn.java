package Practice01.Bai12;

class DoAn implements Comparable<DoAn> {
    private String maSV;
    private String tenSV;
    private String tenGV;
    private String tenDoAn;
    private String sdt;

    public DoAn(String maSV, String tenSV, String tenGV, String tenDoAn, String sdt) {
        this.maSV = maSV;
        this.tenSV = tenSV;
        this.tenGV = tenGV;
        this.tenDoAn = tenDoAn;
        this.sdt = sdt;
    }

    @Override
    public int compareTo(DoAn other) {
        return this.maSV.compareTo(other.maSV);
    }

    @Override
    public String toString() {
        return maSV + " " + tenSV + " " + tenGV + " " + tenDoAn + " " + "0" + sdt;
    }
}
