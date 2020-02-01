package com.htboiz.weatherapp;

import java.util.List;

public class WeatherResponse {

    public final Coord coord;
    public final List<WeatherDescription> list;

    public WeatherResponse(Coord coord, List<WeatherDescription> list) {
        this.coord = coord;
        this.list = list;
    }

    @Override
    public String toString() {
        WeatherDescription item = null;
        if (list != null && list.size() > 0)
            item = list.get(0);

        return "WeatherResponse{" +
                "coord=" + coord +
                ", item=" + item +
                '}';
    }

    public class Coord {
        float lon;
        float lat;
    }

    public class WeatherDescription {
        int id;
        WeatherDetails main;

        @Override
        public String toString() {
            return "{" +
                    "id=" + id +
                    ", main=" + main +
                    '}';
        }
    }

    public class WeatherDetails {
        float temp;
        float feelsLike;

        @Override
        public String toString() {
            return "{" +
                    "temp=" + temp +
                    ", feelsLike=" + feelsLike +
                    '}';
        }
    }

}
