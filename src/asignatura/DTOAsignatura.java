package asignatura;

public class DTOAsignatura {
    private int id;
    private String nombre;
    private int horasSemanales;
    
    public DTOAsignatura(String nombre, int horasSemanales) {
        this.nombre = nombre;
        this.horasSemanales = horasSemanales;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getHorasSemanales() {
        return horasSemanales;
    }

    public void setHorasSemanales(int horasSemanales) {
        this.horasSemanales = horasSemanales;
    }

    @Override
    public String toString() {
        return "DTOAsignatura [id=" + id + ", nombre=" + nombre + ", horasSemanales=" + horasSemanales + "]";
    }

    
}
