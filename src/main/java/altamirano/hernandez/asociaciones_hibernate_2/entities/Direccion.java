package altamirano.hernandez.asociaciones_hibernate_2.entities;

import jakarta.persistence.*;

import java.lang.ref.Cleaner;
import java.util.Objects;

@Entity
@Table(name = "direcciones")
public class Direccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String calle;
    private String numero;

    //Constructores
    public Direccion() {}
    public Direccion(String calle, String numero){
        this.calle = calle;
        this.numero = numero;
    }

    //Get y Set
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getCalle() {
        return calle;
    }
    public void setCalle(String calle) {
        this.calle = calle;
    }
    public String getNumero() {
        return numero;
    }
    public void setNumero(String numero) {
        this.numero = numero;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Direccion direccion = (Direccion) o;
        return id == direccion.id && Objects.equals(calle, direccion.calle) && Objects.equals(numero, direccion.numero);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, calle, numero);
    }
}
