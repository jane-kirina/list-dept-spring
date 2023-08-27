DROP DATABASE IF EXISTS departments;
CREATE DATABASE departments ENCODING 'UTF8';

DROP TABLE IF EXISTS employee;
DROP TABLE IF EXISTS dept;

CREATE TABLE dept(
                     dept_id BIGSERIAL PRIMARY KEY,
                     dept_name VARCHAR(255) NOT NULL UNIQUE,
                     dept_phone_number VARCHAR(20) NOT NULL UNIQUE
);

CREATE TABLE employee (
                          employee_id BIGSERIAL PRIMARY KEY,
                          employee_name VARCHAR(255) NOT NULL,
                          employee_birth DATE NOT NULL,
                          employee_email VARCHAR(30) NOT NULL UNIQUE,
                          employee_years_working INT,
                          dept_id BIGSERIAL,
                          FOREIGN KEY (dept_id) REFERENCES dept(dept_id)
                              ON UPDATE CASCADE
                              ON DELETE CASCADE
);

-- -- TEST DATA, OPTIONAL

-- INSERT INTO dept VALUES (1, 'first department', 120123456789);
-- INSERT INTO dept VALUES (2, 'second department', 07654321341);
--
-- INSERT INTO employee VALUES (1, 'first employee', '01-01-2000', 'qwe@qwe.com', 25, 1);
-- INSERT INTO employee VALUES (2, 'second employee', '10-10-1999', 'asdfg@asd.com', 2, 2);
-- INSERT INTO employee VALUES (3, 'third employee', '01-01-2000', 'zxcvb@zxc.com', 10, 2);