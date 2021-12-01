package com.app.demo.services;

import com.app.demo.domain.Cliente;
import com.app.demo.repositories.ClienteRepository;
import com.app.demo.services.exception.ObjNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    public Cliente find(Integer id) {
        Optional<Cliente> obj = repository.findById(id); // como método if.
        return obj.orElseThrow(() -> new ObjNotFoundException("Não encontrado! "));
    }

    public void insert(Cliente obj) {
        obj.setId(null);
        repository.save(obj);
    }

    public List<Cliente> findAll() {
        return repository.findAll();
    }

    public void update(Cliente cliente) {
        Cliente novoCliente = find(cliente.getId());
        if (cliente.getNome() == null) {
            cliente.setNome(novoCliente.getNome());
        } else {
            novoCliente.setNome(cliente.getNome());
        }
        if (cliente.getCpf() == null){
            cliente.setCpf(novoCliente.getCpf());
        }else {
            novoCliente.setCpf(cliente.getCpf());
        }
        if (cliente.getEmail() == null){
            cliente.setEmail(novoCliente.getEmail());
        }else {
            novoCliente.setEmail(cliente.getEmail());
        }
        if (cliente.getSenha() == null){
            cliente.setSenha(novoCliente.getSenha());
        }else {
            novoCliente.setSenha(cliente.getSenha());
        }

        repository.save(novoCliente);
    }

    public void delete(Integer id) {
        find(id);
        repository.deleteById(id);
    }
}
