package com.example.service.Impl;

import com.example.exception.ResourceNotFoundException;
import com.example.model.Client;
import com.example.repository.ClientRepository;
import com.example.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Transactional
    @Override
    public Client createClient(Client client) {
        return clientRepository.save(client);
    }

    @Transactional(readOnly = true)
    @Override
    public Client getClientById(Long id) {
        return clientRepository.findClientById(id);
    }

    @Transactional
    @Override
    public Client updateClient(Long id ,Client client) {
        Client clientDB = clientRepository.getOne(id);

        clientDB.setName(client.getName());
        clientDB.setPhone(client.getPhone());
        clientDB.setEmail(client.getEmail());
        clientDB.setUsername(client.getUsername());
        clientDB.setPassword(client.getPassword());

        return clientRepository.save(clientDB);
    }

    @Transactional
    @Override
    public ResponseEntity<?> deleteClient(Long id) {
        Client clientDB = clientRepository.getOne(id);
        if(clientDB == null){
            throw new ResourceNotFoundException("There is no client with Id " + id);
        }
        clientRepository.delete(clientDB);

        return ResponseEntity.ok().build();
    }

    @Transactional(readOnly = true)
    @Override
    public Client getClientByUsernameAndPassword(String username, String password) {
        return clientRepository.findClientByUsernameAndPassword(username, password);
    }

    @Transactional(readOnly = true)
    @Override
    public Client getClientByUsernameAndEmail(String username, String email) {
        return clientRepository.findClientByUsernameAndEmail(username, email);
    }
}
