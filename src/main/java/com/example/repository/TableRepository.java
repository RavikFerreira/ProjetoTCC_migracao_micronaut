package com.example.repository;

import com.example.models.TableBar;
import com.fasterxml.jackson.annotation.OptBoolean;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;

import java.util.Optional;

@Repository
public interface TableRepository extends JpaRepository<TableBar, Long> {

    Optional<TableBar> findByIdTable(String idTable);
}
