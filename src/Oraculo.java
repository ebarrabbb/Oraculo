import java.security.SecureRandom;
import java.util.*;

public class Oraculo {
    private Map<Pregunta, Respuesta> mapa;

    public Oraculo() {
        this.mapa = new HashMap<>();

        List<Pregunta> preguntas = Fabrica.generarPreguntas();
        List<Respuesta> respuestas = Fabrica.generarRespuestas();
        for (int i = 0; i < preguntas.size(); i++) {
            mapa.put(preguntas.get(i), respuestas.get(i));
        }
    }

    public Pregunta obtenerPregunta() {
        SecureRandom random = new SecureRandom();
        int num =random.nextInt(mapa.size()+1);
        Iterator<Pregunta> iterador = mapa.keySet().iterator();
        int contador = 1;
        while (iterador.hasNext()) {
            Pregunta pregunta = iterador.next();
            if (contador == num) {
                return pregunta;
            }
            contador++;
        }
        System.err.println("No se encontro la pregunta.");
        System.exit(1);
        return null;
    }

    public Respuesta obtenerRespuesta(Pregunta pregunta) {
        return mapa.get(pregunta);
    }

    @Override
    public String toString() {
        return "Oraculo{" +
                "mapa=" + mapa +
                '}';
    }
}
