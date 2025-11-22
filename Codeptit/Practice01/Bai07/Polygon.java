package Practice01.Bai07;

public class Polygon {
    private Point[] p;
    public Polygon(Point[] p){
        this.p = p;
    }
    public String getArea(){
        int n = p.length;
        long sum = 0;
        for(int i = 0;i < n;i++) {
            int j = (i + 1) % n;
            sum += (long) p[i].x * p[j].y - (long) p[j].x * p[i].y;
        }
        double area = Math.abs(sum / 2.0);
        return String.format("%.3f", area);
    }
}
