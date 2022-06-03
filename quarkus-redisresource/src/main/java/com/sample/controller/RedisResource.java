package com.sample.controller;

import java.util.Arrays;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import io.quarkus.redis.client.RedisClient;
import io.vertx.redis.client.Response;

@Path("/api/v1/redis")
@Produces(MediaType.APPLICATION_JSON)
public class RedisResource {

    @Inject
    RedisClient redisClient;
    
    @GET
    public JsonObject set(@QueryParam("chave") String chave, @QueryParam("valor") String valor) {
        // Chamada ao Redis
        redisClient.set(Arrays.asList(chave, valor));
        // Criacao do JSON
        JsonObject response = Json.createObjectBuilder()
            .add("chave", chave)
            .add("valor", redisClient.get(chave) + "")
            .build();
        return response;
    }

    @GET
    @Path("add")
    public JsonObject incr(@QueryParam("chave") String chave) {
        // Chamada ao Redis
        redisClient.incr(chave);
        // Criacao do JSON
        JsonObject response = Json.createObjectBuilder()
            .add("chave", chave)
            .add("valor", redisClient.get(chave) + "")
            .build();
        return response;
    }

    @GET
    @Path("itens")
    public JsonObject lpush(@QueryParam("chave") String chave, @QueryParam("valor") String valor) {
        // Chamada ao Redis
        redisClient.lpush(Arrays.asList(chave, valor));
        Response lrange = redisClient.lrange(chave, "0", "-1");
        // Criacao do array do JSON
        Object[] array = lrange.stream().toArray();
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        for (Object o: array) {
            arrayBuilder.add(o.toString());
        }
        // Criacao do JSON
        JsonObject response = Json.createObjectBuilder()
            .add("itens", arrayBuilder)
            .build();
        return response;
    }

    @DELETE
    public void del(@QueryParam("chave") String chave) {
        redisClient.del(Arrays.asList(chave));
    }
}
