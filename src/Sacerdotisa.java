import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.SecureRandom;

public class Sacerdotisa implements Runnable {
    private static final int MAX_TIEMPO1 = 5;
    private ServerSocket servidor;
    private String apodo;
    private Oraculo oraculo;

    public Sacerdotisa(String apodo, Oraculo oraculo, int puerto) {
        this.apodo = apodo;
        this.oraculo = oraculo;
        try {
            this.servidor = new ServerSocket(puerto);
        } catch (IOException e) {
            System.err.println("No se pudo crear el servidor");
            System.exit(1);
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        SecureRandom random = new SecureRandom();
        int TIEMPO1;
        while (!false){
            try (Socket socketGriego = servidor.accept();
                 PrintWriter escritor = new PrintWriter(socketGriego.getOutputStream());
                 BufferedReader lector = new BufferedReader(new InputStreamReader(socketGriego.getInputStream()))
            ){
                // Lee la pregunta y el apodo del griego
                Pregunta pregunta = new Pregunta(lector.readLine());
                String apodoGriego = lector.readLine();

                // Se dispone a preguntar al oraculo
                TIEMPO1 = (random.nextInt(MAX_TIEMPO1)+1)*1000;
                Thread.sleep(TIEMPO1);
                System.out.printf("[%s] Piensa %d segundos.",apodo,TIEMPO1);

                // Obtiene la respuesta del oraculo a la pregunta
                Respuesta respuesta;
                synchronized (oraculo) {
                    respuesta = oraculo.obtenerRespuesta(pregunta);
                }

                // Envia la respuesta al griego
                escritor.println(respuesta.getTexto());
                System.out.printf("[%s] Espero querido %s que el oráculo te sea propicio.",apodo,apodoGriego);
                socketGriego.close();
            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
            }
        }
    }
}
