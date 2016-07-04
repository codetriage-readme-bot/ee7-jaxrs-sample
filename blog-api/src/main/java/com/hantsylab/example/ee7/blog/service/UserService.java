package com.hantsylab.example.ee7.blog.service;

import com.hantsylab.example.ee7.blog.DTOUtils;
import com.hantsylab.example.ee7.blog.domain.model.User;
import com.hantsylab.example.ee7.blog.domain.repository.UserRepository;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 *
 * @author hantsy
 */
@ApplicationScoped
public class UserService {

    @Inject
    UserRepository users;

    public UserDetail findUserById(Long id) {
        User user = fetchUserById(id);

        return DTOUtils.map(user, UserDetail.class);
    }

    public UserDetail findUserByUsername(String username) {
        User user = users.findByUsername(username);
        if (user == null) {
            throw new ResourceNotFoundException("username:" + username + " was not found");
        }

        return DTOUtils.map(user, UserDetail.class);
    }

    public boolean usernameExists(String username) {
        User user = users.findByUsername(username);
        return user != null;
    }

    public List<UserDetail> findByKeyword(String q) {
        List<User> userlist = users.findByKeyword(q);

        return DTOUtils.mapList(userlist, UserDetail.class);
    }

    public UserDetail createUser(UserForm form) {
        final String username = form.getUsername();
        if (usernameExists(username)) {
            throw new UsernameWasTakenException("username:" + username + " was taken.");
        }

        User user = DTOUtils.map(form, User.class);
        User saved = users.save(user);

        return DTOUtils.map(saved, UserDetail.class);
    }

    public UserDetail updateUser(Long id, UserForm form) {
        User user = fetchUserById(id);

        DTOUtils.mapTo(form, user);

        User saved = users.save(user);

        return DTOUtils.map(saved, UserDetail.class);
    }

    public void deleteUserById(Long id) {
        User user = fetchUserById(id);
        users.delete(user);
    }

    private User fetchUserById(Long id) throws ResourceNotFoundException {
        User user = users.findById(id);
        if (user == null) {
            throw new ResourceNotFoundException("user:" + id + " was not found");
        }
        return user;
    }

}