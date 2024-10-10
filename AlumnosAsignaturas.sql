-- Crear la base de datos
CREATE DATABASE gestion_educativa;

-- Usar la base de datos
USE gestion_educativa;

-- Crear la tabla Alumno
CREATE TABLE Alumno (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    Nombre VARCHAR(100) NOT NULL,
    FechaNacimiento DATE NOT NULL,
    Telefono VARCHAR(15),
    Direccion VARCHAR(255)
);

-- Crear la tabla Asignatura
CREATE TABLE Asignatura (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    Nombre VARCHAR(100) NOT NULL,
    HorasSemanales INT NOT NULL
);

-- Crear la tabla intermedia Matricula
CREATE TABLE Matricula (
    idAlumno INT,
    idAsignatura INT,
    FechaMatricula DATE NOT NULL,
    PRIMARY KEY (idAlumno, idAsignatura),
    FOREIGN KEY (idAlumno) REFERENCES Alumno(ID) ON DELETE CASCADE,
    FOREIGN KEY (idAsignatura) REFERENCES Asignatura(ID) ON DELETE CASCADE
);

