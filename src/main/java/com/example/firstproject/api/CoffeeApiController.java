package com.example.firstproject.api;

import com.example.firstproject.dto.CoffeeForm;
import com.example.firstproject.entity.Coffee;
import com.example.firstproject.service.CoffeeService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class CoffeeApiController {
    @Autowired
    CoffeeService coffeeService;

    @GetMapping("/api/coffees")
    public List<Coffee> index() {
        return coffeeService.index();
    }

    @GetMapping("/api/coffees/{id}")
    public ResponseEntity<Coffee> show(@PathVariable Long id) {
        Coffee target = coffeeService.show(id);
        return (target != null) ?
                ResponseEntity.status(HttpStatus.OK).body(target) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

    }

    @PostMapping("/api/coffees")
    public ResponseEntity<Coffee> create(@RequestBody CoffeeForm dto) {
        Coffee created = coffeeService.create(dto);
        return (created != null) ?
                ResponseEntity.status(HttpStatus.OK).body(created) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

    }

    @PatchMapping("/api/coffees/{id}")
    public ResponseEntity<Coffee> update(@PathVariable Long id, @RequestBody CoffeeForm dto) {
        Coffee updated = coffeeService.update(id, dto);
        return (updated != null) ?
                ResponseEntity.status(HttpStatus.OK).body(updated) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @DeleteMapping("/api/coffees/{id}")
    public ResponseEntity<Coffee> delete(@PathVariable Long id) {
        Coffee deleted = coffeeService.delete(id);
        return (deleted != null) ?
                ResponseEntity.status(HttpStatus.OK).body(deleted) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PostMapping("/api/transaction-test/coffees")
    public ResponseEntity<List<Coffee>> transactionTest(List<CoffeeForm> dtos) {
        List<Coffee> createdCoffees = coffeeService.createCoffees(dtos);
        return (createdCoffees != null) ?
                ResponseEntity.status(HttpStatus.OK).body(createdCoffees) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
