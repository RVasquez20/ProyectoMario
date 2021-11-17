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
    public final String urlArchivo = "/usr/src/proyecto/preguntas.txt";
    public final String urlArchivoRespuestas = "/usr/src/proyecto/respuestas.txt";
    public final String urlArchivoReporte = "/usr/src/reportes/respuestas.html";

    public generacionReporte(Semaphore semaphore) {
        this.semaphore = semaphore;
    }

    /**
     * Esta funcion realiza una verificacion para saber 
     * si el semaforo esta disponible, en caso de que este lo este pasara y 
     * escribira el reporte html leera el archivo que contiene las respuestas
     * linea por linea y escribira la categoria, luego buscara el numero de 
     * linea en el archivo de preguntas y escribira la pregunta que corresponda
     * a ese numero, luego escribira la respuesta, al terminar de procesar el archivo
     * de respuestas , liberara el semaforo y esperara 8 segundos para poder repetir
     * todo el proceso mencionado con anterioridad.
     */
    @Override
    public void run() {
        while (true) {
            try {
                semaphore.acquire();
                String linea = "", preguntaPorRespuesta = "", respuesta = "", categoriaRespuesta = "", numeroEnRespuesta = "";
                int numLinea = 0;
                FileReader fileRespuestas = new FileReader(urlArchivoRespuestas);
                FileWriter fileRespuestasReporte = new FileWriter(urlArchivoReporte);
                fileRespuestasReporte.write("<!DOCTYPE html>\n"
                        + "<html lang=\"en\">\n"
                        + "<head>\n"
                        + "  <title>Bootstrap Example</title>\n"
                        + "  <meta charset=\"utf-8\">\n"
                        + "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n"
                        + "  <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css\">\n"
                        + "  <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js\"></script>\n"
                        + "  <script src=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js\"></script>\n"
                        + "  <script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js\"></script>\n"
                        + "</head>\n"
                        + "<body style=\"background: #85929E;\">\n"
                        + "\n"
                        + "<div class=\"container\" >\n"
                        + "  <h2>Respuestas de Estudiantes</h2>\n"
                        + "  <p>Aca se guardaran las respuestas de los estudiantes tras haber respondido en el modulo de evaluacion</p>\n"
                        + "  <table class=\"table\">\n"
                        + "    <thead class=\"thead-dark\">\n"
                        + "      <tr>\n"
                        + "        <th>Categoria</th>\n"
                        + "        <th>Pregunta</th>\n"
                        + "        <th>Respuesta</th>\n"
                        + "      </tr>\n"
                        + "    </thead>\n"
                        + "    <tbody>");
                BufferedReader bufferFileRespuestas = new BufferedReader(fileRespuestas);
                while ((linea = bufferFileRespuestas.readLine()) != null) {
                    StringTokenizer stringToken = new StringTokenizer(linea, "\\");
                    categoriaRespuesta = stringToken.nextToken();
                    numeroEnRespuesta = stringToken.nextToken();
                    respuesta = stringToken.nextToken();

                    String linea2 = "";
                    FileReader filePreguntas = new FileReader(urlArchivo);
                    BufferedReader bufferFilePreguntas = new BufferedReader(filePreguntas);
                    while ((linea2 = bufferFilePreguntas.readLine()) != null) {
                        StringTokenizer stringTokenPreguntas = new StringTokenizer(linea2, "\\");
                        numLinea++;
                        if (String.valueOf(numLinea).equals(numeroEnRespuesta)) {
                            stringTokenPreguntas.nextToken();
                            preguntaPorRespuesta = stringTokenPreguntas.nextToken();
                        }

                    }
                    filePreguntas.close();
                    bufferFilePreguntas.close();
                    fileRespuestasReporte.write("<tr class=\"table-secondary\"><td>" + categoriaRespuesta + "</td><td>" + preguntaPorRespuesta + "</td><td>"
                            + respuesta + "</td></tr>\n");

                }
                fileRespuestasReporte.write("</tbody>\n</table>\n</div>\n</body>\n</html>");
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
