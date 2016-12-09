package com.nc.task3.dao.impl;

import com.nc.task3.dao.WeatherDAO;
import com.nc.task3.entities.CityWeather;
import com.nc.task3.exception.DAOException;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class MySQLWeatherDAO implements WeatherDAO {

    private static final String SELECT_WEATHER = "select city, temp, text from weather where city = ? ";
    private static final String INSERT_WEATHER = "insert into weather (city, temp, text) values ( ? , ? , ? )";
    private static final String UPDATE_WEATHER = "update weather set temp = ? , text = ? where city = ? ";

    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private PreparedStatement getPreparedStatement(String query, String[] params) throws SQLException {
        PreparedStatement statement = dataSource.getConnection().prepareStatement(query);
        for (int i = 0; i < params.length; i++) {
            statement.setString(i + 1, params[i]);
        }
        return statement;
    }

    private ResultSet executeSelect(String query, String... params) throws SQLException {
        return getPreparedStatement(query, params).executeQuery();
    }

    private void executeDML(String query, String... params) throws SQLException {
        getPreparedStatement(query, params).execute();
    }

    private CityWeather selectWeather(String city) throws DAOException {
        CityWeather cityWeather = null;
        try {
            ResultSet result = executeSelect(SELECT_WEATHER, city.toLowerCase());
            if (result.next()) {
                cityWeather = new CityWeather(city, result.getString("temp"), result.getString("text"));
            }
        } catch (SQLException e) {
            // TODO log
            throw new DAOException(DAOException.SELECT_MESSAGE);
        }
        return cityWeather;
    }

    private boolean existWeather(String city) throws DAOException {
        return (selectWeather(city) != null);
    }

    private void insertWeather(CityWeather cityWeather) throws DAOException {
        try {
            executeDML(INSERT_WEATHER, cityWeather.getCity().toLowerCase(), cityWeather.getTemp(), cityWeather.getText());
        } catch (SQLException e) {
            // TODO log
            throw new DAOException(DAOException.INSERT_MESSAGE);
        }
    }

    private void updateWeather(CityWeather cityWeather) throws DAOException {
        try {
            executeDML(UPDATE_WEATHER, cityWeather.getTemp(), cityWeather.getText(), cityWeather.getCity());
        } catch (SQLException e) {
            // TODO log
            throw new DAOException(DAOException.UPDATE_MESSAGE);
        }
    }

    public void saveWeather(CityWeather cityWeather) throws DAOException {
        if (existWeather(cityWeather.getCity())) {
            updateWeather(cityWeather);
        } else {
            insertWeather(cityWeather);
        }
    }

    public CityWeather getWeather(String city) throws DAOException {
        return selectWeather(city);
    }
}
