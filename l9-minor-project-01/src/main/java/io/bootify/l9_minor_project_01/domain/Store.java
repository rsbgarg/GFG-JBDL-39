package io.bootify.l9_minor_project_01.domain;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


@Entity
public class Store {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @OneToMany(mappedBy = "store")
    private Set<Employee> storeEmployees;

    @ManyToMany(mappedBy = "storeProductStores")
    private Set<Product> storeProductProducts;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(final Address address) {
        this.address = address;
    }

    public Set<Employee> getStoreEmployees() {
        return storeEmployees;
    }

    public void setStoreEmployees(final Set<Employee> storeEmployees) {
        this.storeEmployees = storeEmployees;
    }

    public Set<Product> getStoreProductProducts() {
        return storeProductProducts;
    }

    public void setStoreProductProducts(final Set<Product> storeProductProducts) {
        this.storeProductProducts = storeProductProducts;
    }

}
