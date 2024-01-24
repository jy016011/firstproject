package com.example.firstproject.entity;

import com.example.firstproject.dto.PizzaDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class Pizza { // this entity is answer of self check problem.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private String price;

    public static Pizza create(PizzaDto pizzaDto) {
        return new Pizza(
                pizzaDto.getId(),
                pizzaDto.getName(),
                pizzaDto.getPrice()
        );
    }

    public void patch(PizzaDto pizzaDto) {
        if (this.id != pizzaDto.getId()) {
            throw new IllegalArgumentException("피자 수정 실패! 수정할 피자가 경로와 다릅니다.");
        }
        if (pizzaDto.getName() != null) {
            this.name = pizzaDto.getName();
        }
        if (pizzaDto.getPrice() != null) {
            this.price = pizzaDto.getPrice();
        }
    }
}
