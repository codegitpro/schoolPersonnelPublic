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

import com.cl.entity.FenpeisusheEntity;
import com.cl.entity.view.FenpeisusheView;

import com.cl.service.FenpeisusheService;
import com.cl.service.TokenService;
import com.cl.utils.PageUtils;
import com.cl.utils.R;
import com.cl.utils.MPUtil;
import com.cl.utils.CommonUtil;
import java.io.IOException;

/**
 * 分配宿舍
 * 后端接口
 * @author 
 * @email 
 * @date 2024-05-11 23:37:17
 */
@RestController
@RequestMapping("/fenpeisushe")
public class FenpeisusheController {
    @Autowired
    private FenpeisusheService fenpeisusheService;



    


    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,FenpeisusheEntity fenpeisushe,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("xueshengxinxi")) {
			fenpeisushe.setXuehao((String)request.getSession().getAttribute("username"));
		}
        EntityWrapper<FenpeisusheEntity> ew = new EntityWrapper<FenpeisusheEntity>();

		PageUtils page = fenpeisusheService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, fenpeisushe), params), params));

        return R.ok().put("data", page);
    }
    
    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,FenpeisusheEntity fenpeisushe, 
		HttpServletRequest request){
        EntityWrapper<FenpeisusheEntity> ew = new EntityWrapper<FenpeisusheEntity>();

		PageUtils page = fenpeisusheService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, fenpeisushe), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( FenpeisusheEntity fenpeisushe){
       	EntityWrapper<FenpeisusheEntity> ew = new EntityWrapper<FenpeisusheEntity>();
      	ew.allEq(MPUtil.allEQMapPre( fenpeisushe, "fenpeisushe")); 
        return R.ok().put("data", fenpeisusheService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(FenpeisusheEntity fenpeisushe){
        EntityWrapper< FenpeisusheEntity> ew = new EntityWrapper< FenpeisusheEntity>();
 		ew.allEq(MPUtil.allEQMapPre( fenpeisushe, "fenpeisushe")); 
		FenpeisusheView fenpeisusheView =  fenpeisusheService.selectView(ew);
		return R.ok("查询分配宿舍成功").put("data", fenpeisusheView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        FenpeisusheEntity fenpeisushe = fenpeisusheService.selectById(id);
		fenpeisushe = fenpeisusheService.selectView(new EntityWrapper<FenpeisusheEntity>().eq("id", id));
        return R.ok().put("data", fenpeisushe);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        FenpeisusheEntity fenpeisushe = fenpeisusheService.selectById(id);
		fenpeisushe = fenpeisusheService.selectView(new EntityWrapper<FenpeisusheEntity>().eq("id", id));
        return R.ok().put("data", fenpeisushe);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody FenpeisusheEntity fenpeisushe, HttpServletRequest request){
    	fenpeisushe.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(fenpeisushe);
        fenpeisusheService.insert(fenpeisushe);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody FenpeisusheEntity fenpeisushe, HttpServletRequest request){
    	fenpeisushe.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(fenpeisushe);
        fenpeisusheService.insert(fenpeisushe);
        return R.ok();
    }



    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody FenpeisusheEntity fenpeisushe, HttpServletRequest request){
        //ValidatorUtils.validateEntity(fenpeisushe);
        fenpeisusheService.updateById(fenpeisushe);//全部更新
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        fenpeisusheService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	








}
