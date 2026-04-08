package controller;

import model.Usuario;

import java.util.Scanner;

public class LoginController {

    private final UsuarioController usuarioController;

    public LoginController(UsuarioController usuarioController) {
        this.usuarioController = usuarioController;
    }

    public Usuario iniciarSesion(Scanner scn){
        Usuario usuario = null;
        while (usuario == null){
            usuario = usuarioController.validarUsuario(scn);
        }
        return usuario;
    }
}
