package com.qilicoh.boot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qilicoh.boot.domain.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
