package movilidad;

import movilidad.dominio.*;
import movilidad.infraestructura.*;
import movilidad.servicio.*;

public class Main {
    public static void main(String[] args) {
        // Composicion de dependencias: aqui se decide que implementaciones usar
        IRepositorioRecursos repositorio = new RepositorioEnMemoria();
        Scooter scooter = new Scooter("SC-01", 80);
        BusAutonomo bus = new BusAutonomo("BUS-07", 30, 90);
        EstacionCarga estacion = new EstacionCarga("EC-03");
        repositorio.guardar(scooter);
        repositorio.guardar(bus);
        repositorio.guardar(estacion);

        ServicioAlquiler servicio = new ServicioAlquiler(
                repositorio, new PasarelaPagoSimulada(), new NotificadorConsola());

        // El mismo servicio trata a los tres recursos de forma polimorfica
        System.out.println("=== Alquiler de scooter ===");
        Alquiler a1 = servicio.iniciarAlquiler("U-100", "SC-01");
        servicio.finalizarAlquiler(a1, 15);

        System.out.println("\n=== Viaje en bus autonomo ===");
        Alquiler a2 = servicio.iniciarAlquiler("U-200", "BUS-07");
        servicio.finalizarAlquiler(a2, 40);

        System.out.println("\n=== Alquiler de estacion de carga ===");
        Alquiler a3 = servicio.iniciarAlquiler("U-100", "EC-03");
        estacion.cargar(scooter, 10);
        System.out.println("  Bateria del scooter despues de cargar: " + scooter.getNivelBateria() + "%");
        servicio.finalizarAlquiler(a3, 10);
    }
}
