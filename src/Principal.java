import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Principal {
    private static final int PUERTO = 5000;
    public static void main(String[] args) {
        Oraculo oraculo = new Oraculo();
        ExecutorService ex = Executors.newFixedThreadPool(4);
        Sacerdotisa sacerdotisa = new Sacerdotisa("PITIA", oraculo, PUERTO);
        List<Griego> griegos = Fabrica.generarGriegos(oraculo, PUERTO);
        ex.execute(sacerdotisa);
        // Aseguramos que se ejecute primero el servidor y luego los griegos
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (Griego griego : griegos) {
            ex.execute(griego);
        }
        
        ex.shutdown();
    }
}
