package movilidad.dominio;

public class Scooter implements IAlquilable, IConBateria {
    private static final double TARIFA_DESBLOQUEO = 1.00;
    private static final double TARIFA_POR_MINUTO = 0.30;
    private static final int BATERIA_MINIMA_PARA_ALQUILAR = 20;

    private final String codigo;
    private int nivelBateria;
    private boolean alquilado;

    public Scooter(String codigo, int nivelBateria) {
        this.codigo = codigo;
        this.nivelBateria = nivelBateria;
    }

    @Override public String getCodigo() { return codigo; }
    @Override public String getDescripcion() { return "Scooter electrico " + codigo; }

    @Override
    public boolean estaDisponible() {
        return !alquilado && nivelBateria >= BATERIA_MINIMA_PARA_ALQUILAR;
    }

    @Override public void marcarAlquilado()  { this.alquilado = true; }
    @Override public void marcarDisponible() { this.alquilado = false; }

    @Override
    public double calcularTarifa(int minutos) {
        return TARIFA_DESBLOQUEO + TARIFA_POR_MINUTO * minutos;
    }

    @Override public int getNivelBateria() { return nivelBateria; }

    @Override
    public void recibirCarga(int porcentaje) {
        nivelBateria = Math.min(100, nivelBateria + porcentaje);
    }
}
