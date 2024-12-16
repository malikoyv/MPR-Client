package pjwstk.edu.pl.mpr.models;

public class Cat {
    private Long id;
    private String name;
    private int age;
    private long identificator = 0;

    public Cat() {}

    public String getName(){
        return name;
    }

    public Long getId(){
        return id;
    }

    public void setName(String name){
        this.name = name;
    }

    public int getAge(){
        return age;
    }

    public void setAge(int age){
        this.age = age;
    }

    public long getIdentificator(){
        return identificator;
    }

    public void setIdentificator(){
        for (int i = 0; i < this.name.length(); i++) {
            this.identificator += this.name.charAt(i);
        }
        this.identificator += (char)this.age;
    }

    public Cat(Long id, String name, int age){
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public Cat(String name, int age){
        this.name = name;
        this.age = age;
    }
}
