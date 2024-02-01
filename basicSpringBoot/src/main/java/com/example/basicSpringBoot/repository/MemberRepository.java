package com.example.basicSpringBoot.repository;

import com.example.basicSpringBoot.entity.Member;
import org.springframework.data.repository.CrudRepository;

public interface MemberRepository extends CrudRepository<Member, Long> {
}
