package com.mmall.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.mmall.common.Const;
import com.mmall.common.ServerResponse;
import com.mmall.dao.ShippingMapper;
import com.mmall.dao.UserMapper;
import com.mmall.pojo.Shipping;
import com.mmall.pojo.User;
import com.mmall.service.ShippingService;
import com.mmall.service.UserService;
import com.mmall.utils.MD5Util;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * ${DESCRIPTION}
 *
 * @author yangningkai
 * @create 2019-01-03 上午10:53
 **/
@Service("ShippingService")
public class ShippingServiceImpl implements ShippingService {
    @Autowired
    ShippingMapper shippingMapper;

    @Override
    public ServerResponse newAdd(Integer userId, Shipping shipping) {
        //新增地址
        shipping.setUserId(userId);
        int count = shippingMapper.insert(shipping);
        //判定是否成功
        if (count > 0) {
            Map result = Maps.newHashMap();
            result.put("shipingId", shipping.getId());
            return ServerResponse.createBySuccess("新建地址成功", result);
        }
        return ServerResponse.createByErrorMessage("新增地址失败");
    }

    @Override
    public ServerResponse newList(Integer userId, int pageNum, int pageSize) {
        //分页查询
        PageHelper.startPage(pageNum, pageSize);
        List<Shipping> shippingList = shippingMapper.selectByUserId(userId);
        PageInfo pageInfo = new PageInfo(shippingList);
        return ServerResponse.createBySuccess("查询成功", pageInfo);
    }

    @Override
    public ServerResponse newDelete(Integer userId, Shipping shippingId) {

        int resultCount = shippingMapper.deleteByShippingIdUserId(userId,shippingId);
        if(resultCount > 0){
            return ServerResponse.createBySuccess("删除地址成功");
        }
        return ServerResponse.createByErrorMessage("删除地址失败");
    }
}
