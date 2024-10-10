package gestion;

import java.util.Scanner;

public class Gestion {
    public void initMenu(Scanner sc) {
        int option;
        do {
            System.out.println("Gestión del Centro");
            System.out.println("1. Gestión de Alumnos");
            System.out.println("2. Gestión de Asignaturas");
            System.out.println("3. Gestión de Matrículas");
            System.out.println("0. Salir");

            option = sc.nextInt();
            sc.nextLine(); // Limpiar buffer

            switch (option) {
                case 1 -> new GestionAlumno().initMenu(sc);
                case 2 -> new GestionAsignatura().initMenu(sc);
                case 3 -> new GestionMatricula().initMenu(sc);
                case 0 -> System.out.println("Saliendo...");
                default -> System.out.println("ERROR, intente de nuevo.");
            }
        } while (option != 0);
    }
}
