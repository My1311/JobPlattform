package de.hbrs.se2.model.company;

import de.hbrs.se2.model.common.BaseEntity;
import de.hbrs.se2.model.user.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(schema = "public")
public class Company extends BaseEntity {

    @Column(nullable = false)
    private String name = ""; // die dürfen dür die db nicht leer sein
    //image kommt noch
    @Column(nullable = false)
    private String description = "";
    @Column(nullable = false)
    private int phoneNumber;



    public Company(String name, String description, int phoneNumber) {
        this.name = name;
        this.description = description;
        this.phoneNumber = phoneNumber;
    }

    public Company() { //anscheinend wird immer ein Default-Konstruktor benötigt

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }



}
