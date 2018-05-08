package top.sillyfan.security.domain.repository;

import top.sillyfan.dao.mapper.ExampleMapper;
import top.sillyfan.security.domain.model.User;
import top.sillyfan.security.domain.model.UserExample;

public interface UserMapper extends ExampleMapper<UserExample, User, Integer> {
}
