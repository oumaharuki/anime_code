package com.anime.test_client.service;

import com.anime.api.test.Default;
import com.anime.framework.domain.test.DefaultModel;
import com.anime.framework.model.response.CommonCode;
import com.anime.framework.model.response.QueryResponseResult;
import com.anime.framework.model.response.QueryResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author
 * @date 2019-11-15
 */
@Service
public class TestService {

    public QueryResponseResult findList(int page, int size, DefaultModel def) {
        if(def==null){
            def=new DefaultModel();
        }
        ExampleMatcher matching = ExampleMatcher.matching();
        DefaultModel defaultModel = new DefaultModel();

        defaultModel.setId(def.getId());
        if(page<=0){
            page=1;
        }
        page=page-1;
        if(size<=0){
            size=10;
        }

        List<DefaultModel> defs=new ArrayList<>();
        defs.add(defaultModel);

        QueryResult queryResult = new QueryResult();
        queryResult.setList(defs);
        queryResult.setTotal(defs.size());
        QueryResponseResult queryResponseResult = new QueryResponseResult(CommonCode.SUCCESS,queryResult);
        return queryResponseResult;
    }
}
