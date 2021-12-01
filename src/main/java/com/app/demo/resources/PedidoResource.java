package com.app.demo.resources;

import com.app.demo.domain.Pedido;
import com.app.demo.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoResource {

    @Autowired
    private PedidoService service;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Pedido> find(@PathVariable Integer id) {
        Pedido obj = service.find(id);
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(method = RequestMethod.POST) // Void pois o body está vazio.
    public void insert(@Valid @RequestBody Pedido obj) {
        service.insert(obj);
      // Requisitando um body válido, com o nome e o ID.
     // Chamando Insert em PedidoService, passando os dados vindos do usuário.

    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<Pedido> findAll(){
        return service.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public void update(@Valid @RequestBody Pedido pedido, @PathVariable Integer id){
        pedido.setId(id);
        service.update(pedido);

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete (@PathVariable Integer id){
    service.delete(id);
    }
}
