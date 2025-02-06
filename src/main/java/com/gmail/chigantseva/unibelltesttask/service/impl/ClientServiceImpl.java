package com.gmail.chigantseva.unibelltesttask.service.impl;

import com.gmail.chigantseva.unibelltesttask.dto.ClientDTO;
import com.gmail.chigantseva.unibelltesttask.dto.ClientInfoDTO;
import com.gmail.chigantseva.unibelltesttask.dto.ContactDTO;
import com.gmail.chigantseva.unibelltesttask.entity.Client;
import com.gmail.chigantseva.unibelltesttask.entity.Contact;
import com.gmail.chigantseva.unibelltesttask.entity.ContactType;
import com.gmail.chigantseva.unibelltesttask.exception.ContactAlreadyExistsException;
import com.gmail.chigantseva.unibelltesttask.exception.NumberConversionError;
import com.gmail.chigantseva.unibelltesttask.exception.ResourceNotFoundException;
import com.gmail.chigantseva.unibelltesttask.mapper.Mapper;
import com.gmail.chigantseva.unibelltesttask.repositories.ClientRepository;
import com.gmail.chigantseva.unibelltesttask.repositories.ContactRepository;
import com.gmail.chigantseva.unibelltesttask.service.ClientService;
import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.PhoneNumberUtil.PhoneNumberFormat;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements ClientService {
    private ClientRepository clientRepository;
    private ContactRepository contactRepository;

    public ClientDTO createClient(ClientDTO clientDTO) {
        Client client = Mapper.mapToClient(clientDTO);
        Client savedClient = clientRepository.save(client);
        return Mapper.mapToClientDTO(savedClient);
    }

    public ContactDTO addContactToClient(Integer clientID, ContactDTO contactDTO) {
        Client client = clientRepository.findById(clientID)
                .orElseThrow(() -> new ResourceNotFoundException("Client is not exists with given id: " + clientID));

        Contact contact = Mapper.mapToContact(contactDTO);
        contact.setClient(client);

        try {
            if (contact.getType().name().equals("PHONE")) {
                String phone = unifyPhone(contact.getContact());
                contact.setContact(phone);
            }

            return Mapper.mapToContactDTO(contactRepository.save(contact));

        } catch (NumberParseException ex) {
            throw new NumberConversionError (
                    MessageFormat.format("Failed to convert number {0} to format +7XXXXXXXXXX", contact.getContact()));
        } catch (DataIntegrityViolationException ex) {
            throw new ContactAlreadyExistsException(
                    MessageFormat.format("Such {0} contact already exists", contact.getContact()));
        }
    }

    private String unifyPhone(String phone) throws NumberParseException {
        PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
        PhoneNumber phoneNumber = phoneUtil.parse(phone, "RU");
        return phoneUtil.format(phoneNumber, PhoneNumberFormat.E164);
    }

    public List<ClientDTO> getAllClients() {
        List<ClientDTO> clients = clientRepository.findAll().stream()
                .map(client -> new ClientDTO(client.getId(), client.getName()))
                .collect(Collectors.toList());

        return clients;
    }

    public ClientInfoDTO getClientById(Integer clientID) {
        Client client = checkClientExists(clientID);
        List<Contact> contacts = contactRepository.findContactsByClient_Id(clientID);

        return Mapper.mapToClientInfoDTO(client, contacts);
    }

    public List<ContactDTO> getClientContacts(Integer clientID) {
        checkClientExists(clientID);

        List<ContactDTO> contacts = contactRepository.findContactsByClient_Id(clientID).stream()
                .map(contact -> new ContactDTO(contact.getContact(), contact.getType().name()))
                .collect(Collectors.toList());

        return contacts;
    }

    public List<ContactDTO> getClientContactsByType(Integer clientID, ContactType type) {
        checkClientExists(clientID);

        List<ContactDTO> contacts = contactRepository.findContactsByClient_IdAndType(clientID, type).stream()
                .map(contact -> new ContactDTO(contact.getContact(), contact.getType().name()))
                .collect(Collectors.toList());

        return contacts;
    }

    private Client checkClientExists(Integer clientID) {
        return clientRepository.findById(clientID)
                .orElseThrow(() -> new ResourceNotFoundException("Client is not exists with given id: " + clientID));
    }

    @Autowired
    public void setClientRepository(ClientRepository clientRepository, ContactRepository repContact) {
        this.clientRepository = clientRepository;
        this.contactRepository = repContact;
    }
}
