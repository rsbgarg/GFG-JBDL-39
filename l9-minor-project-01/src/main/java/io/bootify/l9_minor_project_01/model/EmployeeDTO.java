package io.bootify.l9_minor_project_01.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class EmployeeDTO {

    private Long id;

    @NotNull
    @Size(max = 255)
    private String name;

    @NotNull
    @Size(max = 255)
    private String email;

    private Double salary;

    private Long address;

    private Long store;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(final Double salary) {
        this.salary = salary;
    }

    public Long getAddress() {
        return address;
    }

    public void setAddress(final Long address) {
        this.address = address;
    }

    public Long getStore() {
        return store;
    }

    public void setStore(final Long store) {
        this.store = store;
    }

}
