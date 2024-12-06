package com.cl.dao;

import com.cl.entity.FenpeisusheEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.FenpeisusheView;


/**
 * 分配宿舍
 * 
 * @author 
 * @email 
 * @date 2024-05-11 23:37:17
 */
public interface FenpeisusheDao extends BaseMapper<FenpeisusheEntity> {
	
	List<FenpeisusheView> selectListView(@Param("ew") Wrapper<FenpeisusheEntity> wrapper);

	List<FenpeisusheView> selectListView(Pagination page,@Param("ew") Wrapper<FenpeisusheEntity> wrapper);
	
	FenpeisusheView selectView(@Param("ew") Wrapper<FenpeisusheEntity> wrapper);
	

}
