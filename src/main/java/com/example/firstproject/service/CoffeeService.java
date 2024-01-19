package com.example.firstproject.service;

import com.example.firstproject.dto.CoffeeForm;
import com.example.firstproject.entity.Coffee;
import com.example.firstproject.repository.CoffeeRepository;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class CoffeeService {

    @Autowired
    CoffeeRepository coffeeRepository;

    public List<Coffee> index() {
        return coffeeRepository.findAll();
    }

    public Coffee show(Long id) {
        return coffeeRepository.findById(id).orElse(null);
    }

    public Coffee create(CoffeeForm dto) {
        Coffee coffee = dto.toEntity();
        if (coffee.getId() != null) {
            log.info("잘못된 id값 입력! id: {}", coffee.getId());
            return null;
        }
        return coffeeRepository.save(coffee);
    }

    public Coffee update(Long id, CoffeeForm dto) {
        Coffee coffee = dto.toEntity();
        log.info("수정 입력 데이터: {}", coffee.toString());
        Coffee target = coffeeRepository.findById(id).orElse(null);
        if (target == null || id != coffee.getId()) {
            log.info("잘못된 PATCH 요청, id: {}, Coffee: {}", id, coffee.toString());
            return null;
        }
        target.patch(coffee);
        return coffeeRepository.save(target);
    }

    public Coffee delete(Long id) {
        Coffee target = coffeeRepository.findById(id).orElse(null);
        if (target == null) {
            log.info("잘못된 DELETE 요청, 이미 없는 데이터 입니다! id: {}", id);
            return null;
        }
        coffeeRepository.delete(target);
        return target;
    }

    @Transactional
    public List<Coffee> createCoffees(List<CoffeeForm> dtos) {
        List<Coffee> coffees = dtos.stream().map(CoffeeForm::toEntity).toList();
        coffees.forEach(coffee -> coffeeRepository.save(coffee));
        Coffee error = coffeeRepository.findById(-1L).orElseThrow(() -> new IllegalArgumentException("커피 불러오기 오류 발생"));
        return coffees;
    }
}
