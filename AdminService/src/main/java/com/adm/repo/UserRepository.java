package com.adm.repo;

import com.adm.count.EmployeeCountDto;
import com.adm.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    
	
	 User findByUsernameAndPassword(String username, String password);
	 
	 @Query("SELECT new com.adm.count.EmployeeCountDto( " +
	           "COUNT(CASE WHEN u.userType = 'OE' THEN 1 END), " +
	           "COUNT(CASE WHEN u.userType = 'CRM' THEN 1 END), " +
	           "COUNT(CASE WHEN u.userType = 'CM' THEN 1 END), " +
	           "COUNT(CASE WHEN u.userType = 'AH' THEN 1 END), " +
	           "COUNT(CASE WHEN u.userType = 'ADMIN' THEN 1 END), " +
	           "COUNT(CASE WHEN u.userType = 'USER' THEN 1 END)) " +
	           "FROM User u")
	    EmployeeCountDto getUserTypeCounts();
}
