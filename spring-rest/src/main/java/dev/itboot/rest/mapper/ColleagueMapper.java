package dev.itboot.rest.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import dev.itboot.rest.model.Colleague;

// マッパーであることを示す。MyBatisは、起動時にこのアノテーションを検索し、使えるようにする
@Mapper
public interface ColleagueMapper {

	@Select("SELECT * FROM colleague")
	List<Colleague> selectAll();
}
