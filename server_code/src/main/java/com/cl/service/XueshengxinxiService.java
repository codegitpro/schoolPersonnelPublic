package com.cl.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.cl.utils.PageUtils;
import com.cl.entity.XueshengxinxiEntity;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.XueshengxinxiView;


/**
 * 学生信息
 *
 * @author 
 * @email 
 * @date 2024-05-11 23:37:17
 */
public interface XueshengxinxiService extends IService<XueshengxinxiEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<XueshengxinxiView> selectListView(Wrapper<XueshengxinxiEntity> wrapper);
   	
   	XueshengxinxiView selectView(@Param("ew") Wrapper<XueshengxinxiEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<XueshengxinxiEntity> wrapper);
   	

}

