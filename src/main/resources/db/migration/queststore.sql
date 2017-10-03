BEGIN TRANSACTION;
CREATE TABLE wallets(personId int, xp int, ballance real, levelId int, foreign key(personId) references persons(personId), foreign key(levelId) references levels(levelId));
INSERT INTO `wallets` VALUES (2,3,3.33,0);
CREATE TABLE "quests" (
	`questId`	INTEGER PRIMARY KEY AUTOINCREMENT,
	`name`	text,
	`description`	text,
	`price`	real
);
INSERT INTO `quests` VALUES (0,'ino','ino sie nie zgub',123.0);
CREATE TABLE persons_classes(personId int, classId int, foreign key(personId) references persons(personId), foreign key(classId) references classes(classId));
INSERT INTO `persons_classes` VALUES (0,NULL);
INSERT INTO `persons_classes` VALUES (1,0);
INSERT INTO `persons_classes` VALUES (2,0);
INSERT INTO `persons_classes` VALUES (3,'null');
CREATE TABLE persons_artifacts(personId int, artifactId int, foreign key(personId) references persons(personId), foreign key(artifactId) references artifacts(artifactId));
INSERT INTO `persons_artifacts` VALUES (0,0);
INSERT INTO `persons_artifacts` VALUES (1,0);
CREATE TABLE "persons" (
	`personId`	INTEGER PRIMARY KEY AUTOINCREMENT,
	`name`	text,
	`surname`	INTEGER,
	`email`	text,
	`login`	text UNIQUE,
	`password`	text,
	`role`	text CHECK(role = 'student' or role = 'mentor' or role = 'administrator')
);
INSERT INTO `persons` VALUES (0,'admin','admin','admin@admin.com','admin','admin','administrator');
INSERT INTO `persons` VALUES (1,'mentor','mentor','mentor@mentor.com','mentor','mentor','mentor');
INSERT INTO `persons` VALUES (2,'student','student','student@student.com','student','student','student');
INSERT INTO `persons` VALUES (3,'Adrian','Kacper','adrian@kacper.com','adi','adi','student');
CREATE TABLE "levels" (
	`levelId`	INTEGER PRIMARY KEY AUTOINCREMENT,
	`name`	text,
	`description`	text
);
INSERT INTO `levels` VALUES (0,'noob','student who knows nothing');
INSERT INTO `levels` VALUES (1,'semi-noob','student who knows something... maybe');
INSERT INTO `levels` VALUES (2,'no-noob','student who knows');
CREATE TABLE "classes" (
	`classId`	INTEGER PRIMARY KEY AUTOINCREMENT,
	`name`	text
);
INSERT INTO `classes` VALUES (0,'krk.2017.1');
CREATE TABLE bills(personId int, status text check(status = 'paid' or status = 'unpaid'), questId int, achieveDate text, foreign key(personId) references persons(personId), foreign key(questId) references quests(questId));
INSERT INTO `bills` VALUES (2,'unpaid',0,NULL);
CREATE TABLE "artifacts" (
	`artifactId`	INTEGER PRIMARY KEY AUTOINCREMENT,
	`name`	text,
	`description`	text,
	`price`	real
);
INSERT INTO `artifacts` VALUES (0,'kaczuszka','kapielowa kaczuszka. jest zolta',10000000.0);
COMMIT;
