package J04007;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Staff {
    private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    private final int id = 1;
    private String name;
    private String sex;
    private Date dob;
    private String address;
    private String taxId;
    private Date hireDate;

    Staff(String name, String sex, String dob, String address, String taxId, String hireDate) throws Exception{
        this.name = name;
        this.sex = sex;
        this.dob = sdf.parse(dob);
        this.address = address;
        this.taxId = taxId;
        this.hireDate = sdf.parse(hireDate);
    }

    @Override
    public String toString(){
        return String.format("%05d", this.id) + " " + this.name + " " + this.sex + " " + sdf.format(this.dob) + " " + this.address + " " + this.taxId + " " + sdf.format(this.hireDate);
    }
}
