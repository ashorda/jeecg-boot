package org.jeecg.modules.demo.school_class_place_type.mapper;

import java.util.List;
import org.jeecg.modules.demo.school_class_place_type.entity.SchoolPlaceCtrl;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Description: 上课地点管理
 * @Author: jeecg-boot
 * @Date:   2021-07-19
 * @Version: V1.0
 */
public interface SchoolPlaceCtrlMapper extends BaseMapper<SchoolPlaceCtrl> {

	public boolean deleteByMainId(@Param("mainId") String mainId);
    
	public List<SchoolPlaceCtrl> selectByMainId(@Param("mainId") String mainId);
}
