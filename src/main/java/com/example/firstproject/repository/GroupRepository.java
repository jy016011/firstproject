package com.example.firstproject.repository;

import com.example.firstproject.entity.SoccerGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<SoccerGroup, Long> {
}
