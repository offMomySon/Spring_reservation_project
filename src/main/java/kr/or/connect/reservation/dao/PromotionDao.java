package kr.or.connect.reservation.dao;

import kr.or.connect.reservation.dto.PromotionRs;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Collections;
import java.util.List;

import static kr.or.connect.reservation.sql.PromotionSql.SELECT_ALL;

@Repository
public class PromotionDao {
	private NamedParameterJdbcTemplate jdbcTemplate;
	private RowMapper<PromotionRs> rowMapper = BeanPropertyRowMapper.newInstance(PromotionRs.class);

	public PromotionDao(@Qualifier("dataSource") DataSource dataSource) {
		jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	public List<PromotionRs> selectAll() {
		try {
			return jdbcTemplate.query(SELECT_ALL, Collections.emptyMap(), rowMapper);
		} catch (EmptyResultDataAccessException e) {
			e.printStackTrace();
			return null;
		}
	}

}
