-- CREATE SCHEMA messagerelay;
-- CREATE USER 'messagerelay'@'localhost' IDENTIFIED BY 'messagerelayPW!';
-- GRANT ALL PRIVILEGES ON * . * TO 'messagerelay'@'%';

CREATE TABLE IF NOT EXISTS `users` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Primary Key',
  `first_name` varchar(45) NOT NULL COMMENT 'First name of user',
  `last_name` varchar(45) NOT NULL COMMENT 'Last name of user',
  `email` varchar(100) NOT NULL COMMENT 'Email of user. This is used to see if user has already been created.',
  `username` varchar(30) NOT NULL COMMENT 'User name or message handle',
  `active` tinyint(1) NOT NULL DEFAULT '0' COMMENT 'Flag to determine if user is logged in',
  PRIMARY KEY (`id`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;
