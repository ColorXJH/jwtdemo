package jwtdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import jwtdemo.entity.SysUser;

/**
 * @author xiajinhui
 * 2020年6月30日
 */
//继承了jpa的接口可以不用书写@Repository
//因为这些jpa接口都继承了Repository接口，Repository接口是一个空接口，凡是继承它的都会被IOC容器识别为一个RepositoryBean
//容纳到ioc容器中
public interface MyRepository extends JpaRepository<SysUser,String>{
   @Query(value="select password from scott.db_user where username=?1",nativeQuery = true)
   String findByName(String username);
}
