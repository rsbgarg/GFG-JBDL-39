package io.bootify.l9_minor_project_01.repos;

import io.bootify.l9_minor_project_01.domain.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;


public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {
}
