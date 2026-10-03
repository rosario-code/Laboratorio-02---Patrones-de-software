package movilidad.infraestructura;

import movilidad.servicio.IPasarelaPago;

public class PasarelaPagoSimulada implements IPasarelaPago {
    @Override
    public boolean cobrar(String usuarioId, double monto) {
        System.out.printf("  [PAGO] Cobrando S/. %.2f al usuario %s%n", monto, usuarioId);
        return true;
    }
}
