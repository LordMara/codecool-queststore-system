CREATE TABLE IF NOT EXISTS `persons_artifacts` (
	`personal_artifacts_id`	INTEGER PRIMARY KEY AUTOINCREMENT,
	`personId`	int,
	`artifactId`	int,
	`status`	TEXT,
	`purchase_date`	TEXT,
	FOREIGN KEY(`personId`) REFERENCES `persons`(`personId`),
	FOREIGN KEY(`artifactId`) REFERENCES `artifacts`(`artifactId`)
);
INSERT INTO `persons_artifacts` VALUES (1,2,1,NULL,NULL);
CREATE TABLE IF NOT EXISTS `wallets` (
	`personId`	int,
	`total_cc_earn`	REAL,
	`ballance`	real,
	`levelId`	int,
	FOREIGN KEY(`levelId`) REFERENCES `levels`(`levelId`),
	FOREIGN KEY(`personId`) REFERENCES `persons`(`personId`)
);
INSERT INTO `wallets` VALUES (2,100.0,50.0,1);
CREATE TABLE IF NOT EXISTS `levels` (
	`levelId`	INTEGER PRIMARY KEY AUTOINCREMENT,
	`name`	text,
	`description`	text,
	`coolcoins_value`	REAL
);
INSERT INTO `levels` VALUES (1,'noob','student who knows nothing',0.0),
 (2,'semi-noob','student who knows something... maybe',500.0),
 (3,'no-noob','student who knows',1000.0);
CREATE TABLE IF NOT EXISTS `artifacts` (
	`artifactId`	INTEGER PRIMARY KEY AUTOINCREMENT,
	`name`	text,
	`description`	text,
	`price`	real
);
INSERT INTO `artifacts` VALUES (1,'Combat training','Private mentoring',50.0),
 (2,'Sanctuary','You can spend a day in home office',300.0),
 (3,'Time Travel','Extend SI week assignment deadline by one day',500.0),
 (4,'Circle of Sorcery','60 min workshop by a mentor(s) of the chosen topic',1000.0),
 (5,'Summon Code Elemental',' mentor joins a students'' team for a one hour',1000.0),
 (6,'Tome of knowledge','Extra material for the current topic',500.0),
 (7,'Transform mentors','All mentors should dress up as pirates (or just funny) for the day',5000.0),
 (8,'Teleport','The whole course goes to an off-school program instead for a day',30000.0);
CREATE TABLE IF NOT EXISTS `classes` (
	`classId`	INTEGER PRIMARY KEY AUTOINCREMENT,
	`name`	text
);
INSERT INTO `classes` VALUES (1,'krk.2017.1'),
 (2,'krk.2017.2');
CREATE TABLE IF NOT EXISTS `persons` (
	`personId`	INTEGER PRIMARY KEY AUTOINCREMENT,
	`name`	text,
	`surname`	INTEGER,
	`email`	text,
	`login`	text UNIQUE,
	`password`	text,
	`role`	text CHECK(role = 'student' or role = 'mentor' or role = 'administrator')
);
INSERT INTO `persons` VALUES (0,'Konrad','Łekawski','admin@admin.com','admin','admin','administrator'),
 (1,'Marcin','Ogorzałek','mentor@mentor.com','mentor','mentor','mentor'),
 (2,'Weronika','Dąbrowska','student@student.com','student','student','student'),
 (3,'Kamil','Konior','adrian@kacper.com','student1','student1','student'),
 (4,'Paweł','Syktus','paweł.syktus@wp.pl','student2','student2','student'),
 (5,'Mateusz','Ostafil','mo@codecool.com','mentor1','mentor1','mentor');
CREATE TABLE IF NOT EXISTS `persons_classes` (
	`personId`	int,
	`classId`	int,
	FOREIGN KEY(`personId`) REFERENCES `persons`(`personId`),
	FOREIGN KEY(`classId`) REFERENCES `classes`(`classId`)
);
INSERT INTO `persons_classes` VALUES (1,1),
 (2,1),
 (3,2),
 (4,1),
 (5,2);
CREATE TABLE IF NOT EXISTS `quests` (
	`questId`	INTEGER PRIMARY KEY AUTOINCREMENT,
	`name`	text,
	`description`	text,
	`price`	real
);
INSERT INTO `quests` VALUES (1,'Exploring a dungeon','Finishing a Teamwork week',100.0),
 (2,'Solving the magic puzzle','Finishing an SI assignment',100.0),
 (3,'Slaying a dragon',' Passing a Checkpoint',500.0),
 (4,'Spot trap','Spot a major mistake in the assignment',50.0),
 (5,'Taming a pet','Doing a demo about a pet project',100.0),
 (6,'Recruiting some n00bs','Taking part in the student screening process',100.0),
 (7,'Forging weapons','Organizing a workshop for other students',400.0),
 (8,'Master the mornings','Attend 1 months without being late',300.0),
 (9,'Fast as an unicorn','deliver 4 consecutive SI week assignments on time',500.0),
 (10,'Achiever','set up a SMART goal accepted by a mentor, then achieve it',1000.0),
 (11,'Fortune','students choose the best project of the week. Selected team scores',500.0),
 (12,'Creating an enchanted scroll','Creating extra material for the current TW/SI topic (should be revised by mentors)',500.0),
 (13,'Enter the arena','Do a presentation on a meet-up',500.0);
CREATE TABLE IF NOT EXISTS `cookies` (
	`userId`	INTEGER,
	`session_id`	TEXT,
	PRIMARY KEY(`session_id`)
);
INSERT INTO `cookies` VALUES (0,'sessionId="18416eb0-e456-4207-b078-eb00135ab632"');
CREATE TABLE IF NOT EXISTS `bills` (
	`personId`	int,
	`status`	text CHECK(status = 'paid' or status = 'unpaid'),
	`questId`	int,
	`achieve_date`	text,
	`billId`	INTEGER PRIMARY KEY AUTOINCREMENT,
	FOREIGN KEY(`personId`) REFERENCES `persons`(`personId`),
	FOREIGN KEY(`questId`) REFERENCES `quests`(`questId`)
);
INSERT INTO `bills` VALUES (1,'unpaid',1,'22.11.2017',1);
CREATE TABLE IF NOT EXISTS `schema_version` (
	`installed_rank`	INT NOT NULL,
	`version`	VARCHAR ( 50 ),
	`description`	VARCHAR ( 200 ) NOT NULL,
	`type`	VARCHAR ( 20 ) NOT NULL,
	`script`	VARCHAR ( 1000 ) NOT NULL,
	`checksum`	INT,
	`installed_by`	VARCHAR ( 100 ) NOT NULL,
	`installed_on`	TEXT NOT NULL DEFAULT (strftime('%Y-%m-%d %H:%M:%f','now')),
	`execution_time`	INT NOT NULL,
	`success`	BOOLEAN NOT NULL,
	PRIMARY KEY(`installed_rank`)
);

