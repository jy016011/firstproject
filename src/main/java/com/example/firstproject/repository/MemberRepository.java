package com.example.firstproject.repository;

import com.example.firstproject.entity.Member;
import java.util.ArrayList;
import org.springframework.data.repository.CrudRepository;

public interface MemberRepository extends CrudRepository<Member, Long> {
    @Override
    ArrayList<Member> findAll();
}
