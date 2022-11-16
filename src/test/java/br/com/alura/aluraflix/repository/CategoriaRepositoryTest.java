package br.com.alura.aluraflix.repository;

import br.com.alura.aluraflix.model.Categorias;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class CategoriaRepositoryTest {

    @Autowired
    private CategoriasRepository repository;

    @Autowired
    private TestEntityManager em;

    @Test
    public void deveriaEncontrarCategoriaPeloTitulo(){
        String tituloCategoria = "Livre";

        Categorias livre = new Categorias();
        livre.setTitulo(tituloCategoria);
        livre.setCor("000000");
        em.persist(livre);

        Categorias categoria = repository.findByTitulo(tituloCategoria);
        Assertions.assertNotNull(categoria);
        Assertions.assertEquals(tituloCategoria, categoria.getTitulo());
    }

    @Test
    public void naoDeveriaEncontrarCategoriaPeloTituloInexistente(){
        String tituloCategoria = "Bolos";
        Categorias categoria = repository.findByTitulo(tituloCategoria);
        Assertions.assertNull(categoria);
    }
}
