package com.ieti.taskplanner.services.impl;

import com.ieti.taskplanner.model.Task;
import com.ieti.taskplanner.model.User;
import com.ieti.taskplanner.services.UserService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author Sergio
 */
@Service
public class UserServiceImpl implements UserService{
    
    HashMap<String,User> usersMap = new HashMap<>();

    @Override
    public List<User> getUsersList() {
        List<User> users; users = new ArrayList<User>(usersMap.values());
        return users;
    }

    @Override
    public User getUserById(String userId) {
        User user = usersMap.get(userId);
        return user;
    }

    @Override
    public User createUser(String userId, String name, String email, String password) {
        User user = new User(userId,name,email,password);
        usersMap.put(userId,user);
        return user;

    }

    @Override
    public User updateUser(String userId, String name, String email, String password) {
        User user = getUserById(userId);
        user.setId(userId);
        user.setFullname(name);
        user.setEmail(email);
        user.setPassword(password);
        usersMap.replace(user.getId(),user);
        return user;
    }

    @Override
    public void removeUser(String userId) {
        usersMap.remove(userId);
    }
}
