# SpringUserService
BostonGene test project

Сборка и запуск:
1. mvn clean install
2. java -jar <файл из target>

Использование:
1. Добавление пользователя:  
POST-запрос http://localhost:8080/   
Пример тела запроса:  
{"firstName": "Lev","secondName": "Tolstoy","birthDate": "1828-09-09", "email": "tolstoy@mail.ru", "password": "18280909"}  
Ответ: статус 200 OK, {"id":1,"firstName":"Lev","secondName":"Tolstoy","birthDate":"1828-09-09T00:00:00.000+0000","email":"tolstoy@mail.ru","password":"$2a$10$G85t9RDMCq6B/vTouOCz.urZCdXH8GefhatfHW.nSbsBJsa2iEwJO"}
2. Поиск пользователя по email:  
GET-запрос http://localhost:8080/{email}  
Пример: http://localhost:8080/tolstoy@mail.ru  
Ответ: статус 200 OK, {"id":1,"firstName":"Lev","secondName":"Tolstoy","birthDate":"1828-09-09T00:00:00.000+0000","email":"tolstoy@mail.ru","password":"$2a$10$G85t9RDMCq6B/vTouOCz.urZCdXH8GefhatfHW.nSbsBJsa2iEwJO"} 
3. Удаление пользователя:  
DELETE-запрос http://localhost:8080/{email}  
Пример: http://localhost:8080/tolstoy@mail.ru  
Ответ: статус 200 OK, "User Lev Tolstoy was deleted."
