package com.example.firstproject.service;

import com.example.firstproject.dto.GroupDto;
import com.example.firstproject.entity.SoccerGroup;
import com.example.firstproject.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupService {
    @Autowired
    GroupRepository groupRepository;

    public GroupDto getGroupBy(Long id) {
        SoccerGroup target = groupRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("그룹 조회 실패! 그룹이 없습니다."));
        return GroupDto.create(target);
    }
}
