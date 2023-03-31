package org.home_work;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DB {
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER_NAME = "postgres";
    private static final String PASSWORD = "2518";

    public static Connection connection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
            System.out.println("Connected to the postgresSQL successfully");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return connection;
    }

    public static void createTableCity() {
        String SQL = "CREATE TABLE cities(" +
                "id SERIAL PRIMARY KEY," +
                "name VARCHAR(50) NOT NULL)";
        try (Connection connection = DB.connection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(SQL);
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
    }

    public static void createTableCountry() {
        String SQL = "CREATE TABLE countries(" +
                "id SERIAL PRIMARY KEY," +
                "name VARCHAR(50) NOT NULL)";
        try (Connection connection = DB.connection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(SQL);
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
    }

    public static void createTableCityLeader() {
        String SQL = "CREATE TABLE city_leaders(" +
                "id SERIAL PRIMARY KEY," +
                "name VARCHAR(50) NOT NULL," +
                "city_name   VARCHAR(50) NOT NULL )";
        try (Connection connection = DB.connection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(SQL);
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
    }

    public static void addCity(String name) {
        String SQL = "INSERT INTO cities (name)" +
                "VALUES (?)";
        try (Connection connect = DB.connection();
             PreparedStatement preparedStatement = connect.prepareStatement(SQL)) {
            preparedStatement.setString(1, name);
            preparedStatement.executeUpdate();
        } catch (SQLException sql) {
            System.out.println(sql.getMessage());
        }
    }

    public static void addCityLeaders(String name, String city_name) {
        String SQL = "INSERT INTO city_leaders (name,city_name)" +
                "VALUES (?,?)";
        try (Connection connect = DB.connection();
             PreparedStatement preparedStatement = connect.prepareStatement(SQL)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, city_name);
            preparedStatement.executeUpdate();
        } catch (SQLException sql) {
            System.out.println(sql.getMessage());
        }

    }

    public static void addCountries(String name) {
        String SQL = "INSERT INTO countries (name)" +
                "VALUES (?)";
        try (Connection connect = DB.connection();
             PreparedStatement preparedStatement = connect.prepareStatement(SQL)) {
            preparedStatement.setString(1, name);
            preparedStatement.executeUpdate();
        } catch (SQLException sql) {
            System.out.println(sql.getMessage());
        }

    }

    public static List<City> getListCities() {
        String SQL = "SELECT * FROM cities";
        List<City> cities = new ArrayList<>();
        try (Connection con = DB.connection();
             Statement statement = con.createStatement()) {
            ResultSet resultSet= statement.executeQuery(SQL);
            while(resultSet.next()){
                City city=new City();
                city.setId(resultSet.getInt("id"));
                city.setName(resultSet.getString("name"));
                cities.add(city);
            }
        }catch(SQLException s){
            System.out.println(s.getMessage());
        }
        return cities;
    }
    public static List<Country> getListCountries() {
        String SQL = "SELECT * FROM countries";
        List<Country> countries= new ArrayList<>();
        try (Connection con = DB.connection();
             Statement statement = con.createStatement()) {
            ResultSet resultSet= statement.executeQuery(SQL);
            while(resultSet.next()){
                Country country=new Country();
                country.setId(resultSet.getInt("id"));
                country.setName(resultSet.getString("name"));
                countries.add(country);
            }
        }catch(SQLException s){
            System.out.println(s.getMessage());
        }
        return countries;
    }
    public static void cityId(long id){
        String SQL="SELECT * FROM  cities WHERE id=?";
        try(Connection con= connection();
            PreparedStatement statement= con.prepareStatement(SQL)){
            statement.setLong(1, id);
            ResultSet resultSet=statement.executeQuery();
            resultSet.next();
            System.out.println("id: "+ resultSet.getLong("id"));
            System.out.println("name: "+ resultSet.getString("name"));
        }catch(SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }
    }
}
