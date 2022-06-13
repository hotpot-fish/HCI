package com.controller;

import com.biboheart.brick.exception.BhException;
import com.constant.Constant;
import com.entity.Shop;
import com.entity.User;
import com.entity.UserPreference;
import com.service.*;
import com.utils.CallCount.Add;
import com.utils.msgutils.Msg;

import com.utils.msgutils.MsgCode;
import com.utils.msgutils.MsgUtil;
import com.utils.sessionutils.SessionUtil;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;


@RestController
public class ShopController {
    private List<Float> Allshopdis;
    private List<Float> allTagWeigh;
    @Autowired
    private ShopService shopService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserFlavorService userFlavorService;


    @Autowired
    private UserPreferenceService userPreferenceService;
    /**
     * 计算用户和商户距离
     * @param posx 用户当前经度
     * @param posy 用户当前纬度
     * @param shop_id 商户id
     * @return
     */


    @RequestMapping(value = "/caldistance", method = {RequestMethod.POST},produces = {"application/json;charset=UTF-8"})
    public Shop caldistance(double posx,double posy,Long shop_id) {
        Shop shop = shopService.load(shop_id);
        float me_posx = (float)posx;
        float me_posy = (float)posy;
        float shop_posx = shop.getPosx();
        float shop_posy = shop.getPosy();
        float res = Math.abs(shop_posx - me_posx)+Math.abs(shop_posy-me_posy);
        return shop;
    }
    @ResponseBody
    @RequestMapping(value = "/caldistancejson", method = {RequestMethod.POST},produces = {"application/json;charset=UTF-8"})
    public float caldistance(@RequestBody JSONObject param) {
        float posx = Float.parseFloat(param.getString("poxx"));
        float posy = Float.parseFloat(param.getString("poxy"));
        Long shop_id = param.getLong("shop_id");
        Shop shop = shopService.load(shop_id);
        float shop_posx = shop.getPosx();
        float shop_posy = shop.getPosy();
        float res = Math.abs(shop_posx - posx)+Math.abs(shop_posy-posy);
        return res;
    }


    @RequestMapping(value = "/getmindisshop", method = {RequestMethod.POST},produces = {"application/json;charset=UTF-8"})
    public Shop traverseentity(Long userid)throws BhException {
        Allshopdis = new ArrayList<>();
        List<Shop> allshop= shopService.findAll();
        long num = allshop.size();
        long minshopid = -1;
        float mindis = 1000000000.0f;
        User user = userService.load(userid);
        float me_posx =  user.getPosx();
        float me_posy =  user.getPosy();
        for(Shop shop : allshop){
            float shop_posx = shop.getPosx();
            float shop_posy = shop.getPosy();
            float res = Math.abs(shop_posx - me_posx)+Math.abs(shop_posy-me_posy);
            Allshopdis.add(res);
        }
        for(int i = 0 ;i <num  ; i++) {
            float tmp = Allshopdis.get(i);
            if (tmp < mindis) {
                minshopid = i + 1;
                mindis = tmp;
            }
        }
        Shop mindisshop = shopService.load(minshopid);
        user.setlastShopId(minshopid);
        userService.save(user);
        return mindisshop;
    }

    @RequestMapping(value = "/getbestshop", method = {RequestMethod.POST,RequestMethod.GET},produces = {"application/json;charset=UTF-8"})
    public Shop getbestshop(Long userid)throws BhException {
        allTagWeigh = userFlavorService.getTagWeigh(userid);
        float allWeigh = 0.0f;
        float nowWeigh = 0.0f;
        int tagIndex = 0;
        for (int i = 0;i<allTagWeigh.size();i++) {
            allWeigh += allTagWeigh.get(i);
        }
        float random = allWeigh * (float)Math.random();
        for (int i = 0;i<allTagWeigh.size();i++) {
            nowWeigh += allTagWeigh.get(i);
            if(random<nowWeigh) {
                tagIndex = i+1;
                break;
            }
        }
        List<Shop> shopList = new ArrayList<>();
        if(tagIndex == 0) {return null;}
        else if(tagIndex == 1) {
            shopList = shopService.findByShopType(Constant.TAG_1);
        }
        else if(tagIndex == 2) {
            shopList = shopService.findByShopType(Constant.TAG_2);
        }
        else if(tagIndex == 3) {
            shopList = shopService.findByShopType(Constant.TAG_3);
        }
        else if(tagIndex == 4) {
            shopList = shopService.findByShopType(Constant.TAG_4);
        }
        else if(tagIndex == 5) {
            shopList = shopService.findByShopType(Constant.TAG_5);
        }
        else if(tagIndex == 6) {
            shopList = shopService.findByShopType(Constant.TAG_6);
        }
        else if(tagIndex == 7) {
            shopList = shopService.findByShopType(Constant.TAG_7);
        }
        else if(tagIndex == 8) {
            shopList = shopService.findByShopType(Constant.TAG_8);
        }
        else if(tagIndex == 9) {
            shopList = shopService.findByShopType(Constant.TAG_9);
        }
        else if(tagIndex == 10) {
            shopList = shopService.findByShopType(Constant.TAG_10);
        }
        else if(tagIndex == 11) {
            shopList = shopService.findByShopType(Constant.TAG_11);
        }
        else if(tagIndex == 12) {
            shopList = shopService.findByShopType(Constant.TAG_12);
        }
        else if(tagIndex == 13) {
            shopList = shopService.findByShopType(Constant.TAG_13);
        }
        else if(tagIndex == 14) {
            shopList = shopService.findByShopType(Constant.TAG_14);
        }
        else if(tagIndex == 15) {
            shopList = shopService.findByShopType(Constant.TAG_15);
        }
        else if(tagIndex == 16) {
            shopList = shopService.findByShopType(Constant.TAG_16);
        }
        else if(tagIndex == 17) {
            shopList = shopService.findByShopType(Constant.TAG_17);
        }
        else if(tagIndex == 18) {
            shopList = shopService.findByShopType(Constant.TAG_18);
        }
        else if(tagIndex == 19) {
            shopList = shopService.findByShopType(Constant.TAG_19);
        }

        UserPreference userPreference = userPreferenceService.load(userid);

        List<Float> shopDistance = new ArrayList<>();
        List<Float> shopReview = new ArrayList<>();
        List<Float> shopCost = new ArrayList<>();
        User user = userService.load(userid);
        float me_posx =  user.getPosx();
        float me_posy =  user.getPosy();
        for(Shop shop : shopList){
            float shop_posx = shop.getPosx();
            float shop_posy = shop.getPosy();
            float res = Math.abs(shop_posx - me_posx)+Math.abs(shop_posy-me_posy);
            shopDistance.add(res);
            shopReview.add(shop.getReview());
            shopCost.add(shop.getCost());
        }

        float maxScore = -1000000.0f;
        int maxScoreIndex = -1;
        for(int i = 0 ; i<shopList.size() ; i++){
            // >-100分
            float posScore =  - userPreference.getPos_pre() * 100.0f * (float)Math.exp(5 * shopDistance.get(i) - 1);
            if (posScore < -1000.0f) {posScore = -1000.0f;}
            posScore = posScore * ((float) Math.random()/2 + 0.5f);
            if(shopDistance.get(i)<0.2) System.out.println("Shop:" + shopList.get(i));
            // 大约 0-100分
            float costScore = - userPreference.getPrice_pre() * shopCost.get(i) * 2;
            costScore = costScore * ((float) Math.random()/2 + 0.5f);
            // 大约0-100分
            float reviewScore = userPreference.getReview_pre() * (shopReview.get(i)-4) * 50.0f + 50.0f;
           reviewScore = reviewScore * ((float) Math.random()/2 + 0.5f);
            float tmpScore = posScore + costScore + reviewScore;
            System.out.println("PosScore:" + posScore);
            System.out.println("costScore:" + costScore);
            System.out.println("reviewScore:" + reviewScore);
            if (tmpScore>maxScore) {
                maxScore = tmpScore;
                maxScoreIndex = i;
            }
        }

        user.setlastShopId(shopList.get(maxScoreIndex).getShopId());
        userService.save(user);
        return shopList.get(maxScoreIndex);
    }

}
