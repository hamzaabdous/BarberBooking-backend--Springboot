package org.example.authservice.repositories;


import org.example.authservice.helpers.RefreshableCRUDRepository;
import org.example.authservice.models.UserInfo;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends RefreshableCRUDRepository<UserInfo, Long> {

   public UserInfo findByUsername(String username);
   UserInfo findFirstById(Long id);

}
