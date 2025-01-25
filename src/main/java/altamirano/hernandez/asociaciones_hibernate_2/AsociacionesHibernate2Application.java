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
        oneToManyNormal();
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
        iClienteRepository.save(cliente3);

        Direccion direccion1 = new Direccion("Cerrada de Palmas", "15");
        Direccion direccion2 = new Direccion("Bugambilias", "15");

        direccion1.setCliente(cliente3);
        direccion2.setCliente(cliente3);
        iDireccionRepository.save(direccion1);
        iDireccionRepository.save(direccion2);
        System.out.println("Cliente y Direcciones Agregadas a la DB");

    }
}
