package life.majiang.community.controller;


import life.majiang.community.mapper.UserMapper;
import life.majiang.community.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
@Slf4j
public class IndexController {

    @Autowired
    private UserMapper userMapper;

    @GetMapping(value = "/")
    public String index(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if (cookies == null){
            return "index";
        }
        for (Cookie cookie : cookies) {
            if(cookie.getName().equals("token")){
                String token = cookie.getValue();
                User user = userMapper.findByToken(token);
                log.info("通过token查找到的用户信息"+user);
                if(user != null){
                    request.getSession().setAttribute("user",user);
                    log.info(user.getName()+"登录成功");
                }
                break;
            }
        }
        return "index";
    }
}
