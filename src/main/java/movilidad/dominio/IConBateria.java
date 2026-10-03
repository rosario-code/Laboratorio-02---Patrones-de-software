package movilidad.dominio;

// ISP: solo los vehiculos electricos dependen de este contrato (la estacion no)
public interface IConBateria {
    int getNivelBateria();
    void recibirCarga(int porcentaje);
}
