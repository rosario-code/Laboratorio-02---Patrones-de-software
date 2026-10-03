package movilidad.servicio;

import movilidad.dominio.Alquiler;
import movilidad.dominio.IAlquilable;

// Alto nivel: depende solo de abstracciones inyectadas por constructor (DIP)
public class ServicioAlquiler {
    private final IRepositorioRecursos repositorio;
    private final IPasarelaPago pasarelaPago;
    private final INotificador notificador;

    public ServicioAlquiler(IRepositorioRecursos repositorio, IPasarelaPago pasarelaPago,
                            INotificador notificador) {
        this.repositorio = repositorio;
        this.pasarelaPago = pasarelaPago;
        this.notificador = notificador;
    }

    public Alquiler iniciarAlquiler(String usuarioId, String codigoRecurso) {
        IAlquilable recurso = buscarRecursoDisponible(codigoRecurso);
        recurso.marcarAlquilado();
        repositorio.guardar(recurso);
        notificador.notificar(usuarioId, "Alquiler iniciado: " + recurso.getDescripcion());
        return new Alquiler(usuarioId, recurso);
    }

    public double finalizarAlquiler(Alquiler alquiler, int minutos) {
        IAlquilable recurso = alquiler.getRecurso();
        double monto = recurso.calcularTarifa(minutos);
        cobrar(alquiler.getUsuarioId(), monto);
        recurso.marcarDisponible();
        repositorio.guardar(recurso);
        alquiler.finalizar();
        notificador.notificar(alquiler.getUsuarioId(),
                String.format("Alquiler finalizado. Monto cobrado: S/. %.2f", monto));
        return monto;
    }

    private IAlquilable buscarRecursoDisponible(String codigo) {
        IAlquilable recurso = repositorio.buscarPorCodigo(codigo)
                .orElseThrow(() -> new IllegalArgumentException("No existe el recurso " + codigo));
        if (!recurso.estaDisponible()) {
            throw new IllegalStateException("El recurso " + codigo + " no esta disponible");
        }
        return recurso;
    }

    private void cobrar(String usuarioId, double monto) {
        if (!pasarelaPago.cobrar(usuarioId, monto)) {
            throw new IllegalStateException("El pago fue rechazado");
        }
    }
}
