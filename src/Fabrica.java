import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Fabrica {
    private static List<Pregunta> preguntas;
    private static List<Respuesta> respuestas;
    private static List<Griego> griegos;

    static {

        preguntas = new LinkedList<>();
        respuestas = new LinkedList<>();
        griegos = new LinkedList<>();

        // Pregunta y respuesta 1
        preguntas.add(new Pregunta("¿Cuál es el destino que nos aguarda como" +
                "sociedad y cómo podemos prepararnos para enfrentarlo?"));
        respuestas.add(new Respuesta("El destino de la sociedad está" +
                "entrelazado con sus acciones y decisiones" +
                "colectivas. Adoptar la virtud, la sabiduría y la" +
                "colaboración contribuirá a un futuro próspero."));

        // Pregunta y respuesta 2
        preguntas.add(new Pregunta(": ¿Cómo podemos encontrar el camino hacia la" +
                "verdad y la comprensión más profunda en un mundo lleno de" +
                "incertidumbres?"));
        respuestas.add(new Respuesta(": Buscad el conocimiento con humildad y" +
                "mantened una mente abierta. La verdad se revela a" +
                "aquellos que buscan con sinceridad y perseverancia"));

        // Pregunta y respuesta 3
        preguntas.add(new Pregunta("¿Cuál es el significado más profundo de la vida" +
                "individual y cómo podemos vivir de acuerdo con nuestro" +
                "destino personal?"));
        respuestas.add(new Respuesta("Cada vida tiene un propósito único.\n" +
                "Reflexionad sobre vuestras acciones, cultivad la\n" +
                "virtud y buscad el equilibrio para vivir en armonía\n" +
                "con vuestro destino."));
    }

    public static List<Griego> generarGriegos(Oraculo oraculo, int puerto) {
        griegos.add(new Griego("Atenea", oraculo, puerto));
        griegos.add(new Griego("Homero", oraculo, puerto));
        griegos.add(new Griego("Platon", oraculo, puerto));
        return griegos;
    }
    public static List<Pregunta> generarPreguntas() {
        return preguntas;
    }

    public static List<Respuesta> generarRespuestas() {
        return respuestas;
    }
}
