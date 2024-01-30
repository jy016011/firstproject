package com.example.firstproject.dto;

import com.example.firstproject.entity.SoccerGroup;
import com.example.firstproject.entity.Team;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class TeamDto {
    Long id;
    SoccerGroup soccerGroup;
    String name;
    String imageUrl;

    public static TeamDto create(Team team) {
        return new TeamDto(team.getId(), team.getSoccerGroup(), team.getName(), team.getImageUrl());
    }
}
