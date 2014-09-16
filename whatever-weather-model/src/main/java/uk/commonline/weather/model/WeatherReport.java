package uk.commonline.weather.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

//@XmlRootElement
public class WeatherReport implements Serializable {

    private static class ForecastTimeComparator implements Comparator<WeatherForecast> {
        @Override
        public int compare(WeatherForecast o1, WeatherForecast o2) {
            if (o2.getWriteTime().equals(o1.getWriteTime())) {
                return o2.getPeriodFrom().compareTo(o1.getPeriodFrom());
            } else {
                return o2.getWriteTime().compareTo(o1.getWriteTime());
            }
        }
    }

    public class WeatherSourceData {

        private List<WeatherForecast> forecasts = new ArrayList<WeatherForecast>();

        private List<Weather> recordings = new ArrayList<Weather>();

        public List<WeatherForecast> getForecasts() {
            return forecasts;
        }

        public List<Weather> getRecordings() {
            return recordings;
        }

        public void setForecasts(List<WeatherForecast> forecasts) {
            this.forecasts = forecasts;
        }

        public void setRecordings(List<Weather> recordings) {
            this.recordings = recordings;
        }

    }

    private static class WeatherTimeComparator implements Comparator<Weather> {
        @Override
        public int compare(Weather o1, Weather o2) {
            return o2.getWriteTime().compareTo(o1.getWriteTime());
        }
    }

    private static final long serialVersionUID = 1L;

    private Date date;

    private long region;

    private Double latitude;

    private Double longitude;

    private Map<String, WeatherSourceData> sourceMap = new HashMap<String, WeatherSourceData>();

    public WeatherReport() {
    }

    public void buildReport(List<Weather> weathers, List<WeatherForecast> forecasts) {
        Calendar calNow = Calendar.getInstance();
        calNow.setTime(new Date());
        long now = new Date().getTime();
        long yesterday = now - 60 * 60 * 1000 * 24;
        Map<String, WeatherSourceData> sourceMap = getSourceMap();
        for (Weather weather : weathers) {
            WeatherSourceData wsd;
            if (!sourceMap.containsKey(weather.getSource())) {
                wsd = new WeatherSourceData();
                sourceMap.put(weather.getSource(), wsd);
            }
            wsd = sourceMap.get(weather.getSource());
            if (weather instanceof WeatherForecast) {
                wsd.getForecasts().add((WeatherForecast) weather);
            } else {
                wsd.getRecordings().add(weather);

            }
        }
        for (WeatherForecast forecast : forecasts) {
            WeatherSourceData wsd;
            if (!sourceMap.containsKey(forecast.getSource())) {
                wsd = new WeatherSourceData();
                sourceMap.put(forecast.getSource(), wsd);
            }
            wsd = sourceMap.get(forecast.getSource());
            wsd.getForecasts().add(forecast);
        }

        for (Map.Entry<String, WeatherSourceData> entry : sourceMap.entrySet()) {
            Collections.sort(entry.getValue().getRecordings(), new WeatherTimeComparator());
            Collections.sort(entry.getValue().getForecasts(), new ForecastTimeComparator());

            List<WeatherForecast> fs = new ArrayList<WeatherForecast>();
            List<Weather> ws = new ArrayList<Weather>();

            int lastWriteDay = -1;
            int lastWriteHour = -1;
            for (Weather w : entry.getValue().getRecordings()) {
                Date date = w.getWriteTime();
                Calendar cal = Calendar.getInstance();
                cal.setTime(date);
                int day = cal.get(Calendar.DAY_OF_MONTH);
                int hour = cal.get(Calendar.HOUR);
                System.out.println("!!Got recording:" + entry.getKey() + ", writeDay:" + day + ", writeHour:" + hour);
                if (date.getTime() >= yesterday) {
                    if (hour != lastWriteHour) {
                        ws.add(w);
                        lastWriteHour = hour;
                        System.out.println("!!Add hourly recording:" + entry.getKey() + ", writeDay:" + day + ", writeHour:" + hour);
                    }
                } else if (day != lastWriteDay) {
                    ws.add(w);
                    lastWriteDay = day;
                    System.out.println("!!Add daily recording:" + entry.getKey() + ", writeDay:" + day + ", writeHour:" + hour);
                }
            }

            lastWriteDay = -1;
            Set<Integer> dayFromSet = new HashSet<Integer>();
            for (WeatherForecast f : entry.getValue().getForecasts()) {
                Date date = f.getWriteTime();
                Calendar cal = Calendar.getInstance();
                cal.setTime(date);
                int day = cal.get(Calendar.DAY_OF_MONTH);
                Date dateFrom = f.getPeriodFrom();
                cal.setTime(dateFrom);
                int dayFrom = cal.get(Calendar.DAY_OF_MONTH);
                System.out.println("!!Got forecast:" + entry.getKey() + ", writeDay:" + day + ", dayfrom:" + dayFrom);
                if (day != lastWriteDay) {
                    fs.add(f);
                    System.out.println("!!Add first day forecast:" + entry.getKey() + ", writeDay:" + day + ", dayfrom:" + dayFrom);
                    lastWriteDay = day;
                    dayFromSet = new HashSet<Integer>();
                } else {
                    if (!dayFromSet.contains(dayFrom)) {
                        fs.add(f);
                        System.out.println("!!Add next day forecast:" + entry.getKey() + ", writeDay:" + day + ", dayfrom:" + dayFrom);
                        dayFromSet.add(dayFrom);
                    }
                }
            }
            entry.getValue().setRecordings(ws);
            entry.getValue().setForecasts(fs);
            Collections.sort(entry.getValue().getRecordings(), new WeatherTimeComparator());
            Collections.sort(entry.getValue().getForecasts(), new ForecastTimeComparator());

        }

        setDate(new Date());
    }

    public void clearBackReferences() {
        for (WeatherSourceData wsd : sourceMap.values()) {
            List<Weather> ws = wsd.getRecordings();
            for (Weather w : ws) {
                w.clearBackReferences();
            }
            List<WeatherForecast> fs = wsd.getForecasts();
            for (WeatherForecast f : fs) {
                f.clearBackReferences();
            }
        }
    }

    public Date getDate() {
        return date;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public long getRegion() {
        return region;
    }

    public Map<String, WeatherSourceData> getSourceMap() {
        return sourceMap;
    }

    public void setBackReferences() {
        for (WeatherSourceData wsd : sourceMap.values()) {
            List<Weather> ws = wsd.getRecordings();
            for (Weather w : ws) {
                w.setBackReferences();
            }
            List<WeatherForecast> fs = wsd.getForecasts();
            for (WeatherForecast f : fs) {
                f.setBackReferences();
            }
        }
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public void setRegion(long region) {
        this.region = region;
    }

    public void setSourceMap(Map<String, WeatherSourceData> sourceMap) {
        this.sourceMap = sourceMap;
    }

}