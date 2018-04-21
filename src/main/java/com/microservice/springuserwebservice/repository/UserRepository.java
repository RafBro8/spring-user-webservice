package com.microservice.springuserwebservice.repository;

import com.microservice.springuserwebservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {    //User is Entity to manager, Integer is Primary Key


}
