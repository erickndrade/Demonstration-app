package com.app.demo.resources;

import com.app.demo.domain.Produto;
import com.app.demo.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoResource {

    @Autowired
    private ProdutoService service;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Produto> find(@PathVariable Integer id) {
        Produto obj = service.find(id);
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(method = RequestMethod.POST) // Void pois o body está vazio.
    public void insert(@Valid @RequestBody Produto obj) {
        service.insert(obj);
      // Requisitando um body válido, com o nome e o ID.
     // Chamando Insert em ProdutoService, passando os dados vindos do usuário.

    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<Produto> findAll(){
        return service.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public void update(@Valid @RequestBody Produto produto, @PathVariable Integer id){
        produto.setId(id);
        service.update(produto);

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete (@PathVariable Integer id){
    service.delete(id);
    }
}
