## Запуск при помощи Docker
#### Режим разработки
Для того чтобы собрать образы на своей машине и запустить контенеры необходимо выполнить команду в папке проекта

docker-compose -f docker-compose.dev.yaml up --build

Для того чтобы запустить только какой то конкретный сервис необхдимо выполнить команду
docker-compose -f docker-compose.dev.yaml up --build [ИмяСервиса]

#### Доступные сервисы
 - event-service - api доступ к инфорации по мероприятиям и событям
 - web-gui - фронт
#### Точки входа 
 - localhost:8081 - event-service
 - localhost:8080 - web-gui