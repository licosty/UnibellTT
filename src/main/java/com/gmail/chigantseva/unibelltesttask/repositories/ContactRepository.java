package com.gmail.chigantseva.unibelltesttask.repositories;

import com.gmail.chigantseva.unibelltesttask.entity.Contact;
import com.gmail.chigantseva.unibelltesttask.entity.ContactType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContactRepository extends JpaRepository<Contact, Integer> {

    List<Contact> findContactsByClient_Id(Integer clientId);
    List<Contact> findContactsByClient_IdAndType(Integer clientId, ContactType type);
}
