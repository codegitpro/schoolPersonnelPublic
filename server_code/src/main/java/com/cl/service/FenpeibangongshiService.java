package com.cl.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.cl.utils.PageUtils;
import com.cl.entity.FenpeibangongshiEntity;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.FenpeibangongshiView;


/**
 * 分配办公室
 *
 * @author 
 * @email 
 * @date 2024-05-11 23:37:17
 */
public interface FenpeibangongshiService extends IService<FenpeibangongshiEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<FenpeibangongshiView> selectListView(Wrapper<FenpeibangongshiEntity> wrapper);
   	
   	FenpeibangongshiView selectView(@Param("ew") Wrapper<FenpeibangongshiEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<FenpeibangongshiEntity> wrapper);
   	

}

