package movilidad.servicio;

import movilidad.dominio.IAlquilable;
import java.util.Optional;

public interface IRepositorioRecursos {
    Optional<IAlquilable> buscarPorCodigo(String codigo);
    void guardar(IAlquilable recurso);
}
