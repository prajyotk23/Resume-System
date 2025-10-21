package com.resumesystem.Repository;

import com.resumesystem.Entity.Internship;
import com.resumesystem.Entity.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface InternshipRepository extends JpaRepository<Internship, Long> {

	List<Internship> findByUser(User user);
	void deleteByUser(User user);


}
