package entities;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "inventory", schema = "sakila")
public class InventoryEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "inventory_id", nullable = false)
    private short inventoryId;
    @Basic
    @Column(name = "film_id", nullable = false  , insertable = false, updatable = false)
    private short filmId;
    @Basic
    @Column(name = "store_id", nullable = false  , insertable = false, updatable = false)
    private byte storeId;
    @Basic
    @Column(name = "last_update", nullable = false)
    private Timestamp lastUpdate;
    @ManyToOne
    @JoinColumn(name = "film_id", referencedColumnName = "film_id", nullable = false)
    private FilmEntity filmByFilmId;
    @ManyToOne
    @JoinColumn(name = "store_id", referencedColumnName = "store_id", nullable = false)
    private StoreEntity storeByStoreId;
    @OneToMany(mappedBy = "inventoryByInventoryId")
    private List<RentalEntity> rentalsByInventoryId;

    public short getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(short inventoryId) {
        this.inventoryId = inventoryId;
    }

    public short getFilmId() {
        return filmId;
    }

    public void setFilmId(short filmId) {
        this.filmId = filmId;
    }

    public byte getStoreId() {
        return storeId;
    }

    public void setStoreId(byte storeId) {
        this.storeId = storeId;
    }

    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InventoryEntity that = (InventoryEntity) o;
        return filmId == that.filmId && storeId == that.storeId && Objects.equals(inventoryId, that.inventoryId) && Objects.equals(lastUpdate, that.lastUpdate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(inventoryId, filmId, storeId, lastUpdate);
    }

    public FilmEntity getFilmByFilmId() {
        return filmByFilmId;
    }

    public void setFilmByFilmId(FilmEntity filmByFilmId) {
        this.filmByFilmId = filmByFilmId;
    }

    public StoreEntity getStoreByStoreId() {
        return storeByStoreId;
    }

    public void setStoreByStoreId(StoreEntity storeByStoreId) {
        this.storeByStoreId = storeByStoreId;
    }

    public List<RentalEntity> getRentalsByInventoryId() {
        return rentalsByInventoryId;
    }

    public void setRentalsByInventoryId(List<RentalEntity> rentalsByInventoryId) {
        this.rentalsByInventoryId = rentalsByInventoryId;
    }

}
