package com.dqr.messagerelay.services;

import com.dqr.messagerelay.repository.UserRepository;
import com.dqr.messagerelay.dto.UserInfo;
import com.dqr.messagerelay.models.User;
import com.dqr.messagerelay.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Slf4j
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Add User if not exists and return user object.
     *
     * @param userInfo the {@link UserInfo} object
     * @return the populated {@link User} object
     */
    public User addUser(UserInfo userInfo) {
        validateNewUser(userInfo);

        // If user does not exist in system, create new user record
        User user = userRepository.findByEmail(userInfo.getEmail());
        if (user == null) {
            user = createNewUser(userInfo);
        }
        return user;
    }

    /**
     * Create a new user.
     *
     * @param userInfo the populated {@Link UserInfo} object
     * @return a persisted {@Link User} object.
     */
    public User createNewUser(UserInfo userInfo) {

        User user = new User();
        user.setFirstName(userInfo.getFirstName());
        user.setLastName(userInfo.getLastName());
        user.setEmail(userInfo.getEmail());
        user.setUsername(userInfo.getUsername());
        user.setActive(userInfo.getActive());

        // Save user to database and return saved version
        return userRepository.save(user);
    }

    // ----------------------------------------------------------------
    // Helper methods
    // ----------------------------------------------------------------
    private void validateNewUser(UserInfo dto) {
        String emailRegex = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern emailPattern = Pattern.compile(emailRegex);

        new Validator()
            .required(dto.getFirstName(), "firstName", "field.blank")
            .required(dto.getLastName(), "lastName", "field.blank")
            .required(dto.getEmail(), "email", "field.blank")
            .required(dto.getUsername(), "username", "field.blank")
            .required(dto.getActive(), "active", "field.blank")
            .validate(dto.getEmail(), e -> emailPattern.matcher(e).matches(), "email", "newuser.email.invalid")
            .finish();
    }

}