DROP TABLE IF EXISTS groups CASCADE;

CREATE TABLE groups(group_id IDENTITY  PRIMARY KEY, group_name VARCHAR(10));
INSERT INTO groups (group_id, group_name) VALUES (DEFAULT, 'AK-27');
INSERT INTO groups (group_id, group_name) VALUES (DEFAULT, 'FJ-52');
INSERT INTO groups (group_id, group_name) VALUES (DEFAULT, 'AE-49');

DROP TABLE IF EXISTS students CASCADE;

CREATE TABLE students(
	student_id IDENTITY PRIMARY KEY,
	group_id INT,
	first_name VARCHAR(30),
	last_name VARCHAR(30),
	FOREIGN KEY (group_id) REFERENCES groups(group_id));
	
INSERT INTO students (student_id, group_id, first_name, last_name) VALUES (DEFAULT, 1, 'Brianne', 'Wetterlund');
INSERT INTO students (student_id, group_id, first_name, last_name) VALUES (DEFAULT, 2, 'Kate', 'Watson');
INSERT INTO students (student_id, group_id, first_name, last_name) VALUES (DEFAULT, 3, 'Josh', 'Lawrence');
INSERT INTO students (student_id, group_id, first_name, last_name) VALUES (DEFAULT, 1, 'Kate', 'Ross');
INSERT INTO students (student_id, group_id, first_name, last_name) VALUES (DEFAULT, 2, 'Sophie', 'Middleditch');
INSERT INTO students (student_id, group_id, first_name, last_name) VALUES (DEFAULT, 3, 'Matt', 'Brener');
INSERT INTO students (student_id, group_id, first_name, last_name) VALUES (DEFAULT, 1, 'Martin', 'Shipp');
INSERT INTO students (student_id, group_id, first_name, last_name) VALUES (DEFAULT, 2, 'Alice', 'Nanjiani');
INSERT INTO students (student_id, group_id, first_name, last_name) VALUES (DEFAULT, 3, 'Emily', 'Connelly');
INSERT INTO students (student_id, group_id, first_name, last_name) VALUES (DEFAULT, 1, 'Leonardo', 'Miller');

DROP TABLE IF EXISTS courses CASCADE;

CREATE TABLE courses(
	course_id IDENTITY PRIMARY KEY,
	course_name VARCHAR(50),
	course_description VARCHAR(300));
	
INSERT INTO courses (course_id, course_name, course_description) VALUES (DEFAULT, 'Database', 'Database is the study of an organized collection of data, generally stored and accessed electronically from a computer system.');
INSERT INTO courses (course_id, course_name, course_description) VALUES (DEFAULT, 'Computer science', 'Computer science is the study of processes that interact with data and that can be represented as data in the form of programs.');
INSERT INTO courses (course_id, course_name, course_description) VALUES (DEFAULT, 'Geography', 'Geography is a field of science devoted to the study of the lands, features, inhabitants, and phenomena of the Earth and planets.');

DROP TABLE IF EXISTS students_courses;

CREATE TABLE IF NOT EXISTS students_courses(
	student_id INT,
	course_id INT,
	FOREIGN KEY (student_id) REFERENCES students (student_id) ON DELETE CASCADE,
	FOREIGN KEY (course_id) REFERENCES courses (course_id) ON DELETE CASCADE);
	
INSERT INTO students_courses (student_id, course_id) VALUES (1, 2);
INSERT INTO students_courses (student_id, course_id) VALUES (1, 3);
INSERT INTO students_courses (student_id, course_id) VALUES (2, 1);
INSERT INTO students_courses (student_id, course_id) VALUES (3, 1);
INSERT INTO students_courses (student_id, course_id) VALUES (3, 3);
INSERT INTO students_courses (student_id, course_id) VALUES (3, 2);
INSERT INTO students_courses (student_id, course_id) VALUES (4, 2);
INSERT INTO students_courses (student_id, course_id) VALUES (4, 3);
INSERT INTO students_courses (student_id, course_id) VALUES (5, 2);
INSERT INTO students_courses (student_id, course_id) VALUES (6, 2);
INSERT INTO students_courses (student_id, course_id) VALUES (6, 2);
INSERT INTO students_courses (student_id, course_id) VALUES (7, 3);
INSERT INTO students_courses (student_id, course_id) VALUES (8, 2);
INSERT INTO students_courses (student_id, course_id) VALUES (8, 3);
INSERT INTO students_courses (student_id, course_id) VALUES (9, 3);
INSERT INTO students_courses (student_id, course_id) VALUES (10, 2);
INSERT INTO students_courses (student_id, course_id) VALUES (10, 3);


