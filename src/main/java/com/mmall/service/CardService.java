package com.mmall.service;

import com.mmall.VO.CartVo;

/**
 * ${DESCRIPTION}
 *
 * @author yangningkai
 * @create 2019-01-05 下午11:29
 **/
public interface CardService {
    /**
     * 获取购物车产品列表
     * @param id
     */
    CartVo list(Integer id);
}
