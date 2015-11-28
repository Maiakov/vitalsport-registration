package com.vitalsport.registration.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "account")
public class Account {
    @Id
    private String email;

    private String password;
}
