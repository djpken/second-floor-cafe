USE second_floor_cafe;
DROP TABLE IF EXISTS SYS_USER;
CREATE TABLE SYS_USER
(
    ID           BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    USERNAME     VARCHAR(100) UNIQUE                NOT NULL,
    PASSWORD     VARCHAR(255)                       NOT NULL,
    CHINESE_NAME VARCHAR(50) default '',
    ENGLISH_NAME VARCHAR(50) default '',
    EMAIL        VARCHAR(50) UNIQUE                NOT NULL,
    CREATE_AT    TIMESTAMP   default NULL,
    UPDATE_AT    TIMESTAMP   default NULL,
    DELETE_AT    TIMESTAMP   default NULL
) CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT 'SYS_USER';
INSERT INTO SYS_USER(ID, USERNAME, PASSWORD, CHINESE_NAME, ENGLISH_NAME, EMAIL, CREATE_AT, UPDATE_AT)
values (1, '003958', '003958', '許正坤', 'Kun', 'djpkendjpken@gmail.com', now(), now()),
       (2, '004015', '004015', '林郁晴', 'Melody', 'ching@gmail.com', now(), now());