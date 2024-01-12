package org.BenayasAlvaro;

import entities.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;


import java.util.List;
import java.util.Scanner;

public class informePelicula {
    private static final String PERSISTENCE_UNIT_NAME = "persistencia"; // Reemplaza con el nombre de tu unidad de persistencia

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = emf.createEntityManager();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Ingrese el ID del cliente (0 para salir): ");
            int filmId = scanner.nextInt();

            if (filmId == 0) {
                break;
            }

            FilmEntity Pelicula = em.find(FilmEntity.class, filmId);

            if (Pelicula != null) {
                mostrarInformePelicula(Pelicula);
            } else {
                System.out.println("Cliente no encontrado.");
            }
        }

        em.close();
        emf.close();
        scanner.close();
    }

    private static void mostrarInformePelicula(FilmEntity Pelicula) {

            System.out.println("Datos de la película:");
            System.out.println("Título: " + Pelicula.getTitle());
            System.out.println("Descripción: " + Pelicula.getDescription());
            System.out.println("Duración: " + Pelicula.getLength() + " minutos");

            List<CategoryEntity> categorias = Pelicula.getCategoriesByCategoriesId();
            for(CategoryEntity categoria : categorias) {
                System.out.println("Categoría: " + categoria.getName());
            }

            if(Pelicula.getLanguageByOriginalLanguageId() != null){
                LanguageEntity idiomaOriginal = Pelicula.getLanguageByOriginalLanguageId();
                if(idiomaOriginal.getName()== null){
                    System.out.println("Idioma Original: null");
                }else{System.out.println("Idioma Original: " + idiomaOriginal.getName());}
            }else{
                System.out.println("Idioma Original: null");
            }




            LanguageEntity idioma = Pelicula.getLanguageByLanguageId();
        if(idioma.getName()== null){
            System.out.println("Idioma : null");
        }else{System.out.println("Idioma: " + idioma.getName());}



            List<ActorEntity> actores = Pelicula.getActorsByActorsId();
            System.out.println("Actores que participan en la película:");
            for (ActorEntity actor : actores) {
                System.out.println("Nombre del Actor: " + actor.getFirstName()+ " " +actor.getLastName());
            }

            List<InventoryEntity> inventarios = Pelicula.getInventoriesByFilmId();
            System.out.println("Copias disponibles en el inventario:");
            for (InventoryEntity inventario : inventarios) {
                StoreEntity tienda = inventario.getStoreByStoreId();
                AddressEntity direccion = tienda.getAddressByAddressId();

                System.out.println("Tienda: " + tienda.getStoreId());
                System.out.println("Dirección de la tienda:");
                System.out.println("Calle: " + direccion.getAddress());
                System.out.println("Ciudad: " + direccion.getCityByCityId().getCity());
                System.out.println("Pais: " + direccion.getCityByCityId().getCountryByCountryId().getCountry());
            }

            System.out.println("-------------------------------------");

    }
}
