package com.mmall.controller.portal;

import com.mmall.VO.CartVo;
import com.mmall.common.Const;
import com.mmall.common.ResponseCode;
import com.mmall.common.ServerResponse;
import com.mmall.pojo.User;
import com.mmall.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * ${DESCRIPTION}
 *
 * @author yangningkai
 * @create 2019-01-05 下午11:10
 **/
@Controller
@RequestMapping(value = "/card/")
public class CardController {
    @Autowired
    CardService cardService;

    @RequestMapping(value = "list.do", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<CartVo> list(HttpSession session) {
        //判断是否登录
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }
        //查询购物车产品列表
        CartVo cartVo = cardService.list(user.getId());
        return ServerResponse.createBySuccess(cartVo);
    }
}
