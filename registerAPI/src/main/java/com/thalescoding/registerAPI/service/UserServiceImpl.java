package com.thalescoding.registerAPI.service;
import com.thalescoding.registerAPI.model.Role;
import com.thalescoding.registerAPI.model.User;
import com.thalescoding.registerAPI.repository.RoleRepository;
import com.thalescoding.registerAPI.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service @Transactional
public class UserServiceImpl implements UserServiceInterface{

    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public ResponseEntity<Object> getUserById(Long id) {
        if(userRepository.existsById(id)){
            Optional<User> userOpt = userRepository.findById(id);
            User user = new User();
            user.setId(userOpt.get().getId());
            user.setLogin(userOpt.get().getLogin());
            user.setPassword(userOpt.get().getPassword());
            user.setName(userOpt.get().getName());
            user.setPhones(userOpt.get().getPhones());
            user.setRoles(userOpt.get().getRoles());
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>("Resource not found. Try another id.", HttpStatus.NOT_FOUND);
    }

    @Override
    public User saveUser(User user) {

        //Precisamos associar o user aos telefones pq o Spring não está referenciando no banco
        for(int i = 0; i < user.getPhones().size(); i++){
            user.getPhones().get(i).setUser(user);
        }
        return userRepository.save(user);
    }

    @Override
    public ResponseEntity<Object> updateUser(User user) {
        if(userRepository.existsById(user.getId())){
            //Precisamos associar o user aos telefones pq o Spring não está referenciando no banco
            for(int i = 0; i < user.getPhones().size(); i++){
                user.getPhones().get(i).setUser(user);
            }
            User savedUser = userRepository.save(user);
            return new ResponseEntity<>(savedUser, HttpStatus.OK);
        };
        return new ResponseEntity<>("We've got a problem. Try latter again...", HttpStatus.NOT_FOUND) ;
    }

    @Override
    public ResponseEntity<String> deleteUser(Long id) {
        if(userRepository.existsById(id)){
             userRepository.deleteById(id);
            return new ResponseEntity<>("User Deleted Successfully!", HttpStatus.OK);
        }
        return new ResponseEntity<>("We've got a problem. Try latter again...", HttpStatus.NOT_FOUND) ;
    }

    @Override
    public User getUser(String userLogin) {
        return userRepository.findUserByLogin(userLogin);
    }

    @Override
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String userLogin, String roleName) {
        User user = userRepository.findUserByLogin(userLogin);
        Role role = roleRepository.findByRoleName(roleName);
        user.getRoles().add(role);

    }

    //    @Override
//    public Optional<User> getUserById(Long id) {
//        if(userRepository.existsById(id)){
//            return userRepository.findById(id);
//        }
//        return Optional.of(new User());
//    }

}
