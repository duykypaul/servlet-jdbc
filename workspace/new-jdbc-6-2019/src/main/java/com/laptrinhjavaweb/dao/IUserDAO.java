package com.laptrinhjavaweb.dao;

import com.laptrinhjavaweb.model.UserModel;

public interface IUserDAO extends IGenericDAO<UserModel> {
	UserModel findUserAndPasswordAndStatus(String userName, String password, Integer status);
}
