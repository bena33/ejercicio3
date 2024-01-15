package org.BenayasAlvaro;

import entities.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.Scanner;

public class alquilerPelicula {
    private static final String PERSISTENCE_UNIT_NAME = "persistencia"; // Reemplaza con el nombre de tu unidad de persistencia
    public static void main(String[] args){
            EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
            EntityManager em = emf.createEntityManager();
            Scanner scanner = new Scanner(System.in);
            try {
                System.out.println("Ingrese el ID del empleado que realiza el alquiler: ");
                int empleadoId = scanner.nextInt();
                StaffEntity empleado = em.find(StaffEntity.class, empleadoId);

                if (empleado == null) {
                    System.out.println("Error: Empleado no encontrado.");
                    return;
                }

                StoreEntity tienda = empleado.getStoreByStoreId();

                System.out.println("Ingrese el ID de la película que desea alquilar: ");
                int peliculaId = scanner.nextInt();
                FilmEntity pelicula = em.find(FilmEntity.class, peliculaId);

                if (pelicula == null) {
                    System.out.println("Error: Película no encontrada en el inventario de la tienda.");
                    return;
                }

                // Aquí puedes agregar lógica adicional para verificar el stock en la tienda.

                System.out.println("Ingrese el ID del cliente que realiza el alquiler: ");
                int clienteId = scanner.nextInt();
                CustomerEntity cliente = em.find(CustomerEntity.class, clienteId);

                if (cliente == null) {
                    System.out.println("Error: Cliente no encontrado.");
                    return;
                }

                // Si todas las comprobaciones han pasado, crea un nuevo alquiler
                RentalEntity alquiler = new RentalEntity();
                alquiler.setStaffByStaffId(empleado);
                //alquiler.setPeliculaByPeliculaId(pelicula);
                alquiler.setCustomerByCustomerId(cliente);

                // Aquí puedes configurar más detalles del alquiler si es necesario.

                // Persistir el nuevo alquiler
                em.getTransaction().begin();
                //em.persist(alquiler);
                em.getTransaction().commit();

                System.out.println("Alquiler realizado con éxito.");
            } finally {
                em.close();
                emf.close();
                scanner.close();
            }
    }
}
