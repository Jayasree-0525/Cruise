INSERT INTO question(questionId, question) VALUES (1, 'How old are you?');
--INSERT INTO cruiseLine(cruiseId, duration, cruiseName, cruiseFeatures) VALUES (1, 5, 'Cruise One', 'pool');
INSERT INTO cruiseLine(cruiseId, duration, cruiseName) VALUES (1, 5, 'Cruise One');
INSERT INTO customer(id, age, gender) VALUES (1, 25, 'Female');

INSERT INTO survey (surveyId, cruiseId, customerId, questionId, question, qualitativeAnswer, quantitativeAnswer) VALUES(101, 1, 1, 1, 'How was your experience overall, on a scale of 1 to 5 from worst to best?', '', 5);
INSERT INTO survey (surveyId, cruiseId, customerId, questionId, question, qualitativeAnswer, quantitativeAnswer) VALUES(102, 1, 1, 2, 'How old are you?', '', 37);
INSERT INTO survey (surveyId, cruiseId, customerId, questionId, question, qualitativeAnswer, quantitativeAnswer) VALUES(103, 1, 1, 3, 'Do you have any concerns or suggestions?', 'Yes', NULL);