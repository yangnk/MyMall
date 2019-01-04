package com.mmall.service;

import com.github.pagehelper.PageInfo;
import com.mmall.common.ServerResponse;
import com.mmall.pojo.Category;
import com.mmall.pojo.Product;

import java.util.List;

/**
 * ${DESCRIPTION}
 *
 * @author yangningkai
 * @create 2019-01-04 下午4:33
 **/
public interface ProductService {

    ServerResponse<PageInfo> getProductList(int pageNum, int pageSize);

    ServerResponse saveOrUpdateProduct(Product product);

    /**
     * 查询商品详情
     * @param productId
     * @return
     */
    ServerResponse manageProductDetail(Integer productId);
}
