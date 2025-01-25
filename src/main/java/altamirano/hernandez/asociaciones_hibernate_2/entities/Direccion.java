package altamirano.hernandez.asociaciones_hibernate_2.entities;

import jakarta.persistence.*;

import java.lang.ref.Cleaner;

@Entity
@Table(name = "direcciones")
public class Direccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String calle;
    private String numero;
    // Relacion ManyToOne (muchas direcciones tienen un cliente)
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

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
    public Cliente getCliente(){
        return this.cliente;
    }
    public void setCliente(Cliente cliente){
        this.cliente = cliente;
    }
}
