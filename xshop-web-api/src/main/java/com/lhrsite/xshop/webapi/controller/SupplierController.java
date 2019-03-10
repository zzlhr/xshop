package com.lhrsite.xshop.webapi.controller;


import com.lhrsite.xshop.vo.ResultVO;
import com.lhrsite.xshop.po.Supplier;
import com.lhrsite.xshop.core.exception.XShopException;
import com.lhrsite.xshop.service.SupplierService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 供货商(StSupplier)表控制层
 *
 * @author lhr
 * @since 2018-09-03 21:56:33
 */
@RestController
@RequestMapping("/supplier")
public class SupplierController {

    private final SupplierService supplierService;
    private ResultVO resultVO;


    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
        resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setMsg("ok");
    }


    @PostMapping("/add")
    public ResultVO add(Supplier supplier) {
        resultVO.setData(supplierService.add(supplier));
        return resultVO;
    }


    @PostMapping("/list")
    public ResultVO list(@RequestParam(defaultValue = "") String supplierName,
                         @RequestParam(defaultValue = "0") Integer status,
                         @RequestParam(defaultValue = "0") Integer del,
                         @RequestParam(defaultValue = "1") long page,
                         @RequestParam(defaultValue = "10") long pageSize) {
        resultVO.setData(supplierService.list(supplierName, status, del, page, pageSize));
        return resultVO;
    }


    @PostMapping("/update")
    public ResultVO update(Supplier supplier) {
        resultVO.setData(supplierService.update(supplier));
        return resultVO;
    }

    @PostMapping("/del")
    public ResultVO del(Integer supplierId) throws XShopException {
        supplierService.del(supplierId);
        return resultVO;
    }


}