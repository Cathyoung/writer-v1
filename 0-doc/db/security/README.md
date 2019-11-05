```
项目中Security包Note: 
此公共模块适用于一般后台管理系统的用户管理体系，可以实现用户的角色和权限管理。
1. 在相关数据库中执行 0-doc/db/common_security.sql 脚本，生成数据库表
2. 该项目默认使用 spring-boot-mybatis 中约定的数据库配置，一般只须将 com.slingerxv.writer.security.mapper 加入 @MapperScan 注解的 basePackages 中
3. LocalAuthorityScanner 可以扫描本项目提供的所有api，并提供函数将权限（我们将权限定义为api url pattern）写入 common_authority 数据库
4. 可以继承 CommonSecurityConfig，加上 @EnableWebSecurity 注解，默认会执行
5. 中描述的功能，默认使用form表单登录，登录成功或失败都会 forward url，也可继承后修改相关配置。使用此配置前务必请查看源代码！ 
```

6. 以下SQL用来生成默认admin用户(密码亦为admin)和administrator角色
