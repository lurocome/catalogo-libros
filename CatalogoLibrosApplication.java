package com.aluracursos.catalogo_libros;

import com.aluracursos.catalogo_libros.principal.Principal;
import com.aluracursos.catalogo_libros.repository.AutorRepository;
import com.aluracursos.catalogo_libros.repository.LibroRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;


@SpringBootApplication
public class CatalogoLibrosApplication implements CommandLineRunner {
	@Autowired
    private LibroRepository librorepository;
	@Autowired
	private AutorRepository autorRepository;
	public static void main(String[] args) {
		SpringApplication.run(CatalogoLibrosApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(librorepository,autorRepository);
		principal.muestraElMenu();

	}
}
