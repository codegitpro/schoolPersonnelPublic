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


import com.cl.dao.FenpeibangongshiDao;
import com.cl.entity.FenpeibangongshiEntity;
import com.cl.service.FenpeibangongshiService;
import com.cl.entity.view.FenpeibangongshiView;

@Service("fenpeibangongshiService")
public class FenpeibangongshiServiceImpl extends ServiceImpl<FenpeibangongshiDao, FenpeibangongshiEntity> implements FenpeibangongshiService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<FenpeibangongshiEntity> page = this.selectPage(
                new Query<FenpeibangongshiEntity>(params).getPage(),
                new EntityWrapper<FenpeibangongshiEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<FenpeibangongshiEntity> wrapper) {
		  Page<FenpeibangongshiView> page =new Query<FenpeibangongshiView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}
    
	@Override
	public List<FenpeibangongshiView> selectListView(Wrapper<FenpeibangongshiEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public FenpeibangongshiView selectView(Wrapper<FenpeibangongshiEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
