package com.example.firstproject.repository;

import com.example.firstproject.entity.Team;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TeamRepository extends JpaRepository<Team, Long> {
    @Query(value = "SELECT * FROM team WHERE group_id = :groupId", nativeQuery = true)
    List<Team> findByGroupId(Long groupId);
}
