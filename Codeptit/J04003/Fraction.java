package J04003;

public class Fraction {
    private long numerator, denominator;

    public Fraction(long numerator, long denominator){
        long gcd = getGcd(numerator, denominator);
        this.numerator = numerator / gcd;
        this.denominator = denominator / gcd;
    }

    public long getGcd(long a, long b){
        if(b == 0) return a;
        return getGcd(b, a % b);
    }

    @Override
    public String toString(){
        return numerator + "/" + denominator;
    }
}
