package altamirano.hernandez.asociaciones_hibernate_2.repositories;

import altamirano.hernandez.asociaciones_hibernate_2.entities.DetalleCliente;
import org.springframework.data.repository.CrudRepository;

public interface IDatelleClienteRepository extends CrudRepository<DetalleCliente, Integer> {
}
