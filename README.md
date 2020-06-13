Run database:

```
docker run -d -p 3306:3306 --name mysql -e MYSQL_ROOT_PASSWORD=hotel@123 -e MYSQL_USER=hotel -e MYSQL_PASSWORD=hotel@123 mysql:latest
```