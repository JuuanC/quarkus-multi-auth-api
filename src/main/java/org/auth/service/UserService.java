package org.auth.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.auth.common.dto.request.LoginRequest;
import org.auth.persistence.entity.UserEntity;
import org.auth.persistence.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public void save(UserEntity user,String traceId){
        userRepository.persist(user);
    }

    @Transactional
    public void updateEmail(String username,  String email, String traceId){
        userRepository.update("email = ?2 where username = ?1", username, email);
    }

    @Transactional
    public void delete(String username, String traceId){
        userRepository.delete("username", username);
    }

    public UserEntity getByUsername(String username, String traceId){
        return userRepository.find("username", username).firstResult();
    }

    public List<UserEntity> getAll(String traceId){
        return userRepository.findAll().list();
    }

    public Optional<UserEntity> getByUsernameAndPassword(LoginRequest loginRequest, String traceId) {
        return userRepository.find("username = ?1 and password = ?2",
                        loginRequest.getUsername(),
                        loginRequest.getPassword())
                .singleResultOptional();
    }

}
