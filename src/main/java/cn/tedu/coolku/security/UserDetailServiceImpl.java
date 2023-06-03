package cn.tedu.coolku.security;


import cn.tedu.coolku.mapper.UserMapper;
import cn.tedu.coolku.pojo.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired(required = false)
    UserMapper usermapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserVO userVO = usermapper.selectByUserName(username);
        if (userVO == null) {
            return null;//代表用户名不存在，此时抛出异常
        }
        System.out.println("****************");
        System.out.println("UserVO=" + userVO);
        System.out.println("****************");
        /*因为没有用户id所以不用这个*/
//        UserDetails ud = User.builder()
//                .username(username)
//                .password(userVO.getPassword())
//                .disabled(false)//是否禁用
//                .accountLocked(false)//是否锁定
//                .accountExpired(false)//登录是否过期
//                .credentialsExpired(false)//登录凭证是否过期
//                .authorities("权限")//授权.授权当前登录用户有哪些权限
//                .build();
        String role=userVO.getRoleName();
        List<GrantedAuthority> list = AuthorityUtils.createAuthorityList(role);
        CustomUserDetails cud = new CustomUserDetails(
                userVO.getUserName(),
                userVO.getPassword(),
                list,
                userVO.getId(),
                userVO.getFirstLogin(),
                userVO.getRoleId(),
                userVO.getRoleName(),
                userVO.getRoleDesc(),
                userVO.getRoleUrlPrefix()
        );
        //如果用户输入的密码和数据库中查的密码不一致，则会抛出异常
        return cud;//用户名不存在
    }
}
