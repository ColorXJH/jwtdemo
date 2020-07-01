package jwtdemo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * @author xiajinhui
 * 2020年6月29日
 */
@Data
@Entity
@Table(name="db_user")
public class SysUser {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
	private String username;
	private String password;
}
