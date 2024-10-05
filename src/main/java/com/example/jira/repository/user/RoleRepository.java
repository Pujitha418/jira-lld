package com.example.jira.repository.user;

import com.example.jira.models.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {


    @Query(value = "select * from role where role = :roleName", nativeQuery = true)
    Optional<Role> findByMName(@Param("roleName") String roleName);
}
