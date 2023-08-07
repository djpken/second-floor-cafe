USE second_floor_cafe;
DROP TABLE IF EXISTS SYS_USER;
CREATE TABLE SYS_USER
(
    ID           BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    USERNAME     VARCHAR(50)                       NOT NULL,
    PASSWORD     VARCHAR(50)                       NOT NULL,
    CHINESE_NAME VARCHAR(50) default '',
    ENGLISH_NAME VARCHAR(50) default '',
    AGE          INT         default 0,
    EMAIL        VARCHAR(50)                       NOT NULL,
    CREATE_AT    DATETIME    default NULL,
    UPDATE_AT    DATETIME    default NULL,
    DELETE_AT    DATETIME    default NULL
) CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT 'SYS_USER';