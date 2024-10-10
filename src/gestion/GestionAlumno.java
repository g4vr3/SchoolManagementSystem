package gestion;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

import alumno.DAOAlumno;
import alumno.DTOAlumno;

public class GestionAlumno {
    private DAOAlumno daoAlumno;
    private ArrayList<DTOAlumno> listaAlumnos;

    public GestionAlumno() {
        daoAlumno = new DAOAlumno();
        listaAlumnos = daoAlumno.readAll();
    }

    public void initMenu(Scanner sc) {
        int option;
        do {
            System.out.println("Gestión de Alumnos");
            System.out.println("1. Leer alumno");
            System.out.println("2. Crear alumno");
            System.out.println("3. Modificar alumno");
            System.out.println("4. Eliminar alumno");
            System.out.println("5. Ordenar alumnos por ID");
            System.out.println("6. Ordenar alumnos por nombre");
            System.out.println("0. Atrás");

            option = sc.nextInt();
            sc.nextLine(); // Limpiar buffer

            switch (option) {
                case 1 -> readAlumno(sc);
                case 2 -> createAlumno(sc);
                case 3 -> updateAlumno(sc);
                case 4 -> deleteAlumno(sc);
                case 5 -> sortAlumnosByID();
                case 6 -> sortAlumnosByName();
                case 0 -> System.out.println("Saliendo...");
                default -> System.out.println("ERROR, intente de nuevo.");
            }
        } while (option != 0);
    }

    private void readAlumno(Scanner sc) {
        System.out.println("Indica ID para leer: ");
        int id = sc.nextInt();
        sc.nextLine(); // Limpiar buffer

        DTOAlumno alumno = daoAlumno.read(id);
        if (alumno != null) {
            System.out.println(alumno);
        } else {
            System.out.println("No se encontró un alumno con ese ID.");
        }
    }

    private void createAlumno(Scanner sc) {
        System.out.println("Nombre:");
        String nombre = sc.nextLine();

        System.out.println("Fecha de nacimiento (YYYY-MM-DD):");
        LocalDate fechaNacimiento = LocalDate.parse(sc.nextLine());

        System.out.println("Teléfono:");
        String telefono = sc.nextLine();

        System.out.println("Dirección:");
        String direccion = sc.nextLine();

        DTOAlumno alumnoCrear = new DTOAlumno(nombre, fechaNacimiento, telefono, direccion);
        boolean success = daoAlumno.create(alumnoCrear);

        if (success) {
            listaAlumnos.add(alumnoCrear);
            System.out.println("Alumno creado con éxito.");
        } else {
            System.out.println("Error al crear el alumno.");
        }
    }

    private void updateAlumno(Scanner sc) {
        System.out.println("Indica ID del alumno a modificar: ");
        int id = sc.nextInt();
        sc.nextLine(); // Limpiar buffer

        DTOAlumno alumno = daoAlumno.read(id);
        if (alumno != null) {
            System.out.println("Nuevo nombre:");
            String nuevoNombre = sc.nextLine();
            alumno.setNombre(nuevoNombre);
            
            System.out.println("Nueva fecha de nacimiento (YYYY-MM-DD):");
            LocalDate nuevaFecha = LocalDate.parse(sc.nextLine());
            alumno.setFechaNacimiento(nuevaFecha);
            
            System.out.println("Nuevo teléfono:");
            String nuevoTelefono = sc.nextLine();
            alumno.setTelefono(nuevoTelefono);
            
            System.out.println("Nueva dirección:");
            String nuevaDireccion = sc.nextLine();
            alumno.setDireccion(nuevaDireccion);
            
            boolean success = daoAlumno.update(alumno);
            if (success) {
                System.out.println("Alumno modificado con éxito.");
            } else {
                System.out.println("Error al modificar el alumno.");
            }
        } else {
            System.out.println("No se encontró un alumno con ese ID.");
        }
    }

    private void deleteAlumno(Scanner sc) {
        System.out.println("Indica ID del alumno a eliminar: ");
        int id = sc.nextInt();
        sc.nextLine(); // Limpiar buffer

        boolean success = daoAlumno.delete(id);
        if (success) {
            System.out.println("Alumno eliminado con éxito.");
        } else {
            System.out.println("No se encontró un alumno con ese ID.");
        }
    }

    private void sortAlumnosByID() {
        listaAlumnos.sort(Comparator.comparing(DTOAlumno::getId));
        System.out.println("Alumnos ordenados por ID:");
        displayAlumnos();
    }

    private void sortAlumnosByName() {
        listaAlumnos.sort(Comparator.comparing(DTOAlumno::getNombre));
        System.out.println("Alumnos ordenados por nombre:");
        displayAlumnos();
    }

    private void displayAlumnos() {
        for (DTOAlumno alumno : listaAlumnos) {
            System.out.println(alumno);
        }
    }
}
