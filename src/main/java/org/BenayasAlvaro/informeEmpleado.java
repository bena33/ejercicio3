package org.BenayasAlvaro;


import entities.StaffEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

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
    }
}
