/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gt.edu.umg.sistemas.modulomantenimiento;

import Preguntas.Cuestionario;
import java.util.Scanner;

/**
 *
 * @author blitxs1226
 */
public class Main {

    /**
     * En esta funcion se mostrara como tal el menu grafico del modulo de
     * mantenimiento, contando con las opciones ver respues, agregar una
     * pregunta o incluso salir de dicho menu.
     *
     * @param args
     */
    public static void main(String[] args) {
        Scanner escaneo = new Scanner(System.in);
        Cuestionario preguntas = new Cuestionario();
        while (true) {
            int opcion = 0;
            System.out.println("==================");
            System.out.println("  Menu Principal  ");
            System.out.println("==================");
            System.out.println(" 1)Ver Preguntas  ");
            System.out.println("------------------");
            System.out.println("2)Agregar Pregunta");
            System.out.println("------------------");
            System.out.println("       3)Salir    ");
            System.out.println("==================");
            opcion = escaneo.nextInt();
            escaneo.nextLine();
            switch (opcion) {
                case 1: {
                    //en este se mostraran todas las preguntas agregadas
                    preguntas.listaPreguntas();
                    break;
                }
                case 2: {
                    //ESte permitira agregar una pregunta nueva al listado
                    preguntas.adicionarNuevaPregunta();
                    break;
                }
                case 3: {
                    //opcion para salir del menu de opcion multiple
                    System.exit(0);
                    break;
                }
                default: {
                    System.out.println("Opcion Disponible Proximamente...\nElija otra Opcion");
                    break;
                }
            }
        }
    }
}
