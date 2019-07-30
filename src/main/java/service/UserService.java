package service;

import entity.User;

public interface UserService {
    boolean add(User user);

    User findById(int key);

    User findByEmail(String email);
}
