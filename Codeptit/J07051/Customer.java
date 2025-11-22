package J07051;
import java.util.Date;

public class Customer implements Comparable<Customer> {
    private String id, name, roomId;
    private Date arrival, depart;
    private int serviceFee;

    public Customer(int index, String name, String roomId, Date arrival, Date depart, int serviceFee) {
        this.id = String.format("KH%02d", index);
        this.name = normalize(name);
        this.roomId = roomId;
        this.arrival = arrival;
        this.depart = depart;
        this.serviceFee = serviceFee;
    }

    private String normalize(String s) {
        s = s.trim().toLowerCase();
        String[] a = s.split("\\s+");
        StringBuilder sb = new StringBuilder();
        for (String x : a) {
            sb.append(Character.toUpperCase(x.charAt(0))).append(x.substring(1)).append(" ");
        }
        return sb.toString().trim();
    }

    private long getDays() {
        long diff = depart.getTime() - arrival.getTime();
        return diff / (1000L * 60 * 60 * 24) + 1;
    }

    private int getPricePerDay() {
        char c = roomId.charAt(0);
        if (c == '1') return 25;
        if (c == '2') return 34;
        if (c == '3') return 50;
        return 80;
    }

    public int total() {
        return (int) (getDays() * getPricePerDay() + serviceFee);
    }

    @Override
    public int compareTo(Customer o) {
        return o.total() - this.total();
    }

    @Override
    public String toString() {
        return String.format("%s %s %s %d %d",
                id, name, roomId, getDays(), total());
    }
}
