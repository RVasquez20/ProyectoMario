/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EvaluarEstudiante;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author blitxs1226
 */
public class evaluacion_estudiante {

    public Scanner escaner = new Scanner(System.in);
    public Semaphore semaphore;
    public final String urlArchivo = "/home/ghostman/volume5/Preguntas.csv";
    public final String urlArchivoRespuestas = "/home/ghostman/volume5/Respuestas.txt";
    public List<Long> numeroLineas = new ArrayList<>();
    public HashSet<Long> preguntasElegidas = new HashSet<>();
    public int numeroAleatorioPosicion;

    public evaluacion_estudiante(Semaphore semaphore) {
        this.semaphore = semaphore;
    }

    public void procesoEvalucionPorEstudiante(String categoria) {
        try {
            String linea = "";
            long numLinea = 0;
            FileReader filePreguntas = new FileReader(urlArchivo);
            BufferedReader bufferFilePreguntas = new BufferedReader(filePreguntas);
            while ((linea = bufferFilePreguntas.readLine()) != null) {
                numLinea++;
                StringTokenizer stringToken = new StringTokenizer(linea, "\\");
                if (stringToken.nextToken().equalsIgnoreCase(categoria)) {
                    numeroLineas.add(numLinea);
                } else {
                    stringToken.nextToken();
                }
            }
            System.out.println(numeroLineas);
            bufferFilePreguntas.close();
            filePreguntas.close();
        } catch (Exception ex) {
            System.out.println("Error ::" + ex.getMessage());
        }
        if(numeroLineas.size()>3){
        while (preguntasElegidas.size() != 3) {
            numeroAleatorioPosicion = (int) (Math.random() * numeroLineas.size());
            long numeroPreguntaAleatorio = numeroLineas.get(numeroAleatorioPosicion);
            preguntasElegidas.add(numeroPreguntaAleatorio);
        }
        System.out.println(preguntasElegidas);
        for (Long numberpreguntaElegida : preguntasElegidas) {
            String respuestaEstudiante = "";
            try {
                String linea = "";
                long numLinea = 0;
                FileReader filePreguntas = new FileReader(urlArchivo);
                BufferedReader bufferFilePreguntas = new BufferedReader(filePreguntas);
                while ((linea = bufferFilePreguntas.readLine()) != null) {
                    numLinea++;
                    if (numLinea == numberpreguntaElegida) {
                        StringTokenizer stringToken = new StringTokenizer(linea, "\\");
                        String[]datos=new String[2];
                        for(int d=0;d<2;d++){
                            datos[d]=stringToken.nextToken();
                        }
                        System.out.println("Categoria:" + datos[0]);
                        System.out.println("------------------------------------------------------");
                        System.out.println("Pregunta:" + datos[1]);
                        System.out.println("Respuesta:");
                        respuestaEstudiante = escaner.nextLine();
                        System.out.println("=======================================================");
                        semaphore.acquire();
                        FileWriter fileRespuestas = new FileWriter(urlArchivoRespuestas, true);
                        fileRespuestas.append(datos[0]);
                        fileRespuestas.append("\\");
                        fileRespuestas.append(String.valueOf(numberpreguntaElegida));
                        fileRespuestas.append("\\");
                        fileRespuestas.append(respuestaEstudiante + "\n");
                        fileRespuestas.close();
                        semaphore.release();
                    }
                }
                bufferFilePreguntas.close();
                filePreguntas.close();
            } catch (Exception ex) {
                System.out.println("Error ::" + ex.getMessage());
            }
        }
        
    }else{
            System.out.println("Existen menos de 3 preguntas con esa categoria\n"
                    + "porfavor elija otra categoria o hable con su profesor para que aniada mas...");
}

    }

}
