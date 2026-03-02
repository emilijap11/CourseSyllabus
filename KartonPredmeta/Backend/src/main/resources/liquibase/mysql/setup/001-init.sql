
CREATE TABLE IF NOT EXISTS teachers (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    email VARCHAR(255) UNIQUE,
    title VARCHAR(255),
    academic_title VARCHAR(255),
    institution VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS courses (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    code VARCHAR(50),
    ects INT,
    teacher_id BIGINT,
    description TEXT,
    CONSTRAINT fk_courses_teacher
        FOREIGN KEY (teacher_id) REFERENCES teachers(id)
);

CREATE TABLE IF NOT EXISTS learning_outcomes (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    description VARCHAR(500) NOT NULL,
    course_id BIGINT NOT NULL,
    CONSTRAINT fk_learning_outcomes_course
        FOREIGN KEY (course_id) REFERENCES courses(id)
);

CREATE TABLE IF NOT EXISTS assessment_components (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    max_points INT NOT NULL,
    type VARCHAR(255),
    points INT,
    course_id BIGINT NOT NULL,
    CONSTRAINT fk_assessment_components_course
        FOREIGN KEY (course_id) REFERENCES courses(id)
);

CREATE TABLE IF NOT EXISTS course_objective (
    objective_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    description TEXT,
    course_id BIGINT,
    CONSTRAINT fk_course_objective_course
        FOREIGN KEY (course_id) REFERENCES courses(id)
);

CREATE TABLE IF NOT EXISTS grade_components (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    description TEXT,
    points INT NOT NULL,
    name VARCHAR(255),
    type_description VARCHAR(255),
    max_points INT,
    assessment_component_id BIGINT NOT NULL,
    course_id BIGINT,
    CONSTRAINT fk_grade_components_assessment
        FOREIGN KEY (assessment_component_id) REFERENCES assessment_components(id),
    CONSTRAINT fk_grade_components_course
        FOREIGN KEY (course_id) REFERENCES courses(id)
);

CREATE TABLE IF NOT EXISTS literature (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255),
    year INT,
    type VARCHAR(255) NOT NULL,
    course_id BIGINT NOT NULL,
    CONSTRAINT fk_literature_course
        FOREIGN KEY (course_id) REFERENCES courses(id)
);

CREATE TABLE IF NOT EXISTS teaching_assignments (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    type VARCHAR(255) NOT NULL,
    hours INT NOT NULL,
    course_id BIGINT NOT NULL,
    teacher_id BIGINT NOT NULL,
    CONSTRAINT fk_teaching_assignments_course
        FOREIGN KEY (course_id) REFERENCES courses(id),
    CONSTRAINT fk_teaching_assignments_teacher
        FOREIGN KEY (teacher_id) REFERENCES teachers(id)
);

CREATE TABLE IF NOT EXISTS thematic_units (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255),
    description TEXT,
    number_of_lectures INT,
    number_of_exercises INT,
    course_id BIGINT,
    CONSTRAINT fk_thematic_units_course
        FOREIGN KEY (course_id) REFERENCES courses(id)
);
