package com.aluracursos.catalogo_libros.model;

import jakarta.persistence.*;

import java.util.List;

@Entity //Convertir en tabla
@Table(name="libros")
public class Libro {
   @Id //primary key
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
    private String titulo;
   private String idioma;
   private Double numeroDescargas;
    @ManyToOne
   // @JoinColumn(name="autor_id")
    private Autor autor;


    public Libro() {
    }

    public Libro(DatosLibro datosLibro,Autor autor){
       this.titulo=datosLibro.titulo();
       this.autor=autor;
        //this.autor=datosLibro.autor();
       this.idioma=datosLibro.idiomas().get(0);
       this.numeroDescargas=datosLibro.numeroDescargas();
    }


    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }



    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public Double getNumeroDescargas() {
        return numeroDescargas ;
    }

    public void setNumeroDescargas(Double numeroDescargas) {
        numeroDescargas = numeroDescargas;
    }

    @Override
    public String toString() {
        return
                " titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                ", idioma='" + idioma + '\'' +
                ", NumeroDescargas=" + numeroDescargas ;

    }
}
