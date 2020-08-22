package com.maistruk.university.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.maistruk.university.dao.StudentDao;
import com.maistruk.university.model.Student;
import com.maistruk.university.service.ConnectionProvider;

public class JdbcStudentDao implements StudentDao {

    private static final String ADD_STUDENT = "INSERT INTO students (student_id, group_id, first_name, last_name) VALUES (DEFAULT, ?, ?, ?)";
    private static final String DELETE_STUDENT = "DELETE FROM students WHERE student_id = ?";
    private static final String FIND_STUDENTS_BY_COURSE = "SELECT students.student_id, students.group_id, students.first_name, students.last_name FROM students_courses "
            + "RIGHT JOIN students ON students_courses.student_id = students.student_id "
            + "LEFT JOIN courses ON students_courses.course_id = courses.course_id " + "WHERE courses.course_name = ?";
    private static final String DELETE_STUDENT_FROM_COURSE = "DELETE FROM students_courses WHERE student_id = ? AND course_id = ?";
    private static final String ADD_STUDENT_TO_COURSE = "INSERT INTO students_courses (student_id, course_id) VALUES (?, ?) ";

    private ConnectionProvider connectionProvider;

    public JdbcStudentDao(ConnectionProvider connectionProvider) {
        this.connectionProvider = connectionProvider;
    }

    @Override
    public void createStudent(Student student) {
        try (Connection connection = connectionProvider.getConnection();
                PreparedStatement statement = connection.prepareStatement(ADD_STUDENT,
                        Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, student.getGroupId());
            statement.setString(2, student.getFirstName());
            statement.setString(3, student.getLastName());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                student.setStudentId(resultSet.getInt(1));
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteStudent(int id) {
        try (Connection connection = connectionProvider.getConnection();
                PreparedStatement statement = connection.prepareStatement(DELETE_STUDENT)) {
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addStudentToCourse(int studentId, int courseId) {
        try (Connection connection = connectionProvider.getConnection();
                PreparedStatement statement = connection.prepareStatement(ADD_STUDENT_TO_COURSE)) {
            statement.setInt(1, studentId);
            statement.setInt(2, courseId);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Student> findStudentsByCourse(String course) {
        List<Student> students = new ArrayList<>();
        try (Connection connection = connectionProvider.getConnection();
                PreparedStatement statement = connection.prepareStatement(FIND_STUDENTS_BY_COURSE)) {
            statement.setString(1, course);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Student student = new Student();
                student.setStudentId(resultSet.getInt(1));
                student.setGroupId(resultSet.getInt(2));
                student.setFirstName(resultSet.getString(3));
                student.setLastName(resultSet.getString(4));
                students.add(student);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    @Override
    public void deleteStudentFromCourse(int studentId, int courseId) {
        try (Connection connection = connectionProvider.getConnection();
                PreparedStatement statement = connection.prepareStatement(DELETE_STUDENT_FROM_COURSE)) {
            statement.setInt(1, studentId);
            statement.setInt(2, courseId);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
