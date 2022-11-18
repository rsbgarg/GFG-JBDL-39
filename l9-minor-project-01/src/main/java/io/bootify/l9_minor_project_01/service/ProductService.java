package io.bootify.l9_minor_project_01.service;

import io.bootify.l9_minor_project_01.domain.Product;
import io.bootify.l9_minor_project_01.domain.Store;
import io.bootify.l9_minor_project_01.model.ProductDTO;
import io.bootify.l9_minor_project_01.repos.ProductRepository;
import io.bootify.l9_minor_project_01.repos.StoreRepository;

import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.h2.jdbc.JdbcSQLSyntaxErrorException;
import org.hibernate.exception.SQLGrammarException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Transactional
@Service
public class ProductService {
    private Logger logger = LoggerFactory.getLogger(ProductService.class);
    private final ProductRepository productRepository;
    private final StoreRepository storeRepository;

    public ProductService(final ProductRepository productRepository,
            final StoreRepository storeRepository) {
        this.productRepository = productRepository;
        this.storeRepository = storeRepository;
    }

    public List<ProductDTO> findAll() {
        return productRepository.findAll(Sort.by("id"))
                .stream()
                .map(product -> mapToDTO(product, new ProductDTO()))
                .collect(Collectors.toList());
    }

    public ProductDTO get(final Long id) {
        return productRepository.findById(id)
                .map(product -> mapToDTO(product, new ProductDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Long create(final ProductDTO productDTO) {
        final Product product = new Product();
        mapToEntity(productDTO, product);
        return productRepository.save(product).getId();
    }

    public void update(final Long id, final ProductDTO productDTO) {
        final Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(productDTO, product);
        productRepository.save(product);
    }

    public void delete(final Long id) {
        productRepository.deleteById(id);
    }

    private ProductDTO mapToDTO(final Product product, final ProductDTO productDTO) {
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setPrice(product.getPrice());
        productDTO.setDescription(product.getDescription());
//        productDTO.setStoreProducts(product.getStoreProductStores() == null ? null : product.getStoreProductStores().stream()
//                .map(store -> store.getId())
//                .collect(Collectors.toList()));
        return productDTO;
    }

    private Product mapToEntity(final ProductDTO productDTO, final Product product) {
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product.setDescription(productDTO.getDescription());
//        final List<Store> storeProducts = storeRepository.findAllById(
//                productDTO.getStoreProducts() == null ? Collections.emptyList() : productDTO.getStoreProducts());
//        if (storeProducts.size() != (productDTO.getStoreProducts() == null ? 0 : productDTO.getStoreProducts().size())) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "one of storeProducts not found");
//        }
//        product.setStoreProductStores(storeProducts.stream().collect(Collectors.toSet()));
        return product;
    }

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    private void initProductStore() {
        try {
            String store_product_sql = "ALTER TABLE STORE_PRODUCT ADD quantity double";

            jdbcTemplate.update(store_product_sql);

        } catch (Exception e) {

            if (e instanceof SQLException) {
                //eat
            } else {
                //do something
                logger.error(e.getMessage());
            }
        }
    }
        @PostConstruct
        private void initProductWarehouse() {
            try {
                String warehouse_product_sql = "ALTER TABLE WAREHOUSE_PRODUCT ADD quantity double";
                jdbcTemplate.update(warehouse_product_sql);

            } catch (Exception e) {

                if (e instanceof SQLException) {
                    //eat
                } else {
                    //do something
                    logger.error(e.getMessage());
                }
            }
        }


}
