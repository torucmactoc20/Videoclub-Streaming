package club.ui;

import java.util.Scanner;
import club.dominio.*;
import club.excepcion.ContenidoNoDisponibleException;

public class Menu {
    private Videoclub videoclub;
    private Scanner scanner;

    public Menu() {
        this.videoclub = new Videoclub();
        this.scanner = new Scanner(System.in);
        // Datos de prueba iniciales
        videoclub.registrarPersona(new Suscriptor(123, "Tomas", "Barraza", "Premium"));
        videoclub.registrarPersona(new Suscriptor(456, "Juan", "Perez", "Basico"));
        videoclub.registrarPersona(new Empleado(789, "Carlos", "Gomez", 1001));
    }

    public void iniciar() {
        int opcion = -1;
        while (opcion != 0) {
            System.out.println("\n=== SISTEMA VIDEOCLUB / STREAMING ===");
            System.out.println("1. Agregar Contenido al Catálogo");
            System.out.println("2. Registrar Suscriptor");
            System.out.println("3. Alquilar / Reproducir Contenido");
            System.out.println("4. Devolver Contenido");
            System.out.println("5. Mostrar Contenido Disponible");
            System.out.println("6. Mostrar Catálogo Completo Ordenado por Título");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine());

                switch (opcion) {
                    case 1: menuAgregarContenido(); break;
                    case 2: menuRegistrarSuscriptor(); break;
                    case 3: menuAlquilar(); break;
                    case 4: menuDevolver(); break;
                    case 5:
                        System.out.println("\n--- CONTENIDO DISPONIBLE ---");
                        videoclub.obtenerContenidoDisponible().forEach(Contenido::mostrarInfo);
                        break;
                    case 6:
                        System.out.println("\n--- CATÁLOGO ORDENADO POR TÍTULO ---");
                        videoclub.mostrarCatalogoOrdenado();
                        break;
                    case 0:
                        System.out.println("Saliendo del sistema...");
                        break;
                    default:
                        System.out.println("Opción inválida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Por favor, ingrese un número válido.");
            } catch (Exception e) {
                System.out.println("Ocurrió un error inesperado: " + e.getMessage());
            } finally {
                System.out.println("[Operación de ciclo finalizada]");
            }
        }
        scanner.close();
    }

    private void menuAgregarContenido() {
        System.out.println("\nTipo de contenido: 1. Película | 2. Serie | 3. Documental");
        int tipo = Integer.parseInt(scanner.nextLine());
        System.out.print("ID: ");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.print("Título: ");
        String titulo = scanner.nextLine();
        System.out.print("Género: ");
        String genero = scanner.nextLine();

        if (tipo == 1) {
            System.out.print("Duración (minutos): ");
            double dur = Double.parseDouble(scanner.nextLine());
            System.out.print("Director: ");
            String dir = scanner.nextLine();
            videoclub.agregarContenido(new Pelicula(id, titulo, genero, dur, dir));
        } else if (tipo == 2) {
            System.out.print("Cantidad de temporadas: ");
            int temp = Integer.parseInt(scanner.nextLine());
            System.out.print("Duración promedio de episodio: ");
            double durEp = Double.parseDouble(scanner.nextLine());
            videoclub.agregarContenido(new Serie(id, titulo, genero, temp, durEp));
        } else if (tipo == 3) {
            System.out.print("Temática: ");
            String tem = scanner.nextLine();
            System.out.print("Duración (minutos): ");
            double durDoc = Double.parseDouble(scanner.nextLine());
            videoclub.agregarContenido(new Documental(id, titulo, genero, tem, durDoc));
        }
        System.out.println("Contenido agregado y persistido correctamente.");
    }

    private void menuRegistrarSuscriptor() {
        System.out.print("DNI: ");
        int dni = Integer.parseInt(scanner.nextLine());
        System.out.print("Nombre: ");
        String nom = scanner.nextLine();
        System.out.print("Apellido: ");
        String ape = scanner.nextLine();
        System.out.print("Plan (Basico/Premium): ");
        String plan = scanner.nextLine();

        videoclub.registrarPersona(new Suscriptor(dni, nom, ape, plan));
        System.out.println("Suscriptor registrado con éxito.");
    }

    private void menuAlquilar() {
        System.out.print("Ingrese DNI del Suscriptor: ");
        int dni = Integer.parseInt(scanner.nextLine());
        System.out.print("Ingrese el Título del contenido a alquilar: ");
        String titulo = scanner.nextLine();

        try {
            videoclub.alquilarContenido(dni, titulo);
            System.out.println("¡Alquiler realizado con éxito!");
        } catch (ContenidoNoDisponibleException e) {
            System.out.println("AVISO IMPORTANTE: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Error de parámetros: " + e.getMessage());
        }
    }

    private void menuDevolver() {
        System.out.print("Ingrese DNI del Suscriptor: ");
        int dni = Integer.parseInt(scanner.nextLine());
        System.out.print("Ingrese el Título del contenido a devolver: ");
        String titulo = scanner.nextLine();

        try {
            videoclub.devolverContenido(dni, titulo);
            System.out.println("Devolución procesada correctamente.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
