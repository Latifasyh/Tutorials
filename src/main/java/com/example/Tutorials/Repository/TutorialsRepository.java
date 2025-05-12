package com.example.Tutorials.Repository;

import com.example.Tutorials.Entity.Tutorials;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TutorialsRepository extends JpaRepository<Tutorials, Long> {
}
