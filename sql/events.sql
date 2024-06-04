
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `events_roles`;

CREATE TABLE `events_roles` (
  `role_id` bigint NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `role_name` varchar(50) NOT NULL COMMENT '角色名称',
  `role_description` varchar(255) DEFAULT NULL COMMENT '角色描述',
  PRIMARY KEY (`role_id`)
);
SET FOREIGN_KEY_CHECKS = 1;


INSERT INTO `events_roles` (`role_name`, `role_description`) VALUES
('教师', '参与活动的教师'),	
('评委', '参与活动的评委');




SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS `events_account`;


CREATE table `events_account`(
	 `account_id` bigint NOT NUll AUTO_INCREMENT COMMENT '活动id',
	 `role_id` bigint NULL DEFAULT NULL COMMENT '身份id',
	 `account_school` VARCHAR(50)  COMMENT '学校',
	 `account_name` VARCHAR(30)  COMMENT '姓名',
	 `account_group` VARCHAR(30)  COMMENT '报名组别',
	 `account_nick` VARCHAR(30)  COMMENT '账号昵称',
	 `account_password` VARCHAR(30) COMMENT '账号密码',
	 `create_by` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT '' COMMENT '创建者',
		`create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
		`update_by` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT '' COMMENT '更新者',
		`update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
		`remark` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL COMMENT '备注',
		PRIMARY KEY (`account_id`),
		#当父表中的记录被删除时，子表中引用该记录的外键列将被设置为 NULL
		constraint `eventsfk_acc_role` FOREIGN KEY (`role_id`) REFERENCES `events_roles` (`role_id`) ON DELETE SET NULL ON UPDATE CASCADE
);

SET FOREIGN_KEY_CHECKS = 1;


INSERT INTO `events_account` (`role_id`, `account_school`, `account_name`, `account_group`, `account_nick`, `account_password`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1, '许昌学院', '张三', '公共基础课程组', 'zhangsan', '123456', '张三', '2024-06-01 10:00:00', '张三', '2024-06-02 10:00:00', '备注1'),
(2, '清华大学', '李明明', '思政课程组', 'qhdx_lmm', '123456', '李明明', '2024-06-03 11:00:00', '李明明', '2024-06-04 11:00:00', '备注2'),
(1, '青岛职业学院', '刘明', '公共基础课程组', 'qdzydx_lm', '123456', '刘明', '2024-06-05 12:00:00', '刘明', '2024-06-06 12:00:00', '备注3'),
(2, '北京大学', '王华', '思政课程组', 'pku_wh', '123456', '王华', '2024-06-07 13:00:00', '王华', '2024-06-08 13:00:00', '备注4'),
(1, '南京大学', '赵强', '专业技能课程一组', 'nju_zq', '123456', '赵强', '2024-06-09 14:00:00', '赵强', '2024-06-10 14:00:00', '备注5'),
(2, '复旦大学', '陈晓', '专业技能课程二组', 'fdu_cx', '123456', '陈晓', '2024-06-11 15:00:00', '陈晓', '2024-06-12 15:00:00', '备注6'),
(1, '浙江大学', '孙丽', '公共基础课程组', 'zju_sl', '123456', '孙丽', '2024-06-13 16:00:00', '孙丽', '2024-06-14 16:00:00', '备注7'),
(2, '武汉大学', '周杰', '思政课程组', 'whu_zj', '123456', '周杰', '2024-06-15 17:00:00', '周杰', '2024-06-16 17:00:00', '备注8'),
(1, '上海交通大学', '郑洁', '公共基础课程组', 'sjtu_zj', '123456', '郑洁', '2024-06-17 18:00:00', '郑洁', '2024-06-18 18:00:00', '备注9'),
(2, '同济大学', '何丽', '思政课程组', 'tongji_hl', '123456', '何丽', '2024-06-19 19:00:00', '何丽', '2024-06-20 19:00:00', '备注10'),
(1, '中国人民大学', '魏明', '公共基础课程组', 'ruc_wm', '123456', '魏明', '2024-06-21 20:00:00', '魏明', '2024-06-22 20:00:00', '备注11'),
(2, '厦门大学', '刘莉', '思政课程组', 'xmu_ll', '123456', '刘莉', '2024-06-23 21:00:00', '刘莉', '2024-06-24 21:00:00', '备注12'),
(1, '西安交通大学', '张静', '公共基础课程组', 'xjtu_zj', '123456', '张静', '2024-06-25 22:00:00', '张静', '2024-06-26 22:00:00', '备注13'),
(2, '中山大学', '徐涛', '思政课程组', 'sysu_xt', '123456', '徐涛', '2024-06-27 23:00:00', '徐涛', '2024-06-28 23:00:00', '备注14'),
(1, '哈尔滨工业大学', '杨刚', '公共基础课程组', 'hit_yg', '123456', '杨刚', '2024-06-29 00:00:00', '杨刚', '2024-06-30 00:00:00', '备注15'),
(2, '天津大学', '吴芳', '思政课程组', 'tju_wf', '123456', '吴芳', '2024-07-01 09:00:00', '吴芳', '2024-07-02 09:00:00', '备注16'),
(1, '南开大学', '周强', '公共基础课程组', 'nankai_zq', '123456', '周强', '2024-07-03 10:00:00', '周强', '2024-07-04 10:00:00', '备注17'),
(2, '四川大学', '李红', '思政课程组', 'scu_lh', '123456', '李红', '2024-07-05 11:00:00', '李红', '2024-07-06 11:00:00', '备注18'),
(1, '中南大学', '陈刚', '公共基础课程组', 'csu_cg', '123456', '陈刚', '2024-07-07 12:00:00', '陈刚', '2024-07-08 12:00:00', '备注19'),
(2, '华中科技大学', '王丽', '思政课程组', 'hust_wl', '123456', '王丽', '2024-07-09 13:00:00', '王丽', '2024-07-10 13:00:00', '备注20');



CREATE TABLE `events_works` (
  `work_id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '作品ID',
  `account_id` BIGINT NOT NULL COMMENT '参赛账号ID',
  `work_school` VARCHAR(50) NOT NULL COMMENT '学校',
  `work_group` VARCHAR(30) NOT NULL COMMENT '报名组别',
  `work_name` VARCHAR(100) NOT NULL COMMENT '作品名称',
  `work_author` VARCHAR(50) NOT NULL COMMENT '参赛姓名',
  `work_details` TEXT COMMENT '作品详情',
  `contact_info` VARCHAR(50) NOT NULL COMMENT '联系方式',
  `average_score` DECIMAL(5,2) DEFAULT NULL COMMENT '实施平均分',
  `submission_time` DATETIME NOT NULL COMMENT '提交时间',
  PRIMARY KEY (`work_id`),
  CONSTRAINT `eventsfk_works_account` FOREIGN KEY (`account_id`) REFERENCES `events_account` (`account_id`) ON DELETE CASCADE ON UPDATE CASCADE
);


INSERT INTO `events_works` (`account_id`, `work_school`, `work_group`, `work_name`, `work_author`, `work_details`, `contact_info`, `average_score`, `submission_time`) VALUES
(1, '许昌学院', '公共基础课程组', '作品一', '张三', '这是作品一的详情', '1234567890', 85.50, '2024-06-01 10:00:00'),
(2, '清华大学', '思政课程组', '作品二', '李明明', '这是作品二的详情', '0987654321', 90.75, '2024-06-03 11:00:00'),
(3, '青岛职业学院', '公共基础课程组', '作品三', '刘明', '这是作品三的详情', '1122334455', 88.00, '2024-06-05 12:00:00'),
(4, '北京大学', '思政课程组', '作品四', '王华', '这是作品四的详情', '2233445566', 92.25, '2024-06-07 13:00:00'),
(5, '南京大学', '专业技能课程一组', '作品五', '赵强', '这是作品五的详情', '3344556677', 89.50, '2024-06-09 14:00:00'),
(6, '复旦大学', '专业技能课程二组', '作品六', '陈晓', '这是作品六的详情', '4455667788', 91.00, '2024-06-11 15:00:00');


-- 设置字符集
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- 如果表已存在，则删除
DROP TABLE IF EXISTS `events_evaluate`;

-- 创建评价表并关联到 events_works 表
CREATE TABLE `events_evaluate` (
  `evaluate_id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '评价ID',
  `work_id` BIGINT NOT NULL COMMENT '作品ID',
  `school` VARCHAR(50) NOT NULL COMMENT '学校',
  `work_name` VARCHAR(100) NOT NULL COMMENT '作品名称',
  `judge_name` VARCHAR(50) NOT NULL COMMENT '评委姓名',
  `score` DECIMAL(5,2) NOT NULL COMMENT '得分',
  `evaluation_content` TEXT COMMENT '评价内容',
  PRIMARY KEY (`evaluate_id`),
  CONSTRAINT `eventsfk_evaluate_works` FOREIGN KEY (`work_id`) REFERENCES `events_works` (`work_id`) ON DELETE CASCADE ON UPDATE CASCADE
);

-- 恢复外键检查
SET FOREIGN_KEY_CHECKS = 1;

-- 插入示例数据
INSERT INTO `events_evaluate` (`work_id`, `school`, `work_name`, `judge_name`, `score`, `evaluation_content`) VALUES
(1, '许昌学院', '作品一', '评委A', 85.00, '作品一的评价内容'),
(2, '清华大学', '作品二', '评委B', 90.00, '作品二的评价内容'),
(3, '青岛职业学院', '作品三', '评委C', 88.50, '作品三的评价内容'),
(4, '北京大学', '作品四', '评委D', 92.00, '作品四的评价内容'),
(5, '南京大学', '作品五', '评委E', 89.50, '作品五的评价内容'),
(6, '复旦大学', '作品六', '评委F', 91.00, '作品六的评价内容');




























