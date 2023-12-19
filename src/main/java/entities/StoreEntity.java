package entities;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "store", schema = "sakila", catalog = "")
public class StoreEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "store_id", nullable = false)
    private byte storeId;
    @Basic
    @Column(name = "manager_staff_id", nullable = false)
    private byte managerStaffId;
    @Basic
    @Column(name = "address_id", nullable = false)
    private short addressId;
    @Basic
    @Column(name = "last_update", nullable = false)
    private Timestamp lastUpdate;
    @OneToMany(mappedBy = "storeByStoreId")
    private Collection<InventoryEntity> inventoriesByStoreId;
    @OneToMany(mappedBy = "storeByStoreId")
    private Collection<CustomerEntity> customersByStoreId;
    @OneToMany(mappedBy = "storeByStoreId")
    private Collection<StaffEntity> staffByStoreId;

    public byte getStoreId() {
        return storeId;
    }

    public void setStoreId(byte storeId) {
        this.storeId = storeId;
    }

    public byte getManagerStaffId() {
        return managerStaffId;
    }

    public void setManagerStaffId(byte managerStaffId) {
        this.managerStaffId = managerStaffId;
    }

    public short getAddressId() {
        return addressId;
    }

    public void setAddressId(short addressId) {
        this.addressId = addressId;
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
        StoreEntity that = (StoreEntity) o;
        return storeId == that.storeId && managerStaffId == that.managerStaffId && addressId == that.addressId && Objects.equals(lastUpdate, that.lastUpdate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(storeId, managerStaffId, addressId, lastUpdate);
    }

    public Collection<InventoryEntity> getInventoriesByStoreId() {
        return inventoriesByStoreId;
    }

    public void setInventoriesByStoreId(Collection<InventoryEntity> inventoriesByStoreId) {
        this.inventoriesByStoreId = inventoriesByStoreId;
    }

    public Collection<CustomerEntity> getCustomersByStoreId() {
        return customersByStoreId;
    }

    public void setCustomersByStoreId(Collection<CustomerEntity> customersByStoreId) {
        this.customersByStoreId = customersByStoreId;
    }

    public Collection<StaffEntity> getStaffByStoreId() {
        return staffByStoreId;
    }

    public void setStaffByStoreId(Collection<StaffEntity> staffByStoreId) {
        this.staffByStoreId = staffByStoreId;
    }
}
