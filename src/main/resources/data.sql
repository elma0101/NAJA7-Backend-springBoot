-- =================================================================
-- SCHEMA CREATION
-- =================================================================
-- This script first creates all necessary tables if they don't already exist.
-- Using "CREATE TABLE IF NOT EXISTS" makes the script safe to run multiple times.

CREATE TABLE IF NOT EXISTS users (
                                     id INT PRIMARY KEY,
                                     name VARCHAR(255) NOT NULL,
                                     email VARCHAR(255) NOT NULL UNIQUE,
                                     password VARCHAR(255) NOT NULL,
                                     level VARCHAR(100),
                                     role VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS courses (
                                       id BIGINT PRIMARY KEY,
                                       title VARCHAR(255) NOT NULL,
                                       description TEXT,
                                       image_url VARCHAR(255),
                                       level VARCHAR(100),
                                       subject VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS modules (
                                       id BIGINT PRIMARY KEY,
                                       title VARCHAR(255) NOT NULL,
                                       course_id BIGINT,
                                       INDEX (course_id),
                                       FOREIGN KEY (course_id) REFERENCES courses(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS lessons (
                                       id BIGINT PRIMARY KEY,
                                       title VARCHAR(255) NOT NULL,
                                       video_url VARCHAR(255),
                                       content TEXT,
                                       module_id BIGINT,
                                       INDEX (module_id),
                                       FOREIGN KEY (module_id) REFERENCES modules(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS live_sessions (
                                             id BIGINT PRIMARY KEY,
                                             title VARCHAR(255) NOT NULL,
                                             description TEXT,
                                             session_time DATETIME,
                                             instructor VARCHAR(255),
                                             recording_url VARCHAR(255)
);


-- =================================================================
-- DATA POPULATION
-- =================================================================
-- This section populates the tables with initial data for testing.
-- Using "ON DUPLICATE KEY UPDATE" prevents errors if the script is run again.

-- USERS
INSERT INTO users (id, name, email, password, level, role) VALUES
    (1, 'Aicha Student', 'student@naja7online.com', '$2a$10$GiseG0yT35/RTx0a/O5tI.aaP0iXF.gFFGso2yJmR4Wwomr6YwP3K', '2eme_BAC_SVT', 'STUDENT')
ON DUPLICATE KEY UPDATE name = VALUES(name);

-- COURSES
INSERT INTO courses (id, title, description, image_url, level, subject) VALUES
                                                                            (1, 'Mathématiques - Analyse', 'Ce cours couvre les concepts fondamentaux de l''analyse mathématique, y compris les limites, les dérivées et les intégrales.', '/assets/courses/math-advanced.png', '2eme_BAC', 'Mathematiques'),
                                                                            (2, 'Physique - Mécanique Newtonienne', 'Explorez les lois du mouvement de Newton, le travail, l''énergie et la puissance dans ce cours de mécanique classique.', '/assets/courses/physique-quantum.png', '2eme_BAC', 'Physique-Chimie')
ON DUPLICATE KEY UPDATE title = VALUES(title);

-- MODULES
INSERT INTO modules (id, title, course_id) VALUES
                                               (1, 'Module 1: Les Limites et la Continuité', 1),
                                               (2, 'Module 2: La Dérivation', 1),
                                               (3, 'Module 3: Les Intégrales', 1)
ON DUPLICATE KEY UPDATE title = VALUES(title);

INSERT INTO modules (id, title, course_id) VALUES
                                               (4, 'Module 1: Les Lois de Newton', 2),
                                               (5, 'Module 2: Travail et Énergie Cinétique', 2)
ON DUPLICATE KEY UPDATE title = VALUES(title);

-- LESSONS
INSERT INTO lessons (id, title, video_url, content, module_id) VALUES
                                                                   (1, '1.1 Introduction aux Limites', 'https://www.youtube.com/watch?v=some_video_id', 'Contenu textuel sur les limites...', 1),
                                                                   (2, '1.2 Propriétés des Limites', 'https://www.youtube.com/watch?v=some_video_id', 'Contenu textuel...', 1)
ON DUPLICATE KEY UPDATE title = VALUES(title);

INSERT INTO lessons (id, title, video_url, content, module_id) VALUES
    (3, '2.1 Définition de la Dérivée', 'https://www.youtube.com/watch?v=some_video_id', 'Contenu textuel sur la dérivée...', 2)
ON DUPLICATE KEY UPDATE title = VALUES(title);

INSERT INTO lessons (id, title, video_url, content, module_id) VALUES
                                                                   (4, '1.1 La Première Loi de Newton', 'https://www.youtube.com/watch?v=some_video_id', 'Contenu textuel sur l''inertie...', 4),
                                                                   (5, '1.2 La Deuxième Loi de Newton (F=ma)', 'https://www.youtube.com/watch?v=some_video_id', 'Contenu textuel...', 4)
ON DUPLICATE KEY UPDATE title = VALUES(title);

-- LIVE SESSIONS
INSERT INTO live_sessions (id, title, description, session_time, instructor, recording_url) VALUES
                                                                                                (1, 'Révision de Chimie Organique', 'Une session de révision complète sur la nomenclature et les réactions.', '2024-05-20 18:00:00', 'Dr. Fatima Zahra', 'https://www.youtube.com/watch?v=past_recording_id'),
                                                                                                (2, 'Masterclass sur les Intégrales', 'Préparez-vous pour vos examens avec cette session approfondie.', '2029-10-25 19:00:00', 'Mr. Hassan El Amrani', null)
ON DUPLICATE KEY UPDATE title = VALUES(title);