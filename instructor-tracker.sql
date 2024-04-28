CREATE DATABASE  IF NOT EXISTS `instructor_tracker`;
USE `instructor_tracker`;

drop table if exists `review`;
drop table if exists `course_student`;
drop table if exists `student`;
drop table if exists `review`;
drop table if exists `course`;
drop table if exists `instructor`;
drop table if exists `instructor_detail`;

drop table if exists `users`;
drop table if exists `roles`;
drop table if exists `users_roles`;


CREATE TABLE `users` (
	`id` 	 INT NOT NULL AUTO_INCREMENT,
    `username`   VARCHAR(50) NOT NULL,
    `password`	 VARCHAR(50) NOT NULL,
    `enabled`    varchar(1) NOT NULL,
    `first_name` VARCHAR(50) NOT NULL,
    `last_name`  VARCHAR(50) DEFAULT NULL,
	`email`		 VARCHAR(50) DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE `roles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE `users_roles` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`, `role_id`),
  FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`),
  FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE `instructor_detail` (
  `id` 		        INT NOT NULL AUTO_INCREMENT,
  `youtube_channel` VARCHAR(145) DEFAULT NULL,
  `hobby` 		    VARCHAR(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE `instructor` (
  `id` 					 INT NOT NULL AUTO_INCREMENT,
  `instructor_detail_id` INT NOT NULL,
  `user_id`				 INT NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`instructor_detail_id`) REFERENCES `instructor_detail` (`id`),
  FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE `course` (
  `id` 			  INT NOT NULL AUTO_INCREMENT,
  `title` 		  VARCHAR(150) NOT NULL,
  `description`   VARCHAR(1000),
  `price` 		  INT NOT NULL,
  `instructor_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`instructor_id`) REFERENCES `instructor` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE `student` (
	`id` 	  INT NOT NULL AUTO_INCREMENT,
	`user_id` INT NOT NULL,
    PRIMARY KEY (`id`),
	FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE `course_student` (
	`course_id`  INT NOT NULL,
    `student_id` INT NOT NULL,
    PRIMARY KEY (`course_id`, `student_id`),
	FOREIGN KEY (`course_id`) REFERENCES `course` (`id`),
	FOREIGN KEY (`student_id`) REFERENCES `student` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE `review` (
	`id` 		 INT NOT NULL AUTO_INCREMENT,
    `rating` 	 INT NOT NULL CHECK (`rating` >= 1 AND `rating` <= 10),
    `comment` 	 VARCHAR(256) NOT NULL,
    `course_id`  INT NOT NULL,
    `student_id` INT NOT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`course_id`) REFERENCES `course` (`id`),
    FOREIGN KEY (`student_id`) REFERENCES `student` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;