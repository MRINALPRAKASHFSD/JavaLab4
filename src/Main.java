import java.util.List;
import java.util.Scanner;
import model.Student;
import service.FileUtil;
import service.StudentManager;

public class Main {
    public static void main(String[] args) {
        List<Student> loaded = FileUtil.loadStudents();
        StudentManager manager = new StudentManager(loaded);

        System.out.println("Loaded students from file:");
        manager.viewAllStudents();
        FileUtil.printFileInfo();
        FileUtil.showRandomRecord();

        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.println("===== Capstone Student Menu =====");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search by Name");
            System.out.println("4. Delete by Name");
            System.out.println("5. Sort by Marks");
            System.out.println("6. Save and Exit");
            System.out.print("Enter choice: ");
            String line = sc.nextLine();
            if (line.isEmpty()) {
                continue;
            }
            choice = Integer.parseInt(line);

            switch (choice) {
                case 1:
                    System.out.print("Enter Roll No: ");
                    int roll = Integer.parseInt(sc.nextLine());
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Email: ");
                    String email = sc.nextLine();
                    System.out.print("Enter Course: ");
                    String course = sc.nextLine();
                    System.out.print("Enter Marks: ");
                    double marks = Double.parseDouble(sc.nextLine());
                    Student s = new Student(roll, name, email, course, marks);
                    manager.addStudent(s);
                    break;
                case 2:
                    manager.viewAllStudents();
                    break;
                case 3:
                    System.out.print("Enter name to search: ");
                    String searchName = sc.nextLine();
                    Student found = manager.searchByName(searchName);
                    if (found != null) {
                        System.out.println("Student found:");
                        found.display();
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;
                case 4:
                    System.out.print("Enter name to delete: ");
                    String deleteName = sc.nextLine();
                    boolean deleted = manager.deleteByName(deleteName);
                    if (deleted) {
                        System.out.println("Student deleted.");
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;
                case 5:
                    manager.sortByMarks();
                    System.out.println("Sorted Student List by Marks:");
                    manager.viewAllStudents();
                    break;
                case 6:
                    FileUtil.saveStudents(manager.getStudents());
                    System.out.println("Records saved. Exiting.");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 6);

        sc.close();
    }
}
