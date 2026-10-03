package movilidad.dominio;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BusAutonomoTest {

    @Test
    @DisplayName("calcularTarifa: tarifa fija mas minutos")
    void calcularTarifaFijaMasMinutos() {
        BusAutonomo bus = new BusAutonomo("BUS-01", 30, 90);
        assertEquals(4.50, bus.calcularTarifa(40), 0.001);
    }

    @Test
    @DisplayName("estaDisponible: falso cuando el bus esta lleno")
    void noEstaDisponibleCuandoEstaLleno() {
        BusAutonomo bus = new BusAutonomo("BUS-02", 1, 90);
        bus.marcarAlquilado();
        assertFalse(bus.estaDisponible());
    }

    @Test
    @DisplayName("marcarDisponible: libera un asiento")
    void marcarDisponibleLiberaUnAsiento() {
        BusAutonomo bus = new BusAutonomo("BUS-03", 30, 90);
        bus.marcarAlquilado();
        bus.marcarDisponible();
        assertEquals(0, bus.getAsientosOcupados());
    }
}
