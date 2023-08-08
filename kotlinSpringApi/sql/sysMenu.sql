USE
    second_floor_cafe;
DROP TABLE IF EXISTS SYS_MENU;
CREATE TABLE SYS_MENU
(
    ID        BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    PERMS     VARCHAR(50) default '' UNIQUE,
    CREATE_AT TIMESTAMP   default NULL,
    UPDATE_AT TIMESTAMP   default NULL,
    DELETE_AT TIMESTAMP   default NULL
) CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT 'SYS_MENU';
INSERT INTO SYS_MENU(id, perms,CREATE_AT,UPDATE_AT)
values (1, 'login',now(),now()),
       (2, 'manage',now(),now()),
       (3, 'menuTest',now(),now());