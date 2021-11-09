/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Preguntas;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author blitxs1226
 */
public class Cuestionario {

    public final String urlArchivo = "/home/ghostman/volume5/Preguntas.csv";
    public String linea;
    public final String[] categoriasDisponibles={"BASIC_COMMANDS", "SHELL_SCRIPTS", "SECURE_SHELL",
        "POSIX_SEMAPHORES", "MAVEN", "JAVA_THREADS", "DOCKERS"};
    public void listaPreguntas() {
        long numLinea=0;
        System.out.println("Listado de Preguntas");
        System.out.println("------------------------------------------------------------");
        try {
            FileReader filePreguntas = new FileReader(urlArchivo);
            BufferedReader bufferFilePreguntas = new BufferedReader(filePreguntas);
            while ((linea = bufferFilePreguntas.readLine()) != null) {
                numLinea++;
                System.out.println("Numero de Pregunta:" + numLinea);
                StringTokenizer stringToken = new StringTokenizer(linea, "\\");
                System.out.println("Categoria:" + stringToken.nextToken());
                System.out.println("Pregunta:" + stringToken.nextToken());
                System.out.println("------------------------------------------------------");   
            }
            bufferFilePreguntas.close();
            filePreguntas.close();
        } catch (Exception execp) {
            System.out.println("Error :" + execp.getMessage());
        }
    }

    public void adicionarNuevaPregunta() {
        Scanner escaneo=new Scanner(System.in);
        System.out.println("De las siguiente categorias escriba la que corresponda a su Pregunta:");
        System.out.println(Arrays.asList(categoriasDisponibles));
        String categoriaElegida=escaneo.nextLine();
        if(verificarCategoria(categoriaElegida.toUpperCase())){
            System.out.println("Ingrese la nueva Pregunta:");
            String preguntaNueva=escaneo.nextLine();
            
            try {
            FileWriter filePregunta = new FileWriter(urlArchivo, true);
             filePregunta.append(categoriaElegida.toUpperCase()+"\\"+ preguntaNueva + "\n");
            filePregunta.flush();
            filePregunta.close();
            System.out.println("Pregunta Agregada Exitosamente :D");  
        } catch (Exception execp) {
              System.out.println("Error :" + execp.getMessage());
        }
            
            
        }else{
            System.out.println("Categoria Elegida no Disponible...");
        }
    }
    
    public boolean verificarCategoria(String categoriaSeleccionada){
        return (Arrays.asList(categoriasDisponibles).contains(categoriaSeleccionada));
    }

}
