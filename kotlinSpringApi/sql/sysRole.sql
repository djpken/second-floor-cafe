USE
second_floor_cafe;
DROP TABLE IF EXISTS SYS_ROLE;
CREATE TABLE SYS_ROLE
(
    ID        BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    NAME      VARCHAR(50) DEFAULT '',
    CREATE_AT DATETIME    default NULL,
    UPDATE_AT DATETIME    default NULL,
    DELETE_AT DATETIME    default NULL
) CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT 'SYS_ROLE';