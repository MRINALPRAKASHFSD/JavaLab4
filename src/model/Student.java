package model;

public class Student implements Comparable<Student> {
    private int rollNo;
    private String name;
    private String email;
    private String course;
    private double marks;

    public Student(int rollNo, String name, String email, String course, double marks) {
        this.rollNo = rollNo;
        this.name = name;
        this.email = email;
        this.course = course;
        this.marks = marks;
    }

    public int getRollNo() {
        return rollNo;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getCourse() {
        return course;
    }

    public double getMarks() {
        return marks;
    }

    public void setMarks(double marks) {
        this.marks = marks;
    }

    @Override
    public int compareTo(Student other) {
        return this.name.compareToIgnoreCase(other.name);
    }

    public void display() {
        System.out.println("Roll No: " + rollNo);
        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
        System.out.println("Course: " + course);
        System.out.println("Marks: " + marks);
    }

    public String toFileString() {
        return rollNo + "," + name + "," + email + "," + course + "," + marks;
    }

    public static Student fromFileString(String line) {
        String[] parts = line.split(",");
        if (parts.length != 5) {
            return null;
        }
        int roll = Integer.parseInt(parts[0].trim());
        String name = parts[1].trim();
        String email = parts[2].trim();
        String course = parts[3].trim();
        double marks = Double.parseDouble(parts[4].trim());
        return new Student(roll, name, email, course, marks);
    }
}
