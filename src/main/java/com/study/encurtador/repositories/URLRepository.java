package com.study.encurtador.repositories;


import com.study.encurtador.models.URLModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface URLRepository extends JpaRepository<URLModel, UUID> {
}
