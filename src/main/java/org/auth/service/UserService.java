package org.auth.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.auth.persistence.entity.UserEntity;
import org.auth.persistence.repository.UserRepository;

import java.util.List;

@ApplicationScoped
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public void save(UserEntity user){
        userRepository.persist(user);
    }

    public List<UserEntity> getAll(){
        return userRepository.findAll().list();
    }
}
