package de.fhws.fiw.fds.sutton.client.testCases;

import com.owlike.genson.Genson;
import de.fhws.fiw.fds.sutton.AdministrationSystem.models.Series;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class SeriesTest {
    @Test
    public void testCreateASeries() throws Exception{
        Genson genson = new Genson();
        Series series = new Series("Bleach", "Japan", "Adventure", "20 min", LocalDate.of(2004,01,01));
        String json = genson.serialize(series);
        HttpClient client = HttpClients.createDefault();
        HttpPost httpRequest = new HttpPost("http://localhost:8080/de/fhws/fiw/fds/sutton/AdministrationSystem/series");
        httpRequest.setHeader("Content-type", "application/json");
        httpRequest.setEntity(new StringEntity(json));
        HttpResponse httpResponse = client.execute(httpRequest);
        assertEquals("The status should be 201", 201, httpResponse.getStatusLine().getStatusCode());
    }

    @Test
    public void testLoadAnExistingSeries() throws Exception{
        Series series = new Series("Bleach", "Japan", "Adventure", "20 min", LocalDate.of(2004,01,01));
        long seriesId = series.getId();
        HttpClient client = HttpClients.createDefault();
        HttpGet httpRequest = new HttpGet("http://localhost:8080/de/fhws/fiw/fds/sutton/AdministrationSystem/series" + "/" + seriesId);
        HttpResponse httpResponse = client.execute(httpRequest);
        assertEquals( 200, httpResponse.getStatusLine().getStatusCode( ) );
    }

    @Test
    public void testDeleteExistingSeries()throws Exception{
    long seriesId = 1;
    HttpClient client = HttpClients.createDefault();
    HttpDelete httpRequest = new HttpDelete("http://localhost:8080/de/fhws/fiw/fds/sutton/AdministrationSystem/series" + "/" + seriesId);
    httpRequest.setHeader("Accept", "application/json");
    HttpResponse httpResponse = client.execute(httpRequest);
    assertEquals("already deleted data", 204, httpResponse.getStatusLine().getStatusCode());

    }
}
