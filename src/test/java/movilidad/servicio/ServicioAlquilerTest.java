package movilidad.servicio;

import movilidad.dominio.Alquiler;
import movilidad.dominio.Scooter;
import movilidad.infraestructura.RepositorioEnMemoria;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

// la inyeccion de dependencias permite usar pruebas
class ServicioAlquilerTest {

    private RepositorioEnMemoria repositorio;
    private List<String> mensajesEnviados;
    private INotificador notificadorFalso;
    private Scooter scooter;

    @BeforeEach
    void preparar() {
        repositorio = new RepositorioEnMemoria();
        mensajesEnviados = new ArrayList<>();
        notificadorFalso = (usuario, mensaje) -> mensajesEnviados.add(mensaje);
        scooter = new Scooter("SC-01", 80);
        repositorio.guardar(scooter);
    }

    private ServicioAlquiler crearServicio(boolean pagoAprobado) {
        IPasarelaPago pagoFalso = (usuario, monto) -> pagoAprobado;
        return new ServicioAlquiler(repositorio, pagoFalso, notificadorFalso);
    }

    @Test
    @DisplayName("iniciarAlquiler: marca el recurso como alquilado")
    void iniciarAlquilerMarcaRecursoAlquilado() {
        crearServicio(true).iniciarAlquiler("U-1", "SC-01");
        assertFalse(scooter.estaDisponible());
    }

    @Test
    @DisplayName("iniciarAlquiler: falla si el recurso no existe")
    void iniciarAlquilerFallaSiNoExiste() {
        ServicioAlquiler servicio = crearServicio(true);
        assertThrows(IllegalArgumentException.class, () -> servicio.iniciarAlquiler("U-1", "XX-99"));
    }

    @Test
    @DisplayName("iniciarAlquiler: falla si el recurso no esta disponible")
    void iniciarAlquilerFallaSiNoDisponible() {
        ServicioAlquiler servicio = crearServicio(true);
        servicio.iniciarAlquiler("U-1", "SC-01");
        assertThrows(IllegalStateException.class, () -> servicio.iniciarAlquiler("U-2", "SC-01"));
    }

    @Test
    @DisplayName("finalizarAlquiler: devuelve el monto y libera el recurso")
    void finalizarAlquilerCobraYLibera() {
        ServicioAlquiler servicio = crearServicio(true);
        Alquiler alquiler = servicio.iniciarAlquiler("U-1", "SC-01");
        double monto = servicio.finalizarAlquiler(alquiler, 15);
        assertEquals(5.50, monto, 0.001);
        assertTrue(scooter.estaDisponible());
    }

    @Test
    @DisplayName("finalizarAlquiler: si el pago es rechazado, el recurso sigue alquilado")
    void finalizarAlquilerConPagoRechazado() {
        ServicioAlquiler servicio = crearServicio(false);
        Alquiler alquiler = servicio.iniciarAlquiler("U-1", "SC-01");
        assertThrows(IllegalStateException.class, () -> servicio.finalizarAlquiler(alquiler, 15));
        assertFalse(scooter.estaDisponible());
        assertFalse(alquiler.estaFinalizado());
    }

    @Test
    @DisplayName("finalizarAlquiler: notifica al usuario")
    void finalizarAlquilerNotificaAlUsuario() {
        ServicioAlquiler servicio = crearServicio(true);
        Alquiler alquiler = servicio.iniciarAlquiler("U-1", "SC-01");
        servicio.finalizarAlquiler(alquiler, 15);
        assertEquals(2, mensajesEnviados.size());
    }
}
