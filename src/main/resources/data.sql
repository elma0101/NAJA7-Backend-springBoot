-- This script will be executed automatically by Spring Boot on startup.
-- It populates the database with initial data for testing.
-- Note: The passwords are plain text here ('password123'). The AuthService will hash them upon registration,
-- but for manual insertion, we will use a pre-hashed password for a test user.
-- The BCrypt hash for "password123" is: $2a$10$GiseG0yT35/RTx0a/O5tI.aaP0iXF.gFFGso2yJmR4Wwomr6YwP3K

-- =================================================================
-- USERS
-- =================================================================
-- We'll create one pre-registered user. You can log in with:
-- email: student@naja7online.com
-- password: password123
INSERT INTO users (id, name, email, password, level, role)
VALUES (1, 'Aicha Student', 'student@naja7online.com', '$2a$10$GiseG0yT35/RTx0a/O5tI.aaP0iXF.gFFGso2yJmR4Wwomr6YwP3K', '2eme_BAC_SVT', 'STUDENT')
    ON CONFLICT (id) DO NOTHING;


-- =================================================================
-- COURSES
-- =================================================================
INSERT INTO courses (id, title, description, image_url, level, subject)
VALUES
    (1, 'Mathématiques - Analyse', 'Ce cours couvre les concepts fondamentaux de l''analyse mathématique, y compris les limites, les dérivées et les intégrales.', '/assets/courses/math-advanced.png', '2eme_BAC', 'Mathematiques'),
    (2, 'Physique - Mécanique Newtonienne', 'Explorez les lois du mouvement de Newton, le travail, l''énergie et la puissance dans ce cours de mécanique classique.', '/assets/courses/physique-quantum.png', '2eme_BAC', 'Physique-Chimie')
    ON CONFLICT (id) DO NOTHING;


-- =================================================================
-- MODULES
-- =================================================================
-- Modules for Math Course (ID: 1)
INSERT INTO modules (id, title, course_id)
VALUES
    (1, 'Module 1: Les Limites et la Continuité', 1),
    (2, 'Module 2: La Dérivation', 1),
    (3, 'Module 3: Les Intégrales', 1)
    ON CONFLICT (id) DO NOTHING;

-- Modules for Physics Course (ID: 2)
INSERT INTO modules (id, title, course_id)
VALUES
    (4, 'Module 1: Les Lois de Newton', 2),
    (5, 'Module 2: Travail et Énergie Cinétique', 2)
    ON CONFLICT (id) DO NOTHING;


-- =================================================================
-- LESSONS
-- =================================================================
-- Lessons for Math Module 1 (ID: 1)
INSERT INTO lessons (id, title, video_url, content, module_id)
VALUES
    (1, '1.1 Introduction aux Limites', 'https://www.youtube.com/watch?v=some_video_id', 'Contenu textuel sur les limites...', 1),
    (2, '1.2 Propriétés des Limites', 'https://www.youtube.com/watch?v=some_video_id', 'Contenu textuel...', 1);

-- Lessons for Math Module 2 (ID: 2)
INSERT INTO lessons (id, title, video_url, content, module_id)
VALUES
    (3, '2.1 Définition de la Dérivée', 'https://www.youtube.com/watch?v=some_video_id', 'Contenu textuel sur la dérivée...', 2);

-- Lessons for Physics Module 1 (ID: 4)
INSERT INTO lessons (id, title, video_url, content, module_id)
VALUES
    (4, '1.1 La Première Loi de Newton', 'https://www.youtube.com/watch?v=some_video_id', 'Contenu textuel sur l''inertie...', 4),
    (5, '1.2 La Deuxième Loi de Newton (F=ma)', 'https://www.youtube.com/watch?v=some_video_id', 'Contenu textuel...', 4);


-- =================================================================
-- LIVE SESSIONS
-- =================================================================
-- A past session (date is in the past)
INSERT INTO live_sessions (id, title, description, session_time, instructor, recording_url)
VALUES (1, 'Révision de Chimie Organique', 'Une session de révision complète sur la nomenclature et les réactions.', '2024-05-20 18:00:00', 'Dr. Fatima Zahra', 'https://www.youtube.com/watch?v=past_recording_id')
    ON CONFLICT (id) DO NOTHING;

-- An upcoming session (date is in the future)
INSERT INTO live_sessions (id, title, description, session_time, instructor, recording_url)
VALUES (2, 'Masterclass sur les Intégrales', 'Préparez-vous pour vos examens avec cette session approfondie.', '2029-10-25 19:00:00', 'Mr. Hassan El Amrani', null)
    ON CONFLICT (id) DO NOTHING;


-- =================================================================
-- Update sequences to avoid primary key conflicts with new insertions
-- =================================================================
-- This is important to prevent "duplicate key value violates unique constraint" errors
-- when you try to create new items via the API after the script runs.
SELECT setval('users_id_seq', (SELECT MAX(id) FROM users), true);
SELECT setval('courses_id_seq', (SELECT MAX(id) FROM courses), true);
SELECT setval('modules_id_seq', (SELECT MAX(id) FROM modules), true);
SELECT setval('lessons_id_seq', (SELECT MAX(id) FROM lessons), true);
SELECT setval('live_sessions_id_seq', (SELECT MAX(id) FROM live_sessions), true);