package com.system.dao.mapper;

import com.system.dao.bean.User;
import org.apache.ibatis.session.RowBounds;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author weiyu
 * @version V1.0
 * @since 2018-10-24
 */
public interface UserMapper extends Mapper<User> {


    List<User>  selectByAge();

}
