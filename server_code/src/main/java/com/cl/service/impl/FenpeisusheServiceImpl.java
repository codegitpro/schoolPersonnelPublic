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


import com.cl.dao.FenpeisusheDao;
import com.cl.entity.FenpeisusheEntity;
import com.cl.service.FenpeisusheService;
import com.cl.entity.view.FenpeisusheView;

@Service("fenpeisusheService")
public class FenpeisusheServiceImpl extends ServiceImpl<FenpeisusheDao, FenpeisusheEntity> implements FenpeisusheService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<FenpeisusheEntity> page = this.selectPage(
                new Query<FenpeisusheEntity>(params).getPage(),
                new EntityWrapper<FenpeisusheEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<FenpeisusheEntity> wrapper) {
		  Page<FenpeisusheView> page =new Query<FenpeisusheView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}
    
	@Override
	public List<FenpeisusheView> selectListView(Wrapper<FenpeisusheEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public FenpeisusheView selectView(Wrapper<FenpeisusheEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
