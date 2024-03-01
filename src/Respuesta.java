public class Respuesta {
    private String texto;

    public Respuesta(String texto) {
        this.texto = texto;
    }

    public String getTexto() {
        return texto;
    }

    @Override
    public String toString() {
        return "Respuesta:  " + texto + ".\n";
    }
}