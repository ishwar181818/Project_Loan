package com.adm.servicei;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.adm.model.User;

public interface ServiceI {

	public void saveUser(User user);

	public User getUserByUsernameAndPassword(String username, String password);

	public List<User> getAllEmployee();

	public User getUser(int userid);

	public User updateEmployee(int userid, String json, MultipartFile empImage, MultipartFile empPancard);

	public void  delUser(int userid);

}
