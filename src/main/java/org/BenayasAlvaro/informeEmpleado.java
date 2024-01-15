package org.BenayasAlvaro;


import entities.AddressEntity;
import entities.RentalEntity;
import entities.StaffEntity;
import entities.StoreEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;
import java.util.Scanner;

public class informeEmpleado {
    private static final String PERSISTENCE_UNIT_NAME = "persistencia"; // Reemplaza con el nombre de tu unidad de persistencia

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = emf.createEntityManager();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Ingrese el ID del cliente (0 para salir): ");
            int empleadoId = scanner.nextInt();

            if (empleadoId == 0) {
                break;
            }

            StaffEntity empleado = em.find(StaffEntity.class, empleadoId);

            if (empleado != null) {
                mostrarInformeEmpleado(empleado);
            } else {
                System.out.println("Cliente no encontrado.");
            }
        }

        em.close();
        emf.close();
        scanner.close();
    }

    private static void mostrarInformeEmpleado(StaffEntity empleado) {
        System.out.println("Datos del empleado:");
        System.out.println("ID: " + empleado.getStaffId());
        System.out.println("Nombre: " + empleado.getFirstName() +" "+ empleado.getLastName());
        //System.out.println("Cargo: " + empleado.getCargo());

        AddressEntity direccionEmpleado = empleado.getAddressByAddressId();
        System.out.println("Dirección del empleado:");
        System.out.println("Calle: " + direccionEmpleado.getAddress());
        System.out.println("Ciudad: " + direccionEmpleado.getCityByCityId().getCity());
        System.out.println("Pais: " + direccionEmpleado.getCityByCityId().getCountryByCountryId().getCountry());

        StoreEntity tiendaAsociada = empleado.getStoreByStoreId();
        System.out.println("Tienda asociada:");
        System.out.println("Nombre: " + tiendaAsociada.getStoreId());
        System.out.println("Dirección de la tienda:");
        System.out.println("Calle: " + tiendaAsociada.getAddressByAddressId().getAddress());
        System.out.println("Ciudad: " + tiendaAsociada.getAddressByAddressId().getCityByCityId().getCity());
        System.out.println("Pais: " + tiendaAsociada.getAddressByAddressId().getCityByCityId().getCountryByCountryId().getCountry());

        List<RentalEntity> alquileres = empleado.getRentalsByStaffId();
        System.out.println("Alquileres realizados:");
        for (RentalEntity alquiler : alquileres) {
            System.out.println("Película: " + alquiler.getInventoryByInventoryId().getFilmByFilmId().getTitle());
            // Puedes imprimir más información sobre el alquiler si lo necesitas
        }

        System.out.println("-------------------------------------");
    }
    }

