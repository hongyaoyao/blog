package com.hyy.service;

import com.github.pagehelper.PageInfo;
import com.hyy.po.Blog;

import java.util.List;
import java.util.Map;

/**
 * @PROJECT_NAME: blog
 * @PACKAGE_NAME: com.hyy.service
 * @CLASS_NAME: BlogService
 * @USER: hongyaoyao
 * @DATETIME: 2023/6/15 16:09
 * @Emial: 1299694047@qq.com
 */
public interface BlogService {

    PageInfo<Blog> getAllBlogs(int pageNum, int pageSize);

    int saveBlogAndTags(Blog blog);

    Blog getBlogById(Long id);

    int updateBlog(Blog blog);

    int deleteBlog(Long id);

    PageInfo<Blog> searchAllBlogs(Blog blog, int pageNum, int pageSize);






    PageInfo<Blog> getIndexBlogs(int pageNum, int pageSize);

    List<Blog> getAllRecommendBlogs(Integer top);

    List<Blog> getHostBlogs(Integer top);

    PageInfo<Blog> getSearchBlogs(String query, int pageNum, int pageSize);

    Blog getDetailedBlog(Long id);

    PageInfo<Blog> getBlogsByTypeId(Long typeId, int pageNum, int pageSize);

    PageInfo<Blog> getBlogsByTagId(Long tagId, int pageNum, int pageSize);

    Map<String, List<Blog>> archiveBlogs();

    List<Blog> getNewBlogs(Integer topN);

    Integer getBlogsCount();

    List<Integer> getBlogsViews();
}
