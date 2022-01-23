package ru.gb.telegrambot.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
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
    private LocalDateTime dateRegistration;

    @Column(name = "site_id")
    private Long siteId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "date_last_notification")
    private LocalDateTime dateLastNotification;

    public User(Long telegramId) {
        this.telegramId = telegramId;
    }

    public boolean isNew() {
        return id == null;
    }
}
