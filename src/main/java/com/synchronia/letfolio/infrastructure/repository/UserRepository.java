package com.synchronia.letfolio.infrastructure.repository;
import com.synchronia.letfolio.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
