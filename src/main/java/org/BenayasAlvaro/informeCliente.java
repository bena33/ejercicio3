package org.BenayasAlvaro;
import entities.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;
import java.util.Scanner;
public class informeCliente {
            private static final String PERSISTENCE_UNIT_NAME = "persistencia"; // Reemplaza con el nombre de tu unidad de persistencia

            public static void main(String[] args) {
                EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
                EntityManager em = emf.createEntityManager();
                Scanner scanner = new Scanner(System.in);

                while (true) {
                    System.out.println("Ingrese el ID del cliente (0 para salir): ");
                    int clientId = scanner.nextInt();

                    if (clientId == 0) {
                        break;
                    }

                    CustomerEntity cliente = em.find(CustomerEntity.class, clientId);

                    if (cliente != null) {
                        mostrarInformeCliente(cliente);
                    } else {
                        System.out.println("Cliente no encontrado.");
                    }
                }

                em.close();
                emf.close();
                scanner.close();
            }

            private static void mostrarInformeCliente(CustomerEntity cliente) {
                System.out.println("Datos del cliente:");
                System.out.println("ID: " + cliente.getCustomerId());
                System.out.println("Nombre: " + cliente.getFirstName() +" "+ cliente.getLastName());
                System.out.println("Email: " + cliente.getEmail());

                AddressEntity direccionCliente = cliente.getAddressByAddressId();
                System.out.println("Dirección del cliente:");
                System.out.println("Calle: " + direccionCliente.getAddress());
                System.out.println("Ciudad: " + direccionCliente.getCityByCityId().getCity());
                System.out.println("Pais: " + direccionCliente.getCityByCityId().getCountryByCountryId().getCountry());

                StoreEntity tiendaAsociada = cliente.getStoreByStoreId();
                System.out.println("Tienda asociada:");
                System.out.println("Nombre: " + tiendaAsociada.getStoreId());
                System.out.println("Dirección de la tienda:");
                System.out.println("Calle: " + tiendaAsociada.getAddressByAddressId().getAddress());
                System.out.println("Ciudad: " + tiendaAsociada.getAddressByAddressId().getCityByCityId().getCity());
                System.out.println("Pais: " + tiendaAsociada.getAddressByAddressId().getCityByCityId().getCountryByCountryId().getCountry());

                List<RentalEntity> alquileres = cliente.getRentalsByCustomerId();
                System.out.println("Alquileres realizados:");
                for (RentalEntity alquiler : alquileres) {
                    System.out.println("Película: " + alquiler.getInventoryByInventoryId().getFilmByFilmId().getTitle());
                    // Puedes imprimir más información sobre el alquiler si lo necesitas
                }

                List<PaymentEntity> pagos = cliente.getPaymentsByCustomerId();
                System.out.println("Pagos realizados:");
                for (PaymentEntity pago : pagos) {
                    System.out.println("Cantidad: " + pago.getAmount());
                    // Puedes imprimir más información sobre el pago si lo necesitas
                }

                System.out.println("-------------------------------------");
            }
        }


