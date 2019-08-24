package life.majiang.community.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private Integer id;
    private String name;
    private String accountId;
    private String token;
    private String userImage;
    private Long createTime;
    private Long updateTime;

}
