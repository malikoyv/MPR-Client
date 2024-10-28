package pjwstk.edu.pl.mpr.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Cat {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private int age;
    private long identificator = 0;

    protected Cat() {}

    public String getName(){
        return name;
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

    public Cat(String name, int age){
        this.name = name;
        this.age = age;
    }
}
