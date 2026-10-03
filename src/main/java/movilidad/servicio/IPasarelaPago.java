package movilidad.servicio;

public interface IPasarelaPago {
    boolean cobrar(String usuarioId, double monto);
}
