package jwtdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import jwtdemo.entity.SysUser;

/**
 * @author xiajinhui
 * 2020年6月30日
 */
@Repository
public interface MyRepository2 extends JpaRepository<SysUser,String>{
	 @Query(value="select password from scott.db_user where username=?1",nativeQuery = true)
	 String findByName2(String username);
}
