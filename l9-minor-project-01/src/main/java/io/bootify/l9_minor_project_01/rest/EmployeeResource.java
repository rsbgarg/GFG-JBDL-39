package io.bootify.l9_minor_project_01.rest;

import io.bootify.l9_minor_project_01.exceptions.EmployeeException;
import io.bootify.l9_minor_project_01.model.EmployeeDTO;
import io.bootify.l9_minor_project_01.service.EmployeeService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import java.util.List;
import java.util.regex.Pattern;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/employees", produces = MediaType.APPLICATION_JSON_VALUE)
public class EmployeeResource {

    Logger logger = LoggerFactory.getLogger(EmployeeResource.class);

    private static final String EMAIL_REGEX = "^(.+)@(\\S+)$";
    private final EmployeeService employeeService;

    public EmployeeResource(final EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> getEmployee(@PathVariable final Long id) {
        return ResponseEntity.ok(employeeService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createEmployee(@RequestBody @Valid final EmployeeDTO employeeDTO) {
        Long id = -1l;
        try {
            validate(employeeDTO);
           id   = employeeService.create(employeeDTO);
        } catch (EmployeeException e){

            return new ResponseEntity<>(id , HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(id , HttpStatus.CREATED);
    }



    @PutMapping("/{id}")
    public ResponseEntity<Void> updateEmployee(@PathVariable final Long id,
            @RequestBody @Valid final EmployeeDTO employeeDTO) {
        employeeService.update(id, employeeDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteEmployee(@PathVariable final Long id) {
        employeeService.delete(id);
        return ResponseEntity.noContent().build();
    }

    private void validate(EmployeeDTO employeeDTO) throws EmployeeException {
        logger.info("Validatig employee DTO {}", employeeDTO);
        String email = employeeDTO.getEmail();
        if(!patternMatch(email, EMAIL_REGEX)){
            throw new EmployeeException("Incorrect Email");
        }
    }

    private boolean patternMatch(String email, String regex){
        return Pattern.compile(regex)
                .matcher(email).matches();
    }


}
