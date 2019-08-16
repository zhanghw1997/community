package life.majiang.community.provider;


import com.alibaba.fastjson.JSONObject;
import life.majiang.community.dto.AccessTokenDTO;
import life.majiang.community.dto.GithubUser;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * github的服务提供者
 */
@Slf4j
@Component
public class GithubProvider {

    @Value("${github.access.token.uri}")
    private String accessTokenUrl;

    @Value("${github.user.uri}")
    private String userUrl;

    public String getAccessToken(AccessTokenDTO accessTokenDTO){

         MediaType mediaType = MediaType.get("application/json; charset=utf-8");
         OkHttpClient client = new OkHttpClient();
         RequestBody body = RequestBody.create(mediaType, JSONObject.toJSONString(accessTokenDTO));
         Request request = new Request.Builder()
                                        .url(accessTokenUrl)
                                        .post(body)
                                         .build();
        try(Response response=client.newCall(request).execute()){
            String string = response.body().string();
            log.info("获取到的Token和Type字符串:"+string);
            String[] split = string.split("&");
            String[] splitstr = split[0].split("=");
            String token = splitstr[1];
            return token;
        }catch (IOException e){
            e.printStackTrace();
        }


        return null;
    }

    public GithubUser getUser(String accessToken){
        OkHttpClient client = new OkHttpClient();


            Request request = new Request.Builder()
                    .url(userUrl+accessToken)
                    .build();

            try (Response response = client.newCall(request).execute()) {
                String result = response.body().string();
                log.info(result);
                GithubUser githubUser = JSONObject.parseObject(result, GithubUser.class);
                return githubUser;
            }catch (IOException e){
                e.printStackTrace();
            }
        return null;
    }
}
