package de.fhws.fiw.fds.sutton.client.testCases;

import com.owlike.genson.Genson;
import de.fhws.fiw.fds.sutton.AdministrationSystem.models.Season;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class SeasonTest {
    @Test
    public void testCreateASeason() throws Exception {
        Genson genson = new Genson();
        Season season = new Season(1, 2004);
        String json = genson.serialize(season);
        HttpClient client = HttpClients.createDefault();
        HttpPost httpRequest = new HttpPost("http://localhost:8080/de/fhws/fiw/fds/sutton/AdministrationSystem/season");
        httpRequest.setHeader("Content-type", "application/json");
        httpRequest.setEntity(new StringEntity(json));
        HttpResponse httpResponse = client.execute(httpRequest);
        assertEquals("The status should be 201", 201, httpResponse.getStatusLine().getStatusCode());
    }

    @Test
    public void testLoadAnExistingSeason() throws Exception {
        Season season = new Season(1, 2004);
        long seasonId = season.getId();
        HttpClient client = HttpClients.createDefault();
        HttpGet httpRequest = new HttpGet("http://localhost:8080/de/fhws/fiw/fds/sutton/AdministrationSystem/season" + "/" + seasonId);
        HttpResponse httpResponse = client.execute(httpRequest);
        assertEquals(200, httpResponse.getStatusLine().getStatusCode());
    }

    @Test
    public void testDeleteExistingSeason() throws Exception {
        long seasonId = 1;
        HttpClient client = HttpClients.createDefault();
        HttpDelete httpRequest = new HttpDelete("http://localhost:8080/de/fhws/fiw/fds/sutton/AdministrationSystem/season" + "/" + seasonId);
        httpRequest.setHeader("Accept", "application/json");
        HttpResponse httpResponse = client.execute(httpRequest);
        assertEquals("already deleted data", 204, httpResponse.getStatusLine().getStatusCode());

    }
}
