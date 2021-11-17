/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Preguntas;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;
/**
 *
 * @author blitxs1226
 */
public class Cuestionario {

    public final String urlArchivo = "/usr/src/proyecto/preguntas.txt";
    public String linea;
    public final String[] categoriasDisponibles={"BASIC_COMMANDS", "SHELL_SCRIPTS", "SECURE_SHELL",
        "POSIX_SEMAPHORES", "MAVEN", "JAVA_THREADS", "DOCKERS"};
    /**
     * Esta funcion se encargara de listar las preguntas con sus respectivos indices
     * y categoria las cuales estan almacenadas en el archivo el cual lleva de nombre
     * "preguntas"
     */
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

    /**
     * En esta funcion mostraremos las categorias displonibles para poder
     * escribir 1 pregunta a cerca de la categoria previamente escrita
     * por el usuario y se aniadira al archivo de preguntas
     */
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
        } catch (Exception execp) {
              System.out.println("Error :" + execp.getMessage());
        }
            
            
        }else{
            System.out.println("Categoria Elegida no Disponible...");
        }
    }
    /**
     * Esta funcion de encarga de verificar que si la categoria escrita
     * por el usuario esta dentro de las categorias disponibles
     * @param categoriaSeleccionada
     * @return 
     */
    public boolean verificarCategoria(String categoriaSeleccionada){
        return (Arrays.asList(categoriasDisponibles).contains(categoriaSeleccionada));
    }

}
