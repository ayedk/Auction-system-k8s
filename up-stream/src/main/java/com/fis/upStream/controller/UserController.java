package com.fis.upStream.controller;

import com.fis.upStream.model.Response;
import com.fis.upStream.model.User;
import com.fis.upStream.model.UserInfo;
import com.fis.upStream.repository.UserRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @PostMapping("/signup")
    public Response signUp(@RequestBody User user){
        if(userRepository.findByUsername(user.getUsername())==null){

            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            userRepository.save(user);
            JSONObject entity = new JSONObject();
            entity.put("status","200");
            return new Response(200,"user successfully registered");
        }else {
            JSONObject entity = new JSONObject();
            entity.put("status","404");
            return new Response(404,"wrong username");
        }

    }

    @GetMapping("/info/{usr}")
    public UserInfo info(@PathVariable(value="usr") String usr){
        User user =  userRepository.findByUsername(usr);
        UserInfo userinfo = new UserInfo(user.getId(),user.getUsername(),user.getFirstname(),user.getLastname(),user.getEmail());
        return userinfo;
    }

}