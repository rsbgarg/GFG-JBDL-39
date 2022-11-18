package io.bootify.l9_minor_project_01.domain;

import javax.persistence.*;
import java.util.Set;


@Entity
public class Warehouse {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @ManyToMany(mappedBy = "productWarehouses")
    private Set<Product> warehouseProducts;


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

}
