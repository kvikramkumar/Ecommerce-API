package com.ecommerce.app.service;

import com.ecommerce.app.dto.LoginRequest;
import com.ecommerce.app.dto.UserDTO;
import com.ecommerce.app.model.User;
import com.ecommerce.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserDTO userDTO;

    public void createUser(User user){

        String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(hashedPassword);
        userRepository.save(user);
    }

    public boolean validateUser(LoginRequest loginRequest){

        User user = userRepository.findByEmail(loginRequest.getEmail());

        if(user!=null){
            String pwd = loginRequest.getPassword();

            if(BCrypt.checkpw(pwd,user.getPassword())){
                return true;
            }
        }
        return false;
    }

    public UserDTO getUserDetails(Long userID){
        User user = userRepository.findById(userID).orElse(null);

        if(user == null)
            return null;

        userDTO.setUserID(user.getId());
        userDTO.setUserName(user.getUsername());
        userDTO.setEmail(user.getEmail());
        userDTO.setRole(user.getRole());

        return userDTO;

    }

    public boolean updateUserDetails(User user) {

        User tempUser = userRepository.findById(user.getId()).orElse(null);
        if(tempUser != null)
        {
            tempUser.setUsername(user.getUsername());
            tempUser.setEmail(user.getEmail());

            // Only update and hash the password if a new one is provided
            if (user.getPassword() != null && !user.getPassword().isEmpty()) {
                String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
                tempUser.setPassword(hashedPassword);
            }

            // Save the updated user object
            userRepository.save(tempUser);
            return true;
        }
        else {
            return false;
        }
    }
}
