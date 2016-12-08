package com.nc.task3.dao.impl;

import com.nc.task3.dao.WeatherDAO;
import com.nc.task3.entities.CityWeather;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class MySQLWeatherDAO implements WeatherDAO {

    private static final String SELECT_WEATHER = "select city, temp, text from weather where city = ? ";
    private static final String INSERT_WEATHER = "insert into weather (city, temp, text) values ( ? , ? , ? )";
    private static final String UPDATE_WEATHER = "update weather set temp = ? , text = ? where city = ? ";

    private DataSource dataSource;

    public DataSource getDataSource() {
        return dataSource;
    }

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

    private CityWeather selectWeather(String city) {
        CityWeather cityWeather = null;
        try {
            ResultSet result = executeSelect(SELECT_WEATHER, city.toLowerCase());
            if (result.next()) {
                cityWeather = new CityWeather(city, result.getString("temp"), result.getString("text"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cityWeather;
    }

    private boolean existWeather(String city) {
        return (selectWeather(city) != null);
    }

    private void insertWeather(CityWeather cityWeather) {
        try {
            executeDML(INSERT_WEATHER, cityWeather.getCity().toLowerCase(), cityWeather.getTemp(), cityWeather.getText());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateWeather(CityWeather cityWeather) {
        try {
            executeDML(UPDATE_WEATHER, cityWeather.getTemp(), cityWeather.getText(), cityWeather.getCity());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveWeather(CityWeather cityWeather) {
        if (existWeather(cityWeather.getCity())) {
            updateWeather(cityWeather);
        } else {
            insertWeather(cityWeather);
        }
    }

    public CityWeather getWeather(String city) {
        return selectWeather(city);
    }
}
