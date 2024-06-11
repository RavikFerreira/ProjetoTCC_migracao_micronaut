package com.example.repository;

import com.example.models.Menu;
import com.example.models.TableBar;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;

import java.util.Optional;


@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {

    Optional<Menu> findByIdMenu(String idMenu);
}
