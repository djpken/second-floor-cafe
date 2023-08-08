USE
    second_floor_cafe;
DROP TABLE IF EXISTS SYS_ROLE;
CREATE TABLE SYS_ROLE
(
    ID        BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    NAME      VARCHAR(50) DEFAULT '' UNIQUE,
    CREATE_AT TIMESTAMP   default NULL,
    UPDATE_AT TIMESTAMP   default NULL,
    DELETE_AT TIMESTAMP   default NULL
) CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT 'SYS_ROLE';
INSERT INTO SYS_ROLE(ID, NAME, CREATE_AT, UPDATE_AT)
VALUES (1, 'boss', now(), now()),
       (2, 'manager', now(), now()),
       (3, 'fullTime', now(), now()),
       (4, 'partTime', now(), now()),
       (5, 'visitor', now(), now()),
       (6, 'programmer', now(), now()),
       (7, 'frontForeman', now(), now()),
       (8, 'backForeman', now(), now());