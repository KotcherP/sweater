package com.example.sweater.repos;

        import com.example.sweater.domain.User;
        import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> { //для поиска юзеров в RegistrationController
    User findByUsername(String username);//специальное название для метода,описание на сайте spring.io
}