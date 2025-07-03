package com.naja7online.api.repository;
import com.naja7online.api.model.Module;
import com.naja7online.api.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModuleRepository extends JpaRepository<Module, Long> {
}

