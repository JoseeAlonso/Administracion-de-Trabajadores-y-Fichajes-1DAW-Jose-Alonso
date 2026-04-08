package controller;

import model.Usuario;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FichajeController {

    public FichajeController() {
    }

    public void registrarFichaje(Usuario usuario){

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm:ss");
        String date = now.format(formatter);

        try {
            FileWriter fw = new FileWriter("horas.txt",true);
            String fichaje = usuario.getEmail() + " - " +date;
            fw.write(fichaje + "\n");
            System.out.println("Fichaje registrado exitosamente.");
            fw.close();

        } catch (IOException e) {
            System.out.println("Error al registrar fichaje.");
        }
    }

    public void verRegistros(){

        File file = new File("horas.txt");

        if(!file.exists()){
            System.out.println("No existen registros de jornada todavía.");
            return;
        }

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

            String line;

            System.out.println("--- REGISTROS DE JORNADA ---");

            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }

            bufferedReader.close();
        } catch (IOException e) {
            System.out.println("Error al leer los registros.");
        }
    }

}
