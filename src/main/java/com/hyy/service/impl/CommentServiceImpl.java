package com.hyy.service.impl;

import com.hyy.mapper.CommentMapper;
import com.hyy.po.Comment;
import com.hyy.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @PROJECT_NAME: blog
 * @PACKAGE_NAME: com.hyy.service.impl
 * @CLASS_NAME: CommentServiceImpl
 * @USER: hongyaoyao
 * @DATETIME: 2023/6/20 16:56
 * @Emial: 1299694047@qq.com
 */
@Service
@Transactional
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    // 只有两级评论，即最上层父级评论parent和所有派生出的子级评论reply
    @Override
    public List<Comment> getCommentsByBlogId(Long blogId) {
        // 获得最上层没有父级评论的评论,即parent
        List<Comment> comments = commentMapper.getCommentsByBlogIdAndParentCommentNull(blogId);
        if (comments.size() != 0){
            // 将所有从该评论parent派生出去的所有评论作reply为其子评论
            combineChildren(comments);
        }
        return comments;
    }

    private void combineChildren(List<Comment> comments){
        for(Comment comment : comments){
            // 获取最上层父级评论parent的下一层评论replys1
            List<Comment> replys1 = commentMapper.getReplyComment(comment.getId());
            // 递归找出reply1和所有其派生出的评论，并且请其作为最上层评论parent的子评论
            if(replys1.size() != 0){
                for (Comment reply1 : replys1){
                    recursively(reply1, comment.getNickname());
                }
                // 将从parent评论派生出的评论全部作为其子评论加入其replyComments中
                comment.setReplyComments(tempReplys);
                // 为了下次使用，重新置空
                tempReplys = new ArrayList<>();
            }
        }
    }

    //存放迭代找出的所有子代评论的集合
    private List<Comment> tempReplys = new ArrayList<>();

    /**
     * 递归迭代，剥洋葱
     * @param comment 被迭代的对象
     */
    private void recursively(Comment comment, String parentNickname){
        comment.setParentNickname(parentNickname);
        tempReplys.add(comment);
        List<Comment> replys = commentMapper.getReplyComment(comment.getId());
        if (replys.size() > 0){
            for (Comment reply : replys){
                recursively(reply, comment.getNickname());
            }
        }
    }

    @Override
    public int saveComment(Comment comment) {
        Long parentCommentId = comment.getParentCommentId();
        if(parentCommentId == -1){
            comment.setParentCommentId(null);
        }
        comment.setCreateTime(new Date());
        // 如果是管理员，则头像肯定存在
        if(comment.getAvatar() == null){
            // 根据评论用户的邮箱计算出哈希值，再根据哈希值在头像目录中选择一张作为头像，这样每个用户只要邮箱一致，头像就会一致
            int code = comment.getEmail().hashCode() % 10;
            comment.setAvatar("/images/header_pic/pic_"+ Math.abs(code) +".jpg");
        }
        return commentMapper.saveComment(comment);
    }

    @Override
    public Integer getCommentsCount() {
        return commentMapper.getCommentsCount();
    }
}
