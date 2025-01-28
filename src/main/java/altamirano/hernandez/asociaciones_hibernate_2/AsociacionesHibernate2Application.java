package altamirano.hernandez.asociaciones_hibernate_2;

import altamirano.hernandez.asociaciones_hibernate_2.entities.*;
import altamirano.hernandez.asociaciones_hibernate_2.repositories.*;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
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
    @Autowired
    private IDatelleClienteRepository iDatelleClienteRepository;
    @Autowired
    private IEstudianteRepository iEstudianteRepository;
    @Autowired
    private ICursoRepository iCursoRepository;

    public static void main(String[] args) {
        SpringApplication.run(AsociacionesHibernate2Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        eliminarCursos();
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

    @Transactional(readOnly = false)
    public void oneToOneClienteNuevo(){
        Cliente cliente = new Cliente("Aichah", "Rangel Marquez");
        DetalleCliente datalleClienteNuevo = new DetalleCliente(false, 500);
        datalleClienteNuevo.setCliente(cliente);

        iClienteRepository.save(cliente);
        iDatelleClienteRepository.save(datalleClienteNuevo);
        System.out.println("Cliente nuevo agregado y con detalles agregados a la DB");
    }

    @Transactional(readOnly = false)
    public void oneToOneClienteExistente(){
        Cliente clienteVanessa = iClienteRepository.findById(13).orElse(null);

        if (clienteVanessa != null){
            DetalleCliente detalleCliente = new DetalleCliente(true, 1500);
            detalleCliente.setCliente(clienteVanessa);

            iClienteRepository.save(clienteVanessa);
            iDatelleClienteRepository.save(detalleCliente);
            System.out.println("A cliente: " + clienteVanessa.getNombre() + " le fueron agregados detalles");
        }
    }

    @Transactional(readOnly = false)
    public void oneToOneBidireccionalClienteNuevo(){
        Cliente clienteNuevo = new Cliente("Yvonne", "Rubio");
        DetalleCliente datelleYvonne = new DetalleCliente(true, 750);

        clienteNuevo.setDetalleCliente(datelleYvonne);
        datelleYvonne.setCliente(clienteNuevo);
        iClienteRepository.save(clienteNuevo);
        System.out.println("Cliente: " + clienteNuevo.getNombre() + " agregado con nuevos detalles");
    }

    @Transactional(readOnly = false)
    public void ManyToManyUni(){
        Estudiante estudiante = new Estudiante("Vanessa", "Adriana");
        Curso java = new Curso("Java", "Andres");
        Curso Js = new Curso("JS", "Juan Pablo");

        estudiante.getCursos().add(java);
        estudiante.getCursos().add(Js);
        iEstudianteRepository.save(estudiante);
        System.out.println("Estudiante agregado y con curso");
    }

    @Transactional(readOnly = false)
    public void ManyToManyBiEstudianteExistente(){
        Estudiante estudianteAlan = iEstudianteRepository.findById(7).orElse(null);
        if (estudianteAlan != null){
            Curso springBoot = new Curso("Spring Boot", "Andres");
            estudianteAlan.getCursos().add(springBoot);
            iEstudianteRepository.save(estudianteAlan);
            System.out.println("Curso Spring agregado");
        }
    }

    @Transactional(readOnly = false)
    public void eliminarCursos(){
        Estudiante vanessa = iEstudianteRepository.findById(7).orElse(null);
        if (vanessa != null){
            Curso cursoEliminar = vanessa.getCursos().stream()
                    .filter(curso -> curso.getId() == 11)
                    .findFirst()
                    .orElse(null);

            if (cursoEliminar != null){
                vanessa.getCursos().remove(cursoEliminar);
                iEstudianteRepository.save(vanessa);
                System.out.println("Curs de JS eliminado para vanessa");
            }
        }
    }
}
