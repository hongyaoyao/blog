package com.hyy.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hyy.mapper.BlogAndTagMapper;
import com.hyy.mapper.BlogMapper;
import com.hyy.mapper.CommentMapper;
import com.hyy.mapper.TagMapper;
import com.hyy.po.Blog;
import com.hyy.service.BlogService;
import com.hyy.utils.MarkDownUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @PROJECT_NAME: blog
 * @PACKAGE_NAME: com.hyy.service.impl
 * @CLASS_NAME: BlogServiceImpl
 * @USER: hongyaoyao
 * @DATETIME: 2023/6/15 16:09
 * @Emial: 1299694047@qq.com
 */
@Service
@Transactional
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogMapper blogMapper;

    @Autowired
    private BlogAndTagMapper blogAndTagMapper;

    @Autowired
    private TagMapper tagMapper;

    @Autowired
    private CommentMapper commentMapper;

    // 管理端首页博客列表,分页展示
    @Override
    public PageInfo<Blog> getAllBlogs(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(blogMapper.getAllBlogs());
    }

    // 管理端新增博客，同时将其拥有的标签加入t_blog_tag表中
    @Override
    public int saveBlogAndTags(Blog blog) {
        blog.setCreateTime(new Date());
        blog.setUpdateTime(new Date());
        blog.setViews(0);
        // 先保存博客
        // 必须先保存博客，因为需要其返回的主键
        blogMapper.saveBlog(blog);
        // 如果标签存在，则将对应的标签数据存到t_blog_tag表中
        if(!"".equals(blog.getTagIds())){
            String[] tagIds = blog.getTagIds().split(",");
            Long id = blog.getId();
            for (String tagId : tagIds) {
                blogAndTagMapper.saveBlogAndTag(id, Long.parseLong(tagId));
            }
        }
        return 1;
    }

    // 管理端编辑更新博客时根据id先查询博客显示出来
    @Override
    public Blog getBlogById(Long id) {
        Blog blog = blogMapper.getBlogById(id);
        return blog;
    }

    // 管理端编辑更新博客，同时更改其拥有的标签
    @Override
    public int updateBlog(Blog blog) {
        blog.setUpdateTime(new Date());
        // 保存博客
        blogMapper.updateBlog(blog);
        Long id = blog.getId();
        // 更新博客后先删除博客在t_blog_tag表中的标签记录，然后再往t_blog_tag添加新的博客标签记录，不然更新博客可能会删除一些标签
        blogAndTagMapper.deleteBlogAndTag(id);
        // 如果标签存在，则将对应的标签数据存到t_blog_tag表中
        if(!"".equals(blog.getTagIds())){
            String[] tagIds = blog.getTagIds().split(",");
            for (String tagId : tagIds) {
                blogAndTagMapper.saveBlogAndTag(id, Long.parseLong(tagId));
            }
        }

        return 1;
    }

    // 管理端删除博客，同时删除掉其对应的标签记录
    @Override
    public int deleteBlog(Long id) {
        // 删除博客的同时要删除对应的标签记录和其所属的评论
        commentMapper.deleteCommentByBlogId(id);
        blogAndTagMapper.deleteBlogAndTag(id);
        return blogMapper.deleteBlog(id);
    }

    // 管理端搜索博客，分页展示
    @Override
    public PageInfo<Blog> searchAllBlogs(Blog blog, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(blogMapper.searchAllBlogs(blog));
    }







    // 客户端首页博客列表,分页展示
    @Override
    public PageInfo<Blog> getIndexBlogs(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Blog> blogs = blogMapper.getIndexBlogs();
        // 为获取的博客设置标签
        for(Blog blog : blogs){
            blog.setTags(tagMapper.getTagsByBlogId(blog.getId()));
        }
        return new PageInfo<>(blogs);
    }

    // 客户端首页最新推荐博客
    @Override
    public List<Blog> getAllRecommendBlogs(Integer top) {
        return blogMapper.getAllRecommendBlogs(top);
    }

    // 客户端首页点击排行榜博客
    @Override
    public List<Blog> getHostBlogs(Integer top) {
        return blogMapper.getHostBlogs(top);
    }

    // 客户端首页搜索博客，只能支持内容、标题的模糊搜索
    @Override
    public PageInfo<Blog> getSearchBlogs(String query, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Blog> blogs = blogMapper.getSearchBlogs(query);
        // 为获取的博客设置标签
        for(Blog blog : blogs){
            blog.setTags(tagMapper.getTagsByBlogId(blog.getId()));
        }
        return new PageInfo<>(blogs);
    }

    // 客户端博客详情
    @Override
    public Blog getDetailedBlog(Long id) {
        // 增加一次访问量
        blogMapper.updateBlogViews(id);
        // 根据博客Id查找具体博客
        Blog blog = blogMapper.getDetailedBlog(id);
        // 再填充其拥有的标签
        blog.setTags(tagMapper.getTagsByBlogId(id));
        if (blog != null){
            // 将数据库中的MarkDown格式的内容转换成HTML内容
            String str = MarkDownUtils.markdownToHtmlExtensions(blog.getContent());
            blog.setContent(str);
        }
        return blog;
    }

    // 客户端分类页面获取博客，分页展示
    @Override
    public PageInfo<Blog> getBlogsByTypeId(Long typeId, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Blog> blogs = blogMapper.getBlogsByTypeId(typeId);
        // 为获取的博客设置标签
        for(Blog blog : blogs){
            blog.setTags(tagMapper.getTagsByBlogId(blog.getId()));
        }
        return new PageInfo<>(blogs);
    }

    // 客户端标签页面获取博客，分页展示
    @Override
    public PageInfo<Blog> getBlogsByTagId(Long tagId, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Blog> blogs = blogMapper.getBlogsByTagId(tagId);
        // 为获取的博客设置标签
        for(Blog blog : blogs){
            blog.setTags(tagMapper.getTagsByBlogId(blog.getId()));
        }
        return new PageInfo<>(blogs);
    }

    // 客户端归档页获取博客
    @Override
    public Map<String, List<Blog>> archiveBlogs() {
        // 查出所有博客的年份后
        List<String> years = blogMapper.getBlogsYear();
        // 需要使用有序的HashMap，因为最后展示是有序的
        Map<String, List<Blog>> map = new LinkedHashMap<>();
        for (String year : years) {
            map.put(year, blogMapper.findBlogsByYear(year));
        }
        return map;
    }

    @Override
    public List<Blog> getNewBlogs(Integer topN) {
        return blogMapper.getNewBlogs(topN);
    }

    @Override
    public Integer getBlogsCount() {
        return blogMapper.getBlogsCount();
    }

    @Override
    public List<Integer> getBlogsViews() {
        return blogMapper.getBlogsViews();
    }
}
