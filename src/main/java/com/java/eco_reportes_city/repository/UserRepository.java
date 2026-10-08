   package com.java.eco_reportes_city.repository;

   import com.java.eco_reportes_city.entity.User;
   import org.springframework.data.jpa.repository.JpaRepository;

   import java.util.Optional;

   public interface UserRepository extends JpaRepository<User, Long> {

       Optional<User> findByCorreo(String correo);

       boolean existsByCorreo(String correo);
   }