#这是一个包的描述

/*修改时间:2019/1/15  修改人：罗怡  修改内容：为便于书写Mapper.xml将员工表涉及的增删改查方法整合到一个接口IEmpDao*/
```java
/* 
 * @author EchoMao
 * @date 2019.01.10
 * >对本文件有修改请写明修改描述,并注明作者与时间
 */
```
***DAO包***

这是数据访问对象包,主要放置数据访问层**接口**,请注意模块分包,并遵循以下约定:

- 接口应摒弃访问修饰符,使用默认方式
- 请勿编写"大而全"的接口,应尽量使接口功能单一
- 每个接口中的方法不应超过3个
- 请尽量不要修改原有代码,如有新加功能请创建新接口
- 请为你的属性/方法/类/接口等编写注释,并在注释中注明作者,时间及配以简洁的说明
- 请尽量避免在同一个类/接口中定义两个或多个相似的方法名
- mapper映射文件应统一放入src/main/resources/mappers/文件夹下
- 接口名应以 I+[模块名+功能名]+Dao 为标准格式,示例:IUserSelectDao
- 方法名应以 [操作]+[目标]+[介词]+[查找方式] 为标准格式,示例:findUserByUserId,findEmpByNameAndEmpPwd等

