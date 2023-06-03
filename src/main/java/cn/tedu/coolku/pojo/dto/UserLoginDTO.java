package cn.tedu.coolku.pojo.dto;

import lombok.Data;

@Data
public class UserLoginDTO {
    private String userName;
    private String password;
    /*验证码,之后补上*/
//    private String code;
}
