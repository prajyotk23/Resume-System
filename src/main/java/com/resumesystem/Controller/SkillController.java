package com.resumesystem.Controller;

import com.resumesystem.Entity.Skill;
import com.resumesystem.Entity.User;
import com.resumesystem.Repository.SkillRepository;
import com.resumesystem.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/skills")
@RequiredArgsConstructor
public class SkillController {

    private final SkillRepository skillRepo;
    private final UserRepository userRepo;

    @PostMapping
    public ResponseEntity<Skill> add(@RequestBody Skill skill) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepo.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("User not found"));
        skill.setUser(user);
        return ResponseEntity.ok(skillRepo.save(skill));
    }



    @GetMapping("/{id}")
    public ResponseEntity<Skill> get(@PathVariable Long id) {
        return ResponseEntity.of(skillRepo.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Skill> update(@PathVariable Long id, @RequestBody Skill updated) {
        Skill existing = skillRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Skill not found"));

        existing.setSkillName(updated.getSkillName());
        existing.setProficiencyLevel(updated.getProficiencyLevel());

        return ResponseEntity.ok(skillRepo.save(existing));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        skillRepo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
