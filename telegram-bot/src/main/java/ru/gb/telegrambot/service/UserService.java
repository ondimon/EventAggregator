package ru.gb.telegrambot.service;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gb.telegrambot.domain.User;
import ru.gb.telegrambot.repository.UserRepository;

@Getter
@Setter
//@RequiredArgsConstructor
@Service
public class UserService  {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }


}
