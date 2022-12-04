﻿# DestLibrary 1.0

<h2>Информация</h2>
<hr>
Данная бибилотека представляет из себя пакет классов, утилит и методов для
разработки плагинов для Minecraft под платформу Bukkit.

<h2>Подключение библиотеки</h2>
<hr>
Подключение при помощи maven (Текущая версия - 1.1.0):

```xml
<repositories>
    <repository>
        <id>destkoder-repo</id>
        <url>https://destkoder.github.io/maven/</url>
    </repository>
</repositories>

<dependencies>
    <dependency>
        <groupId>ru.dest</groupId>
        <artifactId>DLibrary</artifactId>
        <version>{VERSION}</version>
        <scope>provided</scope>
    </dependency>
</dependencies>
```

<h2>Использование библиотеки</h2>

```java

import java.security.Permission;

public class MyPlugin extends BukkitPlugin<MyPlugin> {

    @Override
    public void onLoad() {
        super.onLoad();
    }

    @Override
    public void onEnable() {
        super.onEnable();

        //Your code here
    }

    @Override
    public void onDisable() {
        super.onDisable();

        //Your code here
    }
}
```

Документация по бибилиотеке: https://destkoder.github.io/docs/DLibrary/
