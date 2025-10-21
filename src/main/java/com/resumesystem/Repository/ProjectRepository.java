package com.resumesystem.Repository;

import com.resumesystem.Entity.Project;
import com.resumesystem.Entity.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {

	List<Project> findByUser(User user);
	void deleteByUser(User user);


}
