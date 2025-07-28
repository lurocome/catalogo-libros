package com.aluracursos.catalogo_libros.repository;

import com.aluracursos.catalogo_libros.model.Autor;
import com.aluracursos.catalogo_libros.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LibroRepository extends JpaRepository<Libro,Long> {
    Optional<Libro> findByTitulo(String nombreLibro);

}
