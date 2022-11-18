package io.bootify.l9_minor_project_01.model;

import io.bootify.l9_minor_project_01.domain.Address;

public class StoreDTO {


    private Long id;

    private Address address;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
