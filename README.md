## Описание тестового задания
Создать сервис, который обращается к сервису курсов валют, и отображает gif:       
если курс по отношению к USD за сегодня стал выше вчерашнего, то отдаем рандомную отсюда [https://giphy.com/search/rich](https://giphy.com/search/rich)   
если ниже - отсюда [https://giphy.com/search/broke](https://giphy.com/search/broke)   
**Ссылки**  
REST API курсов валют - [https://docs.openexchangerates.org/](https://docs.openexchangerates.org/)   
REST API гифок - [https://developers.giphy.com/docs/api#quick-start-guide](https://developers.giphy.com/docs/api#quick-start-guide)   
**Must Have**   
Сервис на Spring Boot 2 + Java / Kotlin
Запросы приходят на HTTP endpoint (должен быть написан в соответствии с rest conventions), туда передается код валюты по отношению с которой сравнивается USD
Для взаимодействия с внешними сервисами используется Feign
Все параметры (валюта по отношению к которой смотрится курс, адреса внешних сервисов и т.д.) вынесены в настройки
На сервис написаны тесты (для мока внешних сервисов можно использовать @mockbean или WireMock)
Для сборки должен использоваться Gradle
Результатом выполнения должен быть репо на GitHub с инструкцией по запуску  
**Nice to Have**   
Сборка и запуск Docker контейнера с этим сервисом   

***
## Запуск
- Склонировать репозиторий, выполнив команду:   
`git clone https://github.com/Andrey-Ul/test_task_ab.git`   
- Перейди в корневую папку проекта и собрать проект:    
`gradlew build`   
- Собрать Docker-образ с произвольным именем (в примере это "anyName"):    
`docker image build -t anyName`   
- Запустить контейнер с собранным Docker-образом:   
`docker run -p 8080:8080 docker.io/library/anyName`   
***
## Endpoints
- `/api/gif`  
Возвращает gif в зависимости от роста / падения курса, переданной в запросе валюты, по отношению к USD.   
**Parameters**   
base: string (буквенный код валюты alpha-3)   
**_Пример_**   
`http://localhost:8080/api/gif?currency=RUB`
------
- `/api/*`  
Возвращает gif в зависимости от роста / падения курса RUB по отношению к USD.  
**_Пример_**   
`http://localhost:8080/api/abc`
