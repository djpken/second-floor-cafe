USE second_floor_cafe;
DROP TABLE IF EXISTS MENU_DISH;
CREATE TABLE MENU_DISH
(
    id           BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    chinese_name VARCHAR(50) DEFAULT '' UNIQUE ,
    english_name VARCHAR(50) DEFAULT '' UNIQUE,
    price        INT         DEFAULT 0,
    season       INT         DEFAULT 0,
    visible      BOOLEAN     DEFAULT false,
    delete_at    TIMESTAMP   DEFAULT NULL
) CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT 'MENU_DISH';
# INSERT INTO MENU_DISH(chinese_name, english_name, season)
# values ();