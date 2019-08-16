package life.majiang.community.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
/**
 * login  同户名
 * id 用户唯一表示
 * bio
 */
public class GithubUser {

    private String login;
    private Long id;
    private String avatar_url;
    private String bio;
}
