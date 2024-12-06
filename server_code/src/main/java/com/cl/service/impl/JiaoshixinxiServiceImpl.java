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


import com.cl.dao.JiaoshixinxiDao;
import com.cl.entity.JiaoshixinxiEntity;
import com.cl.service.JiaoshixinxiService;
import com.cl.entity.view.JiaoshixinxiView;

@Service("jiaoshixinxiService")
public class JiaoshixinxiServiceImpl extends ServiceImpl<JiaoshixinxiDao, JiaoshixinxiEntity> implements JiaoshixinxiService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<JiaoshixinxiEntity> page = this.selectPage(
                new Query<JiaoshixinxiEntity>(params).getPage(),
                new EntityWrapper<JiaoshixinxiEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<JiaoshixinxiEntity> wrapper) {
		  Page<JiaoshixinxiView> page =new Query<JiaoshixinxiView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}
    
	@Override
	public List<JiaoshixinxiView> selectListView(Wrapper<JiaoshixinxiEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public JiaoshixinxiView selectView(Wrapper<JiaoshixinxiEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
