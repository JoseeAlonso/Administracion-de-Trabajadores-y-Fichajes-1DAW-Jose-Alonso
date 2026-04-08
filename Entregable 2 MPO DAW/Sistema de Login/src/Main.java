import controller.AdministradorController;
import controller.FichajeController;
import controller.LoginController;
import controller.UsuarioController;
import model.Administrador;
import model.Trabajador;
import model.Usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        List<Usuario> usuarios = new ArrayList<>();
        UsuarioController usuarioController = new UsuarioController(usuarios);
        AdministradorController administradorController = new AdministradorController(usuarios, usuarioController);
        FichajeController fichajeController = new FichajeController();
        LoginController loginController = new LoginController(usuarioController);
        Scanner scn = new Scanner(System.in);

        usuarioController.cargarUsuarios();

        //POSIBILIDADES: Elegir una

        // 1. Crea un usuario administrador inicial "por defecto"

        /*usuarios.add(new Administrador("Jose", "Alonso", "12345678-P", "jose@administrador.com", "1234P"));*/

        // 2. Pide crear un administrador si la lista es Null
        if (usuarios.isEmpty()) {
            System.out.println("No existen administradores. Crea un nuevo Administrador: ");
            System.out.print("Nombre: ");
            String nombre = scn.nextLine();
            System.out.print("Apellido: ");
            String apellido = scn.nextLine();
            System.out.print("DNI: ");
            String dni = scn.nextLine();
            System.out.print("Email: ");
            String email = scn.nextLine();
            System.out.print("Password: ");
            String password = scn.nextLine();

            usuarios.add(new Administrador(
                    nombre.trim(),
                    apellido.trim(),
                    dni.trim(),
                    email.trim(),
                    password.trim()
            ));
            usuarioController.guardarUsuarios();

            usuarios.add(new Trabajador(
                    "Borja",
                    "Martín",
                    "00000000A",
                    "borja@empresa.com",
                    "1234"
            ));

            usuarios.add(new Trabajador(
                    "Jose",
                    "Alonso",
                    "11111111A",
                    "jose@empresa.com",
                    "1111"
            ));
        }

        while (true) {
            Usuario usuario = loginController.iniciarSesion(scn);

            if (usuario instanceof Administrador) {
                boolean salir = false;
                while (!salir) {
                    System.out.println("\n--- MENÚ ADMINISTRADOR ---");
                    System.out.println("1. Alta trabajador");
                    System.out.println("2. Baja trabajador");
                    System.out.println("3. Ver trabajador por DNI");
                    System.out.println("4. Ver todos los trabajadores");
                    System.out.println("5. Cambiar contraseña de trabajador");
                    System.out.println("6. Ver registros de jornada");
                    System.out.println("7. Cerrar sesión");
                    System.out.println("8. Salir");
                    System.out.print("Elige una opción: ");
                    int opcion = Integer.parseInt(scn.nextLine());

                    switch (opcion) {
                        case 1:
                            administradorController.altaTrabajador(scn);
                            usuarioController.guardarUsuarios();
                            break;
                        case 2:
                            administradorController.bajaTrabajador(scn);
                            usuarioController.guardarUsuarios();
                            break;
                        case 3:
                            administradorController.verTrabajadorPorDni(scn);
                            break;
                        case 4:
                            administradorController.verTrabajadores();
                            break;
                        case 5:
                            administradorController.cambiarPasswordTrabajador(scn);
                            usuarioController.guardarUsuarios();
                            break;
                        case 6:
                            fichajeController.verRegistros();
                            break;
                        case 7:
                            System.out.println("Cerrando Sesión...");
                            salir = true;
                            break;
                        case 8:
                            System.out.println("Saliendo del programa. ¡Que tengas un buen día!");
                            scn.close();
                            return;
                        default:
                            System.out.println("Opción no válida");
                    }
                }
            }

            if (usuario instanceof Trabajador) {
                boolean salir = false;
                while (!salir) {
                    System.out.println("\n--- MENÚ TRABAJADOR ---");
                    System.out.println("1. Fichar");
                    System.out.println("2. Cerrar sesión");
                    System.out.println("3. Salir");
                    System.out.print("Elige una opción: ");
                    int opcion = Integer.parseInt(scn.nextLine());

                    switch (opcion) {
                        case 1:
                            fichajeController.registrarFichaje(usuario);
                            break;
                        case 2:
                            System.out.println("Cerrando sesión...");
                            salir = true;
                            break;
                        case 3:
                            System.out.println("Saliendo del programa. ¡Que tengas un buen día!");
                            scn.close();
                            return;
                        default:
                            System.out.println("Opción no válida");
                    }
                }
            }
        }
    }
}
