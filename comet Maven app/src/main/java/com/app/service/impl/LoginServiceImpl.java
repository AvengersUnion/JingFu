package com.app.service.impl;

import com.app.dao.LoginDao;
import com.app.entity.User;
import com.app.service.LoginService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("loginService")
public class LoginServiceImpl implements LoginService {

    @Resource
    LoginDao loginDao;


    public List<User> getData() {
        return loginDao.getData();
    }
}
