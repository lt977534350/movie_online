package com.woniu.api;

import com.woniu.entity.Like;
import com.woniu.myutil.myeneity.User;
import com.woniu.service.LikeService;
import com.woniu.util.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("like")
public class LikeAPI {
    @Resource
    private LikeService likeService;

    /**
     * 根据评论id查询评论点赞总数
     * @param cid
     * @return
     * @throws Exception
     */
    @GetMapping("/{cid}")
    public Result getCount(@PathVariable Integer cid)throws Exception{
        return new Result("success",null,likeService.getCount(cid),null);
    }

    /**
     * 查询用户点赞信息;
     * @param session
     * @return
     * @throws Exception
     */
    @GetMapping
    public Result getLikes(HttpSession session)throws Exception{
        User user = (User) session.getAttribute("user");
        if(user==null){
            return new Result("fail",null,null,null);
        }
        return new Result("success",null,null,likeService.getLikes(user.getId()));
    }

    /**
     * 新增点赞信息;
     * @return
     * @throws Exception
     */
    @PostMapping("/{cid}")
    public Result insertLike(@PathVariable("cid") Integer cid,HttpSession session)throws Exception{
        User user = (User) session.getAttribute("user");
        if(user==null){
            return new Result("error","请登录后操作",null,null);
        }
        likeService.insertList(new Like(user.getId(),cid));
        return new Result("success",null,null,null);
    }

    /**
     * 删除点赞信息;
     * @param cid
     * @return
     * @throws Exception
     */
    @DeleteMapping("delete/{cid}")
    public Result deleteLike(@PathVariable Integer cid,HttpSession session)throws Exception{
        User user = (User) session.getAttribute("user");
        likeService.deleteLike(user.getId(),cid);
        return new Result("success",null,null,null);
    }

}
