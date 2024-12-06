package com.cl.dao;

import com.cl.entity.XueshengxinxiEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.XueshengxinxiView;


/**
 * 学生信息
 * 
 * @author 
 * @email 
 * @date 2024-05-11 23:37:17
 */
public interface XueshengxinxiDao extends BaseMapper<XueshengxinxiEntity> {
	
	List<XueshengxinxiView> selectListView(@Param("ew") Wrapper<XueshengxinxiEntity> wrapper);

	List<XueshengxinxiView> selectListView(Pagination page,@Param("ew") Wrapper<XueshengxinxiEntity> wrapper);
	
	XueshengxinxiView selectView(@Param("ew") Wrapper<XueshengxinxiEntity> wrapper);
	

}
