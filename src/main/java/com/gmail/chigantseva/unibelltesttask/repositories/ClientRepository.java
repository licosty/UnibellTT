package com.gmail.chigantseva.unibelltesttask.repositories;

import com.gmail.chigantseva.unibelltesttask.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
}
