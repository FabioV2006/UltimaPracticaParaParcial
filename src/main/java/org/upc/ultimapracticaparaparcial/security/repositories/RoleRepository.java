package org.upc.ultimapracticaparaparcial.security.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.upc.ultimapracticaparaparcial.security.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
