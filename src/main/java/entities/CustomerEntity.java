package entities;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "customer", schema = "sakila")
public class CustomerEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "customer_id", nullable = false)
    private short customerId;
    @Basic
    @Column(name = "store_id", nullable = false)
    private byte storeId;
    @Basic
    @Column(name = "first_name", nullable = false, length = 45)
    private String firstName;
    @Basic
    @Column(name = "last_name", nullable = false, length = 45)
    private String lastName;
    @Basic
    @Column(name = "email", nullable = true, length = 50)
    private String email;
    @Basic
    @Column(name = "address_id", nullable = false)
    private short addressId;
    @Basic
    @Column(name = "active", nullable = false)
    private byte active;
    @Basic
    @Column(name = "create_date", nullable = false)
    private Timestamp createDate;
    @Basic
    @Column(name = "last_update", nullable = false)
    private Timestamp lastUpdate;
    @ManyToOne
    @JoinColumn(name = "store_id", referencedColumnName = "store_id", nullable = false)
    private StoreEntity storeByStoreId;
    @ManyToOne
    @JoinColumn(name = "address_id", referencedColumnName = "address_id", nullable = false)
    private AddressEntity addressByAddressId;
    @OneToMany(mappedBy = "customerByCustomerId")
    private Collection<PaymentEntity> paymentsByCustomerId;
    @OneToMany(mappedBy = "customerByCustomerId")
    private Collection<RentalEntity> rentalsByCustomerId;

    public short getCustomerId() {
        return customerId;
    }

    public void setCustomerId(short customerId) {
        this.customerId = customerId;
    }

    public byte getStoreId() {
        return storeId;
    }

    public void setStoreId(byte storeId) {
        this.storeId = storeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public short getAddressId() {
        return addressId;
    }

    public void setAddressId(short addressId) {
        this.addressId = addressId;
    }

    public byte getActive() {
        return active;
    }

    public void setActive(byte active) {
        this.active = active;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
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
        CustomerEntity that = (CustomerEntity) o;
        return customerId == that.customerId && storeId == that.storeId && addressId == that.addressId && active == that.active && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(email, that.email) && Objects.equals(createDate, that.createDate) && Objects.equals(lastUpdate, that.lastUpdate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, storeId, firstName, lastName, email, addressId, active, createDate, lastUpdate);
    }

    public StoreEntity getStoreByStoreId() {
        return storeByStoreId;
    }

    public void setStoreByStoreId(StoreEntity storeByStoreId) {
        this.storeByStoreId = storeByStoreId;
    }

    public AddressEntity getAddressByAddressId() {
        return addressByAddressId;
    }

    public void setAddressByAddressId(AddressEntity addressByAddressId) {
        this.addressByAddressId = addressByAddressId;
    }

    public Collection<PaymentEntity> getPaymentsByCustomerId() {
        return paymentsByCustomerId;
    }

    public void setPaymentsByCustomerId(Collection<PaymentEntity> paymentsByCustomerId) {
        this.paymentsByCustomerId = paymentsByCustomerId;
    }

    public Collection<RentalEntity> getRentalsByCustomerId() {
        return rentalsByCustomerId;
    }

    public void setRentalsByCustomerId(Collection<RentalEntity> rentalsByCustomerId) {
        this.rentalsByCustomerId = rentalsByCustomerId;
    }
}
