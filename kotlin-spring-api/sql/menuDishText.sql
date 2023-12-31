USE SECOND_FLOOR_CAFE;
DROP TABLE IF EXISTS MENU_DISH_TEXT;
CREATE TABLE MENU_DISH_TEXT
(
    ID           BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    CHINESE_NAME VARCHAR(50) DEFAULT '',
    ENGLISH_NAME VARCHAR(50) DEFAULT '',
    PRICE        INT         DEFAULT 0,
    SEASON       INT         DEFAULT 0,
    VISIBLE      TINYINT(1)  DEFAULT FALSE,
    DELETE_AT    TIMESTAMP   DEFAULT NULL
) CHARACTER SET = UTF8MB4
  COLLATE = UTF8MB4_GENERAL_CI COMMENT 'MENU_DISH';
INSERT INTO MENU_DISH_TEXT (`ID`, `CHINESE_NAME`, `SEASON`)
VALUES (1, '水牛城辣雞翅', 2022),
       (2, '蜜釀十三香雞翅', 2022),
       (3, '普丁肉醬薯條', 2022),
       (4, '舊金山蒜香薯條', 2022),
       (5, '松露薯條', 2022),
       (6, '爆漿金沙薯片', 2022),
       (7, '美墨辣肉醬薯片', 2022),
       (8, '南方懷舊起司corn', 2022),
       (9, '經典凱薩沙拉', 2022),
       (10, '經典凱薩沙拉-舒肥雞', 2022),
       (11, '經典凱薩沙拉-燻鮭魚', 2022),
       (12, '貳樓金牌鹽水雞沙拉', 2022),
       (13, '深灣烤牛排蜜桃核沙拉', 2022),
       (14, '焙煎胡麻雞沙拉', 2022),
       (15, '實打實招牌漢堡', 2022),
       (16, '朝陽蘑菇肉醬牛肉堡', 2022),
       (17, '舒肥雞胸佐火腿營養三明治', 2022),
       (18, '金黃流沙海鮮披薩', 2022),
       (19, '港式油蔥雞披薩', 2022),
       (20, '橙香蛋煎丹麥舒肥雞早午餐', 2022),
       (21, '橙香蛋煎丹麥牛排早午餐', 2022),
       (22, '橙香蛋煎丹麥佐海鮮洋芋', 2022),
       (23, '台胃口水雞班尼蛋', 2022),
       (24, '烤牛排班尼蛋', 2022),
       (25, 'Mexican不老鮭班尼蛋', 2022),
       (26, '總匯活力歐姆蕾', 2022),
       (27, '海料嗶啵歐姆蕾', 2022),
       (28, '肉醬歐蛋法式達', 2022),
       (29, '橙香蛋煎法棍高蛋白早午餐', 2022),
       (30, '布蕾法烤丹麥早午餐', 2022),
       (31, '經典貳樓早午餐', 2022),
       (32, 'hashtag肉桂捲', 2022),
       (33, '低碳-舒肥嫩雞奶油花椰飯', 2022),
       (34, '低碳-香煎魚排奶油花椰飯', 2022),
       (35, '低碳-碳烤牛排奶油花椰飯', 2022),
       (36, '生酮-總匯海陸拼盤', 2022),
       (37, '藜麥香烤牛優格Taco袋', 2022),
       (38, '藜麥巴沙魚優格Taco袋', 2022),
       (39, '小米煎魚溫沙拉', 2022),
       (40, '橙香蛋煎法棍烤時蔬早午餐', 2022),
       (41, '蔬食開放式三明治早午餐', 2022),
       (42, '巴西莓果優格碗', 2022),
       (43, '熱帶水果優格碗', 2022),
       (44, '蜜桃酸奶水果沙拉', 2022),
       (45, '泡菜豆腐烤蔬菜披薩', 2022),
       (46, '焦糖胡椒蔬菜細扁麵', 2022),
       (47, '烤蔬奶油鹽麴細扁麵', 2022),
       (48, '貝夏梅濃醬焗烤飯', 2022),
       (49, '南洋辛香風味飯', 2022),
       (50, '歐爸泡菜野蔬麵', 2022),
       (51, '手抓咖哩野蔬豆泥烤餅', 2022),
       (52, '水波蛋咖哩時蔬花椰飯烤餅', 2022),
       (53, '咖哩時蔬捲餅佐優格醬', 2022),
       (54, '鷹嘴豆起司優格薄餅', 2022),
       (55, '酒香蒜味蛤蜊墨魚麵', 2022),
       (56, '經典青醬鮮蝦細扁麵', 2022),
       (57, '南洋辛香雙料細扁麵', 2022),
       (58, '家鄉肉醬麵佐老媽子的肉丸', 2022),
       (59, '香爆椒麻唐揚雞細扁麵', 2022),
       (60, '朝日海味奶油蛋黃墨魚麵', 2022),
       (61, '曙光汁鮮蝦雞肉細扁麵', 2022),
       (62, '藍帶雞排奶油焗烤飯', 2022),
       (63, '歐爸辣唐揚雞泡菜飯', 2022),
       (64, '小東京黑咖哩奶油牛排飯', 2022),
       (65, '歐式麵包盤', 2022),
       (66, '主廚濃湯', 2022),
       (67, '番茄蔬菜湯', 2022),
       (68, '安妞辣洋釀炸雞', 2022),
       (69, '香烤起司馬鈴薯', 2022),
       (70, '超多汁酥炸蘑菇', 2022),
       (71, '美式起司QQ球', 2022),
       (72, '布蕾波波冰淇淋杯', 2022),
       (73, '金銀島冰淇淋杯', 2022),
       (74, 'null', 2022),
       (75, 'null', 2022),
       (76, 'null', 2022),
       (77, 'null', 2022),
       (78, 'null', 2022),
       (79, 'null', 2022),
       (80, 'null', 2022),
       (81, 'null', 2022),
       (82, 'null', 2022),
       (83, 'null', 2022),
       (84, 'null', 2022),
       (85, '主廚脆皮豬腳', 2022),
       (86, '費城炸牛排佐甜豆泥吐司', 2022),
       (87, 'BBQ溫烤半雞', 2022),
       (88, '吃光光雞肉蛋包飯', 2022),
       (89, '熊孩子雞肉奶油通心粉', 2022),
       (90, '怪博士吐司', 2022),
       (91, '最愛棒棒糖早午餐', 2022),
       (92, '鹽麴奶油雞肉飯', 2022),
       (93, '經典青醬雞肉細扁麵', 2022),
       (94, '厚起司蛋漿牛肉堡', 2022),
       (95, '蜜桃覆盆子雪沙', 2022),
       (96, '桑葚野莓雪沙', 2022),
       (97, '焦糖香草奶昔', 2022),
       (98, '奧利奧黑巧奶昔', 2022),
       (99, 'null', 2022),
       (100, 'null', 2022),
       (101, 'null', 2022),
       (102, 'null', 2022),
       (103, '天堂鳥冰茶', 2022),
       (104, '盛夏光年', 2022),
       (105, '盛夏光年(熱)', 2022),
       (106, 'null', 2022),
       (107, 'null', 2022),
       (108, '經典拿鐵-冰', 2022),
       (109, 'null', 2022),
       (110, '黑糖拿鐵-冰', 2022),
       (111, '黑糖拿鐵-熱', 2022),
       (112, 'null', 2022),
       (113, '焦糖海鹽拿鐵-熱', 2022),
       (114, '西西里氣泡咖啡', 2022),
       (115, 'null', 2022),
       (116, 'null', 2022),
       (117, 'null', 2022),
       (118, 'null', 2022),
       (119, 'null', 2022),
       (120, 'null', 2022),
       (121, 'null', 2022),
       (122, '大人味提拉米蘇', 2022),
       (123, '經典火腿班尼蛋', 2022),
       (124, '水牛城辣雞翅', 2023),
       (125, '墨西哥 雞肉/德腸 酥餅', 2023),
       (126, '酥炸花枝圈佐雞尾酒醬', 2023),
       (127, '手作花椰餅佐辣味海卵醬', 2023),
       (128, '舊金山蒜香薯條', 2023),
       (129, '松露薯條', 2023),
       (130, '天使辣起司薯片', 2023),
       (131, '經典凱薩沙拉', 2023),
       (132, '經典舒肥雞凱薩沙拉', 2023),
       (133, '經典燻鮭魚凱薩沙拉', 2023),
       (134, '貳樓金牌鹽水雞沙拉', 2023),
       (135, '焙煎胡麻雞沙拉', 2023),
       (136, '低碳舒肥雞藜麥花椰飯', 2023),
       (137, '低碳牛排藜麥花椰飯', 2023),
       (138, '低碳魚排藜麥花椰飯', 2023),
       (139, '生酮-總匯海陸拼盤', 2023),
       (140, '實打實招牌漢堡', 2023),
       (141, '老墨辣鞭炮漢堡', 2023),
       (142, '厚烤奶油Ham三明治', 2023),
       (143, '陽光雙蛋海地未來堡', 2023),
       (144, '巴西莓果優格碗', 2023),
       (145, '熱帶水果優格碗', 2023),
       (146, '南方豆泥起司捲餅', 2023),
       (147, '橙香法式丹麥Sunny舒肥雞', 2023),
       (148, '橙香法式丹麥舒肥牛排', 2023),
       (149, 'null', -1),
       (150, '橙香法式丹麥佐水波海鮮洋芋', 2023),
       (151, '橙香法式丹麥蕈菇水波洋芋', 2023),
       (152, '橙香法式丹麥MINI', 2023),
       (153, '橙香法式丹麥佐地中海bowl', 2023),
       (154, '台胃口水雞班尼蛋', 2023),
       (155, '燻鮭魚奶油炒菇班尼蛋', 2023),
       (156, '舒肥牛排奶油炒菇班尼蛋', 2023),
       (157, '經典火腿班尼蛋', 2023),
       (158, '下城區貝里托捲', 2023),
       (159, '日耳曼貝里托捲', 2023),
       (160, '烏斯特肉醬歐姆蕾', 2023),
       (161, '總匯芝心歐姆蕾', 2023),
       (162, '歐爸小辣歐姆蕾', 2023),
       (163, '101長棍佐Whisky奶油雞肉盅', 2023),
       (164, '酒香蒜味蛤蜊墨魚麵', 2023),
       (165, '經典青醬鮮蝦細扁麵', 2023),
       (166, '南洋辛香雙料細扁麵', 2023),
       (167, '香爆椒麻唐揚雞細扁麵', 2023),
       (168, '貳樓費氏鮮蝦細扁麵', 2023),
       (169, '曙光汁鮮蝦雞肉細扁麵', 2023),
       (170, '越南酸辣花枝細扁麵', 2023),
       (171, '家鄉肉醬麵佐老媽子的肉丸', 2023),
       (172, '台式熱炒鹹蛋苦瓜麵', 2023),
       (173, '巴薩米克蕈菇細扁麵', 2023),
       (174, '松露蕈菇奶油細扁麵', 2023),
       (175, '辣起司綠蔬奶油佐糯米椒麵', 2023),
       (176, '歐爸辣唐揚雞泡菜飯', 2023),
       (177, '藍帶豬排奶油焗烤飯', 2023),
       (178, '馬德里德腸海鮮飯', 2023),
       (179, '漢堡排+飯+黑胡椒醬=讚', 2023),
       (180, '奶油起司野蔬打拋飯', 2023),
       (181, '肉丸山丘飯', 2023),
       (182, '月見苦瓜奶油飯', 2023),
       (183, '歐式麵包盤', 2023),
       (184, '火腿玉米濃湯', 2023),
       (185, '蕃茄蔬菜湯', 2023),
       (186, '安妞辣洋釀炸雞', 2023),
       (187, '９4香炸菇盤', 2023),
       (188, 'Venice松露黃瓜角', 2023),
       (189, '布蕾波波冰淇淋杯', 2023),
       (190, '摩卡奶油泥巴', 2023),
       (191, '冰', 2023),
       (192, '檸檬果萊姆茶', 2023),
       (193, '狗狗嫩蛋', 2023),
       (194, '主厨脆皮豬腳', 2023),
       (195, 'BBQ溫烤半鷄', 2023),
       (196, '吃光光雞肉蛋包飯', 2023),
       (197, '熊孩子的奶油雞肉麵', 2023),
       (198, '大俠愛吃漢寶包', 2023),
       (199, '經典青醬雞肉飯', 2023),
       (200, '南洋辛香雞肉飯', 2023),
       (201, '朝日藍帶豬排堡', 2023),
       (202, '巨峰葡萄冰沙', 2023),
       (203, '接骨木覆盆莓冰沙', 2023),
       (204, '焦糖香草奶昔', 2023),
       (205, '奧利奧黑巧奶昔', 2023),
       (206, '翠綠羽衣甘藍果汁', 2023),
       (207, '橙橘胡蘿蔔芭樂汁', 2023),
       (208, '洋紅甜菜葡萄汁', 2023),
       (209, '粉紅芭樂蘋果汁', 2023),
       (210, '天堂鳥冰茶', 2023),
       (211, '女巫Sangria', 2023),
       (212, '桃花朵朵開運茶', 2023),
       (213, '美式咖啡-冰', 2023),
       (214, '美式咖啡-熱', 2023),
       (215, '經典拿鐵-冰', 2023),
       (216, '經典拿鐵-熱', 2023),
       (217, '奶油啤酒花拿鐵', 2023),
       (218, '西西里氣泡咖啡', 2023),
       (219, '野釀康普茶(原味、百香果)', 2023),
       (220, 'WAT 美人蜜蘋果氣泡雞尾酒', 2023),
       (221, 'WAT 凍檸茶氣泡雞尾酒', 2023),
       (222, '強的', 2023),
       (223, '全美起司蛋糕', 2023),
       (224, '焦糖脆片冰淇淋布朗尼', 2023),
       (225, 'Homemade肉桂蜜桃派', 2023),
       (226, '黑皮狗狗', 2023),
       (227, '多多果凍', 2023),
       (228, '鮮果派對沙拉', 2023),
       (229, '莎莎黑咖哩炸雞', 2023),
       (230, '金黃流沙薯條', 2023),
       (231, '普丁肉醬薯條', 2023),
       (232, '歐爸培根德腸焗烤麵', 2023);
