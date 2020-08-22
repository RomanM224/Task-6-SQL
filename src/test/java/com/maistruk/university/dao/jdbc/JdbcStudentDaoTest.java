package com.maistruk.university.dao.jdbc;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.maistruk.university.model.Student;

public class JdbcStudentDaoTest extends DaoTest {

    private JdbcStudentDao studentDao;

    @BeforeEach
    public void setUp() {
        super.setUp();
        studentDao = new JdbcStudentDao(connectionProvider);
    }

    @Test
    public void givenCourseName_whenSendQueryInDatabase_thenGetStudents() {
        List<Student> expectedStudents = new ArrayList<>();
        Student student1 = new Student();
        student1.setStudentId(2);
        student1.setGroupId(2);
        student1.setFirstName("Kate");
        student1.setLastName("Watson");
        expectedStudents.add(student1);
        Student student2 = new Student();
        student2.setStudentId(3);
        student2.setGroupId(3);
        student2.setFirstName("Josh");
        student2.setLastName("Lawrence");
        expectedStudents.add(student2);

        List<Student> actualStudents = studentDao.findStudentsByCourse("Database");

        assertEquals(expectedStudents, actualStudents);
    }

    @Test
    public void givenWrongCourseName_whenSendQueryInDatabase_thenReturnEmptyList() {
        List<Student> expectedStudents = new ArrayList<>();
        List<Student> actualStudents = studentDao.findStudentsByCourse("Abc");

        assertEquals(expectedStudents, actualStudents);
    }

    @Test
    public void createNewStudent_whenSendStudentInDatabase_thenGetGeneratedId() {
        Student expectedStudent = new Student();
        expectedStudent.setStudentId(11);
        expectedStudent.setGroupId(2);
        expectedStudent.setFirstName("Jemmy");
        expectedStudent.setLastName("Carr");
        Student actualStudent = new Student();
        actualStudent.setGroupId(2);
        actualStudent.setFirstName("Jemmy");
        actualStudent.setLastName("Carr");

        studentDao.createStudent(actualStudent);

        assertEquals(expectedStudent, actualStudent);

    }

}
