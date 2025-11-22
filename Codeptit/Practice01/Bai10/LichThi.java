package Practice01.Bai10;

import java.time.LocalDate;
import java.time.LocalTime;

class LichThi implements Comparable<LichThi> {
    private String maCa;
    private String ngayThi;
    private String gioThi;
    private String phongThi;
    private String tenMon;
    private String nhom;
    private int soSinhVien;

    private LocalDate ngayThiDate;
    private LocalTime gioThiTime;

    public LichThi(CaThi ca, String tenMon, String nhom, int soSinhVien) {
        this.maCa = ca.maCa;
        this.ngayThi = ca.ngayThi;
        this.gioThi = ca.gioThi;
        this.phongThi = ca.phongThi;
        this.ngayThiDate = ca.ngayThiDate;
        this.gioThiTime = ca.gioThiTime;
        this.tenMon = tenMon;
        this.nhom = nhom;
        this.soSinhVien = soSinhVien;
    }

    @Override
    public int compareTo(LichThi other) {
        int dateCmp = this.ngayThiDate.compareTo(other.ngayThiDate);
        if (dateCmp != 0) {
            return dateCmp;
        }

        int timeCmp = this.gioThiTime.compareTo(other.gioThiTime);
        if (timeCmp != 0) {
            return timeCmp;
        }

        return this.maCa.compareTo(other.maCa);
    }

    @Override
    public String toString() {
        return ngayThi + " " + gioThi + " " + phongThi + " " + tenMon + " " + nhom + " " + soSinhVien;
    }
}
