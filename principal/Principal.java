package com.aluracursos.catalogo_libros.principal;

import com.aluracursos.catalogo_libros.model.*;
import com.aluracursos.catalogo_libros.repository.AutorRepository;
import com.aluracursos.catalogo_libros.repository.LibroRepository;
import com.aluracursos.catalogo_libros.service.ConsumoAPI;
import com.aluracursos.catalogo_libros.service.ConvierteDatos;

import java.util.*;

public class Principal {
    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private static String URL_BASE = "https://gutendex.com/books/";
    private final String API_KEY = "&apikey=d4d0bf92";
    private List<DatosLibro> datosLibrosList=  new ArrayList<>();
    private ConvierteDatos conver = new ConvierteDatos();
    private String json;
    private AutorRepository repositorioAutor;
    private LibroRepository repositorioLibro;
    private List<Libro> librosRegistrados;
    private List<Autor> autoresRegistrados;
    //private Optional<Libro> libroBuscado ;
    //private Optional<Autor> autorBuscado;


    public Principal(LibroRepository repositorioLibro,AutorRepository repositorioAutor) {

        this.repositorioLibro = repositorioLibro;
        this.repositorioAutor =repositorioAutor;
    }



    public void muestraElMenu() {
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    Elija la opcion a travez de su numero:
                    1 - Buscar libros por titulo
                    2 - Listar libros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos en un determinado año
                    5 - Listar libros por idioma
                    0 - salir 
                    
                    """;
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                     buscarLibroApi();
                    break;
                case 2:
                    ListarLibrosRegistrados();
                    break;
                case 3:
                    ListarAutoresRegistrados();
                    break;
                case 4:
                    ListarAutoresVivos();
                    break;
                case 5:
                    ListarLibrosPorIdioma();
                    break;

                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }

    }

    private DatosLibro getDatosLibro() {
        System.out.println("Escribe el nombre del libro que deseas buscar");
        var tituloLibro = teclado.nextLine();
        //var json = consApi.obtenerDatos(URL_BASE + nombreSerie.replace(" ", "+") + API_KEY);
        var json = consumoApi.obtenerDatos(URL_BASE + "?search=" + tituloLibro.replace(" ", "+"));
       // System.out.println(json);
        var datosBusqueda = conver.obtenerDatos(json, Datos.class);
        Optional<DatosLibro> libroBuscado = datosBusqueda.resultados().stream()
                .filter(libro -> libro.titulo().toUpperCase().contains(tituloLibro.toUpperCase()))
                .findFirst();
        if (libroBuscado.isPresent()) {
            System.out.println("Libro encontrado");
            //System.out.println(libroBuscado.get());
            // esto imprime
            //DatosLibro[titulo=Don Quijote de la Mancha, autor=[DatosAutor[nombre=Cervantes Saavedra,
            // Miguel de, fechaDeNacimiento=1547, fechaDeMuerte=1616]], idiomas=[hu], numeroDescargas=612.0]

           // DatosLibro libro = libroBuscado.get();
           // return libro;
            return libroBuscado.get();
        } else {
            System.out.println("libro no encontrado");
        }


       return null;

    }

    private void buscarLibroApi() {
        DatosLibro datosLibro = getDatosLibro(); // Va a a API
        //datosLibrosList.add(datosLibro);
        //System.out.println(datosLibrosList);
        if(datosLibro!=null){

            Libro libroBuscado;
            DatosAutor datosAutor = datosLibro.autor().get(0);

            //System.out.println(datosAutor);
            //DatosAutor[nombre=Cervantes Saavedra, Miguel de, fechaDeNacimiento=1547, fechaDeMuerte=1616]


             Autor autorBuscado = repositorioAutor.findByNombre(datosAutor.nombre());
             if(autorBuscado != null){

                 //System.out.println("Autor ya existe");
                 //Ahora a buscar si el libro ya esta en la base
                 Optional<Libro> libroRegistrado=repositorioLibro.findByTitulo(datosLibro.titulo());
                 if(libroRegistrado.isPresent()){
                     //Libro ya registrado para este autor
                     //Por lo tanto no se debe grabar nada
                     return;
                 }else {
                     libroBuscado=new Libro(datosLibro,autorBuscado);
                 }

             }else {
                 //Autor No existe

                 Autor nuevoAutor = new Autor(datosAutor);
                 libroBuscado=new Libro(datosLibro,nuevoAutor);
                 repositorioAutor.save((nuevoAutor));


             }
             repositorioLibro.save(libroBuscado);



            }
        }

        private void ListarLibrosRegistrados(){
          librosRegistrados=repositorioLibro.findAll();
          librosRegistrados.stream()
                  .forEach(System.out::println);



        }

    private void ListarAutoresRegistrados(){
        autoresRegistrados=repositorioAutor.findAll();
        autoresRegistrados.stream()
                .forEach(System.out::println);

    }

    private void ListarAutoresVivos(){
        System.out.println("Ingrese Año: ");
        String anio = teclado.nextLine();
        autoresRegistrados=repositorioAutor.encontrarAutoresVivosEn(anio);
        if(autoresRegistrados.isEmpty()){
            System.out.println("No existen autores vivos para el año requerido");
        }else {

            autoresRegistrados.stream()
                    .forEach(System.out::println);
        }

    }

    private void ListarLibrosPorIdioma(){
        librosRegistrados=repositorioLibro.findAll();
        librosRegistrados.stream()
                .sorted(Comparator.comparing(Libro::getIdioma))
                .forEach(System.out::println);

    }
}





