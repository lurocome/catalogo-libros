package com.aluracursos.catalogo_libros.model;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "autores")

public class Autor {
    @Id //primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     Long id;
     @Column(unique = true)
     String nombre;
     String fechaDeNacimiento;
     String fechaDeMuerte;
     @OneToMany(mappedBy = "autor",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
     private List<Libro> libros= new ArrayList<>();

    public Autor() {
    }

    public Autor(DatosAutor datosAutor){
        //this.nombre=String.valueOf(datosAutor.nombre());
        this.nombre=datosAutor.nombre();
        this.fechaDeNacimiento= datosAutor.fechaDeNacimiento();
        this.fechaDeMuerte= datosAutor.fechaDeMuerte();
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public void setFechaDeNacimiento(String fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    public String getFechaDeMuerte() {
        return fechaDeMuerte;
    }

    public void setFechaDeMuerte(String fechaDeMuerte) {
        this.fechaDeMuerte = fechaDeMuerte;
    }

    @Override
    public String toString() {
        return
                " nombre='" + nombre + '\'' +
                ", fechaDeNacimiento='" + fechaDeNacimiento + '\'' +
                ", fechaDeMuerte='" + fechaDeMuerte + '\'' ;

    }
}
