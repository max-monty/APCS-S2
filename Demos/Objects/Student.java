package Demos.Objects;

public class Student {

    private String name;
    private int grade;

    public Student() {
        name = "unknown";
        grade = 0;
    }

    public Student(String n, int g) {
        name = n;
        grade = g;
    }

    public void setName(String n) {
        name = n;
    }

    public void setGrade(int g) {
        grade = g;
    }

    public String getName() {
        return name;
    }

    public int getGrade() {
        return grade;
    }

    public String toString() {
        return "Student: " + name + ", Grade: " + grade;
    }
    
}
