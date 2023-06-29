package com.hyy.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @PROJECT_NAME: blog
 * @PACKAGE_NAME: com.hyy.po
 * @CLASS_NAME: User
 * @USER: hongyaoyao
 * @DATETIME: 2023/6/13 14:07
 * @Emial: 1299694047@qq.com
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    // 用户Id
    private Long id;
    // 用户昵称
    private String nickname;
    // 登录用户名
    private String username;
    // 登录密码
    private String password;
    // 用户邮箱
    private String email;
    // 用户头像
    private String avatar;
    // 用户是否管理员
    private Integer type;
    // 用户创建日期
    private Date createTime;
    // 用户更新日期
    private Date updateTime;
}
