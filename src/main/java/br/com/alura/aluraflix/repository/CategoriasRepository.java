package br.com.alura.aluraflix.repository;

import br.com.alura.aluraflix.model.Categorias;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface CategoriasRepository extends JpaRepository<Categorias, Integer> {

    Categorias findByTitulo (String titulo);
}
