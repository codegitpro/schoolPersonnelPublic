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

import com.cl.entity.JiaoshixinxiEntity;
import com.cl.entity.view.JiaoshixinxiView;

import com.cl.service.JiaoshixinxiService;
import com.cl.service.TokenService;
import com.cl.utils.PageUtils;
import com.cl.utils.R;
import com.cl.utils.MPUtil;
import com.cl.utils.CommonUtil;
import java.io.IOException;

/**
 * 教师信息
 * 后端接口
 * @author 
 * @email 
 * @date 2024-05-11 23:37:17
 */
@RestController
@RequestMapping("/jiaoshixinxi")
public class JiaoshixinxiController {
    @Autowired
    private JiaoshixinxiService jiaoshixinxiService;



    
	@Autowired
	private TokenService tokenService;
	
	/**
	 * 登录
	 */
	@IgnoreAuth
	@RequestMapping(value = "/login")
	public R login(String username, String password, String captcha, HttpServletRequest request) {
		JiaoshixinxiEntity u = jiaoshixinxiService.selectOne(new EntityWrapper<JiaoshixinxiEntity>().eq("gonghao", username));
        if(u==null || !u.getMima().equals(password)) {
            return R.error("账号或密码不正确");
        }
		String token = tokenService.generateToken(u.getId(), username,"jiaoshixinxi",  "教师信息" );
		return R.ok().put("token", token);
	}


	
	/**
     * 注册
     */
	@IgnoreAuth
    @RequestMapping("/register")
    public R register(@RequestBody JiaoshixinxiEntity jiaoshixinxi){
    	//ValidatorUtils.validateEntity(jiaoshixinxi);
    	JiaoshixinxiEntity u = jiaoshixinxiService.selectOne(new EntityWrapper<JiaoshixinxiEntity>().eq("gonghao", jiaoshixinxi.getGonghao()));
		if(u!=null) {
			return R.error("注册用户已存在");
		}
		Long uId = new Date().getTime();
		jiaoshixinxi.setId(uId);
        jiaoshixinxiService.insert(jiaoshixinxi);
        return R.ok();
    }

	
	/**
	 * 退出
	 */
	@RequestMapping("/logout")
	public R logout(HttpServletRequest request) {
		request.getSession().invalidate();
		return R.ok("退出成功");
	}
	
	/**
     * 获取用户的session用户信息
     */
    @RequestMapping("/session")
    public R getCurrUser(HttpServletRequest request){
    	Long id = (Long)request.getSession().getAttribute("userId");
        JiaoshixinxiEntity u = jiaoshixinxiService.selectById(id);
        return R.ok().put("data", u);
    }
    
    /**
     * 密码重置
     */
    @IgnoreAuth
	@RequestMapping(value = "/resetPass")
    public R resetPass(String username, HttpServletRequest request){
    	JiaoshixinxiEntity u = jiaoshixinxiService.selectOne(new EntityWrapper<JiaoshixinxiEntity>().eq("gonghao", username));
    	if(u==null) {
    		return R.error("账号不存在");
    	}
        u.setMima("123456");
        jiaoshixinxiService.updateById(u);
        return R.ok("密码已重置为：123456");
    }


    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,JiaoshixinxiEntity jiaoshixinxi,
		HttpServletRequest request){
        EntityWrapper<JiaoshixinxiEntity> ew = new EntityWrapper<JiaoshixinxiEntity>();

		PageUtils page = jiaoshixinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, jiaoshixinxi), params), params));

        return R.ok().put("data", page);
    }
    
    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,JiaoshixinxiEntity jiaoshixinxi, 
		HttpServletRequest request){
        EntityWrapper<JiaoshixinxiEntity> ew = new EntityWrapper<JiaoshixinxiEntity>();

		PageUtils page = jiaoshixinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, jiaoshixinxi), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( JiaoshixinxiEntity jiaoshixinxi){
       	EntityWrapper<JiaoshixinxiEntity> ew = new EntityWrapper<JiaoshixinxiEntity>();
      	ew.allEq(MPUtil.allEQMapPre( jiaoshixinxi, "jiaoshixinxi")); 
        return R.ok().put("data", jiaoshixinxiService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(JiaoshixinxiEntity jiaoshixinxi){
        EntityWrapper< JiaoshixinxiEntity> ew = new EntityWrapper< JiaoshixinxiEntity>();
 		ew.allEq(MPUtil.allEQMapPre( jiaoshixinxi, "jiaoshixinxi")); 
		JiaoshixinxiView jiaoshixinxiView =  jiaoshixinxiService.selectView(ew);
		return R.ok("查询教师信息成功").put("data", jiaoshixinxiView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        JiaoshixinxiEntity jiaoshixinxi = jiaoshixinxiService.selectById(id);
		jiaoshixinxi = jiaoshixinxiService.selectView(new EntityWrapper<JiaoshixinxiEntity>().eq("id", id));
        return R.ok().put("data", jiaoshixinxi);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        JiaoshixinxiEntity jiaoshixinxi = jiaoshixinxiService.selectById(id);
		jiaoshixinxi = jiaoshixinxiService.selectView(new EntityWrapper<JiaoshixinxiEntity>().eq("id", id));
        return R.ok().put("data", jiaoshixinxi);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody JiaoshixinxiEntity jiaoshixinxi, HttpServletRequest request){
        if(jiaoshixinxiService.selectCount(new EntityWrapper<JiaoshixinxiEntity>().eq("gonghao", jiaoshixinxi.getGonghao()))>0) {
            return R.error("工号已存在");
        }
    	jiaoshixinxi.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(jiaoshixinxi);
    	JiaoshixinxiEntity u = jiaoshixinxiService.selectOne(new EntityWrapper<JiaoshixinxiEntity>().eq("gonghao", jiaoshixinxi.getGonghao()));
		if(u!=null) {
			return R.error("用户已存在");
		}
		jiaoshixinxi.setId(new Date().getTime());
        jiaoshixinxiService.insert(jiaoshixinxi);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody JiaoshixinxiEntity jiaoshixinxi, HttpServletRequest request){
        if(jiaoshixinxiService.selectCount(new EntityWrapper<JiaoshixinxiEntity>().eq("gonghao", jiaoshixinxi.getGonghao()))>0) {
            return R.error("工号已存在");
        }
    	jiaoshixinxi.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(jiaoshixinxi);
    	JiaoshixinxiEntity u = jiaoshixinxiService.selectOne(new EntityWrapper<JiaoshixinxiEntity>().eq("gonghao", jiaoshixinxi.getGonghao()));
		if(u!=null) {
			return R.error("用户已存在");
		}
		jiaoshixinxi.setId(new Date().getTime());
        jiaoshixinxiService.insert(jiaoshixinxi);
        return R.ok();
    }



    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody JiaoshixinxiEntity jiaoshixinxi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(jiaoshixinxi);
        jiaoshixinxiService.updateById(jiaoshixinxi);//全部更新
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        jiaoshixinxiService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	








}
