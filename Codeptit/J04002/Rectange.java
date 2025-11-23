package J04002;

public class Rectange {

    private int width, height;

    private String color;

    public Rectange(){}

    public Rectange(int width, int height, String color){
        this.width = width;
        this.height = height;
        this.color = Character.toUpperCase(color.charAt(0)) + color.substring(1).toLowerCase();
    }

    public double getWidth(){
        return width;
    }

    public double getHeight(){
        return height;
    }

    public String getColor(){
        return color;
    }

    public int findArea(){
        return width * height;
    }

    public int findPerimeter(){
        return (width + height) * 2;
    }

}