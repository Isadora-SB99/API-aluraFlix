package br.com.alura.aluraflix.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "videos")
public class Videos {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Integer id;

    @Column(nullable = false)
    @NotBlank
    @Getter @Setter
    private String titulo;

    @Column(nullable = false)
    @NotBlank
    @Getter @Setter
    private String descricao;

    @Column(nullable = false, unique = true)
    @NotBlank
    @URL
    @Getter @Setter
    private String url;

    @ManyToOne
    @JoinColumn(name = "categoriaId", columnDefinition = "int default 1", nullable = false)
    @Getter @Setter
    private Categorias categoria;

    public Videos() {
    }

    public Videos(String titulo, String descricao, String url, Categorias categoria) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.url = url;
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "Videos{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", descricao='" + descricao + '\'' +
                ", url='" + url + '\'' +
                ", categoria=" + categoria +
                '}';
    }
}
