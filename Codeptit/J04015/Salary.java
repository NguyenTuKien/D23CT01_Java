package J04015;

public class Salary {
    private String id, name;
    long rank, bonus, total;

    Salary(String id, String name, long basic){
        this.id = id;
        this.name = name;
        this.rank = Long.parseLong(id.substring(2));
        this.bonus = getBonus(id.substring(0, 2));
        this.total = basic * rank + bonus;
    }

    public long getBonus(String id){
        if(id.equals("HT")) return 2000000;
        else if (id.equals("HP")) return 900000;
        else return 500000;
    }

    public String toString(){
        return id + " " + name + " " + rank + " " + bonus + " " + total;
    }
}
