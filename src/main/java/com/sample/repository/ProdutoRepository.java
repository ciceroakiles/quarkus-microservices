package com.sample.repository;

import java.io.IOException;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;

@ApplicationScoped
public class ProdutoRepository {
    
    @Inject
    RestClient restClient;

    public Response getResponse(Request request) throws IOException {
        return restClient.performRequest(request);
    }
}
