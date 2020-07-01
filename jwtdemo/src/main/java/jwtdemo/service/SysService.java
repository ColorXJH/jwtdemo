package jwtdemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jwtdemo.dao.SysDao;
import jwtdemo.repository.MyRepository;
import jwtdemo.repository.MyRepository2;

/**
 * @author xiajinhui
 * 2020年6月29日
 */
@Service
public class SysService {
	@Autowired
	private SysDao DAO;
	@Autowired
	private MyRepository repository;
	@Autowired
	private MyRepository2 repository2;
	public  String getPasswordFormDataBase(String username) {
		return DAO.getPasswordFromDB(username);
	};
	
	public String findByName(String username) {
		return repository.findByName(username);
	};
	public String findByName2(String username) {
		return repository2.findByName2(username);
	};
}
