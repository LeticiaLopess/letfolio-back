package com.synchronia.letfolio.infrastructure.repository;
import com.synchronia.letfolio.domain.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
