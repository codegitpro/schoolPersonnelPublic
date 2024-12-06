package com.cl.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import com.cl.utils.ValidatorUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.cl.annotation.IgnoreAuth;

import com.cl.entity.BangongshiEntity;
import com.cl.entity.view.BangongshiView;

import com.cl.service.BangongshiService;
import com.cl.service.TokenService;
import com.cl.utils.PageUtils;
import com.cl.utils.R;
import com.cl.utils.MPUtil;
import com.cl.utils.CommonUtil;
import java.io.IOException;

/**
 * 办公室
 * 后端接口
 * @author 
 * @email 
 * @date 2024-05-11 23:37:17
 */
@RestController
@RequestMapping("/bangongshi")
public class BangongshiController {
    @Autowired
    private BangongshiService bangongshiService;



    


    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,BangongshiEntity bangongshi,
		HttpServletRequest request){
        EntityWrapper<BangongshiEntity> ew = new EntityWrapper<BangongshiEntity>();

		PageUtils page = bangongshiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, bangongshi), params), params));

        return R.ok().put("data", page);
    }
    
    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,BangongshiEntity bangongshi, 
		HttpServletRequest request){
        EntityWrapper<BangongshiEntity> ew = new EntityWrapper<BangongshiEntity>();

		PageUtils page = bangongshiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, bangongshi), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( BangongshiEntity bangongshi){
       	EntityWrapper<BangongshiEntity> ew = new EntityWrapper<BangongshiEntity>();
      	ew.allEq(MPUtil.allEQMapPre( bangongshi, "bangongshi")); 
        return R.ok().put("data", bangongshiService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(BangongshiEntity bangongshi){
        EntityWrapper< BangongshiEntity> ew = new EntityWrapper< BangongshiEntity>();
 		ew.allEq(MPUtil.allEQMapPre( bangongshi, "bangongshi")); 
		BangongshiView bangongshiView =  bangongshiService.selectView(ew);
		return R.ok("查询办公室成功").put("data", bangongshiView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        BangongshiEntity bangongshi = bangongshiService.selectById(id);
		bangongshi = bangongshiService.selectView(new EntityWrapper<BangongshiEntity>().eq("id", id));
        return R.ok().put("data", bangongshi);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        BangongshiEntity bangongshi = bangongshiService.selectById(id);
		bangongshi = bangongshiService.selectView(new EntityWrapper<BangongshiEntity>().eq("id", id));
        return R.ok().put("data", bangongshi);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody BangongshiEntity bangongshi, HttpServletRequest request){
    	bangongshi.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(bangongshi);
        bangongshiService.insert(bangongshi);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody BangongshiEntity bangongshi, HttpServletRequest request){
    	bangongshi.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(bangongshi);
        bangongshiService.insert(bangongshi);
        return R.ok();
    }



    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody BangongshiEntity bangongshi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(bangongshi);
        bangongshiService.updateById(bangongshi);//全部更新
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        bangongshiService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	








}
