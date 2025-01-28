package altamirano.hernandez.asociaciones_hibernate_2.repositories;

import altamirano.hernandez.asociaciones_hibernate_2.entities.Estudiante;
import org.springframework.data.repository.CrudRepository;

public interface IEstudianteRepository extends CrudRepository<Estudiante, Integer> {
}
