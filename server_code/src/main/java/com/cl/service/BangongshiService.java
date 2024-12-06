package com.cl.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.cl.utils.PageUtils;
import com.cl.entity.BangongshiEntity;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.BangongshiView;


/**
 * 办公室
 *
 * @author 
 * @email 
 * @date 2024-05-11 23:37:17
 */
public interface BangongshiService extends IService<BangongshiEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<BangongshiView> selectListView(Wrapper<BangongshiEntity> wrapper);
   	
   	BangongshiView selectView(@Param("ew") Wrapper<BangongshiEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<BangongshiEntity> wrapper);
   	

}

