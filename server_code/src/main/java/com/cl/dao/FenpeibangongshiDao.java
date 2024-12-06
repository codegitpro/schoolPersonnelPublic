package com.cl.dao;

import com.cl.entity.FenpeibangongshiEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.FenpeibangongshiView;


/**
 * 分配办公室
 * 
 * @author 
 * @email 
 * @date 2024-05-11 23:37:17
 */
public interface FenpeibangongshiDao extends BaseMapper<FenpeibangongshiEntity> {
	
	List<FenpeibangongshiView> selectListView(@Param("ew") Wrapper<FenpeibangongshiEntity> wrapper);

	List<FenpeibangongshiView> selectListView(Pagination page,@Param("ew") Wrapper<FenpeibangongshiEntity> wrapper);
	
	FenpeibangongshiView selectView(@Param("ew") Wrapper<FenpeibangongshiEntity> wrapper);
	

}
