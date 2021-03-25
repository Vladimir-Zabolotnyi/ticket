package org.hillel.service;

import org.hillel.Journey;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import javax.xml.transform.Result;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
@Component("JDBCJourneyService")
@Primary
public class JDBCJourneyServiceImpl implements JourneyService {

//    private final String id;
    public JDBCJourneyServiceImpl(){
//        id = identify;
    }

    @Override
    public Collection<Journey> find(String stationFrom, String stationTo, LocalDateTime departure, LocalDateTime arrival) {
        if (Objects.isNull(stationFrom)) throw new IllegalArgumentException("stationFrom must be set!");
        if (Objects.isNull(stationTo)) throw new IllegalArgumentException("stationTo must be set!");
        if (Objects.isNull(departure)) throw new IllegalArgumentException("departure must be set!");
        if (Objects.isNull(arrival)) throw new IllegalArgumentException("arrival must be set!");

        List<Journey> journeyList = new ArrayList<>();
        PreparedStatement statement;
        ResultSet resultSet;
        try (Connection connection = ConnectionFactory.getMySQlConnection()) {
            statement = connection.prepareStatement(
                    "SELECT * FROM route  WHERE station_from = ? AND" +
                            " station_to = ? AND" +
                            " departure = ? AND " +
                            "arrival = ?");

            statement.setString(1, stationFrom);
            statement.setString(2, stationTo);
            statement.setTimestamp(3, Timestamp.valueOf(departure));
            statement.setTimestamp(4, Timestamp.valueOf(arrival));
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                journeyList.add(new Journey(
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getTimestamp(4).toLocalDateTime(),
                        resultSet.getTimestamp(5).toLocalDateTime()));
            }
            resultSet.close();
            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return journeyList;
    }
}
