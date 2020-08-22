package com.maistruk.university.model;

public class Student {

    private int studentId;
    private int groupId;
    private String firstName;
    private String lastName;

    public Student() {
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "Student [studentId=" + studentId + ", groupId=" + groupId + ", firstName=" + firstName + ", lastName="
                + lastName + "]";
    }

    @Override
    public boolean equals(Object comparedStudent) {
        Student student = (Student) comparedStudent;
        if (student.studentId != studentId) {
            return false;
        }
        if (student.groupId != groupId) {
            return false;
        }
        if (!student.firstName.equals(firstName)) {
            return false;
        }
        if (!student.lastName.equals(lastName)) {
            return false;
        }
        return true;
    }

}
