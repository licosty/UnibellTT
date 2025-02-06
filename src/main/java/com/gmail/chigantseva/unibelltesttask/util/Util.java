package com.gmail.chigantseva.unibelltesttask.util;

import com.gmail.chigantseva.unibelltesttask.dto.ContactDTO;
import com.gmail.chigantseva.unibelltesttask.entity.ContactType;
import com.gmail.chigantseva.unibelltesttask.exception.UnknownParamException;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.regex.Pattern;

public class Util {

    public static void validContact(ContactDTO contactDTO) {
        String value = contactDTO.contact();
        ContactType type = null;

        try {
            type = ContactType.valueOf(contactDTO.type().toUpperCase());
        } catch (IllegalArgumentException ex) {
            throw new UnknownParamException(MessageFormat.format(
                    "Unknown type of contact: {0}. Type must be one of {1}", contactDTO.type(), Arrays.toString(ContactType.values())));
        }

        switch (type) {
            case EMAIL:
                if (!isValidEmail(value)) {
                    throw new IllegalArgumentException("Incorrect email: " + value);
                }
                break;
            case PHONE:
                if (!isValidPhone(value)) {
                    throw new IllegalArgumentException("Incorrect phone number: " + value);
                }
        }

    }

    private static boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
        return Pattern.compile(emailRegex).matcher(email).matches();
    }

    private static boolean isValidPhone(String phone) {
        String phoneRegex = "^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$";
        return Pattern.compile(phoneRegex).matcher(phone).matches();
    }
}
