package com.example.devsuperior.client.repository;

import com.example.devsuperior.client.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository  extends JpaRepository<Client, Long> {
    boolean existsByCpf(String cpf);
}
