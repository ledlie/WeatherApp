package com.htboiz.weatherapp;

import java.util.List;

public class WeatherResponse {

    public final Coord coord;
    public final List<WeatherDescription> weather;
    public final WeatherDetails main;

    public WeatherResponse(Coord coord, List<WeatherDescription> weather, WeatherDetails main) {
        this.coord = coord;
        this.weather = weather;
        this.main = main;
    }

    @Override
    public String toString() {
        return "WeatherResponse{" +
                "coord=" + coord +
                ", weather=" + weather +
                ", main=" + main +
                '}';
    }

    // {"coord": { "lon": 139,"lat": 35},
    public class Coord {
        float lon;
        float lat;
    }

    public class WeatherDescription {
        int id;
        String main;
        String description;
    }

    public class WeatherDetails {
        float temp;
        float feelsLike;
    }

}
