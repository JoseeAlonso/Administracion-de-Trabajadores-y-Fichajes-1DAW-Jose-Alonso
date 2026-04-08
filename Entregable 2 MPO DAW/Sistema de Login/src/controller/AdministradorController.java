package controller;

import model.Trabajador;
import model.Usuario;

import java.util.List;
import java.util.Scanner;

public class AdministradorController {

    private List<Usuario> usuarios;
    private UsuarioController usuarioController;

    public AdministradorController() {
    }

    public AdministradorController(List<Usuario> usuarios,  UsuarioController usuarioController) {
        this.usuarios = usuarios;
        this.usuarioController = usuarioController;
    }

    public void altaTrabajador(Scanner scn){
        System.out.println("Introduce el nombre del trabajador: ");
        String nombre = scn.nextLine();
        System.out.println("Introduce el apellido del trabajador: ");
        String apellido = scn.nextLine();
        System.out.println("Introduce el dni del trabajador: ");
        String dni = scn.nextLine();
        System.out.println("Introduce el email del trabajador: ");
        String email = scn.nextLine();
        System.out.println("Introduce el password del trabajador: ");
        String password = scn.nextLine();

        Trabajador trabajador = new Trabajador(nombre, apellido, dni, email, password);

        if (usuarioController.buscarUsuarioPorEmail(email) != null) {
            System.out.println("Error: ya existe un usuario con ese email");
            return;
        }
        if (usuarioController.buscarUsuarioPorDni(dni) != null) {
            System.out.println("Error: ya existe un usuario con ese DNI");
        }

        usuarios.add(trabajador);
        System.out.println("Trabajador agregado correctamente.");
    }

    public void bajaTrabajador (Scanner scn){
        System.out.println("Introduce el DNI del trabajador");
        String dni = scn.nextLine();

        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getDni().equals(dni)) {
                usuarios.remove(i);
                System.out.println("Trabajador eliminado correctamente.");
                return;
            }
        }
        System.out.println("No se encontró ningún trabajador con ese DNI");
    }

    public void verTrabajadorPorDni(Scanner scn){
        System.out.println("Introduce el DNI del trabajador");
        String dni = scn.nextLine();

        boolean existe = false;

        for (Usuario usuario : usuarios) {

            if (usuario.getDni().equals(dni)) {
                System.out.println(usuario);
                existe = true;
                break;
            }
        }
        if (!existe) {
            System.out.println("Error: el usuario con ese DNI no existe.");
        }
    }

    public void verTrabajadores(){

        boolean existenTrabajadores = false;

        for (Usuario usuario : usuarios) {
            if (usuario instanceof Trabajador) {
                System.out.println(usuario);
                existenTrabajadores = true;
            }
        }
        if (!existenTrabajadores) {
            System.out.println("No hay trabajadores en el sistema.");
        }
    }

    public void cambiarPasswordTrabajador(Scanner scn){
        System.out.println("Introduce el DNI del trabajador");
        String dni = scn.nextLine();
        boolean encontrado = false;

        for (Usuario usuario : usuarios) {

            if (usuario.getDni().equals(dni) && usuario instanceof Trabajador) {
                System.out.println("Introduce la nueva contraseña.");
                usuario.setPassword(scn.nextLine());
                System.out.println("Contraseña cambiada correctamente.");
                encontrado = true;
                break;

            }
        }
        if (!encontrado) {
            System.out.println("Error: el trabajador no existe con ese DNI.");
        }
    }
}



