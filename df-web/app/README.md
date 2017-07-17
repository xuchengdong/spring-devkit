## debug 模式启动
```java -server -Xdebug -Xnoagent -Djava.compiler=NONE -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=9000 -jar app\build\libs\app-1.0-SNAPSHOT.jar --spring.profiles.active=development```
## 正常模式启动
```
java -jar app\build\libs\app-1.0-SNAPSHOT.jar --spring.profiles.active=development
java -jar app\build\libs\app-1.0-SNAPSHOT.jar --spring.profiles.active=production
```