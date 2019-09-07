package life.majiang.community.service;


import life.majiang.community.dto.PaginationDTO;
import life.majiang.community.dto.QuestionDTO;
import life.majiang.community.mapper.QuestionMapper;
import life.majiang.community.mapper.UserMapper;
import life.majiang.community.model.Question;
import life.majiang.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionMapper questionMapper;

    /**
     * 获取问题列表
     * @param page  分页起始页
     * @param size  每页多少条数据
     * @return
     */
    public PaginationDTO getQuesttionList(Integer page, Integer size) {
        // 分页数据处理
        Integer offset = size * (page-1);
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        // 获取所有问题列表
        List<Question> questionsList=questionMapper.select(offset,size);
        // 把问题列表放到pagedto对象
        PaginationDTO pageDTO = new PaginationDTO();

        // 根据每个问题获取具体的发布人信息，然后封装到一个dto中
        for (Question question : questionsList) {
            Integer id = question.getCreator();
            User user=userMapper.findById(id);
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        // 所有数据数量
        Integer count = questionMapper.count();
        // 把数据封装到PageDTO中
        pageDTO.setQuestionsDTOList(questionDTOList);
        pageDTO.setPageination(count,page,size);
        return pageDTO;
    }
}
