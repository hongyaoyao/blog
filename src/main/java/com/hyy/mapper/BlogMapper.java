package com.hyy.mapper;

import com.hyy.po.Blog;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @PROJECT_NAME: blog
 * @PACKAGE_NAME: com.hyy.mapper
 * @CLASS_NAME: BlogMapper
 * @USER: hongyaoyao
 * @DATETIME: 2023/6/14 13:04
 * @Emial: 1299694047@qq.com
 */
@Mapper
@Repository
public interface BlogMapper {

    // 管理端显示博客列表
    List<Blog> getAllBlogs();

    // 管理端新增博客
    @Insert("INSERT INTO t_blog(title, content, first_picture, flag, views, appreciation, share_statement, commentabled, published, recommend, create_time, update_time, type_id, user_id, tag_ids, description) " +
            "VALUES(#{blog.title}, #{blog.content}, #{blog.firstPicture}, #{blog.flag}, #{blog.views}, #{blog.appreciation}, #{blog.shareStatement}, #{blog.commentabled}, #{blog.published}, #{blog.recommend}, #{blog.createTime}, #{blog.updateTime}, #{blog.typeId}, #{blog.userId}, #{blog.tagIds}, #{blog.description})")
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    // @Options注解中useGeneratedKeys=true表示使用数据库主键自增，keyColumn="id"表示使用blog对象的id属性来接受返回的主键
    int saveBlog(@Param("blog") Blog blog);

    // 管理端编辑更新博客时根据id先查询博客
    @Select("SELECT id, flag, title, content, first_picture, description, recommend, published, share_statement, appreciation, commentabled, type_id, tag_ids " +
            "FROM t_blog " +
            "WHERE id = #{id}")
    Blog getBlogById(Long id);

    // 管理端编辑更新博客
    @Update("UPDATE t_blog " +
            "SET published = #{blog.published}, flag = #{blog.flag},title = #{blog.title},content = #{blog.content}, type_id = #{blog.typeId}, tag_ids = #{blog.tagIds}, first_picture = #{blog.firstPicture}, description = #{blog.description}, recommend = #{blog.recommend}, share_statement = #{blog.shareStatement}, appreciation = #{blog.appreciation}, commentabled = #{blog.commentabled}, update_time = #{blog.updateTime} " +
            "WHERE id = #{blog.id}")
    int updateBlog(@Param("blog") Blog blog);

    // 管理端删除博客
    @Delete("DELETE " +
            "FROM t_blog " +
            "where id = #{id}")
    int deleteBlog(Long id);

    // 管理端搜索博客
    List<Blog> searchAllBlogs(Blog blog);





    // 客户端首页博客列表
    List<Blog> getIndexBlogs();

    // 客户端首页最新推荐博客
    @Select("SELECT id, title " +
            "FROM t_blog " +
            "WHERE recommend = 1 AND published = 1 " +
            "ORDER BY update_time DESC " +
            "LIMIT #{top}")
    List<Blog> getAllRecommendBlogs(Integer top);

    // 客户端首页点击排行榜博客
    @Select("SELECT id, title " +
            "FROM t_blog " +
            "WHERE published = 1 " +
            "ORDER BY views DESC " +
            "LIMIT #{top}")
    List<Blog> getHostBlogs(Integer top);

    // 客户端首页搜索博客，只能支持内容、标题的模糊搜索
    List<Blog> getSearchBlogs(String query);

    // 客户端点击博客详情就会更新博客浏览量
    @Update("UPDATE t_blog " +
            "SET views = views + 1 " +
            "WHERE id = #{id}")
    int updateBlogViews(Long id);

    // 客户端博客详情
    Blog getDetailedBlog(Long id);


    // 客户端分类页面获取博客
    List<Blog> getBlogsByTypeId(Long id);

   // 客户端标签页面获取博客
    List<Blog> getBlogsByTagId(Long typeId);

    // 客户端归档页获取博客年份
    @Select("SELECT DATE_FORMAT(b.update_time, '%Y') AS year " +
            "FROM t_blog AS b " +
            "WHERE b.published = 1 " +
            "GROUP BY year " +
            "ORDER BY b.update_time DESC")
    List<String> getBlogsYear();

    // 客户端归档页根据年份获取博客
    @Select("SELECT b.id, b.title, b.update_time, b.flag " +
            "FROM t_blog AS b " +
            "WHERE published = 1 AND DATE_FORMAT(b.update_time, '%Y') = #{year}")
    List<Blog> findBlogsByYear(String year);

    // 客户端页面底部的最新博客
    @Select("SELECT b.id, b.title " +
            "FROM t_blog AS b " +
            "WHERE published = 1 " +
            "ORDER BY b.update_time DESC " +
            "LIMIT #{topN}")
    List<Blog> getNewBlogs(Integer topN);

    // 客户端页面底部的博客统计数量
    @Select("SELECT count(*) " +
            "FROM t_blog as b " +
            "WHERE published = 1")
    Integer getBlogsCount();

    // 客户端页面底部的浏览量统计数量
    @Select("SELECT views " +
            "FROM t_blog as b " +
            "WHERE published = 1")
    List<Integer> getBlogsViews();
}
