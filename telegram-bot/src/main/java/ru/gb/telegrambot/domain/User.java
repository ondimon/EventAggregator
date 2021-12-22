package ru.gb.telegrambot.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


//@Entity
@Getter
@Setter
@Table(name = "usr")
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "telegram_id")
    private Long telegram_id;


//    @Column(name = "date_registration")
//    private String date_registration;
//    @Column(name = "site_id")
//    private Long site_id;

}
