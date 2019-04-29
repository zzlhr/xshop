package com.lhrsite.xshop.api.controller;

import com.lhrsite.xshop.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 分类控制器
 */
@RestController
@RequestMapping("/claasify")
public class ClassifyController {

    private ResultVO resultVO;

    @Autowired
    public ClassifyController() {
        resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setMsg("ok");
    }

    /**
     * 获取分类列表
     *
     * @return 分类列表
     */
    @GetMapping("/classifyList")
    public ResultVO classifyList() {
        return resultVO;
    }
}