package com.app.dao;

import com.app.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoginDao {
    List<User> getData();
}
