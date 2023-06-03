package cn.tedu.coolku.mapper;

import cn.tedu.coolku.pojo.vo.UserVO;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    UserVO selectByUserName(String userName);
}
