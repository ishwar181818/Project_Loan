package com.adm.servicei;

import com.adm.model.User;

public interface ServiceI {

	public void saveUser(User user);

	public User getUserByUsernameAndPassword(String username, String password);

}
