package com.gmail.chigantseva.unibelltesttask.service;

import com.gmail.chigantseva.unibelltesttask.dto.ClientDTO;
import com.gmail.chigantseva.unibelltesttask.dto.ClientInfoDTO;
import com.gmail.chigantseva.unibelltesttask.dto.ContactDTO;
import com.gmail.chigantseva.unibelltesttask.entity.ContactType;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ClientService {

    ClientDTO createClient(ClientDTO clientDTO);
    ContactDTO addContactToClient(Integer clientId, ContactDTO contactDTO);
    ClientInfoDTO getClientById(Integer clientID);
    List<ClientDTO> getAllClients();
    List<ContactDTO> getClientContacts(Integer clientID);
    List<ContactDTO> getClientContactsByType(Integer clientID, ContactType type);

}
