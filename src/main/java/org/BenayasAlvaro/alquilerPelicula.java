package org.BenayasAlvaro;

import entities.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
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
                }else{
                    StoreEntity tienda = empleado.getStoreByStoreId();

                    System.out.println("Ingrese el ID de la película que desea alquilar: ");
                    int peliculaId = scanner.nextInt();
                    FilmEntity pelicula = em.find(FilmEntity.class, peliculaId);

                    if (pelicula == null) {
                        System.out.println("Error: Película no encontrada en el inventario de la tienda.");
                    }else{
                        System.out.println("Ingrese el ID del cliente que realiza el alquiler: ");
                        int clienteId = scanner.nextInt();
                        CustomerEntity cliente = em.find(CustomerEntity.class, clienteId);

                        if (cliente == null) {
                            System.out.println("Error: Cliente no encontrado.");
                        }else{
                            RentalEntity alquiler = new RentalEntity();
                            alquiler.setStaffByStaffId(empleado);

                            List<InventoryEntity> inventario = new ArrayList<>(pelicula.getInventoriesByFilmId());
                            if (!inventario.isEmpty()) {
                                // Verifica si hay existencias disponibles en el inventario
                                boolean existenciasDisponibles = false;

                                for (InventoryEntity inventory : inventario) {
                                    if (inventory.getFilmByFilmId().equals(pelicula) && inventory.getRentalsByInventoryId().isEmpty()) {
                                        existenciasDisponibles = true;
                                        break;
                                    }
                                }

                                if (existenciasDisponibles) {
                                    System.out.printf("inventario: %d \n" ,inventario.get(0).getInventoryId());
                                    alquiler.setInventoryId(inventario.get(0).getInventoryId());
                                    alquiler.setRentalDate(Timestamp.from(Instant.now()));

                                    alquiler.setCustomerByCustomerId(cliente);

                                    em.getTransaction().begin();
                                    em.persist(alquiler);
                                    em.flush();
                                    em.getTransaction().commit();

                                    System.out.println("Alquiler realizado con éxito.");
                                } else {
                                    System.out.println("Error: No hay existencias disponibles en el inventario para la película seleccionada.");
                                }
                            } else {
                                System.out.println("Error: No hay existencias disponibles en el inventario para la película seleccionada.");
                            }
                        }
                    }
                }
            } finally {
                em.close();
                emf.close();
                scanner.close();
            }
    }
}
