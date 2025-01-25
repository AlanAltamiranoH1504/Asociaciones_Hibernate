package altamirano.hernandez.asociaciones_hibernate_2.repositories;

import altamirano.hernandez.asociaciones_hibernate_2.entities.Factura;
import org.springframework.data.repository.CrudRepository;

public interface IFacturaRepository extends CrudRepository<Factura, Integer> {
}
