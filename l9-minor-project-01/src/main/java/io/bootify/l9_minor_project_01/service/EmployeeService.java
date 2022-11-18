package io.bootify.l9_minor_project_01.service;

import io.bootify.l9_minor_project_01.domain.Address;
import io.bootify.l9_minor_project_01.domain.Employee;
import io.bootify.l9_minor_project_01.domain.Store;
import io.bootify.l9_minor_project_01.model.EmployeeDTO;
import io.bootify.l9_minor_project_01.repos.AddressRepository;
import io.bootify.l9_minor_project_01.repos.EmployeeRepository;
import io.bootify.l9_minor_project_01.repos.StoreRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final AddressRepository adressRepository;
    private final StoreRepository storeRepository;

    public EmployeeService(final EmployeeRepository employeeRepository,
            final AddressRepository adressRepository, final StoreRepository storeRepository) {
        this.employeeRepository = employeeRepository;
        this.adressRepository = adressRepository;
        this.storeRepository = storeRepository;
    }

    public List<EmployeeDTO> findAll() {
        return employeeRepository.findAll(Sort.by("id"))
                .stream()
                .map(employee -> mapToDTO(employee, new EmployeeDTO()))
                .collect(Collectors.toList());
    }

    public EmployeeDTO get(final Long id) {
        return employeeRepository.findById(id)
                .map(employee -> mapToDTO(employee, new EmployeeDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Long create(final EmployeeDTO employeeDTO) {
        final Employee employee = new Employee();
        mapToEntity(employeeDTO, employee);
        return employeeRepository.save(employee).getId();
    }

    public void update(final Long id, final EmployeeDTO employeeDTO) {
        final Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(employeeDTO, employee);
        employeeRepository.save(employee);
    }

    public void delete(final Long id) {
        employeeRepository.deleteById(id);
    }

    private EmployeeDTO mapToDTO(final Employee employee, final EmployeeDTO employeeDTO) {
        employeeDTO.setId(employee.getId());
        employeeDTO.setName(employee.getName());
        employeeDTO.setEmail(employee.getEmail());
        employeeDTO.setSalary(employee.getSalary());
        employeeDTO.setAddress(employee.getAddress() == null ? null : employee.getAddress().getId());
        employeeDTO.setStore(employee.getStore() == null ? null : employee.getStore().getId());
        return employeeDTO;
    }

    private Employee mapToEntity(final EmployeeDTO employeeDTO, final Employee employee) {
        employee.setName(employeeDTO.getName());
        employee.setEmail(employeeDTO.getEmail());
        employee.setSalary(employeeDTO.getSalary());
        final Address address = employeeDTO.getAddress() == null ? null : adressRepository.findById(employeeDTO.getAddress())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "address not found"));
        employee.setAddress(address);
        final Store store = employeeDTO.getStore() == null ? null : storeRepository.findById(employeeDTO.getStore())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "store not found"));
        employee.setStore(store);
        return employee;
    }

}
