package com.app.demo.services;

import com.app.demo.domain.Pedido;
import com.app.demo.repositories.PedidoRepository;
import com.app.demo.services.exception.ObjNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository repository;

    public Pedido find(Integer id) {
        Optional<Pedido> obj = repository.findById(id); // como método if.
        return obj.orElseThrow(() -> new ObjNotFoundException("Não encontrado! "));
    }

    public void insert(Pedido obj) {
        obj.setId(null);
        repository.save(obj);
    }

    public List<Pedido> findAll() {
        return repository.findAll();
    }

    public void update(Pedido pedido) {
        Pedido novoPed = find(pedido.getId());
        if (pedido.getInstante() == null) {
            pedido.setInstante(novoPed.getInstante());
        } else {
            novoPed.setInstante(pedido.getInstante());
        }
        repository.save(novoPed);
    }

    public void delete(Integer id) {
        find(id);
        repository.deleteById(id);
    }
}
