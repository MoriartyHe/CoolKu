package cn.tedu.coolku.security;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
/*@Data会让当前类添加构造方法,此方法可能会和父类的冲突所以报错
因为继承的类中有构造方法*/

@Getter
public class CustomUserDetails extends User {
    private Long id;
    private Integer firstLogin;
    private Integer roleId;
    private String roleName;
    private String roleDesc;
    private String roleUrlPrefix;

    /*可直接重写所有的方法*/
    /*但是代码太多*/
    /*这里采用继承一个实现类*/

    public CustomUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities, Long id, Integer firstLogin, Integer roleId, String roleName, String roleDesc, String roleUrlPrefix) {
        super(username, password, authorities);
        this.id = id;
        this.firstLogin = firstLogin;
        this.roleId = roleId;
        this.roleName = roleName;
        this.roleDesc = roleDesc;
        this.roleUrlPrefix = roleUrlPrefix;
    }
}
