package com.fiap.challenge.targetcustomer.service;

import com.fiap.challenge.targetcustomer.model.Usuario;
import com.fiap.challenge.targetcustomer.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Usuario> usuarioOptional = usuarioRepository.findByEmail(username);

        if (usuarioOptional.isPresent()) {
            var usuario = usuarioOptional.get();
            return User.builder()
                    .username(usuario.getEmail())
                    .password(usuario.getPassword())
                    .build();
        }else{
            throw new UsernameNotFoundException(username);
        }

    }
}
