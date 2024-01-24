package com.example.firstproject.service;

import com.example.firstproject.dto.PizzaDto;
import com.example.firstproject.entity.Pizza;
import com.example.firstproject.repository.PizzaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PizzaService {
    @Autowired
    PizzaRepository pizzaRepository;

    public PizzaDto show(Long id) {
        Pizza target = pizzaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("피자 조회 실패! 없는 id의 피자 입니다."));
        return PizzaDto.createDto(target);
    }

    public List<PizzaDto> index() {
        return pizzaRepository.findAll()
                .stream().map(PizzaDto::createDto).toList();
    }

    @Transactional
    public PizzaDto create(PizzaDto pizzaDto) {
        if (pizzaDto.getId() != null) {
            throw new IllegalArgumentException("피자 생성 실패! 피자의 고유 키 값이 입력으로 들어왔습니다.");
        }
        Pizza saved = pizzaRepository.save(Pizza.create(pizzaDto));
        return PizzaDto.createDto(saved);
    }

    @Transactional
    public PizzaDto update(Long id, PizzaDto pizzaDto) {
        Pizza target = pizzaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("피자 수정 실패! 없는 피자입니다."));
        target.patch(pizzaDto);
        return PizzaDto.createDto(pizzaRepository.save(target));
    }

    @Transactional
    public PizzaDto delete(Long id) {
        Pizza target = pizzaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("피자 삭제 실패! 없는 피자입니다."));
        pizzaRepository.delete(target);
        return PizzaDto.createDto(target);
    }
}
