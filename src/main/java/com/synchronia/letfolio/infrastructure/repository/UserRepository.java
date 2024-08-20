package com.synchronia.letfolio.infrastructure.repository;
import com.synchronia.letfolio.domain.entity.Role;
import com.synchronia.letfolio.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

}
