/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EvaluarEstudiante;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import static java.lang.Thread.sleep;
import java.util.StringTokenizer;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author blitxs1226
 */
public class generacionReporte implements Runnable {

    public Semaphore semaphore;
    public final String urlArchivo = "/home/ghostman/volume5/Preguntas.csv";
    public final String urlArchivoRespuestas = "/home/ghostman/volume5/Respuestas.txt";
    public final String urlArchivoReporte = "/home/ghostman/volume5/Respuestas.html";

    public generacionReporte(Semaphore semaphore) {
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        while (true) {
            try {
                semaphore.acquire();
                String linea = "", preguntaPorRespuesta = "",respuesta="",categoriaRespuesta="",numeroEnRespuesta="";
                int numLinea=0;
                FileReader fileRespuestas = new FileReader(urlArchivoRespuestas);
                FileWriter fileRespuestasReporte = new FileWriter(urlArchivoReporte);
                fileRespuestasReporte.write("<html>\n"
                     + "<body>\n"
                     + "<table>\n"
                     + "<thead>\n"
                     + "<tr><th>Categoria</th><th>Pregunta</th><th>Respuesta</th></tr>\n"
                     + "<tbody>\n");
                BufferedReader bufferFileRespuestas = new BufferedReader(fileRespuestas);
            while ((linea = bufferFileRespuestas.readLine()) != null) {
                StringTokenizer stringToken = new StringTokenizer(linea, "\\");
                categoriaRespuesta= stringToken.nextToken();
                numeroEnRespuesta=stringToken.nextToken();
                respuesta=stringToken.nextToken();
                
                String linea2="";
                FileReader filePreguntas = new FileReader(urlArchivo); 
                 BufferedReader bufferFilePreguntas = new BufferedReader(filePreguntas);
            while ((linea2 = bufferFilePreguntas.readLine()) != null) {
                StringTokenizer stringTokenPreguntas = new StringTokenizer(linea2, "\\");
                numLinea++;
                if(String.valueOf(numLinea).equals(numeroEnRespuesta)){
                    stringTokenPreguntas.nextToken();
                    preguntaPorRespuesta=stringTokenPreguntas.nextToken();
                }
                
            }
              filePreguntas.close();
              bufferFilePreguntas.close();
             fileRespuestasReporte.write("<tr><td>"+categoriaRespuesta+"</td><td>"+preguntaPorRespuesta+"</td><td>"
                     +respuesta+"</td></tr>\n");   
                
                
            }
            fileRespuestasReporte.write("</tbody>\n</table>\n</body>\n</html>");
            fileRespuestasReporte.close();
            bufferFileRespuestas.close();
            fileRespuestas.close();
                semaphore.release();
                sleep(8);
            } catch (Exception execp) {
                System.out.println("Error ::1" + execp.getMessage());
            }
        }
    }

}
