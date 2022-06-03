# quarkus-redisresource

Microsserviço para testes com o Redis.

* Comando do Docker para preparar o container:
```
docker run -d --name (name) -p 6379:6379 redis:5.0.6
```

* Exemplos de requisições:
```
curl "localhost:8082/api/v1/redis?chave=1&valor=10"
```
```
curl "localhost:8082/api/v1/redis/add?chave=1"
```
```
curl "localhost:8082/api/v1/redis/itens?chave=lista&valor=item1"
```
```
curl -X DELETE "localhost:8082/api/v1/redis?chave=1"
```
