-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema exercisesdb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `exercisesdb` ;

-- -----------------------------------------------------
-- Schema exercisesdb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `exercisesdb` DEFAULT CHARACTER SET utf8 ;
USE `exercisesdb` ;

-- -----------------------------------------------------
-- Table `workout`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `workout` ;

CREATE TABLE IF NOT EXISTS `workout` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(75) NOT NULL,
  `category` VARCHAR(45) NOT NULL,
  `description` VARCHAR(250) NOT NULL,
  `Sets` INT NOT NULL DEFAULT 4,
  `reps` INT NOT NULL DEFAULT 10,
  `rest_in_sec` INT NOT NULL DEFAULT 30,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `day`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `day` ;

CREATE TABLE IF NOT EXISTS `day` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `day` VARCHAR(15) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `workout_day`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `workout_day` ;

CREATE TABLE IF NOT EXISTS `workout_day` (
  `day_id` INT NOT NULL,
  `workout_id` INT NOT NULL,
  PRIMARY KEY (`day_id`, `workout_id`),
  INDEX `fk_workout_id_idx` (`workout_id` ASC),
  CONSTRAINT `fk_workout_id`
    FOREIGN KEY (`workout_id`)
    REFERENCES `workout` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_day_id`
    FOREIGN KEY (`day_id`)
    REFERENCES `day` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `plan`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `plan` ;

CREATE TABLE IF NOT EXISTS `plan` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `day_plan`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `day_plan` ;

CREATE TABLE IF NOT EXISTS `day_plan` (
  `day_id` INT NOT NULL,
  `plan_id` INT NOT NULL,
  PRIMARY KEY (`day_id`, `plan_id`),
  INDEX `fk_week_id_idx` (`plan_id` ASC),
  CONSTRAINT `fk_days_id`
    FOREIGN KEY (`day_id`)
    REFERENCES `day` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_plan_id`
    FOREIGN KEY (`plan_id`)
    REFERENCES `plan` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SET SQL_MODE = '';
DROP USER IF EXISTS exerciseuser@localhost;
SET SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
CREATE USER 'exerciseuser'@'localhost' IDENTIFIED BY 'workout';

GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE * TO 'exerciseuser'@'localhost';

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `workout`
-- -----------------------------------------------------
START TRANSACTION;
USE `exercisesdb`;
INSERT INTO `workout` (`id`, `name`, `category`, `description`, `Sets`, `reps`, `rest_in_sec`) VALUES (1, 'Dumbbell Alternate Hammer Curl', 'Biceps', 'grab a dumbell in both hand. While one arm is down lift the other like you would a hammer. Then Alternate.', 4, 10, 30);
INSERT INTO `workout` (`id`, `name`, `category`, `description`, `Sets`, `reps`, `rest_in_sec`) VALUES (2, 'Stationary Bike', 'Cardio', 'Sit on a bike and ride for an hour', 1, 1, 0);
INSERT INTO `workout` (`id`, `name`, `category`, `description`, `Sets`, `reps`, `rest_in_sec`) VALUES (3, 'Wide Grip Lat Pull-Down', 'Back', 'grab the outside edge of the bar with palms facing away from you. Keep your back striaght and pull down infront of you', 4, 10, 30);
INSERT INTO `workout` (`id`, `name`, `category`, `description`, `Sets`, `reps`, `rest_in_sec`) VALUES (4, 'Cable Seated Row', 'Back', 'Keeping your back striaght grab the handles/bar and pull towards you', 4, 10, 30);
INSERT INTO `workout` (`id`, `name`, `category`, `description`, `Sets`, `reps`, `rest_in_sec`) VALUES (5, 'Dumbbell Alternate Bicep Curl', 'Biceps', 'grab a dumbell in both hand. While one arm is down lift the other and twist your wrist so your palm is facing towards you. Then alternate', 4, 10, 30);
INSERT INTO `workout` (`id`, `name`, `category`, `description`, `Sets`, `reps`, `rest_in_sec`) VALUES (6, 'Pull-Ups', 'Back', 'Grab the bar above you with palms facing away from you and pull up to where your chin is above the bar.', 4, 10, 30);
INSERT INTO `workout` (`id`, `name`, `category`, `description`, `Sets`, `reps`, `rest_in_sec`) VALUES (7, 'Barbell Preacher Curl', 'Biceps', 'grab a barbell and rest your arms on the platform with palms up. then lift keeping your elbows to the platform.', 4, 10, 30);
INSERT INTO `workout` (`id`, `name`, `category`, `description`, `Sets`, `reps`, `rest_in_sec`) VALUES (8, 'T-Bar Row', 'Back', 'Rest your body on the plaform face down. Reach down and grab the weighted T-Bar and pull up', 4, 10, 30);
INSERT INTO `workout` (`id`, `name`, `category`, `description`, `Sets`, `reps`, `rest_in_sec`) VALUES (9, 'Barbell Reverse Curl', 'Biceps Forearms', 'Grab the bar with your palms facing down. then lift up. You should feel this in your upper forearms as well as biceps', 4, 10, 30);
INSERT INTO `workout` (`id`, `name`, `category`, `description`, `Sets`, `reps`, `rest_in_sec`) VALUES (10, 'Dip', 'Triceps', 'Grab the bars beside you. Keep your elbows in and lift yourself off the ground and lower without using your feet', 4, 10, 30);
INSERT INTO `workout` (`id`, `name`, `category`, `description`, `Sets`, `reps`, `rest_in_sec`) VALUES (11, 'Cable Rope Triceps Pushdown', 'Triceps', 'Grab both ropes with palms facing each other, push down keeping your elbows in. Rotate your palms down as you push', 4, 10, 30);
INSERT INTO `workout` (`id`, `name`, `category`, `description`, `Sets`, `reps`, `rest_in_sec`) VALUES (12, 'Machine Tricep Extension ', 'Triceps', 'Rest your elbows on the platform and grip the machine handles and push away from your', 4, 10, 30);
INSERT INTO `workout` (`id`, `name`, `category`, `description`, `Sets`, `reps`, `rest_in_sec`) VALUES (13, 'Cable Reverse Tricep Extension', 'Triceps', 'Grip the bar with palms up, keep elbows in and push down', 4, 10, 30);
INSERT INTO `workout` (`id`, `name`, `category`, `description`, `Sets`, `reps`, `rest_in_sec`) VALUES (14, 'Bench Press', 'Chest', 'Grip the Bar even distance away from the weights slowly lift and lower the bar about 2 inces from your chest then push back up. bench should have no incline.', 4, 10, 30);
INSERT INTO `workout` (`id`, `name`, `category`, `description`, `Sets`, `reps`, `rest_in_sec`) VALUES (15, 'Decline Bench Press', 'Chest', 'Grip the Bar even distance away from the weights slowly lift and lower the bar about 2 inces from your chest then push back up. bench should have a decline', 4, 10, 30);
INSERT INTO `workout` (`id`, `name`, `category`, `description`, `Sets`, `reps`, `rest_in_sec`) VALUES (16, 'Incline Bench Press', 'Chest', 'Grip the Bar even distance away from the weights slowly lift and lower the bar about 2 inces from your chest then push back up. bench should have an incline', 4, 10, 30);
INSERT INTO `workout` (`id`, `name`, `category`, `description`, `Sets`, `reps`, `rest_in_sec`) VALUES (17, 'Machine Fly', 'Chest', 'Grip both handles (should be directly on either side of you) while keeping your arms striaght squeeze the bars to the center', 4, 10, 30);
INSERT INTO `workout` (`id`, `name`, `category`, `description`, `Sets`, `reps`, `rest_in_sec`) VALUES (18, 'Wrist Roller', 'Forearms', 'Grip the bar (has a string with a weight at the bottom) with palms down and arms straight out. Roll the bar till the weight is at the top then reverse the roll slowly till the weight is on the ground again', 4, 5, 30);
INSERT INTO `workout` (`id`, `name`, `category`, `description`, `Sets`, `reps`, `rest_in_sec`) VALUES (19, 'Machine Shoulder Press', 'Shoulders ', 'Grip the bars and press up', 4, 10, 30);
INSERT INTO `workout` (`id`, `name`, `category`, `description`, `Sets`, `reps`, `rest_in_sec`) VALUES (20, 'Cable Internal Rotation', 'Shoulders', 'With one arm grab the handle (should  be directly to your side level with your elbow) and pull it from the outside across your body keeping your elbow by your side. Alternate arms after each set', 4, 10, 30);
INSERT INTO `workout` (`id`, `name`, `category`, `description`, `Sets`, `reps`, `rest_in_sec`) VALUES (21, 'Machine Reverse Fly', 'Shoulders Back', 'Grip the Bars in front of you and pull backwards keeping your arms straight ', 4, 10, 30);
INSERT INTO `workout` (`id`, `name`, `category`, `description`, `Sets`, `reps`, `rest_in_sec`) VALUES (22, 'Cabel External Rotation', 'Shoulders', 'With one arm grab the handle (should  be directly to your side level with your elbow) and pull it from across your body to the outside keeping your elbow by your side. Alternate arms after each set', 4, 10, 30);
INSERT INTO `workout` (`id`, `name`, `category`, `description`, `Sets`, `reps`, `rest_in_sec`) VALUES (23, 'Crunches', 'Abs', 'Lay on the ground and bring your feet closer to you (knees should be at about 90 degrees) keeping your back straight and feet planted raise your chest to meet your knees', 4, 30, 30);
INSERT INTO `workout` (`id`, `name`, `category`, `description`, `Sets`, `reps`, `rest_in_sec`) VALUES (24, 'Dumbbell Shoulder Shrug', 'Shoulders', 'Grip some dumbbells and keep them to your sides. shrug your shoulders up and down', 4, 10, 30);
INSERT INTO `workout` (`id`, `name`, `category`, `description`, `Sets`, `reps`, `rest_in_sec`) VALUES (25, 'Parallel Bar Leg Raises', 'Abs', 'Rest your forearms on the bars suspending your body in the air. Kepping your back and legs straight slowly raise your legs to waist level then slowly lower them. Avoid swinging your legs', 4, 10, 30);
INSERT INTO `workout` (`id`, `name`, `category`, `description`, `Sets`, `reps`, `rest_in_sec`) VALUES (26, 'Alternating Dumbbell Standing Arm Raises', 'Shoulder', 'Gip 2 dumbbells and with your palms facing down and arms straight raise one arm to shoulder level and then slowly lower back to your side. Alternate arms', 4, 10, 30);
INSERT INTO `workout` (`id`, `name`, `category`, `description`, `Sets`, `reps`, `rest_in_sec`) VALUES (27, 'Dumbbell Two Arm Side Bend', 'Abs', 'Gip 2 dumbbells and keep them lowered and by your side. Keeping your legs and hips in place lean your torso to one side as far as you feel comfortable then slowly rise back upright. Alternate sides.', 4, 10, 30);
INSERT INTO `workout` (`id`, `name`, `category`, `description`, `Sets`, `reps`, `rest_in_sec`) VALUES (28, 'Squats ', 'Legs', 'Grip a bar where it is resting on your shoulders. While keeping your legs straight squat down and stand back up.', 4, 10, 30);
INSERT INTO `workout` (`id`, `name`, `category`, `description`, `Sets`, `reps`, `rest_in_sec`) VALUES (29, 'Leg Press', 'Legs', 'Lay on the bench with your feet planted on the platform. Press push the platform away and slowly lower', 4, 10, 30);
INSERT INTO `workout` (`id`, `name`, `category`, `description`, `Sets`, `reps`, `rest_in_sec`) VALUES (30, 'Seated Leg Curl', 'Legs', 'Sit on the bench and with your legs resting on the bar (feet out in fron of you) curl your legs in ward', 4, 10, 30);
INSERT INTO `workout` (`id`, `name`, `category`, `description`, `Sets`, `reps`, `rest_in_sec`) VALUES (31, 'Seated Calf Raises ', 'Legs', 'Sit on the bench. Bar resting on top of your legs, keeping your toes on the ground raise your heels up and down', 4, 10, 30);
INSERT INTO `workout` (`id`, `name`, `category`, `description`, `Sets`, `reps`, `rest_in_sec`) VALUES (32, 'Lunges ', 'Legs', '(weights optional) Take large steps and lower your back knee about 6 inches from the ground. Keep your back straight.', 4, 10, 30);
INSERT INTO `workout` (`id`, `name`, `category`, `description`, `Sets`, `reps`, `rest_in_sec`) VALUES (33, 'Thigh Adductor', 'Legs', 'With the pads agains the inside of your thighs squees to the center and slowly return', 4, 10, 30);
INSERT INTO `workout` (`id`, `name`, `category`, `description`, `Sets`, `reps`, `rest_in_sec`) VALUES (34, 'Thigh Abductor', 'legs', 'With the pads agains the outside of your thighs squees to the center and slowly return', 4, 10, 30);

COMMIT;


-- -----------------------------------------------------
-- Data for table `day`
-- -----------------------------------------------------
START TRANSACTION;
USE `exercisesdb`;
INSERT INTO `day` (`id`, `day`, `name`) VALUES (1, 'Monday', 'Back and Biceps');
INSERT INTO `day` (`id`, `day`, `name`) VALUES (2, 'Wednesday', 'Legs');
INSERT INTO `day` (`id`, `day`, `name`) VALUES (3, 'Thursday', 'Chest and Triceps');
INSERT INTO `day` (`id`, `day`, `name`) VALUES (4, 'Friday', 'Shoulders and Abs');

COMMIT;


-- -----------------------------------------------------
-- Data for table `workout_day`
-- -----------------------------------------------------
START TRANSACTION;
USE `exercisesdb`;
INSERT INTO `workout_day` (`day_id`, `workout_id`) VALUES (1, 2);
INSERT INTO `workout_day` (`day_id`, `workout_id`) VALUES (1, 1);
INSERT INTO `workout_day` (`day_id`, `workout_id`) VALUES (1, 3);
INSERT INTO `workout_day` (`day_id`, `workout_id`) VALUES (1, 4);
INSERT INTO `workout_day` (`day_id`, `workout_id`) VALUES (1, 5);
INSERT INTO `workout_day` (`day_id`, `workout_id`) VALUES (1, 6);
INSERT INTO `workout_day` (`day_id`, `workout_id`) VALUES (1, 7);
INSERT INTO `workout_day` (`day_id`, `workout_id`) VALUES (1, 8);
INSERT INTO `workout_day` (`day_id`, `workout_id`) VALUES (1, 9);
INSERT INTO `workout_day` (`day_id`, `workout_id`) VALUES (3, 2);
INSERT INTO `workout_day` (`day_id`, `workout_id`) VALUES (3, 10);
INSERT INTO `workout_day` (`day_id`, `workout_id`) VALUES (3, 11);
INSERT INTO `workout_day` (`day_id`, `workout_id`) VALUES (3, 12);
INSERT INTO `workout_day` (`day_id`, `workout_id`) VALUES (3, 13);
INSERT INTO `workout_day` (`day_id`, `workout_id`) VALUES (3, 14);
INSERT INTO `workout_day` (`day_id`, `workout_id`) VALUES (3, 15);
INSERT INTO `workout_day` (`day_id`, `workout_id`) VALUES (3, 16);
INSERT INTO `workout_day` (`day_id`, `workout_id`) VALUES (3, 17);
INSERT INTO `workout_day` (`day_id`, `workout_id`) VALUES (3, 18);
INSERT INTO `workout_day` (`day_id`, `workout_id`) VALUES (4, 19);
INSERT INTO `workout_day` (`day_id`, `workout_id`) VALUES (4, 20);
INSERT INTO `workout_day` (`day_id`, `workout_id`) VALUES (4, 21);
INSERT INTO `workout_day` (`day_id`, `workout_id`) VALUES (4, 22);
INSERT INTO `workout_day` (`day_id`, `workout_id`) VALUES (4, 23);
INSERT INTO `workout_day` (`day_id`, `workout_id`) VALUES (4, 24);
INSERT INTO `workout_day` (`day_id`, `workout_id`) VALUES (4, 25);
INSERT INTO `workout_day` (`day_id`, `workout_id`) VALUES (4, 26);
INSERT INTO `workout_day` (`day_id`, `workout_id`) VALUES (4, 27);
INSERT INTO `workout_day` (`day_id`, `workout_id`) VALUES (2, 28);
INSERT INTO `workout_day` (`day_id`, `workout_id`) VALUES (2, 29);
INSERT INTO `workout_day` (`day_id`, `workout_id`) VALUES (2, 30);
INSERT INTO `workout_day` (`day_id`, `workout_id`) VALUES (2, 31);
INSERT INTO `workout_day` (`day_id`, `workout_id`) VALUES (2, 32);
INSERT INTO `workout_day` (`day_id`, `workout_id`) VALUES (2, 33);
INSERT INTO `workout_day` (`day_id`, `workout_id`) VALUES (2, 34);

COMMIT;


-- -----------------------------------------------------
-- Data for table `plan`
-- -----------------------------------------------------
START TRANSACTION;
USE `exercisesdb`;
INSERT INTO `plan` (`id`, `name`) VALUES (1, 'Get Jacked');

COMMIT;


-- -----------------------------------------------------
-- Data for table `day_plan`
-- -----------------------------------------------------
START TRANSACTION;
USE `exercisesdb`;
INSERT INTO `day_plan` (`day_id`, `plan_id`) VALUES (1, 1);
INSERT INTO `day_plan` (`day_id`, `plan_id`) VALUES (2, 1);
INSERT INTO `day_plan` (`day_id`, `plan_id`) VALUES (3, 1);
INSERT INTO `day_plan` (`day_id`, `plan_id`) VALUES (4, 1);

COMMIT;

