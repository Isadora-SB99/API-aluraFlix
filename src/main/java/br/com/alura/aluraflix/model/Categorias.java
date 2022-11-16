package br.com.alura.aluraflix.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.ResponseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.awt.*;
import java.util.List;

@Entity
@Table(name = "categorias")
public class Categorias {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Integer id;

    @Column(nullable = false, unique = true)
    @NotBlank(message = "O campo é obrigatório")
    @Getter @Setter
    private String titulo;

    @Column(nullable = false, unique = true, length = 6)
    @NotBlank(message = "O campo é obrigatório")
    @Getter @Setter
    private String cor;

    public Categorias() {
    }

    public Categorias(String titulo, String cor) {
        this.titulo = titulo;
        this.cor = cor;
    }

    @Transient
    public boolean validaCor(String cor){
        if (cor.length()!=6){
            throw new IllegalArgumentException("A cor deve ter 6 caracteres");
        }
        else {
            for (int i = 0; i < cor.length(); i++) {
                char c = cor.charAt(i);
                if ((c < '0' || c > '9') && (c < 'A' || c > 'F')) {
                    return false;
                }
            }
            return true;
        }

    }

    @Override
    public String toString() {
        return "Categorias{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", cor='" + cor + '\'' +
                '}';
    }
}
