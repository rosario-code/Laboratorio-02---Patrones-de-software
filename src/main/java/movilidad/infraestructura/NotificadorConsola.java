package movilidad.infraestructura;

import movilidad.servicio.INotificador;

public class NotificadorConsola implements INotificador {
    @Override
    public void notificar(String usuarioId, String mensaje) {
        System.out.println("  [NOTIFICACION -> " + usuarioId + "] " + mensaje);
    }
}
