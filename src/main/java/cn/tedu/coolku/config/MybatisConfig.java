package cn.tedu.coolku.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("cn.tedu.coolku.mapper")
public class MybatisConfig {
}
