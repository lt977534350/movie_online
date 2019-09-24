package com.woniu.orders.service.serviceIpml;

import com.woniu.orders.entity.VipPo;
import com.woniu.orders.entity.VipPoExample;
import com.woniu.orders.mapper.auto.VipPoMapper;
import com.woniu.orders.service.VipService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: movie_online
 * @description:
 * @author: liutao
 * @create: 2019-09-24 14:40
 **/
@Service
public class VipServiceImpl implements VipService {
    @Resource
    private VipPoMapper vipPoMapper;
    @Override
    public List<VipPo> selectVipByAid(Integer aid) {
        //查询此影院的VIP
        VipPoExample vipPoExample = new VipPoExample();
        vipPoExample.setOrderByClause("quota asc");
        vipPoExample.createCriteria().andAidEqualTo(aid);
        List<VipPo> vipPos = vipPoMapper.selectByExample(vipPoExample);
        return vipPos;
    }
}
