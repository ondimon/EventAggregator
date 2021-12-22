package ru.gb.telegrambot.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gb.telegrambot.domain.User;
import ru.gb.telegrambot.repos.UserRepo;

@Getter
@Setter
@RequiredArgsConstructor
@Service
public class UserService {

    private UserRepo userRepo;

    @Autowired
    public void setUserRepo(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public void saveUser(User user) {
        userRepo.save(user);
    }


}
