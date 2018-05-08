package top.sillyfan.security.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.sillyfan.security.domain.model.User;
import top.sillyfan.security.domain.model.UserExample;
import top.sillyfan.security.domain.repository.UserMapper;
import top.sillyfan.security.service.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public User getByName(String username) {

        UserExample userExample = new UserExample();
        userExample.createCriteria().andUsernameEqualTo(username);

        List<User> users = userMapper.selectByExample(userExample);
        return users.isEmpty() ? null : users.get(0);
    }
}
