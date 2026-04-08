package model;

public class Trabajador extends Usuario {

    public Trabajador(String nombre, String apellido, String dni, String email, String password) {
        super(nombre, apellido, dni, email, password);
    }

    @Override
    public String toString() {
        return "Trabajador -> " + super.toString();
    }
}
