package com.maistruk.university;

import java.sql.SQLException;
import java.util.Random;
import java.util.Scanner;
import com.maistruk.university.dao.jdbc.JdbcCourseDao;
import com.maistruk.university.dao.jdbc.JdbcGroupDao;
import com.maistruk.university.dao.jdbc.JdbcStudentDao;
import com.maistruk.university.model.Student;
import com.maistruk.university.service.ConnectionProvider;
import com.maistruk.university.service.InfoGenerator;
import com.maistruk.university.service.DatabaseInitializer;

public class Main {

    public static void main(String[] args) throws SQLException {
        String configName = "config.properties";
        ConnectionProvider connectionProvider = new ConnectionProvider(configName);
        JdbcGroupDao groupDao = new JdbcGroupDao(connectionProvider);
        JdbcStudentDao studentDao = new JdbcStudentDao(connectionProvider);
        JdbcCourseDao courseDao = new JdbcCourseDao(connectionProvider);
        InfoGenerator infoGenerator = new InfoGenerator();
        Random random = new Random();
        DatabaseInitializer databaseInitializer = new DatabaseInitializer(studentDao, groupDao, courseDao,
                connectionProvider, random, infoGenerator);
        databaseInitializer.init();
        Scanner scanner = new Scanner(System.in);

        System.out.println("1. Find all groups with less or equals student count");
        System.out.println("2. Find all students related to course with given name");
        System.out.println("3. Add new student");
        System.out.println("4. Delete student by student id");
        System.out.println("5. Add a student to the course (from a list)");
        System.out.println("6. Remove the student from one of his or her courses");
        int select;
        do {
            System.out.print("Choice menu: ");
            select = scanner.nextInt();
            scanner.nextLine();
            selectOption(select, scanner, groupDao, studentDao);
        } while (select != 0);
    }

    public static void selectOption(int select, Scanner scanner, JdbcGroupDao groupDao, JdbcStudentDao studentDao) {

        if (select == 1) {
            findGroupsByStudentsAmount(groupDao, scanner);
        }
        if (select == 2) {
            findStudentsByCourse(studentDao, scanner);
        }
        if (select == 3) {
            addStudent(studentDao, scanner);
        }
        if (select == 4) {
            deleteStudent(studentDao, scanner);
        }
        if (select == 5) {
            addStudentToCourse(studentDao, scanner);
        }
        if (select == 6) {
            deleteStudentFromCourse(studentDao, scanner);
        }
    }

    public static void findGroupsByStudentsAmount(JdbcGroupDao groupDao, Scanner scanner) {
        System.out.println("1. Find all groups with less or equals student count");
        System.out.print("Amount of students: ");
        int amountOfStudents = scanner.nextInt();
        System.out.println(groupDao.findByStudentAmount(amountOfStudents));
    }

    public static void findStudentsByCourse(JdbcStudentDao studentDao, Scanner scanner) {
        System.out.println("2. Find all students related to course with given name");
        System.out.print("Insert course name: ");
        String courseName = scanner.nextLine();
        System.out.println(studentDao.findStudentsByCourse(courseName));
    }

    public static void addStudent(JdbcStudentDao studentDao, Scanner scanner) {
        System.out.println("3. Add new student");
        Student newStudent = new Student();
        System.out.print("Insert group id: ");
        int groupId = scanner.nextInt();
        newStudent.setGroupId(groupId);
        System.out.print("Insert first name: ");
        String name = scanner.next();
        newStudent.setFirstName(name);
        System.out.print("Insert last name: ");
        name = scanner.next();
        newStudent.setLastName(name);
        studentDao.createStudent(newStudent);
    }

    public static void deleteStudent(JdbcStudentDao studentDao, Scanner scanner) {
        System.out.println("4. Delete student by student id");
        System.out.print("Delete student by id: ");
        int id = scanner.nextInt();
        studentDao.deleteStudent(id);
    }

    public static void addStudentToCourse(JdbcStudentDao studentDao, Scanner scanner) {
        System.out.println("5. Add a student to the course (from a list)");
        System.out.println("Insert student id: ");
        int studentId = scanner.nextInt();
        System.out.println("Insert course id: ");
        int courseId = scanner.nextInt();
        studentDao.addStudentToCourse(studentId, courseId);
    }

    public static void deleteStudentFromCourse(JdbcStudentDao studentDao, Scanner scanner) {
        System.out.println("6. Remove the student from one of his or her courses");
        System.out.print("Insert student id: ");
        int studentId = scanner.nextInt();
        System.out.print("Insert course id: ");
        int courseId = scanner.nextInt();
        studentDao.deleteStudentFromCourse(studentId, courseId);
    }
}
