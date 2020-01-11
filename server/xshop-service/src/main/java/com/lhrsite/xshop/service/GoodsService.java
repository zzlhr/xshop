package com.lhrsite.xshop.service;

import com.lhrsite.xshop.po.GoodsCategory;
import com.lhrsite.xshop.vo.GoodsListVO;
import com.lhrsite.xshop.vo.PageVO;

import java.util.List;

public interface GoodsService {


    /**
     * 查询分类
     * @param fid 分类父级id，查询顶级菜单可传0
     * @param status 状态，所有状态传null或者不传
     * @return 分类列表
     */
    List<GoodsCategory> getGoodsCategory(Integer fid, Integer status);

    /**
     * 保存分类
     * @param goodsCategory 分类对象，如果添加不传id
     * @return 保存后的分类对象
     */
    GoodsCategory saveGoodsCategory(GoodsCategory goodsCategory);


    /**
     * 商品列表
     * @param goodsKeyword 商品关键字
     * @param goodsCategoryId 分类id
     * @param page 页码
     * @param pageSize 每页数量
     * @return 商品列表
     */
    PageVO<GoodsListVO> getGoodsList(String goodsKeyword, Integer goodsCategoryId, Integer page, Integer pageSize);









}
