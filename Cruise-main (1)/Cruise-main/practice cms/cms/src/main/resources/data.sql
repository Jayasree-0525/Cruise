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
INSERT INTO survey (surveyId, customerId, cruiseId, dateOfSurvey) VALUES (11, 6, 10, 'March 30, 2025');
INSERT INTO survey (surveyId, customerId, cruiseId, dateOfSurvey) VALUES (12, 7, 9, 'March 11, 2025');
INSERT INTO survey (surveyId, customerId, cruiseId, dateOfSurvey) VALUES (13, 8, 8, 'March 25, 2025');
INSERT INTO survey (surveyId, customerId, cruiseId, dateOfSurvey) VALUES (14, 9, 7, 'March 20, 2025');
INSERT INTO survey (surveyId, customerId, cruiseId, dateOfSurvey) VALUES (15, 10, 6, 'March 19, 2025');
INSERT INTO survey (surveyId, customerId, cruiseId, dateOfSurvey) VALUES (16, 11, 5, 'March 15, 2025');
INSERT INTO survey (surveyId, customerId, cruiseId, dateOfSurvey) VALUES (17, 12, 4, 'March 7, 2025');
INSERT INTO survey (surveyId, customerId, cruiseId, dateOfSurvey) VALUES (18, 13, 3, 'March 5, 2025');
INSERT INTO survey (surveyId, customerId, cruiseId, dateOfSurvey) VALUES (19, 14, 2, 'March 2, 2025');
INSERT INTO survey (surveyId, customerId, cruiseId, dateOfSurvey) VALUES (20, 15, 1, 'March 21, 2025');
INSERT INTO survey (surveyId, customerId, cruiseId, dateOfSurvey) VALUES (21, 6, 2, 'March 26, 2025');
INSERT INTO survey (surveyId, customerId, cruiseId, dateOfSurvey) VALUES (22, 7, 3, 'March 30, 2025');
INSERT INTO survey (surveyId, customerId, cruiseId, dateOfSurvey) VALUES (23, 8, 4, 'March 30, 2025');
INSERT INTO survey (surveyId, customerId, cruiseId, dateOfSurvey) VALUES (24, 9, 5, 'March 11, 2025');
INSERT INTO survey (surveyId, customerId, cruiseId, dateOfSurvey) VALUES (25, 10, 6, 'March 11, 2025');


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

INSERT INTO responses (surveyId, questionId, response) VALUES (5, 1, '4');
INSERT INTO responses (surveyId, questionId, response) VALUES (5, 2, 'Fun, but the wait time was a bit long');
INSERT INTO responses (surveyId, questionId, response) VALUES (5, 3, '3');
INSERT INTO responses (surveyId, questionId, response) VALUES (5, 4, 'Average, nothing too special but still decent');
INSERT INTO responses (surveyId, questionId, response) VALUES (5, 5, 'Overall a good experience, but I would like more entertainment options');

INSERT INTO responses (surveyId, questionId, response) VALUES (6, 1, '5');
INSERT INTO responses (surveyId, questionId, response) VALUES (6, 2, 'Amazing! I loved every second');
INSERT INTO responses (surveyId, questionId, response) VALUES (6, 3, '4');
INSERT INTO responses (surveyId, questionId, response) VALUES (6, 4, 'Delicious, especially the pasta. Would definitely recommend');
INSERT INTO responses (surveyId, questionId, response) VALUES (6, 5, 'Great experience, would come again!');

INSERT INTO responses (surveyId, questionId, response) VALUES (7, 1, '3');
INSERT INTO responses (surveyId, questionId, response) VALUES (7, 2, 'The slide was okay, but I expected more excitement');
INSERT INTO responses (surveyId, questionId, response) VALUES (7, 3, '2');
INSERT INTO responses (surveyId, questionId, response) VALUES (7, 4, 'The food was not great, it was cold and tasteless');
INSERT INTO responses (surveyId, questionId, response) VALUES (7, 5, 'I enjoyed my time, but I hope the quality improves in the future');

INSERT INTO responses (surveyId, questionId, response) VALUES (8, 1, '4');
INSERT INTO responses (surveyId, questionId, response) VALUES (8, 2, 'The slide was great, but the water temperature could have been warmer');
INSERT INTO responses (surveyId, questionId, response) VALUES (8, 3, '5');
INSERT INTO responses (surveyId, questionId, response) VALUES (8, 4, 'Fantastic!!! everything was fresh and flavourful');
INSERT INTO responses (surveyId, questionId, response) VALUES (8, 5, 'Had a wonderful time - will definitely come back next year');

INSERT INTO responses (surveyId, questionId, response) VALUES (9, 1, '2');
INSERT INTO responses (surveyId, questionId, response) VALUES (9, 2, 'Too slow for my taste, I was expecting more thrill');
INSERT INTO responses (surveyId, questionId, response) VALUES (9, 3, '3');
INSERT INTO responses (surveyId, questionId, response) VALUES (9, 4, 'The food was okay, but the portions were small');
INSERT INTO responses (surveyId, questionId, response) VALUES (9, 5, 'Overall it was a decent experience, but there’s room for improvement');

INSERT INTO responses (surveyId, questionId, response) VALUES (10, 1, '5');
INSERT INTO responses (surveyId, questionId, response) VALUES (10, 2, 'Fantastic! Loved the speed and twists');
INSERT INTO responses (surveyId, questionId, response) VALUES (10, 3, '4');
INSERT INTO responses (surveyId, questionId, response) VALUES (10, 4, 'The food was good, especially the grilled fish. A bit pricey');
INSERT INTO responses (surveyId, questionId, response) VALUES (10, 5, 'I had an amazing time and will definitely come back for more fun!');

INSERT INTO responses (surveyId, questionId, response) VALUES (11, 1, '3');
INSERT INTO responses (surveyId, questionId, response) VALUES (11, 2, 'Fun, but I feel like it could be longer');
INSERT INTO responses (surveyId, questionId, response) VALUES (11, 3, '2');
INSERT INTO responses (surveyId, questionId, response) VALUES (11, 4, 'Not great. The salad was wilted and the pizza was cold.');
INSERT INTO responses (surveyId, questionId, response) VALUES (11, 5, 'The experience was okay, but the food quality REALLY needs to improve');

INSERT INTO responses (surveyId, questionId, response) VALUES (12, 1, '4');
INSERT INTO responses (surveyId, questionId, response) VALUES (12, 2, 'The slide was fun, but the line was way too long');
INSERT INTO responses (surveyId, questionId, response) VALUES (12, 3, '1');
INSERT INTO responses (surveyId, questionId, response) VALUES (12, 4, 'The food was disappointing. The pasta was overcooked and lacked flavour');
INSERT INTO responses (surveyId, questionId, response) VALUES (12, 5, 'The experience could have been better if the food quality was better. Also, consider more seating options at the pool.');

INSERT INTO responses (surveyId, questionId, response) VALUES (13, 1, '2');
INSERT INTO responses (surveyId, questionId, response) VALUES (13, 2, 'The slide was too slow and lacked excitement - I was hoping for more speed');
INSERT INTO responses (surveyId, questionId, response) VALUES (13, 3, '3');
INSERT INTO responses (surveyId, questionId, response) VALUES (13, 4, 'Decent food, but the portions were small and the variety was limited.');
INSERT INTO responses (surveyId, questionId, response) VALUES (13, 5, 'It was an okay experience, but I felt like there could have been more to do. More entertainment options would be nice.');

INSERT INTO responses (surveyId, questionId, response) VALUES (14, 1, '1');
INSERT INTO responses (surveyId, questionId, response) VALUES (14, 2, 'Huge letdown. SO slow');
INSERT INTO responses (surveyId, questionId, response) VALUES (14, 3, '5');
INSERT INTO responses (surveyId, questionId, response) VALUES (14, 4, 'Incredible! Best I’ve had on any cruise - especially the BBQ ribs.');
INSERT INTO responses (surveyId, questionId, response) VALUES (14, 5, 'I won’t return for the slide, but I would definitely come back for the food. Maybe focus more on the quality of the amenities');

INSERT INTO responses (surveyId, questionId, response) VALUES (15, 1, '3');
INSERT INTO responses (surveyId, questionId, response) VALUES (15, 2, 'The slide was ok, but not as thrilling as I hoped. could go faster');
INSERT INTO responses (surveyId, questionId, response) VALUES (15, 3, '4');
INSERT INTO responses (surveyId, questionId, response) VALUES (15, 4, 'The food was pretty good. I liked the variety, but the dessert was a bit too sweet for my taste.');
INSERT INTO responses (surveyId, questionId, response) VALUES (15, 5, 'Overall a decent experience. Wasn’t perfect, but I’d consider coming back if there were new attractions');

INSERT INTO responses (surveyId, questionId, response) VALUES (16, 1, '5');
INSERT INTO responses (surveyId, questionId, response) VALUES (16, 2, 'The slide was fantastic! Fast, fun, and definitely a highlight of my day!');
INSERT INTO responses (surveyId, questionId, response) VALUES (16, 3, '2');
INSERT INTO responses (surveyId, questionId, response) VALUES (16, 4, 'The food was a major disappointment which is SO sad, the pizza was soggy and the fries were undercooked');
INSERT INTO responses (surveyId, questionId, response) VALUES (16, 5, 'I enjoyed the experience overall, but the food definitely needs improvement');

INSERT INTO responses (surveyId, questionId, response) VALUES (17, 1, '4');
INSERT INTO responses (surveyId, questionId, response) VALUES (17, 2, 'The slide was great though it could have been more intense. I had fun!');
INSERT INTO responses (surveyId, questionId, response) VALUES (17, 3, '3');
INSERT INTO responses (surveyId, questionId, response) VALUES (17, 4, 'Decent, but there wasn’t much variety - more healthy options would be good');
INSERT INTO responses (surveyId, questionId, response) VALUES (17, 5, 'It was a good experience, but I think there should be more activities for all ages');

INSERT INTO responses (surveyId, questionId, response) VALUES (18, 1, '2');
INSERT INTO responses (surveyId, questionId, response) VALUES (18, 2, 'What a DISAPPOINTMENT! It was so slow and felt outdated');
INSERT INTO responses (surveyId, questionId, response) VALUES (18, 3, '1');
INSERT INTO responses (surveyId, questionId, response) VALUES (18, 4, 'The food was AWFUL!!! The burger was dry, and the salad was wilted - it needs a lot of improvement.');
INSERT INTO responses (surveyId, questionId, response) VALUES (18, 5, 'I won’t be coming back unless there are SIGNIFICANT changes. Both the slide and the food were underwhelming.');

INSERT INTO responses (surveyId, questionId, response) VALUES (19, 1, '4');
INSERT INTO responses (surveyId, questionId, response) VALUES (19, 2, 'VERY fun!');
INSERT INTO responses (surveyId, questionId, response) VALUES (19, 3, '4');
INSERT INTO responses (surveyId, questionId, response) VALUES (19, 4, 'The food was great! I LOVED the variety of fresh fruit');
INSERT INTO responses (surveyId, questionId, response) VALUES (19, 5, 'Overall a great experience! I’ll definitely come back next year, especially for the food');

INSERT INTO responses (surveyId, questionId, response) VALUES (20, 1, '3');
INSERT INTO responses (surveyId, questionId, response) VALUES (20, 2, 'The slide was fun, but I expected more excitement');
INSERT INTO responses (surveyId, questionId, response) VALUES (20, 3, '3');
INSERT INTO responses (surveyId, questionId, response) VALUES (20, 4, 'OK, but nothing special. The burger was fine, but the fries were soggy');
INSERT INTO responses (surveyId, questionId, response) VALUES (20, 5, 'It was a decent visit, but I would love to see some improvements in the food options');

INSERT INTO responses (surveyId, questionId, response) VALUES (21, 1, '5');
INSERT INTO responses (surveyId, questionId, response) VALUES (21, 2, 'The slide was fantastic! - exactly what I wanted!');
INSERT INTO responses (surveyId, questionId, response) VALUES (21, 3, '4');
INSERT INTO responses (surveyId, questionId, response) VALUES (21, 4, 'Delicious, I liked the sandwiches and the dessert options were also nice');
INSERT INTO responses (surveyId, questionId, response) VALUES (21, 5, 'Great overall! You should add more seating areas near the slides, but everything else was PERFECT');

INSERT INTO responses (surveyId, questionId, response) VALUES (22, 1, '1');
INSERT INTO responses (surveyId, questionId, response) VALUES (22, 2, 'Terrible and slow - what a waste of time');
INSERT INTO responses (surveyId, questionId, response) VALUES (22, 3, '2');
INSERT INTO responses (surveyId, questionId, response) VALUES (22, 4, 'So disappointing - the pizza was cold and bland, and the salad didn’t seem fresh');
INSERT INTO responses (surveyId, questionId, response) VALUES (22, 5, 'Overall, a very frustrating experience');

INSERT INTO responses (surveyId, questionId, response) VALUES (23, 1, '4');
INSERT INTO responses (surveyId, questionId, response) VALUES (23, 2, 'a LOT of fun, but could have been faster');
INSERT INTO responses (surveyId, questionId, response) VALUES (23, 3, '4');
INSERT INTO responses (surveyId, questionId, response) VALUES (23, 4, 'Really good, especially the grilled vegetables, but the portions were smaller than expected');
INSERT INTO responses (surveyId, questionId, response) VALUES (23, 5, 'Overall, it was a great experience! I’d love to see more slides to enjoy');

INSERT INTO responses (surveyId, questionId, response) VALUES (24, 1, '3');
INSERT INTO responses (surveyId, questionId, response) VALUES (24, 2, 'okay');
INSERT INTO responses (surveyId, questionId, response) VALUES (24, 3, '5');
INSERT INTO responses (surveyId, questionId, response) VALUES (24, 4, 'Excellent! The burger was tasty, and the fries were so crispy!');
INSERT INTO responses (surveyId, questionId, response) VALUES (24, 5, 'Great experience overall! I would only come back for the food tho');

INSERT INTO responses (surveyId, questionId, response) VALUES (25, 1, '2');
INSERT INTO responses (surveyId, questionId, response) VALUES (25, 2, 'Too short and didn’t have enough thrills for me');
INSERT INTO responses (surveyId, questionId, response) VALUES (25, 3, '3');
INSERT INTO responses (surveyId, questionId, response) VALUES (25, 4, 'Decent, but it could have been fresher. The salad was a bit soggy, which is gross');
INSERT INTO responses (surveyId, questionId, response) VALUES (25, 5, 'The experience was okay overall');


-- Insert Users
INSERT INTO users (username, password, isAdmin) VALUES ('alice', 'abc', false);
INSERT INTO users (username, password, isAdmin) VALUES ('bob', 'p123', false);
INSERT INTO users (username, password, isAdmin) VALUES ('aaa', '123', false);
INSERT INTO users (username, password, isAdmin) VALUES ('abc', '123', true);