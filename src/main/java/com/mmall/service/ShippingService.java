package com.mmall.service;

import com.mmall.common.ServerResponse;
import com.mmall.pojo.Shipping;

/**
 * ${DESCRIPTION}
 *
 * @author yangningkai
 * @create 2019-01-05 下午11:29
 **/
public interface ShippingService {
    /**
     * 新增地址
     * @param userId
     * @param shipping
     * @return
     */
    ServerResponse newAdd(Integer userId, Shipping shipping);

    /**
     * 分页查询地址列表
     * @param userId
     * @param pageNum
     * @param pageSize
     * @return
     */
    ServerResponse newList(Integer userId, int pageNum, int pageSize);

    /**
     * 删除地址
     * @param userId
     * @param shipping
     * @return
     */
    ServerResponse newDelete(Integer userId, Shipping shipping);
}
