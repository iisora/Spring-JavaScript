INSERT INTO employee (employee_id, employee_name, age)
VALUES(1,'山田太郎',30);

/* ADMIN */
INSERT INTO m_user (user_id, password, user_name, birthday, age, marriage, role)
VALUES('yamada@example.com','password','山田太郎','1990-01-01',26,false,'ROLE_ADMIN');

/* GENERAL */
INSERT INTO m_user (user_id, password, user_name, birthday, age, marriage, role)
VALUES('hiroki@example.com','password','大樹森太郎','1998-01-01',23,false,'ROLE_GENERAL');