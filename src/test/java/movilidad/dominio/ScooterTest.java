package movilidad.dominio;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ScooterTest {

    @Test
    @DisplayName("calcularTarifa: cobra desbloqueo mas minutos")
    void calcularTarifaSumaDesbloqueoYMinutos() {
        Scooter scooter = new Scooter("SC-01", 80);
        assertEquals(5.50, scooter.calcularTarifa(15), 0.001);
    }

    @Test
    @DisplayName("estaDisponible: falso si la bateria es menor a 20%")
    void noEstaDisponibleConBateriaBaja() {
        Scooter scooter = new Scooter("SC-02", 10);
        assertFalse(scooter.estaDisponible());
    }

    @Test
    @DisplayName("marcarAlquilado: deja de estar disponible")
    void marcarAlquiladoLoVuelveNoDisponible() {
        Scooter scooter = new Scooter("SC-03", 80);
        scooter.marcarAlquilado();
        assertFalse(scooter.estaDisponible());
    }

    @Test
    @DisplayName("recibirCarga: nunca supera el 100%")
    void recibirCargaNoSuperaCien() {
        Scooter scooter = new Scooter("SC-04", 95);
        scooter.recibirCarga(30);
        assertEquals(100, scooter.getNivelBateria());
    }
}
