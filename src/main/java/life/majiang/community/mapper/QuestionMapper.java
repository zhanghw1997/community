package life.majiang.community.mapper;

import life.majiang.community.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface QuestionMapper {

        @Insert("insert into question (title,description,create_time,update_time,creator,tag) values (#{title},#{description},#{createTime},#{updateTime},#{creator},#{tag})")
        void create(Question question);
}
