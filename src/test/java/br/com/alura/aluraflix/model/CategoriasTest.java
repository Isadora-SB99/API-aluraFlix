package br.com.alura.aluraflix.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class CategoriasTest {

    //testes do metodo de validação da cor

    @Test
    public void validaCorInvalida (){
        Categorias categoria = new Categorias();
        categoria.setCor("pmtrw");
        //boolean corValidada = categoria.validaCor(categoria.getCor());
        Assertions.assertThrows(IllegalArgumentException.class,
                ()-> categoria.validaCor(categoria.getCor()));
    }

    @Test
    public void validaCorInvalida1 (){
        Categorias categoria = new Categorias();
        categoria.setCor("pmtrws");
        boolean corValidada = categoria.validaCor(categoria.getCor());
        Assertions.assertFalse(corValidada);
    }

    @Test
    public void validaCorInvalida2 (){
        Categorias categoria = new Categorias();
        categoria.setCor("P0254W");
        boolean corValidada = categoria.validaCor(categoria.getCor());
        Assertions.assertFalse(corValidada);
    }

    @Test
    public void validaCorInvalida3 () {
        String mensagem = "A cor deve ter 6 caracteres";
        Categorias categoria = new Categorias();
        categoria.setCor("PMTRW");
        //boolean corValidada = categoria.validaCor(categoria.getCor());
        Assertions.assertThrows(IllegalArgumentException.class,
                ()-> categoria.validaCor(categoria.getCor()));
    }

    @Test
    public void validaCorInvalida31 (){
        Categorias categoria = new Categorias();
        categoria.setCor("PMTRWS");
        boolean corValidada = categoria.validaCor(categoria.getCor());
        Assertions.assertFalse(corValidada);
    }

    @Test
    public void validaCorValida (){
        Categorias categoria = new Categorias();
        categoria.setCor("000000");
        boolean corValidada = categoria.validaCor(categoria.getCor());
        Assertions.assertTrue(corValidada);
    }

    @Test
    public void validaCorValida2 (){
        Categorias categoria = new Categorias();
        categoria.setCor("ABCDEF");
        boolean corValidada = categoria.validaCor(categoria.getCor());
        Assertions.assertTrue(corValidada);
    }

    //testes do construtor

    @Test
    public void construtorCategoriasComCorInvalida(){
        Categorias cat = new Categorias("titulo", "mrt026");
        System.out.println(cat);
    }




}
