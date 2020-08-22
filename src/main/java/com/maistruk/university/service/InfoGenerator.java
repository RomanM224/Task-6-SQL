package com.maistruk.university.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import static java.util.stream.Collectors.*;
import java.util.stream.Stream;

import com.maistruk.university.model.Course;
import com.maistruk.university.model.Group;
import com.maistruk.university.model.Student;

public class InfoGenerator {

    private static String[][] coursesInfo = { { "Mathematics",
            "Mathematics includes the study of such topics as quantity (number theory), structure (algebra), space (geometry), and change (mathematical analysis).", },
            { "Biology",
                    "Biology is the natural science that studies life and living organisms, including their physical structure, chemical processes, molecular interactions, physiological mechanisms, development and evolution.", },
            { "Geography",
                    "Geography is a field of science devoted to the study of the lands, features, inhabitants, and phenomena of the Earth and planets.", },
            { "Computer science",
                    "Computer science is the study of processes that interact with data and that can be represented as data in the form of programs.", },
            { "Database",
                    "Database is the study of an organized collection of data, generally stored and accessed electronically from a computer system.", },
            { "Physics",
                    "Physics is the natural science that studies matter, its motion and behavior through space and time, and that studies the related entities of energy and force.", },
            { "English language",
                    "English language is the study of english culture, english grammar and english history", },
            { "Algorithms and their properties", "Study the properties of algorithms and their use", },
            { "Java", "Learning the syntax of a programming language Java", }, { "Economy",
                    "An economy is an area of the production, distribution and trade, as well as consumption of goods and services by different agents." } };
    private static String[][] studentsInfo = {
            { "Sherlock", "John", "Leonardo", "Kate", "Thomas", "Todd", "Josh", "Martin", "Kumail", "Amanda", "Zach",
                    "Matt", "Alice", "Emily", "Rosa", "Jennifer", "Jennifer", "Sophie", "Alexandra", "Brianne" },
            { "Holmes", "Watson", "Dicaprio", "Winslet", "Middleditch", "Miller", "Brener", "Starr", "Nanjiani", "Crew",
                    "Woods", "Ross", "Wetterlund", "Stone", "Salazar", "Connelly", "Lawrence", "Belinda", "Shipp",
                    "Desaulniers" } };

    public List<Course> generateCourses() {
        List<Course> courses = new ArrayList<>();
        for (String[] courseInfo : coursesInfo) {
            Course course = new Course();
            course.setName(courseInfo[0]);
            course.setDiscription(courseInfo[1]);
            courses.add(course);
        }
        return courses;
    }

    public List<Group> generateGroups(Random random) {
        return Stream.generate(() -> setGroupInfo(new Group(), random)).limit(10).collect(toList());
    }

    private Group setGroupInfo(Group group, Random random) {
        StringBuilder groupName = new StringBuilder();
        char character1 = (char) (random.nextInt(26) + 65);
        char character2 = (char) (random.nextInt(26) + 65);
        group.setName(
                groupName.append(character1).append(character2).append("-").append(random.nextInt(90) + 10).toString());
        return group;
    }

    public List<Student> generateStudents(List<Group> groups, Random random) {
        return Stream.generate(() -> setStudentInfo(new Student(), groups, random)).limit(200).collect(toList());
    }

    private Student setStudentInfo(Student student, List<Group> groups, Random random) {
        student.setGroupId(groups.get(random.nextInt(10)).getId());
        student.setFirstName(studentsInfo[0][random.nextInt(20)]);
        student.setLastName(studentsInfo[1][random.nextInt(20)]);
        return student;
    }

}
