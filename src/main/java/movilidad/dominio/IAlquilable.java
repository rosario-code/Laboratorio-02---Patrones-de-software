package movilidad.dominio;

// Contrato comun: todo recurso de movilidad que se puede alquilar (polimorfismo)
public interface IAlquilable {
    String getCodigo();
    String getDescripcion();
    boolean estaDisponible();
    void marcarAlquilado();
    void marcarDisponible();
    double calcularTarifa(int minutos);
}
