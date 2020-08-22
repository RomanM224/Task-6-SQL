package com.maistruk.university.model;

public class Course {

    private int id;
    private String name;
    private String discription;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    @Override
    public String toString() {
        return "Course [course_id=" + id + ", course_name=" + name + ", course_discription=" + discription + "]";
    }

    @Override
    public boolean equals(Object comparedCourse) {
        Course course = (Course) comparedCourse;
        if (course.id != id) {
            return false;
        } else if (!course.name.equals(name)) {
            return false;
        } else if (!course.discription.equals(discription)) {
            return false;
        }
        return true;
    }
}
