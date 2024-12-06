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

import com.cl.entity.FenpeibangongshiEntity;
import com.cl.entity.view.FenpeibangongshiView;

import com.cl.service.FenpeibangongshiService;
import com.cl.service.TokenService;
import com.cl.utils.PageUtils;
import com.cl.utils.R;
import com.cl.utils.MPUtil;
import com.cl.utils.CommonUtil;
import java.io.IOException;

/**
 * 分配办公室
 * 后端接口
 * @author 
 * @email 
 * @date 2024-05-11 23:37:17
 */
@RestController
@RequestMapping("/fenpeibangongshi")
public class FenpeibangongshiController {
    @Autowired
    private FenpeibangongshiService fenpeibangongshiService;



    


    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,FenpeibangongshiEntity fenpeibangongshi,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("jiaoshixinxi")) {
			fenpeibangongshi.setGonghao((String)request.getSession().getAttribute("username"));
		}
        EntityWrapper<FenpeibangongshiEntity> ew = new EntityWrapper<FenpeibangongshiEntity>();

		PageUtils page = fenpeibangongshiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, fenpeibangongshi), params), params));

        return R.ok().put("data", page);
    }
    
    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,FenpeibangongshiEntity fenpeibangongshi, 
		HttpServletRequest request){
        EntityWrapper<FenpeibangongshiEntity> ew = new EntityWrapper<FenpeibangongshiEntity>();

		PageUtils page = fenpeibangongshiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, fenpeibangongshi), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( FenpeibangongshiEntity fenpeibangongshi){
       	EntityWrapper<FenpeibangongshiEntity> ew = new EntityWrapper<FenpeibangongshiEntity>();
      	ew.allEq(MPUtil.allEQMapPre( fenpeibangongshi, "fenpeibangongshi")); 
        return R.ok().put("data", fenpeibangongshiService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(FenpeibangongshiEntity fenpeibangongshi){
        EntityWrapper< FenpeibangongshiEntity> ew = new EntityWrapper< FenpeibangongshiEntity>();
 		ew.allEq(MPUtil.allEQMapPre( fenpeibangongshi, "fenpeibangongshi")); 
		FenpeibangongshiView fenpeibangongshiView =  fenpeibangongshiService.selectView(ew);
		return R.ok("查询分配办公室成功").put("data", fenpeibangongshiView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        FenpeibangongshiEntity fenpeibangongshi = fenpeibangongshiService.selectById(id);
		fenpeibangongshi = fenpeibangongshiService.selectView(new EntityWrapper<FenpeibangongshiEntity>().eq("id", id));
        return R.ok().put("data", fenpeibangongshi);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        FenpeibangongshiEntity fenpeibangongshi = fenpeibangongshiService.selectById(id);
		fenpeibangongshi = fenpeibangongshiService.selectView(new EntityWrapper<FenpeibangongshiEntity>().eq("id", id));
        return R.ok().put("data", fenpeibangongshi);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody FenpeibangongshiEntity fenpeibangongshi, HttpServletRequest request){
    	fenpeibangongshi.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(fenpeibangongshi);
        fenpeibangongshiService.insert(fenpeibangongshi);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody FenpeibangongshiEntity fenpeibangongshi, HttpServletRequest request){
    	fenpeibangongshi.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(fenpeibangongshi);
        fenpeibangongshiService.insert(fenpeibangongshi);
        return R.ok();
    }



    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody FenpeibangongshiEntity fenpeibangongshi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(fenpeibangongshi);
        fenpeibangongshiService.updateById(fenpeibangongshi);//全部更新
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        fenpeibangongshiService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	








}
