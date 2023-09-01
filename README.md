# DI

Задача: нужно смигрировать ваше приложение на сервлетах, написанное в предыдущих домашних заданиях, на Spring Web MVC с Embed Tomcat.

Тема: Spring Web MVC

[Условие задачи](https://github.com/netology-code/jspr-homeworks/tree/master/06_mvc)

### Обоснование реализации в определенном слое:

Рассмотрим, где было бы наиболее разумно реализовать такую логику:

- Слой базы данных: Прямое изменение таблицы или использование триггеров. Это сложно поддерживать и не предоставляет достаточной гибкости. 
- Слой DAO (Data Access Object): DAO отвечает за непосредственное взаимодействие с базой данных. Этот слой может скрывать логику работы с флагом "удалено", обеспечивая тем самым корректное взаимодействие с объектами.
- Слой сервисов: Слой сервисов обычно содержит бизнес-логику приложения. Именно здесь и стоит реализовать логику работы с флагом "удалено", так как это непосредственно связано с бизнес-правилами работы с данными.

Для нашего случая наиболее разумно реализовать данную логику на слое сервисов. Ведь именно этот слой отвечает за бизнес-правила и обеспечивает выполнение определенных операций над объектами в соответствии с этими правилами.