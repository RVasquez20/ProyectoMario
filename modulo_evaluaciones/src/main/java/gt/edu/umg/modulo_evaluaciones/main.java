/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gt.edu.umg.modulo_evaluaciones;

import EvaluarEstudiante.evaluacion_estudiante;
import EvaluarEstudiante.generacionReporte;
import java.util.Scanner;
import java.util.concurrent.Semaphore;

/**
 *
 * @author blitxs1226
 */
public class main {
    /**
     * Esta funcion Iniciara el hilo que genera el reporte y tambien esta
     * funcion se encarga de mostrar el menu de seleccion, en el cual el
     * estudiante escriba la categoria la cual desea acceder y de esta misma
     * forma iniciara su evaluacion
     * @param args 
     */
    public static void main(String[] args) {
         Semaphore semaphore = new Semaphore(1);
Scanner escaner=new Scanner(System.in);
        final String[] categoriasDisponibles={"BASIC_COMMANDS", "SHELL_SCRIPTS", "SECURE_SHELL",
        "POSIX_SEMAPHORES", "MAVEN", "JAVA_THREADS", "DOCKERS"};
                int opciones = 0;
          generacionReporte reporte = new generacionReporte(semaphore);
        Thread reportes = new Thread(reporte);
        reportes.start();
        do{
          
        
            evaluacion_estudiante evaluacion=new evaluacion_estudiante(semaphore);

        System.out.println("+++++++++++++++++++++");
        System.out.println("+        Menu       +");
        System.out.println("+                   +");
        System.out.println("+ 1-basic_comands   +");
        System.out.println("+ 2-shell_script    +");
        System.out.println("+ 3-secure_shell    +");
        System.out.println("+ 4-posix_semaphores+");
        System.out.println("+ 5-maven           +");
        System.out.println("+ 6-java_threads    +");
        System.out.println("+ 7-dockers         +");
        System.out.println("+ 8-exit            +");
        System.out.println("+++++++++++++++++++++");
        opciones=escaner.nextInt();
        escaner.nextLine();
        switch(opciones){
            
            case 1: {
                evaluacion.procesoEvalucionPorEstudiante(categoriasDisponibles[0]);
                break;
            }
            case 2: {
                evaluacion.procesoEvalucionPorEstudiante(categoriasDisponibles[1]);
                break;
            }
            case 3: {
                evaluacion.procesoEvalucionPorEstudiante(categoriasDisponibles[2]);
                break;
            }
            case 4: {
                evaluacion.procesoEvalucionPorEstudiante(categoriasDisponibles[3]);
                break;
            }
            case 5: {
                evaluacion.procesoEvalucionPorEstudiante(categoriasDisponibles[4]);
                break;
            }
            case 6: {
                evaluacion.procesoEvalucionPorEstudiante(categoriasDisponibles[5]);
                break;
            }
            case 7: {
                evaluacion.procesoEvalucionPorEstudiante(categoriasDisponibles[6]);
                break;
            }
            case 8: {
                System.exit(0);
                break;
            }
            
            
            default:{
                System.out.println("Opcion no Disponible");
                break;
            }
        }
         
        }while(opciones!=8);
        try {
            reportes.join();
        } catch (Exception execp) {
            System.out.println("Error :::" + execp.getMessage());
        }
       
    }
}
