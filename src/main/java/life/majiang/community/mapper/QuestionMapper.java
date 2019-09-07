package life.majiang.community.mapper;

import life.majiang.community.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Mapper
public interface QuestionMapper {

        @Insert("insert into question (title,description,create_time,update_time,creator,tag) values (#{title},#{description},#{createTime},#{updateTime},#{creator},#{tag})")
        void create(Question question);

        @Select("select * from question limit #{offset},#{size}" )
        List<Question> select(@PathVariable(name = "offset") Integer offset, @PathVariable(name = "size") Integer size);


        @Select("select count(1) from question")
        Integer count();
}
