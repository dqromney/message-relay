package com.dqr.messagerelay.controllers;

import com.dqr.messagerelay.dto.UserInfo;
import com.dqr.messagerelay.models.User;
import com.dqr.messagerelay.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RepositoryRestController
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> addUser(@RequestBody UserInfo userInfo) {

        User user = userService.addUser(userInfo);

        Resource<User> userAsResource = new Resource<>(user);
        return ResponseEntity.ok(userAsResource);
    }

}
