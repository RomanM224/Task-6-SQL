package com.maistruk.university.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.maistruk.university.dao.GroupDao;
import com.maistruk.university.model.Group;
import com.maistruk.university.service.ConnectionProvider;

public class JdbcGroupDao implements GroupDao {

    private static final String FIND_GROUP = "SELECT groups.group_id, groups.group_name, COUNT(students.group_id) FROM groups "
            + "RIGHT JOIN students ON students.group_id = groups.group_id "
            + "GROUP BY groups.group_id HAVING COUNT(groups.group_id) <= ?";
    private static final String ADD_GROUP = "INSERT INTO groups (group_id, group_name) VALUES (DEFAULT, ?) ";

    private ConnectionProvider connectionProvider;

    public JdbcGroupDao(ConnectionProvider connectionProvider) {
        this.connectionProvider = connectionProvider;
    }

    @Override
    public void create(Group group) {
        try (Connection connection = connectionProvider.getConnection();
                PreparedStatement statement = connection.prepareStatement(ADD_GROUP, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, group.getName());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                group.setId(resultSet.getInt(1));
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Group> findByStudentAmount(int studentsAmount) {
        List<Group> groups = new ArrayList<>();
        try (Connection connection = connectionProvider.getConnection();
                PreparedStatement statement = connection.prepareStatement(FIND_GROUP)) {
            statement.setInt(1, studentsAmount);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Group group = new Group();
                group.setId(resultSet.getInt(1));
                group.setName(resultSet.getString(2));
                groups.add(group);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return groups;
    }
}
