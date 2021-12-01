package com.app.demo.services;

import com.app.demo.domain.Categoria;
import com.app.demo.repositories.CategoriaRepository;
import com.app.demo.services.exception.ObjNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repository;

    public Categoria find(Integer id) {
        Optional<Categoria> obj = repository.findById(id); // como método if.
        return obj.orElseThrow(() -> new ObjNotFoundException("Não encontrado! "));
    }

    public void insert(Categoria obj) {
        obj.setId(null);
        repository.save(obj);
    }

    public List<Categoria> findAll() {
        return repository.findAll();
    }

    public void update(Categoria categoria) {
        Categoria novaCat = find(categoria.getId());
        if (categoria.getNome() == null){
            categoria.setNome(novaCat.getNome());
        }else{
            novaCat.setNome(categoria.getNome());
        }
        if (categoria.getIdade() == null){
            categoria.setIdade(novaCat.getIdade());
        }else{
            novaCat.setIdade(categoria.getIdade());
        }
        repository.save(novaCat);
    }

    public void delete(Integer id) {
        find(id);
        repository.deleteById(id);
    }
}
