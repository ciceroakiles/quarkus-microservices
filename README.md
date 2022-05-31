# quarkus-productcatalog

https://github.com/ciceroakiles/springcloud-microservices/tree/master/product-catalog

Microsserviço "product-catalog" originalmente feito com Spring, agora refeito em Quarkus.

(Os comandos Docker e as requisições HTTP de lá, expostos a seguir, também se aplicam aqui)

* Configuração de um container ElasticSearch com dados voláteis:
```
docker pull docker.elastic.co/elasticsearch/elasticsearch:7.17.4
docker run -d --name (name) -p 9200:9200 -p 9300:9300 -e "discovery.type=single-node" docker.elastic.co/elasticsearch/elasticsearch:7.17.4
```

* POST no endpoint produto:
```
curl -X POST http://localhost:8081/api/v1/produto -H "Content-Type:application/json" -d "{\"id\":1,\"name\":\"Nome do produto\",\"qtde\":10}"
```

* GET do registro salvo no ElasticSearch:
```
curl http://localhost:9200/produto/catalogo/1
```
