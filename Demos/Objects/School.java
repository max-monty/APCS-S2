package Demos.Objects;

public class School {
    public static void main(String[] args) {
        Student defaultStudent = new Student();
        defaultStudent.setName("Jane Doe");
        defaultStudent.setGrade(12);

        Student customStudent = new Student("John Smith", 9);

        System.out.println("Student: " + customStudent.getName() + ", Grade:" + customStudent.getGrade());

        System.out.println(defaultStudent);
    }
    
}
