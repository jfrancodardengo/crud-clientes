package com.example.devsuperior.client.service;

import com.example.devsuperior.client.dto.ClientResponseDTO;
import com.example.devsuperior.client.dto.ClientRequestDTO;
import com.example.devsuperior.client.exception.ResourceNotFoundException;
import com.example.devsuperior.client.model.Client;
import com.example.devsuperior.client.repository.ClientRepository;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientService {

    private final ClientRepository repository;
    private final ModelMapper modelMapper;

    public ClientService(ClientRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    @Transactional(readOnly = true)
    public ClientResponseDTO findById(Long id) {
        Client client = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado" +
                " com o id: " + id));

        return modelMapper.map(client, ClientResponseDTO.class);
    }

    @Transactional(readOnly = true)
    public Page<ClientResponseDTO> findAll(Pageable pageable) {
        Page<Client> result = repository.findAll(pageable);

        return result.map(client -> modelMapper.map(client, ClientResponseDTO.class));
    }

    @Transactional
    public ClientResponseDTO insert(ClientRequestDTO dto) {
        if(repository.existsByCpf(dto.getCpf())) {
            throw new DataIntegrityViolationException("O cpf " + dto.getCpf() + " já está sendo usado por outro usuário.");
        }

        Client client = modelMapper.map(dto, Client.class);

        client = repository.save(client);

        return modelMapper.map(client, ClientResponseDTO.class);
    }

    @Transactional
    public ClientResponseDTO update(Long id, ClientRequestDTO dto) {
        Client client = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado" +
                " com o id: " + id));

        //Valida se o cpf é diferente do que está salvo. Se for,verifica se outro cliente tem o cpf cadastrado
        if (!dto.getCpf().equals(client.getCpf())) {
            if (repository.existsByCpf(dto.getCpf())) {
                throw new DataIntegrityViolationException("Não é possível atualizar. O cpf " + dto.getCpf() + " já está em uso.");
            }
        }

        modelMapper.map(dto, client);

        client = repository.save(client);

        return modelMapper.map(client, ClientResponseDTO.class);
    }

    @Transactional
    public void delete(Long id){
        if(!repository.existsById(id)){
            throw new ResourceNotFoundException("Não foi possível deletar. Cliente não encontrado " +
                    "com o ID:" + id);
        }
        repository.deleteById(id);
    }
}
