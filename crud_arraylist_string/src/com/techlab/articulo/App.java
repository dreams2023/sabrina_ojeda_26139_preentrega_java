package com.techlab.articulo;

import java.util.ArrayList;

import java.util.Scanner;

import com.techlab.articulo.model.Articulo;
import com.techlab.articulo.model.Categoria;






public class App {

    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);

        ArrayList<Articulo> articulos = new ArrayList<>();

        ArrayList<Categoria> categorias = new ArrayList<>();

        precargarCategorias(categorias);


        int opcion;



        do{
           
            System.out.println("\n==========================================");
            System.out.println("   SISTEMA BÁSICO DE ARTÍCULOS - CLASE 3 (categoria objeto)");
            System.out.println("===========================================================");
            System.out.println("1 - Ingresar artículo");
            System.out.println("2 - Listar artículos");
            System.out.println("3 - Consultar un artículo");
            System.out.println("4 - Modificar un artículo");
            System.out.println("5 - Eliminar un artículo");
            System.out.println("6 - Listar categorias precargadas");
            System.out.println("0 - Salir");
            System.out.println("===========================================================");

            opcion = leerEntero(scanner, "Ingrese una opción: ");


            switch (opcion) {
                case 1:
                    ingresarArticulo(scanner, articulos, categorias);
                    break;
                case 2:
                    listarArticulos(articulos);
                    break;
                case 3:
                    consultarArticulo(scanner, articulos);
                    break;
                case 4:
                    modificarArticulo(scanner, articulos, categorias);
                    break;
                case 5:
                    eliminarArticulo(scanner, articulos);
                    break;
                case 6:
                    listarCategorias(categorias);
                case 0:
                    System.out.println("\nSaliendo del sistema. ¡Hasta luego!");
                    break;
                default:
                    System.out.println("\nError: la opción ingresada no es válida.");
            }
        } while (opcion != 0);

        scanner.close();

    }

    public static void precargarCategorias(ArrayList<Categoria> categorias) {
        categorias.add(new Categoria(1, "Electrónica", "Productos tecnológicos y electrónicos"));
        categorias.add(new Categoria(2, "Periféricos", "Accesorios para computadora"));
        categorias.add(new Categoria(3, "Alimentos", "Productos alimenticios"));
        categorias.add(new Categoria(4, "Limpieza", "Artículos de limpieza del hogar"));
    }

    public static void ingresarArticulo(Scanner scanner, ArrayList<Articulo> articulos, ArrayList<Categoria> categorias) {

        System.out.println("\n--- INGRESAR ARTÍCULO ---");

        int codigo = leerEntero(scanner, "Ingrese el codigo del artículo: ");

       if (buscarArticuloPorCodigo(articulos, codigo) != null) {
            System.out.println("Error: ya existe un artículo con ese código.");
            return;
        }
       String nombre = leerTextoNoVacio(scanner, "Ingrese el nombre del artículo: ");
       double precio = leerDoubleNoNegativo(scanner, "Ingrese el precio del artículo: ");

       listarCategorias(categorias);


       Categoria categoriaElegida = pedirCategoriaExistente(scanner, categorias);
       

       Articulo articulo = new Articulo(codigo, nombre, precio, categoriaElegida);

       articulos.add(articulo);

       System.out.println("Articulo ingresado correctamente.");
    }


    public static void listarArticulos( ArrayList<Articulo> articulos){

        System.out.println("\n--- LISTADO DE ARTÍCULOS ---");

         if (articulos.isEmpty()) {
            System.out.println("No hay artículos cargados.");
            return;
        }


        for (Articulo articulo : articulos) {
            System.out.println(articulo);
        }

    }


    public static void consultarArticulo(Scanner scanner, ArrayList<Articulo> articulos ){

        System.out.println("\n--- CONSULTAR ARTÍCULO ---");

        if (articulos.isEmpty()) {
            System.out.println("No hay artículos cargados.");
            return;
        }

        int codigo = leerEntero(scanner, "Ingrese la descripción del artículo a consultar: ");

        Articulo articulo = buscarArticuloPorCodigo(articulos, codigo);

        if (articulo == null) {
            System.out.println("El artículo no existe.");
        } else {
            System.out.println("Artículo encontrado:");
            System.out.println(articulo);
        }

    }

    public static void modificarArticulo(Scanner scanner, ArrayList<Articulo> articulos, ArrayList<Categoria> categorias) {

        System.out.println("\n--- MODIFICAR ARTÍCULO ---");

        if (articulos.isEmpty()) {
            System.out.println("No hay artículos cargados.");
            return;
        }

        int codigo = leerEntero(scanner, "Ingrese el código del artículo a modificar: ");

        Articulo articulo = buscarArticuloPorCodigo(articulos, codigo);

        if (articulo == null) {
            System.out.println("El artículo no existe.");
            return;
        }

        String nuevoNombre = leerTextoNoVacio(scanner, "Ingrese el nuevo nombre del artículo: ");
        double nuevoPrecio = leerDoubleNoNegativo(scanner, "Ingrese el nuevo precio del artículo: ");
        


        articulo.setNombre(nuevoNombre);
        articulo.setPrecio(nuevoPrecio);

     

        System.out.println("Artículo modificado correctamente.");
    }

     public static Categoria buscarCategoriaPorCodigo(ArrayList<Categoria> categorias, int codigo) {

        for (Categoria categoria : categorias) {
            if (categoria.getCodigo() == codigo) {
                return categoria;
            }
        }

        return null;
    }


    public static void eliminarArticulo(Scanner scanner, ArrayList<Articulo> articulos) {

        System.out.println("\n--- ELIMINAR ARTÍCULO ---");

        if (articulos.isEmpty()) {
            System.out.println("No hay artículos cargados.");
            return;
        }

        int codigo = leerEntero(scanner, "Ingrese el código del artículo a eliminar: ");

        Articulo articulo = buscarArticuloPorCodigo(articulos, codigo);

        if (articulo == null) {
            System.out.println("El artículo no existe.");
            return;
        }

        
        articulos.remove(articulo);

        System.out.println("Artículo eliminado correctamente.");
    }

    public static void listarCategorias(ArrayList<Categoria> categorias) {
        System.out.println("\n--- CATEGORÍAS DISPONIBLES ---");

        for (Categoria categoria : categorias) {
            System.out.println(categoria);
        }
    }

    public static Categoria pedirCategoriaExistente(Scanner scanner, ArrayList<Categoria> categorias) {

        while (true) {
            int codigoCategoria = leerEntero(scanner, "Ingrese el código de la categoría: ");

            Categoria categoria = buscarCategoriaPorCodigo(categorias, codigoCategoria);

            if (categoria != null) {
                return categoria;
            }

            System.out.println("Error: la categoría no existe.");
        }
    }


    


    public static Articulo buscarArticuloPorCodigo(ArrayList<Articulo> articulos, int codigo) {

        for (Articulo articulo : articulos) {
            if (articulo.getCodigo() == codigo) {
                return articulo;
            }
        }

        return null;
    }

    public static int leerEntero(Scanner scanner, String mensaje){

        while (true) {

            try {
                System.out.print(mensaje);
                return Integer.parseInt(scanner.nextLine());
            }catch (NumberFormatException e) {
                System.out.println("Error: debe ingresar un número entero válido.");
            }

        }
    }


    public static double leerDoubleNoNegativo(Scanner scanner, String mensaje) {

        while (true) {
            try {
                System.out.print(mensaje);
                double valor = Double.parseDouble(scanner.nextLine());

                if (valor < 0) {
                    System.out.println("Error: el precio no puede ser negativo.");
                    continue;
                }

                return valor;
            } catch (NumberFormatException e) {
                System.out.println("Error: debe ingresar un número decimal válido.");
            }
        }
    }


    public static String leerTextoNoVacio(Scanner scanner, String mensaje) {

        while (true) {
            System.out.print(mensaje);
            String texto = scanner.nextLine();

            if (!texto.trim().isEmpty()) {
                return texto.trim();

            }

          System.out.println("Error: el texto no puede estar vacío.");
        }

    }
}