package com.maistruk.university.dao;

import java.util.List;

import com.maistruk.university.model.Student;

public interface StudentDao {

    public void createStudent(Student student);

    public void deleteStudent(int id);

    public void addStudentToCourse(int studentId, int courseId);

    public List<Student> findStudentsByCourse(String course);

    public void deleteStudentFromCourse(int studentId, int courseId);
}
