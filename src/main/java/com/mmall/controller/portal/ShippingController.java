package com.mmall.controller.portal;

import com.mmall.common.Const;
import com.mmall.common.ResponseCode;
import com.mmall.common.ServerResponse;
import com.mmall.pojo.Shipping;
import com.mmall.pojo.User;
import com.mmall.service.ShippingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpSession;

/**
 * ${DESCRIPTION}
 *
 * @author yangningkai
 * @create 2019-01-06 下午4:46
 **/
@Controller
@RequestMapping("/shipping")

public class ShippingController {
    @Autowired
    ShippingService shippingService;

    /**
     * 新增地址
     * @param session
     * @param shipping
     * @return
     */
    @RequestMapping(value = "/newAdd.do")
    @ResponseBody
    public ServerResponse newAdd(HttpSession session, Shipping shipping) {
        //用户是否登录
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user ==null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getDesc());
        }
        //新增地址
        return shippingService.newAdd(user.getId(), shipping);
    }

    /**
     * 分页查询地址列表
     * @param session
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("/newList.do")
    @ResponseBody
    public ServerResponse newList(HttpSession session,
                                  @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                  @RequestParam(value = "pageSize", defaultValue = "10")int pageSize){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user ==null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getDesc());
        }
        return shippingService.newList(user.getId(), pageNum, pageSize);
    }

    /**
     * 删除地址
     * @param session
     * @param shipping
     * @return
     */
    @RequestMapping(value = "newDelete.do")
    public ServerResponse newDelete(HttpSession session, Shipping shipping) {
        //用户是否登录
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user ==null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getDesc());
        }
        //修改地址
        return shippingService.newDelete(user.getId(), shipping);
    }
}
