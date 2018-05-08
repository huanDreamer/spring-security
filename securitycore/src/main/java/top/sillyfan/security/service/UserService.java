package top.sillyfan.security.service;

import top.sillyfan.security.domain.model.User;

public interface UserService {

    User getByName(String username);
}
