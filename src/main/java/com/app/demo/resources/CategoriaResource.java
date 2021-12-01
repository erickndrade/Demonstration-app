package com.app.demo.resources;


import com.app.demo.domain.Categoria;
import com.app.demo.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

    @Autowired
    private CategoriaService service;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Categoria> find(@PathVariable Integer id) {
        Categoria obj = service.find(id);
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(method = RequestMethod.POST) // Void pois o body está vazio.
    public void insert(@Valid @RequestBody Categoria obj) {
        service.insert(obj);
      // Requisitando um body válido, com o nome e o ID.
     // Chamando Insert em CategoriaService, passando os dados vindos do usuário.

    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<Categoria> findAll(){
        return service.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public void update(@Valid @RequestBody Categoria categoria, @PathVariable Integer id){
        categoria.setId(id);
        service.update(categoria);

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete (@PathVariable Integer id){
    service.delete(id);
    }
}
