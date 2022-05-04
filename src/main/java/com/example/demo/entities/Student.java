package com.example.demo.entities;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;
@Entity
@Table(name = "student")
public class Student {
    @Id
    @SequenceGenerator(name = "student_sequence",
                       sequenceName = "student_sequence",
                       allocationSize = 1
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_sequence"
    )

    private long id;
    private String name;
    private LocalDate dateofbirth;
    private Gender gender;
    @Column(unique = true)
    private String emailid; //should be unique
    private String address;
    private Timestamp created_at;
    private Timestamp updated_at;

    public Timestamp getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Timestamp updated_at) {
        this.updated_at = updated_at;
    }

    public Student() {
    }

    public Student(long id, String name, Gender gender, LocalDate dateofbirth, String emailid, String address, Timestamp created_at) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.dateofbirth = dateofbirth;
        this.emailid = emailid;
        this.address = address;
        this.created_at = created_at;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public LocalDate getDateofbirth() {
        return dateofbirth;
    }

    public void setDateofbirth(LocalDate dateofbirth) {
        this.dateofbirth = dateofbirth;
    }

    public String getEmailid() {
        return emailid;
    }

    public void setEmailid(String emailid) {
        this.emailid = emailid;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dateofbirth=" + dateofbirth +
                ", emailid='" + emailid + '\'' +
                ", address='" + address + '\'' +
                ", created_at=" + created_at +
                '}';
    }
}
