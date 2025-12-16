package my.learn.mireaffjpractice12.repository;

import my.learn.mireaffjpractice12.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
