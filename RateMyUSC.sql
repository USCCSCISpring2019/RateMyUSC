DROP DATABASE IF EXISTS RateMyProfessor;
CREATE DATABASE RateMyProfessor;
USE RateMyProfessor;


CREATE TABLE UserInfo(
	userInfoID INT(11) PRIMARY KEY AUTO_INCREMENT,
	username VARCHAR(20) NOT NULL,
    password VARCHAR(20) NOT NULL,
    email VARCHAR(20) NOT NULL,
    graduationYear INT(4),
    major VARCHAR(20)
);
CREATE TABLE Department(
	DepartmentID INT(11) PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(10)
);

CREATE TABLE Professor(
	professorID INT(11) PRIMARY KEY AUTO_INCREMENT,
    introduction VARCHAR(100),
    name VARCHAR(20),
    departmentID INT(20),
    foreign key (departmentID) references Department(DepartmentID)
);

CREATE TABLE Review(
	reviewID INT(11) PRIMARY KEY AUTO_INCREMENT,
    userID INT(11) NOT NULL,
	foreign key(userID) references UserInfo(userInfoID),
    comment VARCHAR(400),
    overall INT(1) NOT NULL,
    accessibility INT(1),
    difficulty INT(1),
    likability INT(1),
    lectureQuality INT(1),
    professorID INT(11) NOT NULL,
    foreign key(professorID) references Professor(ProfessorID),
    upvote INT (5) NOT NULL,
    downvote INT(5) NOT NULL,
    anonymity bool NOT NULL
);


