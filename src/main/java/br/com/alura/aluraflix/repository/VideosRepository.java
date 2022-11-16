package br.com.alura.aluraflix.repository;

import br.com.alura.aluraflix.model.Videos;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VideosRepository extends JpaRepository<Videos, Integer> {

    Page<Videos> findByTituloContainingIgnoreCase(String titulo, Pageable pageable);

    @Query(value = "select * from videos where categoria_id = :id", nativeQuery = true)
    List<Videos> findByCategoria (@Param("id") int id);
}
