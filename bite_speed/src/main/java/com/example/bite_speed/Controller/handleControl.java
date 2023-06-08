package com.example.bite_speed.Controller;

import java.sql.*;
import java.time.ZonedDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.bite_speed.dao.ContactDao;
import com.example.bite_speed.dao.EmailDao;
import com.example.bite_speed.dao.PhoneDao;
import com.example.bite_speed.dto.Contact;
import com.example.bite_speed.dto.Request;

@RestController
public class handleControl {
    @Autowired
    public ContactDao contactDao;
    @Autowired
    public PhoneDao phoneDao;
    @Autowired
    public EmailDao emailDao;

    @PostMapping(path = "/identify")
    public Request handlRequest(@RequestBody Request input){
        String phoneNumber = input.getPhoneNumber();
        String email = input.getEmail();
        
        if(!phoneDao.existsById(phoneNumber) && !emailDao.existsById(email)){
            Contact contact = new Contact();
            contact.setPhoneNumber(phoneNumber);
            contact.setEmail(email);
            contact.setLinkedId(null);
            contact.setLinkPrecedence("primary");
            contact.setCreatedAt(ZonedDateTime.now());
            contact.setDeletedAt(null);
            contact.setUpdatedAt(ZonedDateTime.now());
            contactDao.save(contact);
        }

        return input;
    }
}
