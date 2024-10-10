package matricula;

import java.time.LocalDate;

public class DTOMatricula {
    private int idAlumno;
    private int idAsignatura;
    private LocalDate fechaMatricula;

    // Constructor para nuevas matriculas
    public DTOMatricula(int idAlumno, int idAsignatura) {
        this.idAlumno = idAlumno;
        this.idAsignatura = idAsignatura;
        this.fechaMatricula = LocalDate.now();
    }

    // Constructor sobrecargado para cargar matriculas de DB
    public DTOMatricula(int idAlumno, int idAsignatura, LocalDate fechaMatricula) {
        this.idAlumno = idAlumno;
        this.idAsignatura = idAsignatura;
        this.fechaMatricula = fechaMatricula;
    }

    public int getIdAlumno() {
        return idAlumno;
    }

    public int getIdAsignatura() {
        return idAsignatura;
    }

    public LocalDate getFechaMatricula() {
        return fechaMatricula;
    }

    @Override
    public String toString() {
        return "DTOMatricula [idAlumno=" + idAlumno + ", idAsignatura=" + idAsignatura + ", fechaMatricula="
                + fechaMatricula + "]";
    }

    
}

