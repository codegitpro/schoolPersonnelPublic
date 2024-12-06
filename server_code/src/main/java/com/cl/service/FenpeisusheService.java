package com.cl.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.cl.utils.PageUtils;
import com.cl.entity.FenpeisusheEntity;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.FenpeisusheView;


/**
 * 分配宿舍
 *
 * @author 
 * @email 
 * @date 2024-05-11 23:37:17
 */
public interface FenpeisusheService extends IService<FenpeisusheEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<FenpeisusheView> selectListView(Wrapper<FenpeisusheEntity> wrapper);
   	
   	FenpeisusheView selectView(@Param("ew") Wrapper<FenpeisusheEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<FenpeisusheEntity> wrapper);
   	

}

