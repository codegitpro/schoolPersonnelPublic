package com.cl.dao;

import com.cl.entity.JiaoshixinxiEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.JiaoshixinxiView;


/**
 * 教师信息
 * 
 * @author 
 * @email 
 * @date 2024-05-11 23:37:17
 */
public interface JiaoshixinxiDao extends BaseMapper<JiaoshixinxiEntity> {
	
	List<JiaoshixinxiView> selectListView(@Param("ew") Wrapper<JiaoshixinxiEntity> wrapper);

	List<JiaoshixinxiView> selectListView(Pagination page,@Param("ew") Wrapper<JiaoshixinxiEntity> wrapper);
	
	JiaoshixinxiView selectView(@Param("ew") Wrapper<JiaoshixinxiEntity> wrapper);
	

}
