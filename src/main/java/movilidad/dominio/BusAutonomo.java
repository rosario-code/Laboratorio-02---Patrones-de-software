package movilidad.dominio;

public class BusAutonomo implements IAlquilable, IConBateria {
    private static final double TARIFA_FIJA_VIAJE = 2.50;
    private static final double TARIFA_POR_MINUTO = 0.05;

    private final String codigo;
    private final int capacidadPasajeros;
    private int asientosOcupados;
    private int nivelBateria;

    public BusAutonomo(String codigo, int capacidadPasajeros, int nivelBateria) {
        this.codigo = codigo;
        this.capacidadPasajeros = capacidadPasajeros;
        this.nivelBateria = nivelBateria;
    }

    @Override public String getCodigo() { return codigo; }
    @Override public String getDescripcion() { return "Bus autonomo " + codigo; }

    // Un bus sigue disponible mientras le queden asientos libres
    @Override
    public boolean estaDisponible() {
        return asientosOcupados < capacidadPasajeros;
    }

    @Override public void marcarAlquilado()  { asientosOcupados++; }
    @Override public void marcarDisponible() { asientosOcupados = Math.max(0, asientosOcupados - 1); }

    @Override
    public double calcularTarifa(int minutos) {
        return TARIFA_FIJA_VIAJE + TARIFA_POR_MINUTO * minutos;
    }

    @Override public int getNivelBateria() { return nivelBateria; }

    @Override
    public void recibirCarga(int porcentaje) {
        nivelBateria = Math.min(100, nivelBateria + porcentaje);
    }

    public int getAsientosOcupados() { return asientosOcupados; }
}
