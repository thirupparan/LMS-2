package com.invicta.lms.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.invicta.lms.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	User findUserById(Long id);

	User findByEmail(String email);

	Optional<User> findByUserNameOrEmail(String username, String email);

	List<User> findByIdIn(List<Long> userIds);

//	Optional<User> findByUserName(String username);

	Boolean existsByUserName(String username);

	Boolean existsByEmail(String email);

	@Modifying
	@Query("UPDATE User u set u.password =:password WHERE u.id = :id")
	void updatePassword(@Param("password") String password, @Param("id") Long userId);

	@Query("SELECT count(userName) FROM User u WHERE u.id <> ?1 AND u.userName= ?2")
	Integer checkWithLockUserId(Long id, String username);

	@Query("SELECT count(userName) FROM User u WHERE u.id <> ?1 AND u.email= ?2")
	Integer checkWithEmailLockUserId(Long id, String email);
}
