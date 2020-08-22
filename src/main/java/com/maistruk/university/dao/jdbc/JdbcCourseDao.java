package com.maistruk.university.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.maistruk.university.dao.CourseDao;
import com.maistruk.university.model.Course;
import com.maistruk.university.service.ConnectionProvider;

public class JdbcCourseDao implements CourseDao {

    private static final String ADD_COURSE = "INSERT INTO courses (course_id, course_name, course_description) VALUES (DEFAULT, ?, ?)";

    private ConnectionProvider connectionProvider;

    public JdbcCourseDao(ConnectionProvider connectionProvider) {
        this.connectionProvider = connectionProvider;
    }

    @Override
    public void create(Course course) {
        try (Connection connection = connectionProvider.getConnection();
                PreparedStatement statement = connection.prepareStatement(ADD_COURSE,
                        Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, course.getName());
            statement.setString(2, course.getDiscription());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                course.setId(resultSet.getInt(1));
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
