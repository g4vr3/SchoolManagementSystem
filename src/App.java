import java.util.Scanner;
import gestion.Gestion;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Gestion gestion = new Gestion();

        // Iniciar el menú de la aplicación
        gestion.initMenu(scanner);

        // Cerrar el escáner
        scanner.close();
    }
}
