package br.com.cpa.spring.repositories;

import br.com.cpa.spring.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
