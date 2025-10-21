package com.resumesystem.Repository;

import com.resumesystem.Entity.Certificate;
import com.resumesystem.Entity.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CertificateRepository extends JpaRepository<Certificate, Long> {

	List<Certificate> findByUser(User user);
	void deleteByUser(User user);


}
