package J04004;

public class Fraction {
    private long numerator, denominator;

    public Fraction(long numerator, long denominator){
        this.numerator = numerator;
        this.denominator = denominator;
        this.simplify();
    }

    public void simplify(){
        long gcd = MyMath.gcd(numerator, denominator);
        numerator /= gcd;
        denominator /= gcd;
    }

    public Fraction add(Fraction other){
        long newNumerator = numerator * other.denominator + other.numerator * denominator;
        long newDenominator = denominator * other.denominator;
        return new Fraction(newNumerator, newDenominator);
    }

    public String toString(){
        return numerator + "/" + denominator;
    }
}
