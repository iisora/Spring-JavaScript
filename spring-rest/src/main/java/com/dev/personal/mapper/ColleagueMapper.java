package com.dev.personal.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import com.dev.personal.model.Colleague;

// マッパーであることを示す。MyBatisは、起動時にこのアノテーションを検索し、使えるようにする
@Mapper
public interface ColleagueMapper {

	/*
	 * SQLはXMLに記載する（SQLが複雑化した場合のために予め対応)
	 */

	Long count();

	// ページング情報を渡す
	List<Colleague> selectAll(RowBounds rowBounds);

//	@Select("SELECT * FROM colleague")
	List<Colleague> selectAll();

	// #{変数名}でSQLに変数を渡せる
//	@Select("SELECT * FROM colleague WHERE id = #{id}")
	Colleague selectByPrimaryKey(Long id);

	// {}や＋で改行できる
	// #{フィールド名}でSQLにクラスの中身を渡せる
	// insertやdeleteは標準では処理件数を返すが、voidでもbooleanでも良い
//	@Insert({ "INSERT INTO colleague(user_name, email)", "VALUES(#{userName}, #{email})" })
	int insert(Colleague record);

//	@Update({ "UPDATE colleague SET user_name = #{userName}, email = #{email} WHERE id = #{id}" })
	int updateByPrimaryKey(Colleague record);

//	@Delete("DELETE FROM colleague WHERE id = #{id}")
	int deleteByPrimaryKey(Long id);
}
