package com.example.testing.service.impl;

import com.example.testing.entity.User;
import com.example.testing.exception.BusinessException;
import com.example.testing.exception.UserNotFoundException;
import com.example.testing.repository.UserRepository;
import com.example.testing.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository repo;

    public User add(User user) {
        return repo.save(user);
    }
    public User update(User user) throws UserNotFoundException {
        if (!repo.existsById(user.getId())) {
            throw new UserNotFoundException();
        }

        return repo.save(user);
    }

    public User get(Long id) throws UserNotFoundException {
        Optional<User> result = repo.findById(id);

        if (result.isPresent()) {
            return result.get();
        }

        throw new UserNotFoundException();
    }

    public List<User> list() {
        return (List<User>) repo.findAll();
    }

    public void delete(Long id) throws UserNotFoundException {
        if (repo.existsById(id)) {
            repo.deleteById(id);
        }

        throw new UserNotFoundException();
    }

    @Override
    public List<User> searchUserByName(String keyword) {

        try {
            List<User> users = repo.searchUser(keyword);
            return users;
        }catch (Exception e){
            throw new BusinessException(e.getMessage());
        }
    }

}
