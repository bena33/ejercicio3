package entities;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "rental", schema = "sakila")
public class RentalEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "rental_id", nullable = false)
    private int rentalId;
    @Basic
    @Column(name = "rental_date", nullable = false)
    private Timestamp rentalDate;
    @Basic
    @Column(name = "inventory_id", nullable = false )
    private short inventoryId;
    @Basic
    @Column(name = "customer_id", nullable = false , insertable = false, updatable = false)
    private short customerId;
    @Basic
    @Column(name = "return_date", nullable = true)
    private Timestamp returnDate;
    @Basic
    @Column(name = "staff_id", nullable = false , insertable = false, updatable = false)
    private byte staffId;
    @Basic
    @Column(name = "last_update", nullable = false, updatable = false, insertable = false)
    private Timestamp lastUpdate;
    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id", nullable = false)
    private CustomerEntity customerByCustomerId;
    @OneToMany(mappedBy = "rentalByRentalId")
    private List<PaymentEntity> paymentsByRentalId;
    @ManyToOne
    @JoinColumn(name = "inventory_id", referencedColumnName = "inventory_id", nullable = false, updatable = false, insertable = false)
    private InventoryEntity inventoryByInventoryId;
    @ManyToOne
    @JoinColumn(name = "staff_id", referencedColumnName = "staff_id", nullable = false)
    private StaffEntity staffByStaffId;

    public int getRentalId() {
        return rentalId;
    }

    public void setRentalId(int rentalId) {
        this.rentalId = rentalId;
    }

    public Timestamp getRentalDate() {
        return rentalDate;
    }

    public void setRentalDate(Timestamp rentalDate) {
        this.rentalDate = rentalDate;
    }

    public short getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(short inventoryId) {
        this.inventoryId = inventoryId;
    }

    public short getCustomerId() {
        return customerId;
    }

    public void setCustomerId(short customerId) {
        this.customerId = customerId;
    }

    public Timestamp getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Timestamp returnDate) {
        this.returnDate = returnDate;
    }

    public byte getStaffId() {
        return staffId;
    }

    public void setStaffId(byte staffId) {
        this.staffId = staffId;
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
        RentalEntity that = (RentalEntity) o;
        return rentalId == that.rentalId && customerId == that.customerId && staffId == that.staffId && Objects.equals(rentalDate, that.rentalDate) && Objects.equals(inventoryId, that.inventoryId) && Objects.equals(returnDate, that.returnDate) && Objects.equals(lastUpdate, that.lastUpdate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rentalId, rentalDate, inventoryId, customerId, returnDate, staffId, lastUpdate);
    }

    public CustomerEntity getCustomerByCustomerId() {
        return customerByCustomerId;
    }

    public void setCustomerByCustomerId(CustomerEntity customerByCustomerId) {
        this.customerByCustomerId = customerByCustomerId;
    }

    public List<PaymentEntity> getPaymentsByRentalId() {
        return paymentsByRentalId;
    }

    public void setPaymentsByRentalId(List<PaymentEntity> paymentsByRentalId) {
        this.paymentsByRentalId = paymentsByRentalId;
    }

    public InventoryEntity getInventoryByInventoryId() {
        return inventoryByInventoryId;
    }

    public void setInventoryByInventoryId(InventoryEntity inventoryByInventoryId) {
        this.inventoryByInventoryId = inventoryByInventoryId;
    }

    public StaffEntity getStaffByStaffId() {
        return staffByStaffId;
    }

    public void setStaffByStaffId(StaffEntity staffByStaffId) {
        this.staffByStaffId = staffByStaffId;
    }
}
