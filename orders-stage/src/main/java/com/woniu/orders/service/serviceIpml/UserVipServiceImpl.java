package com.woniu.orders.service.serviceIpml;

import com.woniu.orders.entity.*;
import com.woniu.orders.mapper.auto.UserVipPOMapper;
import com.woniu.orders.service.UserVipService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: movie_online
 * @description:
 * @author: liutao
 * @create: 2019-09-24 10:44
 **/
@Service
public class UserVipServiceImpl implements UserVipService {
    @Resource
    private UserVipPOMapper userVipPOMapper;
    /**
     * 根据消费金额返回vipid
     * @param vipList
     * @param consumption
     * @return
     */
    @Override
    public int getVipId(List<VipPo> vipList, Double consumption) {
        //vipList是根据额度升序查出来
        for (int i = vipList.size() - 1; i >= 0; i--) {
            //倒叙判断，如果消费大于最大的返回vipId
            if (consumption >= vipList.get(i).getQuota()) {
               if(i==vipList.size() - 1){
                   return vipList.get(i).getId();
               }else {
                   return vipList.get(i+1).getId();
               }

            }
        }
        //如果比最小的还小则返回最小的
        return vipList.get(0).getId();
    }

    @Override
    public List<UserVipPO> select(Integer uid, Integer aid) {
        UserVipPOExample  userVipPOExample = new UserVipPOExample();
        userVipPOExample.createCriteria().andUidEqualTo(uid).andAidEqualTo(aid);
        List<UserVipPO> userVipPOS = userVipPOMapper.selectByExample(userVipPOExample);
        return userVipPOS;

    }
}
