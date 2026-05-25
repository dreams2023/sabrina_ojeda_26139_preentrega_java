package com.techlab.articulo;

import java.util.ArrayList;

import java.util.Scanner;

import com.techlab.articulo.model.Articulo;




public class App {

    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);

        ArrayList<String> articulos = new ArrayList<>();


        int opcion;



        do{
           
            System.out.println("\n==========================================");
            System.out.println("   SISTEMA BÁSICO DE ARTÍCULOS - CLASE 1");
            System.out.println("==========================================");
            System.out.println("1 - Ingresar artículo");
            System.out.println("2 - Listar artículos");
            System.out.println("3 - Consultar un artículo");
            System.out.println("4 - Modificar un artículo");
            System.out.println("5 - Eliminar un artículo");
            System.out.println("0 - Salir");
            System.out.println("==========================================");

            opcion = leerEntero(scanner, "Ingrese una opción: ");


            switch (opcion) {
                case 1:
                    ingresarArticulo(scanner, articulos);
                    break;
                case 2:
                    listarArticulos(articulos);
                    break;
                case 3:
                    consultarArticulo(scanner, articulos);
                    break;
                case 4:
                    modificarArticulo(scanner, articulos);
                    break;
                case 5:
                    eliminarArticulo(scanner, articulos);
                    break;
                case 0:
                    System.out.println("\nSaliendo del sistema. ¡Hasta luego!");
                    break;
                default:
                    System.out.println("\nError: la opción ingresada no es válida.");
            }
        } while (opcion != 0);

        scanner.close();

    }

    public static void ingresarArticulo(Scanner s, ArrayList<String> listaArticulos) {

        System.out.println("\n--- INGRESAR ARTÍCULO ---");

        String descripcion = leerTextoNoVacio(s, "Ingrese la descripción del artículo: ");

        if (existeArticulo(listaArticulos, descripcion)) {
            System.out.println("Error: ese artículo ya existe en el sistema.");
            return;
        }

        listaArticulos.add(descripcion);

        System.out.println("Artículo ingresado correctamente.");
    }


    public static void listarArticulos( ArrayList<String> articulos){

        System.out.println("\n--- LISTADO DE ARTÍCULOS ---");

         if (articulos.isEmpty()) {
            System.out.println("No hay artículos cargados.");
            return;
        }


         for (int i = 0; i < articulos.size(); i++) {
            System.out.println((i + 1) + " - " + articulos.get(i));
        }



    }


    public static void consultarArticulo(Scanner scanner, ArrayList<String> articulos ){

        System.out.println("\n--- CONSULTAR ARTÍCULO ---");

        if (articulos.isEmpty()) {
            System.out.println("No hay artículos cargados.");
            return;
        }

        String descripcionBuscada = leerTextoNoVacio(scanner, "Ingrese la descripción del artículo a consultar: ");

        int posicion = buscarPosicionArticulo(articulos, descripcionBuscada);

        if (posicion == -1) {
            System.out.println("El artículo no existe.");
        } else {
            System.out.println("Artículo encontrado en la posición: " + (posicion + 1));
            System.out.println("Descripción: " + articulos.get(posicion));
        }

    }

    public static void modificarArticulo(Scanner scanner, ArrayList<String> articulos) {

        System.out.println("\n--- MODIFICAR ARTÍCULO ---");

        if (articulos.isEmpty()) {
            System.out.println("No hay artículos cargados.");
            return;
        }

        String descripcionActual = leerTextoNoVacio(scanner, "Ingrese la descripción del artículo a modificar: ");

        int posicion = buscarPosicionArticulo(articulos, descripcionActual);

        if (posicion == -1) {
            System.out.println("El artículo no existe.");
            return;
        }

        String nuevaDescripcion = leerTextoNoVacio(scanner, "Ingrese la nueva descripción: ");

     if (existeArticulo(articulos, nuevaDescripcion) &&
                !articulos.get(posicion).equalsIgnoreCase(nuevaDescripcion)) {
            System.out.println("Error: ya existe otro artículo con esa descripción.");
            return;
        }

         articulos.set(posicion, nuevaDescripcion);

        System.out.println("Artículo modificado correctamente.");
    }


    public static void eliminarArticulo(Scanner scanner, ArrayList<String> articulos) {

        System.out.println("\n--- ELIMINAR ARTÍCULO ---");

        if (articulos.isEmpty()) {
            System.out.println("No hay artículos cargados.");
            return;
        }

        String descripcionAEliminar = leerTextoNoVacio(scanner, "Ingrese la descripción del artículo a eliminar: ");

        int posicion = buscarPosicionArticulo(articulos, descripcionAEliminar);

        if (posicion == -1) {
            System.out.println("El artículo no existe.");
            return;
        }

        // Eliminamos el elemento según su posición.
        articulos.remove(posicion);

        System.out.println("Artículo eliminado correctamente.");
    }



    public static boolean existeArticulo(ArrayList<String> articulos, String descripcion){


         for (String articulo : articulos) {
            if (articulo.equalsIgnoreCase(descripcion.trim())) {
                return true;
            }
        }

        return false;

    }


    public static int buscarPosicionArticulo(ArrayList<String> articulos, String descripcion) {

        for (int i = 0; i < articulos.size(); i++) {
            if (articulos.get(i).equalsIgnoreCase(descripcion.trim())) {
                return i;
            }
        }

        return -1;
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