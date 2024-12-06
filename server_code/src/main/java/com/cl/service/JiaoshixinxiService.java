package com.cl.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.cl.utils.PageUtils;
import com.cl.entity.JiaoshixinxiEntity;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.JiaoshixinxiView;


/**
 * 教师信息
 *
 * @author 
 * @email 
 * @date 2024-05-11 23:37:17
 */
public interface JiaoshixinxiService extends IService<JiaoshixinxiEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<JiaoshixinxiView> selectListView(Wrapper<JiaoshixinxiEntity> wrapper);
   	
   	JiaoshixinxiView selectView(@Param("ew") Wrapper<JiaoshixinxiEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<JiaoshixinxiEntity> wrapper);
   	

}

