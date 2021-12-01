package com.app.demo.resources;

import com.app.demo.domain.Cliente;
import com.app.demo.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

    @Autowired
    private ClienteService service;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Cliente> find(@PathVariable Integer id) {
        Cliente obj = service.find(id);
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(method = RequestMethod.POST) // Void pois o body está vazio.
    public void insert(@Valid @RequestBody Cliente obj) {
        service.insert(obj);
      // Requisitando um body válido, com o nome e o ID.
     // Chamando Insert em ClienteService, passando os dados vindos do usuário.

    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<Cliente> findAll(){
        return service.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public void update(@Valid @RequestBody Cliente cliente, @PathVariable Integer id){
        cliente.setId(id);
        service.update(cliente);

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete (@PathVariable Integer id){
    service.delete(id);
    }
}
