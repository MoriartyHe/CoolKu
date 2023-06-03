package cn.tedu.coolku.pojo.vo;

import lombok.Data;

@Data
public class UserVO {
    private Long id;
    private String userName;
    private String password;
    private Integer firstLogin;
    private Integer roleId;
    private String roleName;
    private String roleDesc;
    private String roleUrlPrefix;
}
