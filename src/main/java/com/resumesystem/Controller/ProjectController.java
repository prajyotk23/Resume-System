package com.resumesystem.Controller;

import com.resumesystem.Entity.Project;
import com.resumesystem.Entity.User;
import com.resumesystem.Repository.ProjectRepository;
import com.resumesystem.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectRepository projectRepo;
    private final UserRepository userRepo;

    @PostMapping
    public ResponseEntity<Project> add(@RequestBody Project p) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User u = userRepo.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("User not found"));
        p.setUser(u);
        return ResponseEntity.ok(projectRepo.save(p));
    }


    @GetMapping("/{id}")
    public ResponseEntity<Project> get(@PathVariable Long id) {
        return ResponseEntity.of(projectRepo.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Project> update(@PathVariable Long id, @RequestBody Project p) {
        Project existing = projectRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Not found"));
        existing.setTitle(p.getTitle());
        existing.setDescription(p.getDescription());
        existing.setTechStack(p.getTechStack());
        existing.setLink(p.getLink());
        return ResponseEntity.ok(projectRepo.save(existing));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        projectRepo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
