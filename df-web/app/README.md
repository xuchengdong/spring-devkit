## debug 模式启动
```java -server -Xdebug -Xnoagent -Djava.compiler=NONE -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=9000 -jar app\build\libs\app-1.0-SNAPSHOT.jar --spring.profiles.active=development```
## 正常模式启动
```
java -jar app\build\libs\app-1.0-SNAPSHOT.jar --spring.profiles.active=development
java -jar app\build\libs\app-1.0-SNAPSHOT.jar --spring.profiles.active=production
```
## Properties 属性优先级
[boot-features-external-config](http://docs.spring.io/spring-boot/docs/1.5.4.RELEASE/reference/htmlsingle/#boot-features-external-config)
[Application property files](http://docs.spring.io/spring-boot/docs/1.5.4.RELEASE/reference/htmlsingle/#boot-features-external-config-application-property-files)
```cmd
command-line-argument > System-Variable > environment-variable > Application property files(application.yml)

java -Dname=System-Variable  -jar app\build\libs\app-1.0-SNAPSHOT.jar --spring.profiles.active=development --name=command-line-argument

java -Dname=System-Variable  -jar app\build\libs\app-1.0-SNAPSHOT.jar --spring.profiles.active=development

set name=environment-variable java -jar app\build\libs\app-1.0-SNAPSHOT.jar --spring.profiles.active=development

java -jar app\build\libs\app-1.0-SNAPSHOT.jar --spring.profiles.active=development
```

### [Logging properties](http://docs.spring.io/spring-boot/docs/1.5.4.RELEASE/reference/htmlsingle/#boot-features-logging-file-output)

logging.file | logging.path | Description 
---|---|---
(none)                      |(none)             |./spring.log
/var/log/df-web/web-app.log | (none)            | /var/log/df-web/web-app.log
(none)                      | /var/log/df-web/  | /var/log/df-web/spring.log
web-app.log                 | /var/log/df-web/  |./spring.log

