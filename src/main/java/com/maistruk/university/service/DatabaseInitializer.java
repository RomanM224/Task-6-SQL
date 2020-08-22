package com.maistruk.university.service;

import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;
import org.apache.ibatis.jdbc.ScriptRunner;
import com.maistruk.university.dao.jdbc.JdbcCourseDao;
import com.maistruk.university.dao.jdbc.JdbcGroupDao;
import com.maistruk.university.dao.jdbc.JdbcStudentDao;
import com.maistruk.university.model.Course;
import com.maistruk.university.model.Group;
import com.maistruk.university.model.Student;

public class DatabaseInitializer {

    private JdbcStudentDao studentDao;
    private JdbcGroupDao groupDao;
    private JdbcCourseDao courseDao;
    private ConnectionProvider connectionProvider;
    private Random random;
    private InfoGenerator infoGenerator;

    public DatabaseInitializer(JdbcStudentDao studentDao, JdbcGroupDao groupDao, JdbcCourseDao courseDao,
            ConnectionProvider connectionProvider, Random random, InfoGenerator infoGenerator) {
        this.connectionProvider = connectionProvider;
        this.groupDao = groupDao;
        this.studentDao = studentDao;
        this.courseDao = courseDao;
        this.random = random;
        this.infoGenerator = infoGenerator;
    }

    public void init() {
        importSchema();
        loadData();
    }

    private void importSchema() {
        try (Connection connection = connectionProvider.getConnection();) {
            ScriptRunner runner = new ScriptRunner(connection);
            runner.runScript(new InputStreamReader(getClass().getClassLoader().getResourceAsStream("schema.sql")));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private List<Group> generateGroups() {
        List<Group> groups = infoGenerator.generateGroups(random);
        groups.forEach(groupDao::create);
        return groups;
    }

    private List<Student> generateStudents(List<Group> groups) {
        List<Student> students = infoGenerator.generateStudents(groups, random);
        students.forEach(studentDao::createStudent);
        return students;
    }

    private List<Course> generateCourses() {
        List<Course> courses = infoGenerator.generateCourses();
        courses.forEach(courseDao::create);
        return courses;
    }

    private void loadData() {
        List<Group> groups = generateGroups();
        List<Student> students = generateStudents(groups);
        List<Course> courses = generateCourses();
        students.forEach(student -> setRandomCourses(student, courses));
    }

    private void setRandomCourses(Student student, List<Course> courses) {
        int courseId = random.nextInt(10);
        while (courseId >= 0) {
            studentDao.addStudentToCourse(student.getStudentId(), courses.get(courseId).getId());
            courseId -= 4;
        }
    }
}
