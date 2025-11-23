package J04008;

public class Triangle {
    private Point a, b, c;

    public Triangle(Point a, Point b, Point c){
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public boolean isTriangle(){
        return a.distance(b) + a.distance(c) > b.distance(c) &&
                b.distance(c) + a.distance(b) > a.distance(c) &&
                a.distance(b) + b.distance(c) > c.distance(a);
    }

    public double getPerimeter(){
        return a.distance(b) + b.distance(c) + c.distance(a);
    }
}
