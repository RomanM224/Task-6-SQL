package com.maistruk.university.dao;

import java.util.List;

import com.maistruk.university.model.Group;

public interface GroupDao {

    public void create(Group group);

    public List<Group> findByStudentAmount(int studentsAmount);
}
