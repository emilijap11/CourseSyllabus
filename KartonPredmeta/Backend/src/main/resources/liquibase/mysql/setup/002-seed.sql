
INSERT IGNORE INTO teachers (id, first_name, last_name, email, title, academic_title, institution)
VALUES
    (1, 'Ana', 'Ivic', 'ana.ivic@example.com', 'Prof.', 'Dr', 'FON'),
    (2, 'Marko', 'Jovic', 'marko.jovic@example.com', 'Asistent', NULL, 'FON');

INSERT IGNORE INTO courses (id, name, code, ects, teacher_id, description)
VALUES
    (1, 'Programiranje 1', 'PRG1', 6, NULL, 'Uvod u osnove programiranja'),
    (2, 'Baze Podataka', 'BP1', 6, NULL, 'Osnove relacione baze podataka');
