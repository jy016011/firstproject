package com.example.firstproject.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.firstproject.dto.CoffeeForm;
import com.example.firstproject.entity.Coffee;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class CoffeeServiceTest {
    @Autowired
    CoffeeService coffeeService;

    @Test
    void index() {
        // 1. 예상 데이터
        Coffee americano = new Coffee(1L, "아메리카노", "4500");
        Coffee latte = new Coffee(2L, "라떼", "5000");
        Coffee mocha = new Coffee(3L, "카페 모카", "5500");
        List<Coffee> expected = new ArrayList<Coffee>(Arrays.asList(americano, latte, mocha));
        // 2. 실제 데이터
        List<Coffee> coffees = coffeeService.index();
        // 3. 비교 및 검증
        assertEquals(expected.toString(), coffees.toString());
    }

    @Test
    void show_성공_존재하는_id_입력() {
        // 1. 예상 데이터
        Long id = 1L;
        Coffee expected = new Coffee(id, "아메리카노", "4500");
        // 2. 실제 데이터
        Coffee actual = coffeeService.show(id);
        // 3. 비교 및 검증
        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    void show_실패_존재하지_않는_id_입력() {
        // 1. 예상 데이터
        Long id = -1L;
        Coffee expected = null;
        // 2. 실제 데이터
        Coffee actual = coffeeService.show(id);
        // 3. 비교 및 검증
        assertEquals(expected, actual);
    }

    @Transactional
    @Test
    void create_성공_name과_price만_있는_dto_입력() {
        // 1. 예상 데이터
        String name = "시그니처 초콜릿";
        String price = "5500";
        CoffeeForm dto = new CoffeeForm(null, name, price);
        Coffee expected = new Coffee(4L, name, price);
        // 2. 실제 데이터
        Coffee actual = coffeeService.create(dto);
        // 3. 비교 및 검증
        assertEquals(expected.toString(), actual.toString());
    }

    @Transactional
    @Test
    void create_실패_id가_포함된_dto_입력() {
        // 1. 예상 데이터
        String name = "시그니처 초콜릿";
        String price = "5500";
        CoffeeForm dto = new CoffeeForm(4L, name, price);
        Coffee expected = null;
        // 2. 실제 데이터
        Coffee actual = coffeeService.create(dto);
        // 3. 비교 및 검증
        assertEquals(expected, actual);
    }

    @Transactional
    @Test
    void update_성공_존재하는_id와_title_content가_있는_dto_입력() {
        // 1. 예상 데이터
        Long id = 1L;
        String name = "아메리카노(디카페인)";
        String price = "5000";
        CoffeeForm dto = new CoffeeForm(id, name, price);
        Coffee expected = new Coffee(id, name, price);
        // 2. 실제 데이터
        Coffee actual = coffeeService.update(id, dto);
        // 3. 비교 및 검증
        assertEquals(expected.toString(), actual.toString());
    }

    @Transactional
    @Test
    void update_성공_존재하는_id와_title만_있는_dto_입력() {
        // 1. 예상 데이터
        Long id = 1L;
        String name = "아메리카노(디카페인)";
        CoffeeForm dto = new CoffeeForm(id, name, null);
        Coffee expected = new Coffee(id, name, "4500");
        // 2. 실제 데이터
        Coffee actual = coffeeService.update(id, dto);
        // 3. 비교 및 검증
        assertEquals(expected.toString(), actual.toString());
    }

    @Transactional
    @Test
    void update_실패_존재하지_않는_id의_dto_입력() {
        // 1. 예상 데이터
        Long id = -1L;
        String name = "아메리카노(디카페인)";
        String price = "5000";
        CoffeeForm dto = new CoffeeForm(id, name, price);
        Coffee expected = null;
        // 2. 실제 데이터
        Coffee actual = coffeeService.update(id, dto);
        // 3. 비교 및 검증
        assertEquals(expected, actual);
    }

    @Transactional
    @Test
    void update_실패_경로_id와_dto의_id가_다른_입력() {
        // 1. 예상 데이터
        Long pathId = 1L;
        Long dtoId = 2L;
        String name = "아메리카노(디카페인)";
        String price = "5000";
        CoffeeForm dto = new CoffeeForm(dtoId, name, price);
        Coffee expected = null;
        // 2. 실제 데이터
        Coffee actual = coffeeService.update(pathId, dto);
        // 3. 비교 및 검증
        assertEquals(expected, actual);
    }

    @Transactional
    @Test
    void delete_성공_존재하는_id_입력() {
        // 1. 예상 데이터
        Long id = 1L;
        Coffee expected = new Coffee(id, "아메리카노", "4500");
        // 2. 실제 데이터
        Coffee actual = coffeeService.delete(id);
        // 3. 비교 및 검증
        assertEquals(expected.toString(), actual.toString());
    }

    @Transactional
    @Test
    void delete_실패_존재하지_않는_id_입력() {
        // 1. 예상 데이터
        Long id = -1L;
        Coffee expected = null;
        // 2. 실제 데이터
        Coffee actual = coffeeService.delete(id);
        // 3. 비교 및 검증
        assertEquals(expected, actual);
    }
}