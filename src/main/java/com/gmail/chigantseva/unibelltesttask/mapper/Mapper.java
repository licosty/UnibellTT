package com.gmail.chigantseva.unibelltesttask.mapper;

import com.gmail.chigantseva.unibelltesttask.dto.ClientDTO;
import com.gmail.chigantseva.unibelltesttask.dto.ClientInfoDTO;
import com.gmail.chigantseva.unibelltesttask.dto.ContactDTO;
import com.gmail.chigantseva.unibelltesttask.entity.Client;
import com.gmail.chigantseva.unibelltesttask.entity.Contact;
import com.gmail.chigantseva.unibelltesttask.entity.ContactType;

import java.util.List;
import java.util.stream.Collectors;

public class Mapper {
    // Client
    public static ClientDTO mapToClientDTO(Client client) {
        return new ClientDTO(
                client.getId(),
                client.getName());
    }

    public static Client mapToClient(ClientDTO clientDTO) {
        return new Client(
                clientDTO.id(),
                clientDTO.name());
    }

    public static ClientInfoDTO mapToClientInfoDTO(Client client, List<Contact> contacts) {
        List<ContactDTO> contactsDTO = contacts.stream()
                .map(contact -> new ContactDTO(contact.getContact(), contact.getType().name()))
                .collect(Collectors.toList());

        return new ClientInfoDTO(
                client.getId(),
                client.getName(),
                contactsDTO);
    }

    // Contact
    public static ContactDTO mapToContactDTO(Contact contact) {
        return new ContactDTO(
                contact.getContact(),
                contact.getType().name());
    }

    public static Contact mapToContact(ContactDTO contactDTO) {
        return new Contact(
                contactDTO.contact(),
                ContactType.valueOf(contactDTO.type().toUpperCase()));
    }
}
