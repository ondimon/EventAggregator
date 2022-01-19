package ru.gb.telegrambot.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


//@Entity
@Getter
@Setter
@Table(name = "users")
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "chat_id")
    private Long telegramId;

    @Column(name = "date_registration")
    private String dateRegistration;

    @Column(name = "site_id")
    private Long siteId;

    @Column(name = "user_name")
    private String userName;

}
