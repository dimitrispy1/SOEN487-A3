DROP TABLE IF EXISTS t_Daily_Stats;
DROP TABLE IF EXISTS t_Player_Team;
DROP TABLE IF EXISTS t_Player;
DROP TABLE IF EXISTS t_Team;
DROP TABLE IF EXISTS t_User;

CREATE TABLE t_User(
	id int PRIMARY KEY AUTO_INCREMENT,
	username varchar(255) NOT NULL,
	password varchar(255) NOT NULL,
	email varchar(255)
)ENGINE=InnoDB;

CREATE TABLE t_Team(
	id int PRIMARY KEY AUTO_INCREMENT,
	name varchar(255),
	total_pts int,
	total_assists int,
	total_rebounds int,
	user_id int NOT NULL,
	FOREIGN KEY (user_id) REFERENCES t_User(id)
)ENGINE=InnoDB;

CREATE TABLE t_Player(
	id int PRIMARY KEY,
	name varchar(255),
	position varchar(255),
	avg_pts FLOAT,
	avg_assists FLOAT,
	avg_rebounds FLOAT,
	picture varchar(255)
)ENGINE=InnoDB;

CREATE TABLE t_Player_Team(
	assigned_position varchar(255),
	player_id int,
	team_id int,
	FOREIGN KEY (player_id) REFERENCES t_Player(id),
	FOREIGN KEY (team_id) REFERENCES t_Team(id) ON DELETE CASCADE
)ENGINE=InnoDB;

CREATE TABLE t_Daily_Stats(
	id int PRIMARY KEY AUTO_INCREMENT,
	total_pts int,
	total_assists int,
	total_rebounds int,
	player_id int,
	FOREIGN KEY (player_id) REFERENCES t_Player(id)
)ENGINE=InnoDB;