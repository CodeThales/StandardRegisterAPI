package com.thalescoding.registerAPI.service;

import com.thalescoding.registerAPI.model.User;
import com.thalescoding.registerAPI.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userLogin) throws UsernameNotFoundException {

        //Buscar user no banco
        User user = userRepository.findUserByLogin(userLogin);
        if(user == null){
            throw new UsernameNotFoundException("User not found!");
        }

//        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
//        user.getRoles().forEach(role -> {
//            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
//        });
        //Como eu fiz a classe User implementar a classe userDetails, não preciso do trecho de código acima para pegar as authorities do user
        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), user.getAuthorities());
    }
}
