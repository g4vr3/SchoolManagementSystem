package gestion;

import java.util.Scanner;
import java.util.ArrayList;

import asignatura.DAOAsignatura;
import alumno.DAOAlumno;
import matricula.DTOMatricula;
import matricula.DAOMatricula;

public class GestionMatricula {
    private DAOMatricula daoMatricula;
    private DAOAlumno daoAlumno;
    private DAOAsignatura daoAsignatura;
    private ArrayList<DTOMatricula> listaMatriculas;

    public GestionMatricula() {
        daoMatricula = new DAOMatricula();
        daoAlumno = new DAOAlumno();
        daoAsignatura = new DAOAsignatura();
        listaMatriculas = new ArrayList<>();
    }

    public void initMenu(Scanner sc) {
        int option;
        do {
            System.out.println("Gestión de Matrículas");
            System.out.println("1. Crear matrícula");
            System.out.println("0. Atrás");
            // Posibilidad de implementar funcionalidades para leer matriculas por alumno o por asignatura.

            option = sc.nextInt();
            sc.nextLine(); // Limpiar buffer

            switch (option) {
                case 1 -> createMatricula(sc);
                case 0 -> System.out.println("Saliendo...");
                default -> System.out.println("ERROR, intente de nuevo.");
            }
        } while (option != 0);
    }

    private void createMatricula(Scanner sc) {
        System.out.println("ID del alumno:");
        int alumnoId = sc.nextInt();
        sc.nextLine(); // Limpiar buffer
    
        System.out.println("ID de la asignatura:");
        int asignaturaId = sc.nextInt();
        sc.nextLine(); // Limpiar buffer
    
        // Verificar si el alumno y la asignatura existen
        var alumno = daoAlumno.read(alumnoId);
        var asignatura = daoAsignatura.read(asignaturaId);
    
        if (alumno == null) {
            System.out.println("No se encontró un alumno con ese ID.");
            return;
        }
        if (asignatura == null) {
            System.out.println("No se encontró una asignatura con ese ID.");
            return;
        }
    
        // Comprobar si la matrícula ya existe
        for (DTOMatricula matriculaExistente : listaMatriculas) {
            if (matriculaExistente.getIdAlumno() == alumnoId && matriculaExistente.getIdAsignatura() == asignaturaId) {
                System.out.println("El alumno ya está matriculado en esta asignatura.");
                return; // Salir si ya existe la matrícula
            }
        }
    
        // Crear la matrícula si no existe
        DTOMatricula matricula = new DTOMatricula(alumnoId, asignaturaId);
        boolean success = daoMatricula.create(matricula);
        if (success) {
            listaMatriculas.add(matricula); // Agregar a la lista en memoria
            System.out.println("Matrícula creada con éxito.");
        } else {
            System.out.println("Error al crear la matrícula.");
        }
    }
    
}
