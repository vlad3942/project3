package project.model;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Service
@NoArgsConstructor
public class DBControl {
    @Value("${spring.datasource.url}")
    private String HOST;
    @Value("${spring.datasource.username}")
    private String USERNAME;
    @Value("${spring.datasource.password}")
    private String PASSWORD;

    private Connection connection;

    @PostConstruct()
    private void post() {
        try{
            connection = DriverManager.getConnection(HOST,USERNAME,PASSWORD);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
