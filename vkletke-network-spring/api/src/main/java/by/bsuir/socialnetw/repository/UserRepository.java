package by.bsuir.socialnetw.repository;

import by.bsuir.socialnetw.domain.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface UserRepository extends CrudRepository<Role, Long> {

    Set<Role> findAll();

    Role findByName(String name);

}
