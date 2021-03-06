package com.lhrsite.xshop.vo;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class AuthGroupVO {

    private Integer agid;

    /** 权限组名称 */
    private String agName;

    /** 权限组状态 */
    private Integer agStatus;

    /** 所属企业 */
    private String epShortName;

    /** 所属项目 */
    private Integer project;



    /** 创建时间 */
    private Timestamp createTime;

    /** 更新人 */
    private String username;

    private Integer updateUser;

    /** 更新时间 */
    private Timestamp updateTime;




}
