-- 删除已存在的表
DROP DATABASE IF EXISTS second_floor_cafe;
-- 创建名为 "second_floor_cafe" 的数据库
CREATE DATABASE IF NOT EXISTS second_floor_cafe;
-- 切换到 "second_floor_cafe" 数据库
USE second_floor_cafe;
-- 设置数据库的字符集和排序规则为 'utf8mb4_unicode_ci'
ALTER DATABASE `second_floor_cafe` COLLATE 'utf8mb4_unicode_ci';
