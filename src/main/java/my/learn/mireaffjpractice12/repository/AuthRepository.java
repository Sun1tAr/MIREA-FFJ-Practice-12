package my.learn.mireaffjpractice12.repository;

import my.learn.mireaffjpractice12.entity.UserAuth;
import org.springframework.data.repository.CrudRepository;

public interface AuthRepository extends CrudRepository<UserAuth, Long> {

    UserAuth findByUsername(String username);

}
