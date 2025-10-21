package com.resumesystem.Repository;

import com.resumesystem.Entity.Skill;
import com.resumesystem.Entity.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SkillRepository extends JpaRepository<Skill, Long> {

	List<Skill> findByUser(User user);
	void deleteByUser(User user);


}
