USE company;
DROP TABLE IF EXISTS emp_jdbc;
CREATE TABLE emp_jdbc (
  empcode  INT PRIMARY KEY,
  empname  VARCHAR(50) NOT NULL,
  empage   INT NOT NULL,
  esalary  INT NOT NULL
);
INSERT INTO emp_jdbc (empcode, empname, empage, esalary) VALUES
(101, 'Jenny', 25, 10000),
(102, 'Jacky', 30, 20000),
(103, 'Joe',   20, 40000),
(104, 'John',  40, 80000),
(105, 'Shameer', 25, 90000);

SELECT * FROM emp_jdbc;