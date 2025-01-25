package altamirano.hernandez.asociaciones_hibernate_2.entities;

import jakarta.persistence.*;
import jdk.jfr.consumer.RecordedThread;
import org.springframework.beans.propertyeditors.ClassEditor;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Entity
@Table(name = "facturas")
public class Factura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String descripcion;
    private double total;
    @Column(name = "creat_at")
    private LocalDateTime creatAt;
    @Column(name = "update_at")
    private LocalDateTime updateAt;
    //Relacion ManyToOne - (Muchas facturas tienen un cliente)
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    //Constructores
    public Factura(){}
    public Factura(String descripcion, double total) {
        this.descripcion = descripcion;
        this.total = total;
    }

    //Eventos del ciclo de vida
    @PostPersist
    public void postPersist(){
        this.creatAt = LocalDateTime.now();
    }
    @PostUpdate
    public void postUpdate(){
        this.updateAt = LocalDateTime.now();
    }

    //Get y Set
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public double getTotal() {
        return total;
    }
    public void setTotal(double total) {
        this.total = total;
    }
    public Cliente getCliente(){
        return this.cliente;
    }
    public void setCliente(Cliente cliente){
        this.cliente = cliente;
    }

    //ToString
    @Override
    public String toString() {
        return "Factura{" +
                "id=" + id +
                ", descripcion='" + descripcion + '\'' +
                ", total=" + total +
                ", cliente=" + cliente +
                '}';
    }
}
