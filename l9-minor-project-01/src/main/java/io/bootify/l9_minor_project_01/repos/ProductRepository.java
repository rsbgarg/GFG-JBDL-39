package io.bootify.l9_minor_project_01.repos;

import io.bootify.l9_minor_project_01.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.jdbc.core.JdbcTemplate;


public interface ProductRepository extends JpaRepository<Product, Long> {


}
