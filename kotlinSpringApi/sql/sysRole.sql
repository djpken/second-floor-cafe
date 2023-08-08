USE
    SECOND_FLOOR_CAFE;
DROP TABLE IF EXISTS SYS_ROLE;
CREATE TABLE SYS_ROLE
(
    ID        BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    NAME      VARCHAR(50) DEFAULT '' UNIQUE,
    CREATE_AT TIMESTAMP   DEFAULT NULL,
    UPDATE_AT TIMESTAMP   DEFAULT NULL,
    DELETE_AT TIMESTAMP   DEFAULT NULL
) CHARACTER SET = UTF8MB4
  COLLATE = UTF8MB4_GENERAL_CI COMMENT 'SYS_ROLE';
INSERT INTO SYS_ROLE(ID, NAME, CREATE_AT, UPDATE_AT)
VALUES (1, 'boss', now(), now()),
       (2, 'manager', now(), now()),
       (3, 'fullTime', now(), now()),
       (4, 'partTime', now(), now()),
       (5, 'visitor', now(), now()),
       (6, 'programmer', now(), now()),
       (7, 'frontForeman', now(), now()),
       (8, 'backForeman', now(), now());