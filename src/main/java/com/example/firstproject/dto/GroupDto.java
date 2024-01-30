package com.example.firstproject.dto;

import com.example.firstproject.entity.SoccerGroup;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class GroupDto {
    Long id;
    String name;

    public static GroupDto create(SoccerGroup soccerGroup) {
        return new GroupDto(soccerGroup.getId(), soccerGroup.getName());
    }
}
