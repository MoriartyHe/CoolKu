package cn.tedu.coolku.controller;

import cn.tedu.coolku.mapper.UserMapper;
import cn.tedu.coolku.pojo.dto.UserLoginDTO;
import cn.tedu.coolku.response.ResultVO;

import cn.tedu.coolku.response.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/")
public class UserController {
    @Autowired
    AuthenticationManager manager;
    @RequestMapping("/login")
    public ResultVO login(@RequestBody UserLoginDTO userLoginDTO){
        System.out.println(userLoginDTO.getUserName());
        System.out.println(userLoginDTO.getPassword());
        Authentication result=manager.authenticate(new UsernamePasswordAuthenticationToken(
                userLoginDTO.getUserName(),
                userLoginDTO.getPassword()
        ));
        SecurityContextHolder.getContext().setAuthentication(result);
        System.out.println("#############"+result.getPrincipal());
        return new ResultVO(StatusCode.SUCCESS,result.getPrincipal());
    }
}
