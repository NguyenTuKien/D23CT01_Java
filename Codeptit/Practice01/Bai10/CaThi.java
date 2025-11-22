package Practice01.Bai10;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

class CaThi {
    String maCa;
    String ngayThi;
    String gioThi;
    String phongThi;

    LocalDate ngayThiDate;
    LocalTime gioThiTime;

    static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    static DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

    public CaThi(String maCa, String ngayThi, String gioThi, String phongThi) {
        this.maCa = maCa;
        this.ngayThi = ngayThi;
        this.gioThi = gioThi;
        this.phongThi = phongThi;

        this.ngayThiDate = LocalDate.parse(ngayThi, dateFormatter);
        this.gioThiTime = LocalTime.parse(gioThi, timeFormatter);
    }
}
