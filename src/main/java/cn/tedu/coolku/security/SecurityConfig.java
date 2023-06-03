package cn.tedu.coolku.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)/*开启方法授权检测*/
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    /*配置密码加密方式*/
    /*将默认的密码编码器*/
    @Bean
    public PasswordEncoder passwordEncoder() {
        /*先进行无加密的*/
        return NoOpPasswordEncoder.getInstance();
//        return new BCryptPasswordEncoder();
        /*用户输入的密码会通过此编码器加密后再和数据库里面的数据比较*/
    }
    /*
    * */
    @Bean/*添加注解的目的是为了在Controller中自动装配*/
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
//        super.configure(http);
        //当需要登录认证才能访问的资源,没有登录时跳转到登录页面
        http.formLogin().loginPage("/login.html");//弹出登录页面
                /*设置白名单*/
        String[] urlsbai = {"/login.html","/loginSuccessTest.html","/user/login"};
        http.authorizeHttpRequests()//对请求进行授权
                .mvcMatchers(urlsbai)//匹配某些路径
                .permitAll()//直接放行,即使不需要登录也可以访问
                .anyRequest()//其它任意请求
                .authenticated();//需要通过登录认证
//        /*设置黑名单(只有这些网址才需要登录认证)*/
//        String[] urlshei= {"/admin.html","/personal.html"};
//        http.authorizeHttpRequests()//对请求进行授权
//                .mvcMatchers(urlshei)//匹配某些路径
//                .authenticated()//需要通过登录认证
//                .anyRequest()//其它任意请求
//                .permitAll();//直接放行,即使不需要登录也可以访问

        /*关闭跨域攻击防御策略,否则所有post请求将失效(post请求缺失标识符)*/
        http.csrf().disable();
    }
}
