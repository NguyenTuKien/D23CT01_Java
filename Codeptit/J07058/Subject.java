package J07058;

public class Subject implements Comparable<Subject>{
    private String id, name, method;

    public Subject(String id, String name, String method) {
        this.id = id;
        this.name = name;
        this.method = method;
    }

    @Override
    public int compareTo(Subject o) {
        return this.id.compareTo(o.id);
    }

    @Override
    public String toString(){
        return id + " " + name + " " + method;
    }
}
