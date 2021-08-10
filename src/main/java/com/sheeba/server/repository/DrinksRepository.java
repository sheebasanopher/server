package com.sheeba.server.repository;

import com.sheeba.server.domain.OnlyDrinksPojo;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface DrinksRepository extends CrudRepository<OnlyDrinksPojo, Integer> {

    Optional<OnlyDrinksPojo> findByProductName(String productName);

}