package life.majiang.community.controller;


import life.majiang.community.dto.PaginationDTO;
import life.majiang.community.dto.QuestionDTO;
import life.majiang.community.mapper.UserMapper;
import life.majiang.community.model.User;
import life.majiang.community.service.QuestionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@Slf4j
public class IndexController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionService userService;

    @GetMapping(value = "/")
    public String index(HttpServletRequest request, Model model,
                        @RequestParam(name = "page",defaultValue = "1") Integer page,
                        @RequestParam(name = "size",defaultValue = "5") Integer size){
        //获取问题信息表
        PaginationDTO pageination = userService.getQuesttionList(page, size);
        log.info("问题数据"+pageination);
        model.addAttribute("pageination",pageination);
        log.info(pageination.toString());
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
