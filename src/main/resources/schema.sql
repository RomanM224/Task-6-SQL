DROP TABLE IF EXISTS groups CASCADE;

CREATE TABLE IF NOT EXISTS groups(
	group_id SERIAL PRIMARY KEY,
	group_name VARCHAR(10));

DROP TABLE IF EXISTS students CASCADE;

CREATE TABLE IF NOT EXISTS students(
	student_id SERIAL PRIMARY KEY,
	group_id SERIAL,
	first_name VARCHAR(30),
	last_name VARCHAR(30),
	FOREIGN KEY (group_id) REFERENCES groups(group_id));

DROP TABLE IF EXISTS courses CASCADE;

CREATE TABLE IF NOT EXISTS courses(
	course_id SERIAL PRIMARY KEY,
	course_name VARCHAR(50),
	course_description VARCHAR(300));

DROP TABLE IF EXISTS students_courses;

CREATE TABLE IF NOT EXISTS students_courses(
	student_id INTEGER,
	course_id INTEGER,
	FOREIGN KEY (student_id) REFERENCES students (student_id) ON DELETE CASCADE,
	FOREIGN KEY (course_id) REFERENCES courses (course_id) ON DELETE CASCADE);

