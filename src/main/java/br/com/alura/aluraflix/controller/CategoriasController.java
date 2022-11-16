package br.com.alura.aluraflix.controller;

import br.com.alura.aluraflix.model.Categorias;
import br.com.alura.aluraflix.repository.CategoriasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/categorias")
public class CategoriasController {

    @Autowired
    private CategoriasRepository categoriasRepository;

    @GetMapping("/")
    public Iterable<Categorias> listarCategorias (@RequestParam(value = "page") int pagina){

        Pageable paginacao = PageRequest.of(pagina-1, 5);

        return categoriasRepository.findAll(paginacao);
    }

    @GetMapping("/{id}/")
    public ResponseEntity<Optional<Categorias>> obterCategoriaPeloId(@PathVariable int id){
        Optional<Categorias> categoria = categoriasRepository.findById(id);
        if (categoria.isPresent()){
            return ResponseEntity.ok(categoria);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public @ResponseBody ResponseEntity<Object> cadastrarCategoria(@Valid Categorias categoria){
        boolean validada = categoria.validaCor(categoria.getCor());

        if (!validada){
            return ResponseEntity.badRequest().build();
        }
        else {
            categoriasRepository.save(categoria);
            return ResponseEntity.ok(categoria);
        }
    }

    @RequestMapping(method = {RequestMethod.PUT, RequestMethod.PATCH}, path = "/{id}/")
    public ResponseEntity<Categorias> alterarCategoria(@PathVariable int id, @Valid Categorias categoria){
        Optional<Categorias> optional = categoriasRepository.findById(id);
        if (optional.isPresent()){
            categoriasRepository.save(categoria);
            return ResponseEntity.ok(categoria);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}/")
    public ResponseEntity<?> deletarCategorias (@PathVariable int id){
        Optional<Categorias> categoria = categoriasRepository.findById(id);
        if (categoria.isPresent()){
            categoriasRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
