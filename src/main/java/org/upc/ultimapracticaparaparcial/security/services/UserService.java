package org.upc.ultimapracticaparaparcial.security.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.upc.ultimapracticaparaparcial.security.entities.Role;
import org.upc.ultimapracticaparaparcial.security.entities.User;
import org.upc.ultimapracticaparaparcial.security.repositories.RoleRepository;
import org.upc.ultimapracticaparaparcial.security.repositories.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Transactional
    public void save(User user) {
        userRepository.save(user);
    }

    @Transactional
    public void grabar(Role role) {
        roleRepository.save(role);
    }
    public Integer insertUserRol(Long user_id, Long rol_id) {
        Integer result = 0;
        userRepository.insertUserRol(user_id, rol_id);
        return 1;
    }

}
