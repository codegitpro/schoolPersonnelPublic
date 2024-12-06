package com.cl.dao;

import com.cl.entity.BangongshiEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.BangongshiView;


/**
 * 办公室
 * 
 * @author 
 * @email 
 * @date 2024-05-11 23:37:17
 */
public interface BangongshiDao extends BaseMapper<BangongshiEntity> {
	
	List<BangongshiView> selectListView(@Param("ew") Wrapper<BangongshiEntity> wrapper);

	List<BangongshiView> selectListView(Pagination page,@Param("ew") Wrapper<BangongshiEntity> wrapper);
	
	BangongshiView selectView(@Param("ew") Wrapper<BangongshiEntity> wrapper);
	

}
