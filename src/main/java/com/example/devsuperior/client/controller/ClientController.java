package com.example.devsuperior.client.controller;

import com.example.devsuperior.client.dto.ClientResponseDTO;
import com.example.devsuperior.client.dto.ClientRequestDTO;
import com.example.devsuperior.client.service.ClientService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clients")
public class ClientController {
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    public ClientResponseDTO insert(@RequestBody ClientRequestDTO dto) {
        ClientResponseDTO responseDTO = clientService.insert(dto);
        return responseDTO;
    }

    @GetMapping("/{id}")
    public ClientResponseDTO findById(@PathVariable Long id) {
        return clientService.findById(id);
    }

    @GetMapping
    public Page<ClientResponseDTO> findAll(Pageable pageable) {
        return clientService.findAll(pageable);
    }

    @PutMapping("/{id}")
    public ClientResponseDTO update(@PathVariable Long id, @RequestBody ClientRequestDTO dto) {
        return clientService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        clientService.delete(id);
    }
}

