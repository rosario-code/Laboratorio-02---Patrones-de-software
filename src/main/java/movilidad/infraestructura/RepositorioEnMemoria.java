package movilidad.infraestructura;

import movilidad.dominio.IAlquilable;
import movilidad.servicio.IRepositorioRecursos;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class RepositorioEnMemoria implements IRepositorioRecursos {
    private final Map<String, IAlquilable> recursos = new HashMap<>();

    @Override
    public Optional<IAlquilable> buscarPorCodigo(String codigo) {
        return Optional.ofNullable(recursos.get(codigo));
    }

    @Override
    public void guardar(IAlquilable recurso) {
        recursos.put(recurso.getCodigo(), recurso);
    }
}
