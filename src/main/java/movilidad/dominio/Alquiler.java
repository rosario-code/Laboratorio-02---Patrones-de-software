package movilidad.dominio;

public class Alquiler {
    private final String usuarioId;
    private final IAlquilable recurso;
    private boolean finalizado;

    public Alquiler(String usuarioId, IAlquilable recurso) {
        this.usuarioId = usuarioId;
        this.recurso = recurso;
    }

    public String getUsuarioId()   { return usuarioId; }
    public IAlquilable getRecurso() { return recurso; }
    public boolean estaFinalizado() { return finalizado; }
    public void finalizar()         { this.finalizado = true; }
}
