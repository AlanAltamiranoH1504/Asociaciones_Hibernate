package altamirano.hernandez.asociaciones_hibernate_2.repositories;

import altamirano.hernandez.asociaciones_hibernate_2.entities.Cliente;
import org.springframework.data.repository.CrudRepository;

public interface IClienteRepository extends CrudRepository<Cliente, Integer> {
}
