package jwtdemo.dao;

import org.apache.ibatis.annotations.Param;

/**
 * @author xiajinhui
 * 2020年6月29日
 */
public interface SysDao {
	
	public String getPasswordFromDB(@Param("username")String username);
}
