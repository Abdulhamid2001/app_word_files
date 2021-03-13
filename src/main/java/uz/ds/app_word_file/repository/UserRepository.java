package uz.ds.app_word_file.repository;




import org.springframework.data.jpa.repository.JpaRepository;
import uz.ds.app_word_file.entity.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User,UUID> {
    Optional<User> findByPhoneNumber(String phoneNumber);

}
