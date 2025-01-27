package altamirano.hernandez.asociaciones_hibernate_2.repositories;

import altamirano.hernandez.asociaciones_hibernate_2.entities.Factura;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface IFacturaRepository extends CrudRepository<Factura, Integer> {

    @Query("SELECT f FROM Factura f WHERE f.descripcion =:descripcion")
    Factura facturaVeterinario(@Param("descripcion") String descripcion);
}
