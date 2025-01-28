package altamirano.hernandez.asociaciones_hibernate_2.entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "datelles_clientes")
public class DetalleCliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private boolean premium;
    private int puntos;
    //Relacion OneToOne Unidireccional → Un DetalleCliente tiene un cliente
    @OneToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    //Constructores
    public DetalleCliente(){}
    public DetalleCliente(boolean premium, int puntos){
        this.premium = premium;
        this.puntos = puntos;
    }

    //Get y Set
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public boolean isPremium() {
        return premium;
    }
    public void setPremium(boolean premium) {
        this.premium = premium;
    }
    public int getPuntos() {
        return puntos;
    }
    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }
    public Cliente getCliente() {
        return cliente;
    }
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    //Equals y hashcode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DetalleCliente that = (DetalleCliente) o;
        return id == that.id && premium == that.premium && puntos == that.puntos && Objects.equals(cliente, that.cliente);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, premium, puntos, cliente);
    }
}
