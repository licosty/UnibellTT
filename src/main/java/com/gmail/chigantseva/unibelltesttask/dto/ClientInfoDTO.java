package com.gmail.chigantseva.unibelltesttask.dto;

import java.util.List;

public record ClientInfoDTO (Integer id, String name, List<ContactDTO> contacts){}
