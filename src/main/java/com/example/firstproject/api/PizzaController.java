package com.example.firstproject.api;

import com.example.firstproject.dto.PizzaDto;
import com.example.firstproject.service.PizzaService;
import java.util.List;
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

@RestController
public class PizzaController {
    @Autowired
    private PizzaService pizzaService;

    // 1. 조회
    @GetMapping("/api/pizzas/{id}")
    private ResponseEntity<PizzaDto> show(@PathVariable Long id) {
        PizzaDto pizzaDto = pizzaService.show(id);
        return ResponseEntity.status(HttpStatus.OK).body(pizzaDto);
    }

    @GetMapping("/api/pizzas")
    private ResponseEntity<List<PizzaDto>> index() {
        List<PizzaDto> pizzaDtos = pizzaService.index();
        return ResponseEntity.status(HttpStatus.OK).body(pizzaDtos);
    }

    // 2. 생성
    @PostMapping("/api/pizzas")
    private ResponseEntity<PizzaDto> create(@RequestBody PizzaDto pizzaDto) {
        PizzaDto created = pizzaService.create(pizzaDto);
        return ResponseEntity.status(HttpStatus.OK).body(created);
    }

    // 3. 수정
    @PatchMapping("/api/pizzas/{id}")
    private ResponseEntity<PizzaDto> update(@PathVariable Long id, @RequestBody PizzaDto pizzaDto) {
        PizzaDto updatedDto = pizzaService.update(id, pizzaDto);
        return ResponseEntity.status(HttpStatus.OK).body(updatedDto);
    }

    // 4. 삭제
    @DeleteMapping("/api/pizzas/{id}")
    private ResponseEntity<PizzaDto> delete(@PathVariable Long id) {
        PizzaDto deleted = pizzaService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(deleted);
    }
}
