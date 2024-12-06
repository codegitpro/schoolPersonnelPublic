package com.cl.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cl.utils.PageUtils;
import com.cl.utils.Query;


import com.cl.dao.BangongshiDao;
import com.cl.entity.BangongshiEntity;
import com.cl.service.BangongshiService;
import com.cl.entity.view.BangongshiView;

@Service("bangongshiService")
public class BangongshiServiceImpl extends ServiceImpl<BangongshiDao, BangongshiEntity> implements BangongshiService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<BangongshiEntity> page = this.selectPage(
                new Query<BangongshiEntity>(params).getPage(),
                new EntityWrapper<BangongshiEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<BangongshiEntity> wrapper) {
		  Page<BangongshiView> page =new Query<BangongshiView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}
    
	@Override
	public List<BangongshiView> selectListView(Wrapper<BangongshiEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public BangongshiView selectView(Wrapper<BangongshiEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
