package io.bootify.l9_minor_project_01.repos;

import io.bootify.l9_minor_project_01.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AddressRepository extends JpaRepository<Address, Long> {
}
