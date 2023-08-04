use `FlashMemorizer`;

-- users table
DROP TABLE IF EXISTS `FlashMemorizer`.`users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `FlashMemorizer`.`users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `full_name` varchar(255) DEFAULT NULL,
  `last_login` datetime(6) NOT NULL,
  `pass_hash` varchar(255) DEFAULT NULL,
  `registration` datetime(6) NOT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `FlashMemorizer`.`users` (`email`, `full_name`, `last_login`, `pass_hash`, `registration`, `username`)
VALUES
  ('john@example.com', 'John Doe', '2023-07-14 10:00:00.000000', 'hash1', '2023-07-01 08:00:00.000000', 'johndoe'),
  ('jane@example.com', 'Jane Smith', '2023-07-14 11:30:00.000000', 'hash2', '2023-07-02 09:15:00.000000', 'janesmith'),
  ('alice@example.com', 'Alice Johnson', '2023-07-14 12:45:00.000000', 'hash3', '2023-07-03 10:30:00.000000', 'alicejohnson'),
  ('bob@example.com', 'Bob Anderson', '2023-07-14 13:15:00.000000', 'hash4', '2023-07-04 11:45:00.000000', 'bobanderson'),
  ('emma@example.com', 'Emma Davis', '2023-07-14 14:30:00.000000', 'hash5', '2023-07-05 12:00:00.000000', 'emmadavis'),
  ('alex@example.com', 'Alex Wilson', '2023-07-14 15:45:00.000000', 'hash6', '2023-07-06 13:15:00.000000', 'alexwilson'),
  ('sarah@example.com', 'Sarah Thompson', '2023-07-14 16:30:00.000000', 'hash7', '2023-07-07 14:30:00.000000', 'sarahthompson'),
  ('michael@example.com', 'Michael Brown', '2023-07-14 17:15:00.000000', 'hash8', '2023-07-08 15:45:00.000000', 'michaelbrown'),
  ('laura@example.com', 'Laura Miller', '2023-07-14 18:00:00.000000', 'hash9', '2023-07-09 16:00:00.000000', 'lauramiller'),
  ('david@example.com', 'David Wilson', '2023-07-14 19:30:00.000000', 'hash10', '2023-07-10 17:30:00.000000', 'davidwilson');


-- decks table
DROP TABLE IF EXISTS `FlashMemorizer`.`decks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `decks` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `creation` datetime(6) NOT NULL,
  `deck_desc` varchar(255) DEFAULT NULL,
  `last_modified` datetime(6) NOT NULL,
  `deck_name` varchar(255) DEFAULT NULL,
  `user_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKj0ey511pphfxbxbh8ri1616uv` (`user_id`),
  CONSTRAINT `FKj0ey511pphfxbxbh8ri1616uv` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `FlashMemorizer`.`decks` (`creation`, `deck_desc`, `last_modified`, `deck_name`, `user_id`)
VALUES
  ('2023-07-14 10:00:00.000000', 'This is the description for Deck 1. It contains cards related to topic A.', '2023-07-14 10:00:00.000000', 'Deck 1: Topic A', 1),
  ('2023-07-14 11:30:00.000000', 'Deck 2 is focused on topic B. It includes various cards covering different aspects of the topic.', '2023-07-14 11:30:00.000000', 'Deck 2: Topic B', 2),
  ('2023-07-14 12:45:00.000000', 'Deck 3 is designed for advanced users. It consists of challenging cards to test your knowledge.', '2023-07-14 12:45:00.000000', 'Deck 3: Advanced', 3),
  ('2023-07-14 13:15:00.000000', 'Deck 4 is a collection of cards related to topic C. It covers both theoretical concepts and practical examples.', '2023-07-14 13:15:00.000000', 'Deck 4: Topic C', 4),
  ('2023-07-14 14:30:00.000000', 'Deck 5 includes cards with exercises and quizzes to help you practice and reinforce your knowledge.', '2023-07-14 14:30:00.000000', 'Deck 5: Practice', 5),
  ('2023-07-14 15:45:00.000000', 'Deck 6 focuses on real-world scenarios. It presents case studies and problem-solving challenges.', '2023-07-14 15:45:00.000000', 'Deck 6: Real-World Scenarios', 6),
  ('2023-07-14 16:30:00.000000', 'Deck 7 contains cards related to topic D. It explores different aspects and applications of the subject.', '2023-07-14 16:30:00.000000', 'Deck 7: Topic D', 7),
  ('2023-07-14 17:15:00.000000', 'Deck 8 is suitable for beginners. It provides a comprehensive introduction to the fundamentals of the subject.', '2023-07-14 17:15:00.000000', 'Deck 8: Beginners Guide', 8),
  ('2023-07-14 18:00:00.000000', 'Deck 9 is a curated collection of advanced concepts and techniques. It is intended for experienced users.', '2023-07-14 18:00:00.000000', 'Deck 9: Advanced Concepts', 9),
  ('2023-07-14 19:30:00.000000', 'Deck 10 covers a wide range of topics. It includes cards from different domains and areas of study.', '2023-07-14 19:30:00.000000', 'Deck 10: General Knowledge', 10);

-- cards table
DROP TABLE IF EXISTS `FlashMemorizer`.`cards`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `FlashMemorizer`.`cards` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `creation` datetime(6) NOT NULL,
  `deck_id` bigint DEFAULT NULL,
  `card_desc` varchar(255) DEFAULT NULL,
  `last_modified` datetime(6) NOT NULL,
  `card_term` varchar(255) DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKi7eg9pr1nooc66s02ht1h3ew8` (`deck_id`),
  KEY `FKcmanafgwbibfijy2o5isfk3d5` (`user_id`),
  CONSTRAINT `FKcmanafgwbibfijy2o5isfk3d5` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKi7eg9pr1nooc66s02ht1h3ew8` FOREIGN KEY (`deck_id`) REFERENCES `decks` (`id`)
  CONSTRAINT `FKi7eg9pr1nooc66s02ht1h3ew8` FOREIGN KEY (`deck_id`) REFERENCES `decks` (`id`)

INSERT INTO `FlashMemorizer`.`cards` (`creation`, `deck_id`, `card_desc`, `last_modified`, `card_term`, `user_id`)
VALUES
  ('2023-07-14 10:00:00.000000', 1, 'Card 1 Description', '2023-07-14 10:00:00.000000', 'Card 1 Term', 1),
  ('2023-07-14 11:30:00.000000', 2, 'Card 2 Description', '2023-07-14 11:30:00.000000', 'Card 2 Term', 2),
  ('2023-07-14 12:45:00.000000', 3, 'Card 3 Description', '2023-07-14 12:45:00.000000', 'Card 3 Term', 3),
  ('2023-07-14 13:15:00.000000', 4, 'Card 4 Description', '2023-07-14 13:15:00.000000', 'Card 4 Term', 4),
  ('2023-07-14 14:30:00.000000', 5, 'Card 5 Description', '2023-07-14 14:30:00.000000', 'Card 5 Term', 5),
  ('2023-07-14 15:45:00.000000', 6, 'Card 6 Description', '2023-07-14 15:45:00.000000', 'Card 6 Term', 6),
  ('2023-07-14 16:30:00.000000', 7, 'Card 7 Description', '2023-07-14 16:30:00.000000', 'Card 7 Term', 7),
  ('2023-07-14 17:15:00.000000', 8, 'Card 8 Description', '2023-07-14 17:15:00.000000', 'Card 8 Term', 8),
  ('2023-07-14 18:00:00.000000', 9, 'Card 9 Description', '2023-07-14 18:00:00.000000', 'Card 9 Term', 9),
  ('2023-07-14 19:30:00.000000', 10, 'Card 10 Description', '2023-07-14 19:30:00.000000', 'Card 10 Term', 10);

-- user_card table
DROP TABLE IF EXISTS `FlashMemorizer`.`user_card`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `FlashMemorizer`.`user_card` (
  `card_id` bigint NOT NULL,
  `user_id` bigint NOT NULL,
  `next_interval` bigint NOT NULL,
  `last_review` datetime(6) NOT NULL,
  `rating` enum('ABOVE_AVERAGE','AVERAGE','BELOW_AVERAGE','HIGH','LOW') DEFAULT NULL,
  `review_count` bigint NOT NULL,
  PRIMARY KEY (`card_id`,`user_id`),
  KEY `FK2uknqrtp1axn9cwn6st1pvwkd` (`user_id`),
  CONSTRAINT `FK2uknqrtp1axn9cwn6st1pvwkd` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKnyxteoyap9qyregw2ou3olvav` FOREIGN KEY (`card_id`) REFERENCES `cards` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `FlashMemorizer`.`user_card` (`card_id`, `user_id`, `next_interval`, `last_review`, `rating`, `review_count`)
VALUES
  (1, 1, 1, '2023-07-14 10:00:00.000000', 'ABOVE_AVERAGE', 1),
  (2, 2, 2, '2023-07-14 11:30:00.000000', 'AVERAGE', 2),
  (3, 3, 3, '2023-07-14 12:45:00.000000', 'BELOW_AVERAGE', 3),
  (4, 4, 4, '2023-07-14 13:15:00.000000', 'HIGH', 4),
  (5, 5, 5, '2023-07-14 14:30:00.000000', 'LOW', 5),
  (6, 6, 6, '2023-07-14 15:45:00.000000', 'ABOVE_AVERAGE', 6),
  (7, 7, 7, '2023-07-14 16:30:00.000000', 'AVERAGE', 7),
  (8, 8, 8, '2023-07-14 17:15:00.000000', 'BELOW_AVERAGE', 8),
  (9, 9, 9, '2023-07-14 18:00:00.000000', 'HIGH', 9),
  (10, 10, 10, '2023-07-14 19:30:00.000000', 'LOW', 10);