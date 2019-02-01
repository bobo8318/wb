package cn.openui.jdbc.mapper;

import cn.openui.jdbc.po.Unit;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UnitMapper {

    @Select("select * from unit where id=#{id}")
    public Unit getUnitById(Integer id);
}
