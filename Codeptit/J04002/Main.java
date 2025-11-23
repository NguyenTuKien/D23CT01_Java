package J04002;

import java.util.Scanner;

public class Main {

    static Scanner cin = new Scanner(System.in);

    public static void main(String[] args){
        double height = cin.nextDouble(), width = cin.nextDouble();
        String color = cin.next();
        if(height != (int)height || width != (int)width || height < 1 || width < 1) System.out.println("INVALID");
        else{
            Rectange rect = new Rectange((int)width, (int)height, color);
            System.out.println(rect.findPerimeter() + " " + rect.findArea() + " " + rect.getColor());
        }
    }

}