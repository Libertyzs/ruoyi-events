# 活动项目

> 本活动项目中主要分为三部分：**账号管理、作品管理、评价管理**
>
> ReadMe只对其中的**账号管理模块**进行分析，其他的雷同。



## 账号管理



![image-20240604140843921](https://img2023.cnblogs.com/blog/2794716/202406/2794716-20240604140842677-2035963817.png)



![image-20240604150249373](https://img2023.cnblogs.com/blog/2794716/202406/2794716-20240604150248272-380375259.png)







### 实现效果展示

页面布局显示

<img src="https://img2023.cnblogs.com/blog/2794716/202406/2794716-20240604112118090-273364043.png" alt="image-20240604112119134" style="zoom: 33%;" />



增加功能

<img src="https://img2023.cnblogs.com/blog/2794716/202406/2794716-20240604120013292-517135469.png" alt="image-20240604120014979" style="zoom:50%;" />

修改功能

<img src="https://img2023.cnblogs.com/blog/2794716/202406/2794716-20240604115842478-1141186065.png" alt="image-20240604115843748" style="zoom:50%;" />

搜索功能

<img src="https://img2023.cnblogs.com/blog/2794716/202406/2794716-20240604120118126-146872094.png" alt="image-20240604120119774" style="zoom: 33%;" />

导出功能

<img src="https://img2023.cnblogs.com/blog/2794716/202406/2794716-20240604120210278-313969117.png" alt="image-20240604120212004" style="zoom: 50%;" />







### 设计数据库表

#### 原型图

根据页面原型设计数据库字段，这里出于长期考虑，使用另一个表存储页面中的身份字段，一遍后续可能添加更多的身份，并且对应前端的下拉框内容。

报名组别字段直接在前端限制内容输入，不在后台进行其他处理。

原型设计界面如下图1.1

<img src="https://img2023.cnblogs.com/blog/2794716/202406/2794716-20240604081929603-511545085.png" alt="image-20240604081929659" style="zoom:67%;" />

<center style ="font-size:14px;text-decoration:underline">图1.1</center>

#### SQL

events_roles表：用于存储账号角色信息

```sql
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
```

events_account表：用于存储用户账号信息

```sql
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

```





#### 数据库设计分析

在events_account表的设计中，使用外键关联另一个表，这里是account表中的role_id关联roles表中的主键。

这里设置规则是，当父表 events_roles 中的记录被删除时，子表 events_account 中引用该记录的外键列 role_id 将被设置为 NULL

```mysql
#当父表中的记录被删除时，子表中引用该记录的外键列将被设置为 NULL
constraint `eventsfk_acc_role` FOREIGN KEY (`role_id`) REFERENCES `events_roles` (`role_id`) ON DELETE SET NULL ON UPDATE CASCADE
```



```sql
-- 设置连接的字符集为 utf8mb4，支持更多的Unicode字符
SET NAMES utf8mb4;

-- 禁用外键检查，以避免在添加外键约束时可能出现的约束冲突
SET FOREIGN_KEY_CHECKS = 0;
```



### 需求分析设计

账号列表查询页：查询条件为

1） 身份：下拉框，下拉项为“教师、评委“；

2） 学校：输入框；

3） 报名组别：下拉框，下拉项为“思政课程组、公共基础课程组（不含思政）、专业技能课程一组、专业技能课程二组“；



 添加新增：点击新增按钮弹框展示新增页面

<img src="https://img2023.cnblogs.com/blog/2794716/202406/2794716-20240604084334959-1994368976.png" alt="image-20240604084335274" style="zoom:50%;" />



### 关键代码分析

> 主要是分析后端代码

#### 查询实现

这里面的权限字段的实现可以不用管，对于ruoyi的admin账号来说权限是 * : * : * 可以访问全部接口

以及在底层这里并没有使用使用若依中的数据权限过滤

前端请求路径 http://localhost/dev-api/events/account/list?pageNum=1&pageSize=10

nginx反向代理+SpringMVC到该controller层的list处理器

```java
/**
 * 查询账号管理列表
 */
@PreAuthorize("@ss.hasPermi('events:account:list')")
@GetMapping("/list")
public TableDataInfo list(EventsAccount eventsAccount)
{
    startPage();
    List<EventsAccount> list = eventsAccountService.selectEventsAccountList(eventsAccount);
    return getDataTable(list);
}
```

startPage()方法内容其实还是使用了PageHelper插件的分页功能

内部通过HttpServletRequest拿到路径参数给PageHelper的startPage方法

PageHelper.startPage(pageNum, pageSize, orderBy).setReasonable(reasonable);



然后直接从数据库查询数据返回给前端就可以了

简单的sql语句--左外连接+模糊查询

```sql
<sql id="selectEventsAccountVo">
    select account_id ,events_account.role_id,e.role_name as roleName, account_school, account_name, account_group, account_nick, account_password, create_by, create_time, update_by, update_time, remark from events_account left join events_roles e on e.role_id = events_account.role_id
</sql>

<select id="selectEventsAccountList" parameterType="EventsAccount" resultMap="EventsAccountResult">
    <include refid="selectEventsAccountVo"/>
    <where>
        <if test="roleId != null "> and events_account.role_id = #{roleId}</if>
        <if test="accountSchool != null  and accountSchool != ''"> and account_school = #{accountSchool}</if>
        <if test="accountGroup != null  and accountGroup != ''"> and account_group = #{accountGroup}</if>
        <if test="accountNick != null  and accountNick != ''"> and account_nick like concat('%',#{accountNick},'%')</if>
     </where>
</select>
```



#### 增加功能

前端表单，这里使用了下拉框

<img src="https://img2023.cnblogs.com/blog/2794716/202406/2794716-20240604122248345-865050431.png" alt="image-20240604122249416" style="zoom: 50%;" />



http://localhost/dev-api/events/account

<img src="https://img2023.cnblogs.com/blog/2794716/202406/2794716-20240604122403266-882190791.png" alt="image-20240604122404266" style="zoom:50%;" />

插入数据

<img src="https://img2023.cnblogs.com/blog/2794716/202406/2794716-20240604122451014-942341740.png" alt="image-20240604122452713" style="zoom: 33%;" />



#### 导出功能

前端请求：http://localhost/dev-api/events/account/export

post传递内容：pageNum=3&pageSize=10



导出处理器

```java
/**
 * 导出账号管理列表
 */
@PreAuthorize("@ss.hasPermi('events:account:export')")
@Log(title = "账号管理", businessType = BusinessType.EXPORT)
@PostMapping("/export")
public void export(HttpServletResponse response, EventsAccount eventsAccount)
{
    List<EventsAccount> list = eventsAccountService.selectEventsAccountList(eventsAccount);
    ExcelUtil<EventsAccount> util = new ExcelUtil<EventsAccount>(EventsAccount.class);
    util.exportExcel(response, list, "账号管理数据");
}
```



查询出account表中的所有数据

```
List<EventsAccount> list = eventsAccountService.selectEventsAccountList(eventsAccount);
```

若依内置的ExcelUtil工具类导出excel

```java
ExcelUtil<EventsAccount> util = new ExcelUtil<EventsAccount>(EventsAccount.class);
util.exportExcel(response, list, "账号管理数据");
```



```java
public void exportExcel(HttpServletResponse response, List<T> list, String sheetName, String title)
{
    response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
    response.setCharacterEncoding("utf-8");
    // 这里将查询的list赋值给ExcelUtil的成员变量
    this.init(list, sheetName, title, Type.EXPORT);
    //继续往下走
    exportExcel(response);
}
```



```java
/**
 * 创建并写入数据到 Excel 的 Sheet 中
 */
public void writeSheet()
{
    // 计算需要创建多少个 sheet
    int sheetNo = Math.max(1, (int) Math.ceil(list.size() * 1.0 / sheetSize));
    
    // 循环创建每个 sheet
    for (int index = 0; index < sheetNo; index++)
    {
        // 创建当前索引下的 sheet
        createSheet(sheetNo, index);

        // 创建一行
        Row row = sheet.createRow(rownum);
        int column = 0;
        
        // 遍历字段列表，为每个字段创建列头名称
        for (Object[] os : fields)
        {
            // 获取字段和对应的 Excel 注解
            Field field = (Field) os[0];
            Excel excel = (Excel) os[1];
            
            // 如果字段是集合类型，则需要进一步遍历处理子字段
            if (Collection.class.isAssignableFrom(field.getType()))
            {
                // 遍历子字段列表
                for (Field subField : subFields)
                {
                    // 获取子字段的 Excel 注解
                    Excel subExcel = subField.getAnnotation(Excel.class);
                    // 创建子字段的列头名称
                    this.createHeadCell(subExcel, row, column++);
                }
            }
            else
            {
                // 创建字段的列头名称
                this.createHeadCell(excel, row, column++);
            }
        }
        
        // 如果是导出操作，则填充 Excel 数据并添加统计行
        if (Type.EXPORT.equals(type))
        {
            fillExcelData(index, row); // 填充 Excel 数据
            addStatisticsRow(); // 添加统计行
        }
    }
}

```





















