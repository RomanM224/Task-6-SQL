package com.maistruk.university.dao.jdbc;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.maistruk.university.model.Course;

public class JdbcCourseDaoTest extends DaoTest {

    private JdbcCourseDao courseDao;

    @BeforeEach
    public void setUp() {
        super.setUp();
        courseDao = new JdbcCourseDao(connectionProvider);
    }

    @Test
    public void createNewCourse_whenSendCourseInDatabase_thenGetGeneratedId() {
        Course expectedCourse = new Course();
        expectedCourse.setId(4);
        expectedCourse.setName("Course Name");
        expectedCourse.setDiscription("Course Discription");
        Course actualCourse = new Course();
        actualCourse.setName("Course Name");
        actualCourse.setDiscription("Course Discription");

        courseDao.create(actualCourse);

        assertEquals(expectedCourse, actualCourse);
    }
}
