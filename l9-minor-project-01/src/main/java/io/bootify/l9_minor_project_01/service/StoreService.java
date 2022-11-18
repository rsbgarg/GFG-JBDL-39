package io.bootify.l9_minor_project_01.service;

import io.bootify.l9_minor_project_01.domain.Address;
import io.bootify.l9_minor_project_01.domain.Store;

import io.bootify.l9_minor_project_01.model.StoreDTO;
import io.bootify.l9_minor_project_01.repos.AddressRepository;
import io.bootify.l9_minor_project_01.repos.StoreRepository;
import io.bootify.l9_minor_project_01.rest.EmployeeResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.PostConstruct;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class StoreService {
    private Logger logger = LoggerFactory.getLogger(StoreService.class);
    private final AddressRepository adressRepository;
    private final StoreRepository storeRepository;

    public StoreService(final AddressRepository adressRepository, final StoreRepository storeRepository) {
     
        this.adressRepository = adressRepository;
        this.storeRepository = storeRepository;
    }

    public List<StoreDTO> findAll() {
        return storeRepository.findAll(Sort.by("id"))
                .stream()
                .map(store -> mapToDTO(store, new StoreDTO()))
                .collect(Collectors.toList());
    }

    public StoreDTO get(final Long id) {
        return storeRepository.findById(id)
                .map(store -> mapToDTO(store, new StoreDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Long create(final StoreDTO storeDTO) {
        final Store store = new Store();
        mapToEntity(storeDTO, store);
        return storeRepository.save(store).getId();
    }

    public void update(final Long id, final StoreDTO storeDTO) {
        final Store store = storeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(storeDTO, store);
        storeRepository.save(store);
    }

    public void delete(final Long id) {
        storeRepository.deleteById(id);
    }

    private StoreDTO mapToDTO(final Store store, final StoreDTO storeDTO) {
        storeDTO.setId(store.getId());
        storeDTO.setAddress(store.getAddress() == null ? null : store.getAddress());
        return storeDTO;
    }

    private Store mapToEntity(final StoreDTO storeDTO, final Store store) {

        final Address address = storeDTO.getAddress() == null ? null : adressRepository.findById(storeDTO.getAddress().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "address not found"));
        store.setAddress(address);

        return store;
    }

    @Autowired
    private JdbcTemplate jdbcTemplate;



    private void initStore() {
        try {
            String store_init_sql = "ALTER TABLE STORE_PRODUCT ADD quantity double";

            jdbcTemplate.update(store_init_sql);

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
