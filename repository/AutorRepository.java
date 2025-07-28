package com.aluracursos.catalogo_libros.repository;

import com.aluracursos.catalogo_libros.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor,Long> {
   // Optional<Autor> findByNombre(String nombreAutor);
    Autor findByNombre(String nombreAutor);

   @Query("SELECT a FROM Autor a WHERE a.fechaDeNacimiento <= :anio AND (a.fechaDeMuerte IS NULL OR a.fechaDeMuerte >= :anio)")
   List<Autor> encontrarAutoresVivosEn(@Param("anio") String anio);
}
