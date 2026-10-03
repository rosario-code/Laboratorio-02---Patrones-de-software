package movilidad.dominio;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EstacionCargaTest {

    @Test
    @DisplayName("calcularTarifa: cobra por minuto de uso")
    void calcularTarifaPorMinuto() {
        EstacionCarga estacion = new EstacionCarga("EC-01");
        assertEquals(5.00, estacion.calcularTarifa(10), 0.001);
    }

    @Test
    @DisplayName("cargar: aumenta la bateria de cualquier vehiculo con bateria")
    void cargarAumentaBateria() {
        EstacionCarga estacion = new EstacionCarga("EC-02");
        Scooter scooter = new Scooter("SC-10", 50);
        estacion.cargar(scooter, 10);
        assertEquals(70, scooter.getNivelBateria());
    }
}
