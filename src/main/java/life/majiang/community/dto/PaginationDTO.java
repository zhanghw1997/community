package life.majiang.community.dto;


import life.majiang.community.model.Question;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaginationDTO {

    private List<QuestionDTO> questionsDTOList;

    private Boolean showPrevious;

    private Boolean showFirstPage;

    private Boolean showNext;

    private Boolean showEndPage;

    private Integer currentPage;

    private List<Integer> pages = new ArrayList<>();

    private Integer count;

    public void setPageination(Integer count, Integer page, Integer size) {
        //计算总页数，分为除尽和除不尽
        int countPage = 0;
        if(count % size == 0){
            countPage = count/size;
        }else {
            countPage = count/size + 1;
        }
        // 设置页码
        pages.add(page);
        for (int i = 1; i<= 3; i++){
            if (page - i > 0){
                pages.add(page - i,0);
            }
            if(page + i > count){
                pages.add(page + i);
            }
        }


        // 是否展示首页
        if(page == 1){
            showPrevious = false;
        }else {
            showPrevious = true;
        }
        // 是否展示尾页
        if(page == countPage){
            showNext = false;
        }else {
            showNext = true;
        }

        if(pages.contains(1)){
            showFirstPage = false;
        }else {
            showFirstPage = true;
        }

        if (pages.contains(countPage)){
            showEndPage = false;
        }else {
            showEndPage = true;
        }
    }
}
