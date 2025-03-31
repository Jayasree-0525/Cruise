-- Cruise + Customer = Survey
-- Survey + Question = Responses


-- Staff
INSERT INTO staff (id, firstName, lastName, email, position) VALUES (1, 'Sarah', 'Wong', 'sarah.wong@royalcaribbean.com', 'Junior Assistant Cruise Director');
INSERT INTO staff (id, firstName, lastName, email, position) VALUES (2, 'Bhoovi', 'Handa', 'bhoovi.handa@royalcaribbean.com', 'Security Officer');
INSERT INTO staff (id, firstName, lastName, email, position) VALUES (3, 'Dhyani', 'Thakar', 'dhyani.thakar@royalcaribbean.com', 'Performer');
INSERT INTO staff (id, firstName, lastName, email, position) VALUES (4, 'Emma', 'Gillott', 'emma.gillott@royalcaribbean.com', 'Food and Beverage Manager');
INSERT INTO staff (id, firstName, lastName, email, position) VALUES (5, 'Jayasree', 'Sreeram', 'jayasree.sreeram@royalcaribbean.com', 'IT Officer');


-- Customer
INSERT INTO customer (id, firstName, lastName, email, age, gender) VALUES (6, 'Nikita', 'Shashidhar', 'nikita.shashidhar@email.com', 20, 'female');
INSERT INTO customer (id, firstName, lastName, email, age, gender) VALUES (7, 'Rangajah', 'Manoharan', 'rangajah.manoharan@email.com', 25, 'female');
INSERT INTO customer (id, firstName, lastName, email, age, gender) VALUES (8, 'Taleen', 'Kutob', 'taleen.kutob@email.com', 30, 'female');
INSERT INTO customer (id, firstName, lastName, email, age, gender) VALUES (9, 'Liam', 'Brown', 'liam.brown@email.com', 50, 'male');
INSERT INTO customer (id, firstName, lastName, email, age, gender) VALUES (10, 'James', 'Smith', 'james.smith@email.com', 40, 'male');
INSERT INTO customer (id, firstName, lastName, email, age, gender) VALUES (11, 'Evelyn', 'King', 'evelyn.king@email.com', 65, 'female');
INSERT INTO customer (id, firstName, lastName, email, age, gender) VALUES (12, 'Anna', 'Lee', 'anna.lee@email.com', 32, 'female');
INSERT INTO customer (id, firstName, lastName, email, age, gender) VALUES (13, 'Harold', 'Baker', 'harold.baker@email.com', 45, 'male');
INSERT INTO customer (id, firstName, lastName, email, age, gender) VALUES (14, 'Lily', 'Woods', 'lily.woods@email.com', 35, 'female');
INSERT INTO customer (id, firstName, lastName, email, age, gender) VALUES (15, 'Thomas', 'Queen', 'thomas.queen@email.com', 65, 'male');


-- Cruise
INSERT INTO cruise (cruiseId, duration, cruiseName, destination) VALUES (1, 7, 'Adventure of the Seas- Greece', 'Greece');
INSERT INTO cruise (cruiseId, duration, cruiseName, destination) VALUES (2, 7, 'Enchantment of the Seas', 'Italy');
INSERT INTO cruise (cruiseId, duration, cruiseName, destination) VALUES (3, 10, 'Freedom of the Seas- Barbados', 'Barbados');
INSERT INTO cruise (cruiseId, duration, cruiseName, destination) VALUES (4, 10, 'Adventure of the Seas- Bahamas', 'Bahamas');
INSERT INTO cruise (cruiseId, duration, cruiseName, destination) VALUES (5, 14, 'Harmony of the Seas', 'Jamaica');
INSERT INTO cruise (cruiseId, duration, cruiseName, destination) VALUES (6, 14, 'Oasis of the Seas', 'Bermuda');
INSERT INTO cruise (cruiseId, duration, cruiseName, destination) VALUES (7, 21, 'Legend of the Seas- Italy', 'Italy');
INSERT INTO cruise (cruiseId, duration, cruiseName, destination) VALUES (8, 21, 'Freedom of the Seas- Thailand', 'Thailand');
INSERT INTO cruise (cruiseId, duration, cruiseName, destination) VALUES (9, 30, 'Allure of the Seas', 'Spain');
INSERT INTO cruise (cruiseId, duration, cruiseName, destination) VALUES (10, 30, 'Legend of the Seas- Greece', 'Greece');


-- Questions, note: no need to have questions for cruiseId, customerId in the survey since those will manually be inputted into the data.sql file
INSERT INTO questions (questionId, question, type) VALUES (1, 'How would you rate your experience with the water slide on a scale of 1 to 5?', 'quantitative');
INSERT INTO questions (questionId, question, type) VALUES (2, 'Please elaborate on your rating for the water slide.', 'qualitative');
INSERT INTO questions (questionId, question, type) VALUES (3, 'How would you rate the quality of the food on board on a scale of 1 to 5?', 'quantitative');
INSERT INTO questions (questionId, question, type) VALUES (4, 'Please elaborate on your rating for the food quality.', 'qualitative');
INSERT INTO questions (questionId, question, type) VALUES (5, 'Do you have any additional feedback?', 'qualitative');


-- Survey, add dateOfSurvey as a string
INSERT INTO survey (surveyId, customerId, cruiseId, dateOfSurvey) VALUES (1, 6, 1, 'March 25, 2025');
INSERT INTO survey (surveyId, customerId, cruiseId, dateOfSurvey) VALUES (2, 7, 2, 'March 20, 2025');
INSERT INTO survey (surveyId, customerId, cruiseId, dateOfSurvey) VALUES (3, 8, 3, 'March 19, 2025');
INSERT INTO survey (surveyId, customerId, cruiseId, dateOfSurvey) VALUES (4, 9, 4, 'March 15, 2025');
INSERT INTO survey (surveyId, customerId, cruiseId, dateOfSurvey) VALUES (5, 10, 5, 'March 7, 2025');
INSERT INTO survey (surveyId, customerId, cruiseId, dateOfSurvey) VALUES (6, 11, 6, 'March 5, 2025');
INSERT INTO survey (surveyId, customerId, cruiseId, dateOfSurvey) VALUES (7, 12, 7, 'March 2, 2025');
INSERT INTO survey (surveyId, customerId, cruiseId, dateOfSurvey) VALUES (8, 13, 8, 'March 21, 2025');
INSERT INTO survey (surveyId, customerId, cruiseId, dateOfSurvey) VALUES (9, 14, 9, 'March 26, 2025');
INSERT INTO survey (surveyId, customerId, cruiseId, dateOfSurvey) VALUES (10, 15, 10, 'March 30, 2025');
INSERT INTO survey (surveyId, customerId, cruiseId, dateOfSurvey) VALUES (11, 6, 2, 'March 30, 2025');
INSERT INTO survey (surveyId, customerId, cruiseId, dateOfSurvey) VALUES (12, 6, 3, 'March 11, 2025');


-- Responses
INSERT INTO responses (surveyId, questionId, response) VALUES (1, 1, '1');
INSERT INTO responses (surveyId, questionId, response) VALUES (1, 2, 'Terrible experience, frankly dangerous');
INSERT INTO responses (surveyId, questionId, response) VALUES (1, 3, '4');
INSERT INTO responses (surveyId, questionId, response) VALUES (1, 4, 'Pretty good!');
INSERT INTO responses (surveyId, questionId, response) VALUES (1, 5, 'Decent experience');

INSERT INTO responses (surveyId, questionId, response) VALUES (2, 1, '2');
INSERT INTO responses (surveyId, questionId, response) VALUES (2, 2, 'Lousy, way too noisy');
INSERT INTO responses (surveyId, questionId, response) VALUES (2, 3, '2');
INSERT INTO responses (surveyId, questionId, response) VALUES (2, 4, 'Bad, some of the food looked close to spoiling');
INSERT INTO responses (surveyId, questionId, response) VALUES (2, 5, 'My experience was pretty bad');

INSERT INTO responses (surveyId, questionId, response) VALUES (3, 1, '5');
INSERT INTO responses (surveyId, questionId, response) VALUES (3, 2, 'Amazing, had so much fun, overall a good time!');
INSERT INTO responses (surveyId, questionId, response) VALUES (3, 3, '3');
INSERT INTO responses (surveyId, questionId, response) VALUES (3, 4, 'Okay, overall decent');
INSERT INTO responses (surveyId, questionId, response) VALUES (3, 5, 'Great experience! A good time');

INSERT INTO responses (surveyId, questionId, response) VALUES (4, 1, '3');
INSERT INTO responses (surveyId, questionId, response) VALUES (4, 2, 'Okay, overall decent');
INSERT INTO responses (surveyId, questionId, response) VALUES (4, 3, '5');
INSERT INTO responses (surveyId, questionId, response) VALUES (4, 4, 'Delicious!');
INSERT INTO responses (surveyId, questionId, response) VALUES (4, 5, 'Decent experience');

INSERT INTO responses (surveyId, questionId, response) VALUES (11, 1, '3');
INSERT INTO responses (surveyId, questionId, response) VALUES (11, 2, 'Fine');
INSERT INTO responses (surveyId, questionId, response) VALUES (11, 3, '3');
INSERT INTO responses (surveyId, questionId, response) VALUES (11, 4, 'Okay');
INSERT INTO responses (surveyId, questionId, response) VALUES (11, 5, 'Overall decent');

INSERT INTO responses (surveyId, questionId, response) VALUES (12, 1, '4');
INSERT INTO responses (surveyId, questionId, response) VALUES (12, 2, 'Overall good');
INSERT INTO responses (surveyId, questionId, response) VALUES (12, 3, '5');
INSERT INTO responses (surveyId, questionId, response) VALUES (12, 4, 'Amazing!');
INSERT INTO responses (surveyId, questionId, response) VALUES (12, 5, 'Excellent experience');


-- Insert Users
INSERT INTO users (username, password, isAdmin) VALUES ('alice', 'abc', false);
INSERT INTO users (username, password, isAdmin) VALUES ('bob', 'p123', false);
INSERT INTO users (username, password, isAdmin) VALUES ('aaa', '123', false);
INSERT INTO users (username, password, isAdmin) VALUES ('abc', '123', true);