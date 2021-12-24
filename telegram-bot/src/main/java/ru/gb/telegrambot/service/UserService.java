package ru.gb.telegrambot.service;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gb.telegrambot.domain.User;
import ru.gb.telegrambot.repos.UserRepository;

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


//    @Override
//    @Transactional
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = (User) findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(String.format("User '%s' not found", username)));
//        return new org.springframework.security.core.userdetails.User(user.getId(),user.getTelegramId()));
//    }
//
//    private Optional<Object> findByUsername(String username) {
//        return userRepo.findBy(username);
//    }

}
