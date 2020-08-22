package com.maistruk.university.dao.jdbc;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.maistruk.university.model.Group;

public class JdbcGroupDaoTest extends DaoTest {

    private JdbcGroupDao groupDao;

    @BeforeEach
    public void setUp() {
        super.setUp();
        groupDao = new JdbcGroupDao(connectionProvider);
    }

    @Test
    public void givenAmountOfStudents3_whenSendQueryInDatabase_thenGetGroups() {
        List<Group> expectedGroups = new ArrayList<>();
        Group group1 = new Group();
        group1.setName("FJ-52");
        group1.setId(2);
        expectedGroups.add(group1);
        Group group2 = new Group();
        group2.setName("AE-49");
        group2.setId(3);
        expectedGroups.add(group2);

        List<Group> actualGroups = groupDao.findByStudentAmount(3);

        assertEquals(expectedGroups, actualGroups);
    }

    @Test
    public void givenAmountOfStudentsZero_whenSendQueryInDatabase_thenGetEmptyList() {
        List<Group> expectedGroups = new ArrayList<>();
        List<Group> actualGroups = groupDao.findByStudentAmount(0);

        assertEquals(expectedGroups, actualGroups);
    }

    @Test
    public void givenNegativaNumber_whenSendQueryInDatabase_thenGetEmptyList() {
        List<Group> expectedGroups = new ArrayList<>();
        List<Group> actualGroups = groupDao.findByStudentAmount(-1);

        assertEquals(expectedGroups, actualGroups);
    }

    @Test
    public void createNewGroup_whenSendGroupInDatabase_thenGetGeneratedId() {
        Group expectedGroup = new Group();
        expectedGroup.setId(4);
        expectedGroup.setName("AK-22");
        Group actualGroup = new Group();
        actualGroup.setName("AK-22");

        groupDao.create(actualGroup);

        assertEquals(expectedGroup, actualGroup);

    }
}
