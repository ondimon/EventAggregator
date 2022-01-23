package ru.gb.telegrambot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.telegrambot.domain.User;
import ru.gb.telegrambot.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService  {

    private final UserRepository userRepository;

    public void saveUser(User user) {
        userRepository.save(user);
    }
    public List<User>  findAll() {
        return userRepository.findAll();
    }

    public Optional<User> findByChatId(Long id) {
        return userRepository.findByTelegramId(id);
    }

    public void delete(User user) {
        userRepository.delete(user);
    }

}
