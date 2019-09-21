package com.woniu.api;

import com.woniu.entity.Comment;
import com.woniu.entity.User;
import com.woniu.service.CommentService;
import com.woniu.service.LikeService;
import com.woniu.util.Page;
import com.woniu.util.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("comment")
public class CommentAPI {
    @Resource
    private CommentService commentService;
    @Resource
    private LikeService likeService;
    /**
     * 分页查询评论信息；
     * @return
     * @throws Exception
     */
    @GetMapping
    public Result getComments(Integer pageIndex)throws Exception{
        /*不传参默认为第一页*/
        if(null==pageIndex||"".equals(pageIndex)){
            pageIndex=1;
        }
        int num=5;
        /*查询count*/
        int dataCount = commentService.getCount();
        /*计算总页数*/
        int pageCount=dataCount%num==0?dataCount/num:dataCount/num+1;
        /*查询评论集合*/
        List<Comment> comments = commentService.getComments(pageIndex, num);
        /*封装page对象*/
        Page page=new Page(pageIndex,pageCount,dataCount);
        for (Comment comment : comments) {
            comment.setLikeNum(likeService.getCount(comment.getId()));
        }
        return new Result("success",null,page,comments);
    }

    /**
     * 新增评论信息
     * @param comment
     * @return
     * @throws Exception
     */
    @PostMapping
    public Result insertComment(Comment comment,HttpSession session)throws Exception{
        /*获取当前时间*/
        Date date=new Date();
        /*获取user对象*/
        User user = (User) session.getAttribute("user");
        /*封装数据*/
        comment.setTime(new SimpleDateFormat().format(date));
        comment.setUid(user.getId());
        /*调用新增方法*/
        commentService.insertComment(comment);
        return new Result("success",null,null,null);
    }

}
