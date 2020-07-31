package kr.or.connect.reservation.dao;

import static kr.or.connect.reservation.sql.CategorySql.*;

import java.util.Collections;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import kr.or.connect.reservation.dto.Category;

@Repository
public class CategoryDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<Category> rowMapper = BeanPropertyRowMapper.newInstance(Category.class);

	public CategoryDao(DataSource dataSource) {
		jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	public List<Category> selectAll() {
		try {
			return jdbc.query(SELECT_ALL_WITH_COUNT, Collections.emptyMap(), rowMapper);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
}
