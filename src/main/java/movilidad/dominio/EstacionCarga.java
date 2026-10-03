package movilidad.dominio;

// Se alquila un punto de carga; no tiene bateria propia, por eso NO implementa IConBateria (ISP)
public class EstacionCarga implements IAlquilable {
    private static final double TARIFA_POR_MINUTO = 0.50;
    private static final int PORCENTAJE_POR_MINUTO = 2;

    private final String codigo;
    private boolean ocupada;

    public EstacionCarga(String codigo) {
        this.codigo = codigo;
    }

    @Override public String getCodigo() { return codigo; }
    @Override public String getDescripcion() { return "Estacion de carga " + codigo; }
    @Override public boolean estaDisponible() { return !ocupada; }
    @Override public void marcarAlquilado()  { this.ocupada = true; }
    @Override public void marcarDisponible() { this.ocupada = false; }

    @Override
    public double calcularTarifa(int minutos) {
        return TARIFA_POR_MINUTO * minutos;
    }

    // Recibe cualquier vehiculo con bateria: depende de la abstraccion, no de Scooter o Bus
    public void cargar(IConBateria vehiculo, int minutos) {
        vehiculo.recibirCarga(PORCENTAJE_POR_MINUTO * minutos);
    }
}
