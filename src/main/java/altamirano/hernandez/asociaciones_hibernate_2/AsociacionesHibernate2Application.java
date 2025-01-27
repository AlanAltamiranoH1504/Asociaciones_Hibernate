package altamirano.hernandez.asociaciones_hibernate_2;

import altamirano.hernandez.asociaciones_hibernate_2.entities.Cliente;
import altamirano.hernandez.asociaciones_hibernate_2.entities.Direccion;
import altamirano.hernandez.asociaciones_hibernate_2.entities.Factura;
import altamirano.hernandez.asociaciones_hibernate_2.repositories.IClienteRepository;
import altamirano.hernandez.asociaciones_hibernate_2.repositories.IDireccionRepository;
import altamirano.hernandez.asociaciones_hibernate_2.repositories.IFacturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
public class AsociacionesHibernate2Application implements CommandLineRunner {
    //Inyectamos repositorios
    @Autowired
    private IClienteRepository iClienteRepository;
    @Autowired
    private IFacturaRepository iFacturaRepository;
    @Autowired
    private IDireccionRepository iDireccionRepository;

    public static void main(String[] args) {
        SpringApplication.run(AsociacionesHibernate2Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        deleteOneToManyBidireccional();
    }

    public void manyToOne(){
        Cliente cliente1 = new Cliente("Alan", "Altamirano");
        iClienteRepository.save(cliente1);
        Factura facturaCliente1 = new Factura("Laptop Lennovo", 17000);
        facturaCliente1.setCliente(cliente1);
        iFacturaRepository.save(facturaCliente1);
        System.out.println("Cliente1 y FacturaCliente1 Agregadas a la DB");
    }

    public void manyToOneFindCliente(){
        Cliente clienteEncontrado = iClienteRepository.findById(2).orElse(null);
        if (clienteEncontrado != null){
            Factura factura1Cliente2 = new Factura("Pizza Elvis", 350);
            factura1Cliente2.setCliente(clienteEncontrado);
            iFacturaRepository.save(factura1Cliente2);
            System.out.println("Factura 1 agregada a cliente 2");
        }else{
            System.out.println("Cliente no encontrado");
        }
    }

    public void oneToManyNormal(){
        Cliente cliente3 = new Cliente("Raquel", "Hernandez Hernandez");
        Direccion direccion1 = new Direccion("Cerrada de Palmas", "15");
        Direccion direccion2 = new Direccion("Bugambilias", "15");

        cliente3.getDirecciones().add(direccion1);
        cliente3.getDirecciones().add(direccion2);
        iClienteRepository.save(cliente3);
        System.out.println("Cliente y Direcciones Agregadas a la DB");
    }

    public void oneToManyUniClienteExistente(){
        Cliente cliente1 = iClienteRepository.findById(1).orElse(null);
        if (cliente1 != null){
            Direccion direccion1 = new Direccion("Antonio Rodriguez", "17");
            cliente1.getDirecciones().add(direccion1);

            iClienteRepository.save(cliente1);
            System.out.println("Direccion agregada al cliente con id 1");
        }
    }

    @Transactional(readOnly = false)
    public void eliminarDireccionDeCliente(){
        Cliente cliente = iClienteRepository.findById(1).orElse(null);
        if (cliente != null){
            Direccion direccion1 = iDireccionRepository.findById(3).orElse(null);
            if (direccion1 != null){
                cliente.getDirecciones().remove(direccion1);
                iClienteRepository.save(cliente);
                System.out.println("Al cliente " + cliente.getNombre() + " se le ha elimiado la direccion con id 1");
            }else{
                System.out.println("Direccion no encontrado");
            }
        }else{
            System.out.println("Cliente no encontrado");
        }
    }

    @Transactional(readOnly = false)
    public void OneToManyBidireccional(){
        Cliente clienteNuevo = new Cliente("Vanessa", "Rivera Garcia");
        Factura factura1 = new Factura("Concierto", 2000);
        Factura factura2 = new Factura("Cine", 700);

        clienteNuevo.getFacturas().add(factura1);
        clienteNuevo.getFacturas().add(factura2);
        factura1.setCliente(clienteNuevo);
        factura2.setCliente(clienteNuevo);

        iClienteRepository.save(clienteNuevo);
        iFacturaRepository.save(factura1);
        iFacturaRepository.save(factura2);
        System.out.println("Cliente Agregado con Dos nuevas facturas");
    }

    @Transactional(readOnly = false)
    public void oneToManyBidireccionalFindCliente(){
        Cliente clienteVanessa = iClienteRepository.findById(13).orElse(null);
        if (clienteVanessa != null){
            Factura facturaVeterinario = new Factura("Veterinario de Bruno", 500);
            clienteVanessa.getFacturas().add(facturaVeterinario);
            facturaVeterinario.setCliente(clienteVanessa);

            iClienteRepository.save(clienteVanessa);
            iFacturaRepository.save(facturaVeterinario);
            System.out.println("Al cliente " + clienteVanessa.getNombre() + " se le agrego su factura por: " + facturaVeterinario.getDescripcion());
        }else{
            System.out.println("No hay un cliente con ese ID");
        }
    }

    @Transactional(readOnly = false)
    public void deleteOneToManyBidireccional(){
        Cliente clienteVanessa = iClienteRepository.findById(2).orElse(null);

        if (clienteVanessa != null){
            Factura AlexaEcho = iFacturaRepository.facturaVeterinario("Pizza Elvis");
            if (AlexaEcho != null){
                Factura facturaEliminar = clienteVanessa.getFacturas()
                        .stream()
                        .filter(factura -> factura.getId() == AlexaEcho.getId())
                        .findFirst()
                        .orElse(null);
                if (facturaEliminar != null){
                    clienteVanessa.getFacturas().remove(facturaEliminar);
                    iClienteRepository.save(clienteVanessa);
                    System.out.println("Factura eliminada");
                }
            }
        }else{
            System.out.println("No hay un cliente con ese ID");
        }
    }
}
