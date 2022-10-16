package com.example.spring152.repos;

import com.example.spring152.models.ItemModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepo extends JpaRepository<ItemModel, Long> {
}
