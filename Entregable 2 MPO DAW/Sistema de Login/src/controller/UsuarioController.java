package controller;

import model.Administrador;
import model.Trabajador;
import model.Usuario;

import java.io.*;
import java.util.List;
import java.util.Scanner;

public class UsuarioController {

    private List<Usuario> usuarios;

    public UsuarioController() {}

    public UsuarioController(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public Usuario buscarUsuarioPorDni(String dni){
        for (Usuario usuario : usuarios) {
            if (dni.equals(usuario.getDni())) {
                return usuario;
            }
        }
        return null;
    }

    public Usuario buscarUsuarioPorEmail(String email){
        for (Usuario usuario : usuarios) {
            if (email.equals(usuario.getEmail())) {
                return usuario;
            }
        }
        return null;
    }

    public Usuario validarUsuario(Scanner scn){

        System.out.print("\nIntroduce el correo del usuario:");
        String correo = scn.nextLine().trim();
        System.out.print("Introduce el password del usuario:");
        String password = scn.nextLine().trim();

        Usuario usuarioEncontrado = buscarUsuarioPorEmail(correo);

        if (usuarioEncontrado == null) {
            System.out.println("Usuario no encontrado");
            return null;
        }

        if (password.equals(usuarioEncontrado.getPassword())) {
            System.out.println("Login correcto");
            return usuarioEncontrado;

        }else {
            System.out.println("Usuario o contraseña incorrectos.");
            return null;
        }
    }

    public void guardarUsuarios(){
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("usuarios.txt"));

            for (Usuario usuario : usuarios) {

                String tipo = "TRABAJADOR";

                if (usuario instanceof Administrador){
                    tipo = "ADMINISTRADOR";
                }

                String linea = tipo + ";" +
                        usuario.getNombre() + ";" +
                        usuario.getApellido() + ";" +
                        usuario.getDni() + ";" +
                        usuario.getEmail() + ";" +
                        usuario.getPassword();

                bufferedWriter.write(linea);
                bufferedWriter.newLine();
            }
            bufferedWriter.close();

        } catch (IOException e) {
            System.out.println("Error al guardar usuarios. Comuníquese con el Administrador.");
        }
    }

    public void cargarUsuarios(){

        try {

            File file = new File("usuarios.txt");

            if (!file.exists()) {
                return;
            }

            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String linea;

            while ((linea = bufferedReader.readLine()) != null){

                String[] datos = linea.split(";");

                if (datos.length !=6) continue;

                String tipo = datos[0];
                String nombre = datos[1];
                String apellido = datos[2];
                String dni = datos[3];
                String email = datos[4];
                String password = datos[5];

                if (tipo.equals("ADMINISTRADOR")) {
                    usuarios.add(new Administrador(nombre, apellido, dni, email, password));
                }else {
                    usuarios.add(new Trabajador(nombre, apellido, dni, email, password));
                }
            }
            bufferedReader.close();

        } catch (IOException e) {
            System.out.println("Error al cargar usuarios. Comuníquese con el Administrador.");
        }
    }
}
