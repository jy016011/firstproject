package com.example.firstproject.service;

import com.example.firstproject.dto.TeamDto;
import com.example.firstproject.repository.TeamRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeamService {
    @Autowired
    TeamRepository teamRepository;

    public List<TeamDto> teams(Long groupId) {
        return teamRepository.findByGroupId(groupId).stream().map(TeamDto::create).toList();
    }
}
