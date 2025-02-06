package com.gmail.chigantseva.unibelltesttask.controller;

import com.gmail.chigantseva.unibelltesttask.dto.ClientDTO;
import com.gmail.chigantseva.unibelltesttask.dto.ClientInfoDTO;
import com.gmail.chigantseva.unibelltesttask.dto.ContactDTO;
import com.gmail.chigantseva.unibelltesttask.entity.ContactType;
import com.gmail.chigantseva.unibelltesttask.exception.UnknownParamException;
import com.gmail.chigantseva.unibelltesttask.service.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;

import static com.gmail.chigantseva.unibelltesttask.util.Util.*;

@RestController
@RequestMapping(value = "/unibellTT/api")
@Tag(name = "Client")
public class ClientController {
    private ClientService clientService;

    @Operation(description = "Create a new client")
    @PostMapping("/createClient")
    public ResponseEntity<ClientDTO> createClient(@RequestBody @Valid ClientDTO clientDTO) {
        ClientDTO savedClient = clientService.createClient(clientDTO);
        return new ResponseEntity<>(savedClient, HttpStatus.CREATED);
    }

    @Operation(description = "Add a new contact to the client")
    @PostMapping("/{id}/addContact")
    public ResponseEntity<ContactDTO> addContactToClient(@PathVariable("id") Integer clientID, @RequestBody @Valid ContactDTO contactDTO) {
        validContact(contactDTO);

        ContactDTO savedContactDTO = clientService.addContactToClient(clientID, contactDTO);
        return new ResponseEntity<>(savedContactDTO, HttpStatus.CREATED);
    }

    @Operation(description = "Get a list of clients")
    @GetMapping("/clients")
    public ResponseEntity<List<ClientDTO>> getAllClients() {
        return ResponseEntity.ok(clientService.getAllClients());
    }

    @Operation(description = "Get info about the client by client id")
    @GetMapping("/{id}/clientInfo")
    public ResponseEntity<ClientInfoDTO> getClientById(@PathVariable("id") Integer clientID) {
        ClientInfoDTO clientInfoDTO = clientService.getClientById(clientID);
        return ResponseEntity.ok(clientInfoDTO);
    }

    @Operation(description = "Get all client's contacts")
    @GetMapping("/{id}/contracts")
    public ResponseEntity<List<ContactDTO>> getClientContacts(@PathVariable("id") Integer clientID) {
        return ResponseEntity.ok(clientService.getClientContacts(clientID));
    }

    @Operation(description = "Get all client's contacts by type")
    @GetMapping("/{id}/contacts/{type}")
    public ResponseEntity<List<ContactDTO>> getClientContactsByType(@PathVariable("id") Integer clientID, @PathVariable String type) {
        try {
            ContactType contactType = ContactType.valueOf(type.toUpperCase());
            return ResponseEntity.ok(clientService.getClientContactsByType(clientID, contactType));
        } catch (IllegalArgumentException ex) {
            throw new UnknownParamException(MessageFormat.format(
                    "Unknown type of contact: {0}. Type must be one of {1}", type, Arrays.toString(ContactType.values())));
        }
    }

    @Autowired
    public ClientController(ClientService service) {
        this.clientService = service;
    }
}
