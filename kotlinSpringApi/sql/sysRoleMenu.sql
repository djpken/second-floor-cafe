USE
    second_floor_cafe;
DROP TABLE IF EXISTS SYS_ROLE_MENU;
CREATE TABLE SYS_ROLE_MENU
(
    ID      BIGINT primary key auto_increment NOT NULL,
    ROLE_ID BIGINT                            NOT NULL,
    MENU_ID BIGINT                            NOT NULL

) CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT 'SYS_ROLE_MENU';
ALTER TABLE SYS_ROLE_MENU
    ADD CONSTRAINT FK_SYS_ROLE_MENU_ROLE_ID FOREIGN KEY (ROLE_ID) REFERENCES SYS_ROLE (ID),
    ADD CONSTRAINT FK_SYS_ROLE_MENU_MENU_ID FOREIGN KEY (MENU_ID) REFERENCES SYS_MENU (ID);
INSERT INTO SYS_ROLE_MENU(ROLE_ID, MENU_ID)
VALUES (1, 1),
       (1, 2),
       (1, 3),
       (2, 2),
       (2, 3),
       (3, 3),
       (5, 1),
       (5, 2),
       (5, 3);