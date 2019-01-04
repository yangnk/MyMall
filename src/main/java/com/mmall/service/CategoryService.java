package com.mmall.service;

import com.mmall.common.ServerResponse;
import com.mmall.pojo.Category;
import java.util.List;

/**
 * ${DESCRIPTION}
 *
 * @author yangningkai
 * @create 2019-01-04 下午4:33
 **/
public interface CategoryService {
    /**
     * 添加类别
     * @param categoryName
     * @param parentId
     * @return
     */
    ServerResponse newAddCategory(String categoryName, Integer parentId)throws Exception;
    ServerResponse updateCategoryName(Integer categoryId,String categoryName);
    ServerResponse<List<Category>> getChildrenParallelCategory(Integer categoryId);
    /**
     * 递归查询本节点的id及孩子节点的id
     * @param categoryId
     * @return
     */
    ServerResponse<List<Integer>> selectCategoryAndChildrenById(Integer categoryId);
}
