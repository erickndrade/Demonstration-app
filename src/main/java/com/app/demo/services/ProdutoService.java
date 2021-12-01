package com.app.demo.services;

import com.app.demo.domain.Produto;
import com.app.demo.repositories.ProdutoRepository;
import com.app.demo.services.exception.ObjNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repository;

    public Produto find(Integer id) {
        Optional<Produto> obj = repository.findById(id); // como método if.
        return obj.orElseThrow(() -> new ObjNotFoundException("Não encontrado! "));
    }

    public void insert(Produto obj) {
        obj.setId(null);
        repository.save(obj);
    }

    public List<Produto> findAll() {
        return repository.findAll();
    }

    public void update(Produto produto) {
        Produto novoProd = find(produto.getId());
        if (produto.getNome() == null){
            produto.setNome(novoProd.getNome());
        }else{
            novoProd.setNome(produto.getNome());
        }
        if (produto.getPreco() == null){
            produto.setPreco(novoProd.getPreco());
        }else{
            novoProd.setPreco(produto.getPreco());
        }
        repository.save(novoProd);
    }

    public void delete(Integer id) {
        find(id);
        repository.deleteById(id);
    }
}
