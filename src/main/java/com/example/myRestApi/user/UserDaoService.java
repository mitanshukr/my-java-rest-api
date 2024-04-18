package com.example.myRestApi.user;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

@Service
public class UserDaoService {

    private static List<User> users = new ArrayList<>();
    private static int genId = 0;

    static {
        users.add(new User(++genId, "John Doe", "john@example.com", LocalDate.now().minusYears(35)));
        users.add(new User(++genId, "Rashmi Arora", "rashmi@example.com", LocalDate.now().minusYears(28)));
        users.add(new User(++genId, "Kapil Sharma", "kapil@example.com", LocalDate.now().minusYears(26)));
    }

    public List<User> findAll() {
        return users;
    }

    public User findOne(int id) {
        Predicate<? super User> predicate = (user) -> user.getId() == id;
        return users.stream().filter(predicate).findFirst().orElse(null);
    }

    public boolean create(User user) {
        user.setId(++genId);
        return users.add(user);
    }

    public boolean deleteOne(int id) {
        return users.removeIf(user -> user.getId() == id);
    }
}

