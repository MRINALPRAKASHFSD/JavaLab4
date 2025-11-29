package service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import model.Student;

public class StudentManager {
    private List<Student> students = new ArrayList<>();

    public StudentManager(List<Student> initial) {
        if (initial != null) {
            students.addAll(initial);
        }
    }

    public void addStudent(Student s) {
        students.add(s);
    }

    public List<Student> getStudents() {
        return students;
    }

    public void viewAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No student records available.");
            return;
        }
        Iterator<Student> it = students.iterator();
        while (it.hasNext()) {
            Student s = it.next();
            s.display();
            System.out.println();
        }
    }

    public Student searchByName(String name) {
        for (Student s : students) {
            if (s.getName().equalsIgnoreCase(name)) {
                return s;
            }
        }
        return null;
    }

    public boolean deleteByName(String name) {
        Iterator<Student> it = students.iterator();
        while (it.hasNext()) {
            Student s = it.next();
            if (s.getName().equalsIgnoreCase(name)) {
                it.remove();
                return true;
            }
        }
        return false;
    }

    public void sortByMarks() {
        Collections.sort(students, new Comparator<Student>() {
            @Override
            public int compare(Student a, Student b) {
                return Double.compare(b.getMarks(), a.getMarks());
            }
        });
    }

    public void sortByName() {
        Collections.sort(students);
    }
}
