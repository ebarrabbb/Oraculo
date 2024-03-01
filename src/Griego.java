import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Griego implements Runnable {
    private static final String ipServidor = "localhost";
    private String apodo;   // No nulo
    private Oraculo oraculo;
    private int puerto;
    //// TODO: 01/03/2024 Añadir NotNull
    public Griego(String apodo, Oraculo oraculo, int puerto) {
        this.apodo = apodo;
        this.oraculo = oraculo;
        this.puerto = puerto;
    }

    @Override
    public void run() {
        System.out.printf("[%s] Soy %s y espero que el oráculo me sea propicio\n",apodo,apodo);
        Pregunta pregunta;
        synchronized (oraculo) {
            pregunta = oraculo.obtenerPregunta();
        }
        preguntar(pregunta);
    }

    private synchronized void preguntar(Pregunta pregunta) {
        try (Socket socketGriego = new Socket(ipServidor, puerto);
             PrintWriter escritor = new PrintWriter(socketGriego.getOutputStream());
             BufferedReader lector = new BufferedReader(new InputStreamReader(socketGriego.getInputStream()))
        ){
            // Envia la pregunta
            System.out.printf("[%s] Envio la pregunta \"%s\" a la sacerdotisa\n", apodo, pregunta.getTexto());
            escritor.println(pregunta.getTexto());

            // Envia el apodo
            System.out.printf("[%s] Envio el apodo \"%s\" a la sacerdotisa\n", apodo);
            escritor.println(apodo);

            // Recibe la respuesta
            Respuesta respuesta = new Respuesta(lector.readLine());
            // Imprime la respuesta
            System.out.printf("[%s] Recibo la respuesta \"%s\" de la sacerdotisa\n", apodo, respuesta.getTexto());
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

}
