package lab.dao;

import java.util.List;

import lab.model.Country;

import lombok.SneakyThrows;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

public class CountryDao extends JdbcDaoSupport {
	private static final String LOAD_COUNTRIES_SQL = "insert into country (name, code_name) values ";

	private static final String GET_ALL_COUNTRIES_SQL = "select * from country";
	private static final String GET_COUNTRIES_BY_NAME_SQL = "select * from country where name like :name";
	private static final String GET_COUNTRY_BY_NAME_SQL = "select * from country where name = '";
	//private static final String GET_COUNTRY_BY_CODE_NAME_SQL = "select * from country where code_name = '";
	private static final String GET_COUNTRY_BY_CODE_NAME_SQL = "select id, name, code_name from country where code_name = '";


	private static final String UPDATE_COUNTRY_NAME_SQL_1 = "update country SET name='";
	private static final String UPDATE_COUNTRY_NAME_SQL_2 = " where code_name='";
	private static final String UPDATE_COUNTRY_NAME_SQL = "UPDATE country SET name='%s' WHERE code_name='%s'";

	public static final String[][] COUNTRY_INIT_DATA = { { "Australia", "AU" },
			{ "Canada", "CA" }, { "France", "FR" }, { "Hong Kong", "HK" },
			{ "Iceland", "IC" }, { "Japan", "JP" }, { "Nepal", "NP" },
			{ "Russian Federation", "RU" }, { "Sweden", "SE" },
			{ "Switzerland", "CH" }, { "United Kingdom", "GB" },
			{ "United States", "US" } };

	private static final CountryRowMapper COUNTRY_ROW_MAPPER = new CountryRowMapper();

	@SneakyThrows
	public List<Country> getCountryList() {
		// TODO: implement it
		//return null;
		return getCountries();
	}

	public List<Country> getCountryListStartWith(String name) {
		NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(
				getDataSource());
		SqlParameterSource sqlParameterSource = new MapSqlParameterSource(
				"name", name + "%");
		return namedParameterJdbcTemplate.query(GET_COUNTRIES_BY_NAME_SQL,
				sqlParameterSource, COUNTRY_ROW_MAPPER);
	}

	public void updateCountryName(String codeName, String newCountryName) {
//		getJdbcTemplate().update(String.format(
//				UPDATE_COUNTRY_NAME_SQL, newCountryName, codeName));

		getJdbcTemplate().execute(
				UPDATE_COUNTRY_NAME_SQL_1  +  newCountryName  +  "'"  +
						UPDATE_COUNTRY_NAME_SQL_2 + codeName + "'");
	}

	public void loadCountries() {
		for (String[] countryData : COUNTRY_INIT_DATA) {
			String sql = LOAD_COUNTRIES_SQL + "('" + countryData[0] + "', '"
					+ countryData[1] + "');";
//			System.out.println(sql);
			getJdbcTemplate().execute(sql);
		}
	}

	public Country getCountryByCodeName(String codeName) {
		JdbcTemplate jdbcTemplate = getJdbcTemplate();

		String sql = GET_COUNTRY_BY_CODE_NAME_SQL + codeName + "'";
//		System.out.println(sql);

		return jdbcTemplate.query(sql, COUNTRY_ROW_MAPPER).get(0);
	}

	public Country getCountryByName(String name)
			throws CountryNotFoundException {
		JdbcTemplate jdbcTemplate = getJdbcTemplate();
		List<Country> countryList = jdbcTemplate.query(GET_COUNTRY_BY_NAME_SQL
				+ name + "'", COUNTRY_ROW_MAPPER);
		if (countryList.isEmpty()) {
			throw new CountryNotFoundException();
		}
		return countryList.get(0);
	}

	public List<Country> getCountries()
			throws CountryNotFoundException {
		JdbcTemplate jdbcTemplate = getJdbcTemplate();
		List<Country> countryList = jdbcTemplate.query(GET_ALL_COUNTRIES_SQL, COUNTRY_ROW_MAPPER);
		if (countryList.isEmpty()) {
			throw new CountryNotFoundException();
		}
		return countryList;
	}
}
