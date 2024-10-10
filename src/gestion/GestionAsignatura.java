package gestion;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

import asignatura.DAOAsignatura;
import asignatura.DTOAsignatura;

public class GestionAsignatura {
    private DAOAsignatura daoAsignatura;
    private ArrayList<DTOAsignatura> listaAsignaturas;

    public GestionAsignatura() {
        daoAsignatura = new DAOAsignatura();
        listaAsignaturas = daoAsignatura.readAll();
    }

    public void initMenu(Scanner sc) {
        int option;
        do {
            System.out.println("Gestión de Asignaturas");
            System.out.println("1. Leer asignatura");
            System.out.println("2. Crear asignatura");
            System.out.println("3. Modificar asignatura");
            System.out.println("4. Eliminar asignatura");
            System.out.println("5. Ordenar asignaturas por ID");
            System.out.println("6. Ordenar asignaturas por nombre");
            System.out.println("0. Atrás");

            option = sc.nextInt();
            sc.nextLine(); // Limpiar buffer

            switch (option) {
                case 1 -> readAsignatura(sc);
                case 2 -> createAsignatura(sc);
                case 3 -> updateAsignatura(sc);
                case 4 -> deleteAsignatura(sc);
                case 5 -> sortAsignaturasByID();
                case 6 -> sortAsignaturasByName();
                case 0 -> System.out.println("Saliendo...");
                default -> System.out.println("ERROR, intente de nuevo.");
            }
        } while (option != 0);
    }

    private void readAsignatura(Scanner sc) {
        System.out.println("Indica ID para leer: ");
        int id = sc.nextInt();
        sc.nextLine(); // Limpiar buffer

        DTOAsignatura asignatura = daoAsignatura.read(id);
        if (asignatura != null) {
            System.out.println(asignatura);
        } else {
            System.out.println("No se encontró una asignatura con ese ID.");
        }
    }

    private void createAsignatura(Scanner sc) {
        // Recoger datos
        System.out.println("Nombre:");
        String nombre = sc.nextLine();

        System.out.println("Horas semanales:");
        int horasSemanales = sc.nextInt();
        sc.nextLine(); // Limpiar buffer

        // Crear asignatura y guardarla en la base de datos y en memoria
        DTOAsignatura asignaturaCrear = new DTOAsignatura(nombre, horasSemanales);
        boolean success = daoAsignatura.create(asignaturaCrear);

        if (success) {
            listaAsignaturas.add(asignaturaCrear);
            System.out.println("Asignatura creada con éxito.");
        } else {
            System.out.println("Error al crear la asignatura.");
        }
    }

    private void updateAsignatura(Scanner sc) {
        System.out.println("Indica ID de la asignatura a modificar: ");
        int id = sc.nextInt();
        sc.nextLine(); // Limpiar buffer

        DTOAsignatura asignatura = daoAsignatura.read(id);
        if (asignatura != null) {
            System.out.println("Nuevo nombre (ENTER = SKIP): ");
            String nuevoNombre = sc.nextLine();
            asignatura.setNombre(nuevoNombre);

            System.out.println("Nuevas Horas Semanales (ENTER = SKIP): ");
            int nuevasHorasSemanales = sc.nextInt();
            asignatura.setHorasSemanales(nuevasHorasSemanales);

            boolean success = daoAsignatura.update(asignatura);
            if (success) {
                System.out.println("Asignatura modificada con éxito.");
            } else {
                System.out.println("Error al modificar la asignatura.");
            }
        } else {
            System.out.println("No se encontró una asignatura con ese ID.");
        }
    }

    private void deleteAsignatura(Scanner sc) {
        System.out.println("Indica ID de la asignatura a eliminar: ");
        int id = sc.nextInt();
        sc.nextLine(); // Limpiar buffer

        boolean success = daoAsignatura.delete(id);
        if (success) {
            System.out.println("Asignatura eliminada con éxito.");
        } else {
            System.out.println("No se encontró una asignatura con ese ID.");
        }
    }

    private void sortAsignaturasByID() {
        listaAsignaturas.sort(Comparator.comparing(DTOAsignatura::getId));
        System.out.println("Asignaturas ordenadas por ID:");
        displayAsignaturas();
    }

    private void sortAsignaturasByName() {
        listaAsignaturas.sort(Comparator.comparing(DTOAsignatura::getNombre));
        System.out.println("Asignaturas ordenadas por nombre:");
        displayAsignaturas();
    }

    private void displayAsignaturas() {
        for (DTOAsignatura asignatura : listaAsignaturas) {
            System.out.println(asignatura);
        }
    }
}
