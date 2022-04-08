drop table Employee;
drop table Hourly_Employee;
drop table Salary_Employee;
drop table Timecard;

CREATE TABLE Employee (
  Employee_ID INT NOT NULL,
  Employee_Type INT,
  First_Name VARCHAR(50),
  Last_Name VARCHAR(50),
  SSN bigint,
  User_ID VARCHAR(50),
  Password VARCHAR(50),
  
  PRIMARY KEY(Employee_ID) 
);

INSERT INTO Employee 
  (Employee_ID, Employee_Type, First_Name, Last_Name, SSN, User_ID, Password)
VALUES 
  (1001, 1, 'Bob', 'Smith', 555121234, 'x', 'x');
CREATE TABLE Hourly_Employee (
  Employee_ID INT NOT NULL,
  Hourly_Rate DOUBLE,
  Overtime_Rate DOUBLE,
  
  PRIMARY KEY(Employee_ID) 
);

INSERT INTO Hourly_Employee 
  (Employee_ID, Hourly_Rate, Overtime_Rate)
VALUES 
  (1001, 12.75, 1.5);
CREATE TABLE Salary_Employee (
  Employee_ID INT NOT NULL,
  Salary DOUBLE,
  
  PRIMARY KEY(Employee_ID) 
);

INSERT INTO Salary_Employee 
  (Employee_ID, Salary)
VALUES 
  (1002, 60000);

CREATE TABLE Timecard (
    Timecard_ID INT NOT NULL GENERATED ALWAYS AS IDENTITY,    
    Timecard_Date DATE,
    Employee_ID INT NOT NULL,
    Hours_Worked DOUBLE,
    Overtime_Hours Double,
    
    PRIMARY KEY (Timecard_ID)
);

INSERT INTO Timecard 
  (Timecard_Date, Employee_ID, Hours_Worked, Overtime_Hours)
VALUES 
  ('3/25/2020', 1001, 8, 2.5);