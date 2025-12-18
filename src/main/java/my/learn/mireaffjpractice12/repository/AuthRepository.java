package my.learn.mireaffjpractice12.repository;

import my.learn.mireaffjpractice12.entity.UserAuth;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AuthRepository extends CrudRepository<UserAuth, Long> {

    Optional<UserAuth> findByUsername(String username);

}
