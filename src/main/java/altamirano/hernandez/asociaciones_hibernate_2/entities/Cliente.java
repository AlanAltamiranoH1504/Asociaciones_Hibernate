package altamirano.hernandez.asociaciones_hibernate_2.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "clientes")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nombre;
    private String apellidos;
    @Column(name = "creat_at")
    private LocalDateTime creatAt;
    @Column(name = "update_at")
    private LocalDateTime updateAt;

    //Relacion OneToMany - Bidireccional (Un cliente tiene muchass facturas)
    @OneToMany(mappedBy = "cliente", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Factura> facturas = new ArrayList<>();

    // Relacion OneToMany - (Un cliente va a tener muchas direcciones) - Undireccional
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "cliente_id")
    private List<Direccion> direcciones = new ArrayList<>();

    //Constuctores
    public Cliente(){}
    public Cliente(String nombre, String apellidos){
        this.nombre = nombre;
        this.apellidos = apellidos;
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
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getApellidos() {
        return apellidos;
    }
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
    public List<Direccion> getDirecciones() {
        return direcciones;
    }
    public void setDirecciones(List<Direccion> direcciones) {
        this.direcciones = direcciones;
    }
    public List<Factura> getFacturas() {
        return facturas;
    }
    public void setFacturas(List<Factura> facturas) {
        this.facturas = facturas;
    }

    //toString
    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return id == cliente.id && Objects.equals(nombre, cliente.nombre) && Objects.equals(apellidos, cliente.apellidos) && Objects.equals(creatAt, cliente.creatAt) && Objects.equals(updateAt, cliente.updateAt) && Objects.equals(direcciones, cliente.direcciones);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, apellidos, creatAt, updateAt, direcciones);
    }
}
