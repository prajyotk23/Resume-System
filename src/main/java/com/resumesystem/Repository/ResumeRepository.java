package com.resumesystem.Repository;

import com.resumesystem.Entity.Resume;
import com.resumesystem.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ResumeRepository extends JpaRepository<Resume, Long> {
    Optional<Resume> findByUser(User user);
    void deleteByUser(User user);


}

