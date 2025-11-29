package service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import model.Student;

public class FileUtil {
    private static final String FILE_NAME = "students.txt";

    public static List<Student> loadStudents() {
        List<Student> list = new ArrayList<>();
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            return list;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                Student s = Student.fromFileString(line);
                if (s != null) {
                    list.add(s);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return list;
    }

    public static void saveStudents(List<Student> students) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Student s : students) {
                bw.write(s.toFileString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        }
    }

    public static void printFileInfo() {
        File file = new File(FILE_NAME);
        System.out.println("File exists: " + file.exists());
        System.out.println("File name: " + file.getName());
        System.out.println("Absolute path: " + file.getAbsolutePath());
        System.out.println("Size (bytes): " + file.length());
        System.out.println("Last modified: " + file.lastModified());
    }

    public static void showRandomRecord() {
        File file = new File(FILE_NAME);
        if (!file.exists() || file.length() == 0) {
            return;
        }
        try (RandomAccessFile raf = new RandomAccessFile(file, "r")) {
            long pos = 0;
            if (file.length() > 100) {
                pos = file.length() / 2;
            }
            raf.seek(pos);
            raf.readLine();
            String line = raf.readLine();
            if (line == null) {
                raf.seek(0);
                line = raf.readLine();
            }
            if (line != null) {
                System.out.println("RandomAccessFile sample record: " + line);
            }
        } catch (IOException e) {
            System.out.println("Error with RandomAccessFile: " + e.getMessage());
        }
    }
}

