package com.sample.repository;

import javax.enterprise.context.ApplicationScoped;

import com.sample.entity.Produto;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class ProdutoRepository implements PanacheRepository<Produto> {
    
    public Produto findById(Long id) {
        return find("id", id).firstResult();
    }
}
