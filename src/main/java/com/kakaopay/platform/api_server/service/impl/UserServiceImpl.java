package com.kakaopay.platform.api_server.service.impl;

import com.kakaopay.platform.api_server.exception.UserDuplicationException;
import com.kakaopay.platform.api_server.model.User;
import com.kakaopay.platform.api_server.model.dto.request.UserRequest;
import com.kakaopay.platform.api_server.model.dto.response.TokenInfoResponse;
import com.kakaopay.platform.api_server.model.dto.response.UserResponse;
import com.kakaopay.platform.api_server.repository.UserRepository;
import com.kakaopay.platform.api_server.service.UserService;
import com.kakaopay.platform.api_server.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Objects;

@Service
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserResponse createUser(UserRequest userRequest) {

        User user = userRepository.findByUserId(userRequest.getUserId());

        if (!Objects.isNull(user)) {
            throw new UserDuplicationException();
        }
        user = User.builder()
                .userId(userRequest.getUserId())
                .password(StringUtil.encodingSHA512(userRequest.getPassword()))
                .build();

        userRepository.save(user);

//        TokenInfoResponse tokenInfo = ConnectionAuth.generateAuthKey(pongiftUserId, pongiftClientId, pongiftClientSecret);

        UserResponse userResponse = UserResponse.builder()
                .id(user.getId())
                .userId(user.getUserId())
                .build();

        return userResponse;
    }

    @Override
    public Page<UserResponse> getUserList() {
        Page<User> users = (Page<User>) userRepository.findAll();

        return null;

    }
}
