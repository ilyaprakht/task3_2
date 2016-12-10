package com.nc.task3.dao.impl;

import com.nc.task3.entities.CityWeather;
import com.nc.task3.exception.DAOException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class MySQLWeatherDAOTest {

    private MySQLWeatherDAO mySQLWeatherDAO;

    private static final String DEFAULT_CITY = "saratov";
    private static final String DEFAULT_TEMP = "30";
    private static final String DEFAULT_TEXT = "Cloudy";

    private static final String SELECT_WEATHER = "select city, temp, text from weather where city = ? ";
    private static final String INSERT_WEATHER = "insert into weather (city, temp, text) values ( ? , ? , ? )";
    private static final String UPDATE_WEATHER = "update weather set temp = ? , text = ? where city = ? ";

    @Before
    public void init() {
        mySQLWeatherDAO = new MySQLWeatherDAO();
    }

    @Test
    public void saveWeatherUpdate() throws SQLException, DAOException {
        DataSource mockDataSource = Mockito.mock(DataSource.class);
        Connection mockConnection = Mockito.mock(Connection.class);
        PreparedStatement mockStatement = Mockito.mock(PreparedStatement.class);

        ResultSet mockResultSet = Mockito.mock(ResultSet.class);
        Mockito.doReturn(true).when(mockResultSet).next();
        Mockito.doReturn(DEFAULT_TEMP).when(mockResultSet).getString("temp");
        Mockito.doReturn(DEFAULT_TEXT).when(mockResultSet).getString("text");

        Mockito.doReturn(mockResultSet).when(mockStatement).executeQuery();
        Mockito.doReturn(mockStatement).when(mockConnection).prepareStatement(SELECT_WEATHER);
        Mockito.doReturn(mockStatement).when(mockConnection).prepareStatement(INSERT_WEATHER);
        Mockito.doReturn(mockStatement).when(mockConnection).prepareStatement(UPDATE_WEATHER);
        Mockito.doReturn(mockConnection).when(mockDataSource).getConnection();

        mySQLWeatherDAO.setDataSource(mockDataSource);

        CityWeather cityWeather = new CityWeather(DEFAULT_CITY, DEFAULT_TEMP, DEFAULT_TEXT);
        mySQLWeatherDAO.saveWeather(cityWeather);
    }

    @Test
    public void saveWeatherInsert() throws SQLException, DAOException {
        DataSource mockDataSource = Mockito.mock(DataSource.class);
        Connection mockConnection = Mockito.mock(Connection.class);
        PreparedStatement mockStatement = Mockito.mock(PreparedStatement.class);

        ResultSet mockResultSet = Mockito.mock(ResultSet.class);
        Mockito.doReturn(false).when(mockResultSet).next();
        Mockito.doReturn(DEFAULT_TEMP).when(mockResultSet).getString("temp");
        Mockito.doReturn(DEFAULT_TEXT).when(mockResultSet).getString("text");

        Mockito.doReturn(mockResultSet).when(mockStatement).executeQuery();
        Mockito.doReturn(mockStatement).when(mockConnection).prepareStatement(SELECT_WEATHER);
        Mockito.doReturn(mockStatement).when(mockConnection).prepareStatement(INSERT_WEATHER);
        Mockito.doReturn(mockStatement).when(mockConnection).prepareStatement(UPDATE_WEATHER);
        Mockito.doReturn(mockConnection).when(mockDataSource).getConnection();

        mySQLWeatherDAO.setDataSource(mockDataSource);

        CityWeather newCityWeather = new CityWeather(DEFAULT_CITY, DEFAULT_TEMP, DEFAULT_TEXT);
        mySQLWeatherDAO.saveWeather(newCityWeather);
    }

    @Test(expected = DAOException.class)
    public void saveWeatherNegative() throws SQLException, DAOException {
        DataSource mockDataSource = Mockito.mock(DataSource.class);
        Mockito.doThrow(SQLException.class).when(mockDataSource).getConnection();

        mySQLWeatherDAO.setDataSource(mockDataSource);

        CityWeather cityWeather = new CityWeather(DEFAULT_CITY, DEFAULT_TEMP, DEFAULT_TEXT);
        mySQLWeatherDAO.saveWeather(cityWeather);
    }

    @Test
    public void getWeatherPositive() throws DAOException, SQLException {
        DataSource mockDataSource = Mockito.mock(DataSource.class);
        Connection mockConnection = Mockito.mock(Connection.class);
        PreparedStatement mockStatement = Mockito.mock(PreparedStatement.class);

        ResultSet mockResultSet = Mockito.mock(ResultSet.class);
        Mockito.doReturn(true).when(mockResultSet).next();
        Mockito.doReturn(DEFAULT_TEMP).when(mockResultSet).getString("temp");
        Mockito.doReturn(DEFAULT_TEXT).when(mockResultSet).getString("text");

        Mockito.doReturn(mockResultSet).when(mockStatement).executeQuery();
        Mockito.doReturn(mockStatement).when(mockConnection).prepareStatement(SELECT_WEATHER);
        Mockito.doReturn(mockConnection).when(mockDataSource).getConnection();

        mySQLWeatherDAO.setDataSource(mockDataSource);

        CityWeather weather = mySQLWeatherDAO.getWeather(DEFAULT_CITY);

        assertNotNull("weather is null", weather);
        assertEquals("weather city is incorrect", weather.getCity(), DEFAULT_CITY);
        assertEquals("weather temp is incorrect", weather.getTemp(), DEFAULT_TEMP);
        assertEquals("weather text is incorrect", weather.getText(), DEFAULT_TEXT);
    }

    @Test(expected = DAOException.class)
    public void getWeatherNegative() throws SQLException, DAOException {
        DataSource mockDataSource = Mockito.mock(DataSource.class);
        Mockito.doThrow(SQLException.class).when(mockDataSource).getConnection();

        mySQLWeatherDAO.setDataSource(mockDataSource);

        mySQLWeatherDAO.getWeather(DEFAULT_CITY);
    }

}
