package altamirano.hernandez.asociaciones_hibernate_2.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    // Relacion OneToMany - (Un cliente va a tener muchas direcciones) - Mapeada por al atributo cliente en la clase Direccion
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Direccion> direcciones = new ArrayList<>();

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

    //toString
    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                '}';
    }
}
