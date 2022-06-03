package com.sample.service;

import java.io.IOException;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.Response;
import io.vertx.core.json.JsonObject;
import com.sample.client.ElasticsearchClient;
import com.sample.entity.Produto;

@ApplicationScoped
public class ProdutoService {
    
    @Inject
    private ElasticsearchClient esClient;

    // GET
    public Produto get(String id) throws IOException {
        Request request = new Request("GET", "/produto/catalogo/" + id);
        Response response = esClient.getResponse(request);
        String responseBody = EntityUtils.toString(response.getEntity());
        JsonObject json = new JsonObject(responseBody); 
        return json.getJsonObject("_source").mapTo(Produto.class);
    }

    // PUT
    public void index(Produto produto) throws IOException {
        Request request = new Request("PUT", "/produto/catalogo/" + produto.getId()); 
        request.setJsonEntity(JsonObject.mapFrom(produto).toString()); 
        esClient.getResponse(request);
    }
}
