package de.fhws.fiw.fds.sutton.client.testCases;

import com.owlike.genson.Genson;
import de.fhws.fiw.fds.sutton.AdministrationSystem.models.Episode;
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

public class EpisodeTest {
    @Test
    public void testCreateAnEpisode() throws Exception {
        Genson genson = new Genson();
        Episode episode = new Episode(1, "The Start", "Max Mustermann", LocalDate.of(2001, 01, 01));
        String json = genson.serialize(episode);
        HttpClient client = HttpClients.createDefault();
        HttpPost httpRequest = new HttpPost("http://localhost:8080/de/fhws/fiw/fds/sutton/AdministrationSystem/episode");
        httpRequest.setHeader("Content-type", "application/json");
        httpRequest.setEntity(new StringEntity(json));
        HttpResponse httpResponse = client.execute(httpRequest);
        assertEquals("The status should be 201", 201, httpResponse.getStatusLine().getStatusCode());
    }

    @Test
    public void testLoadAnExistingEpisode() throws Exception {
        Episode episode = new Episode(1, "The Start", "Max Mustermann", LocalDate.of(2001, 01, 01));
        long episodeId = episode.getId();
        HttpClient client = HttpClients.createDefault();
        HttpGet httpRequest = new HttpGet("http://localhost:8080/de/fhws/fiw/fds/sutton/AdministrationSystem/episode" + "/" + episodeId);
        HttpResponse httpResponse = client.execute(httpRequest);
        assertEquals(200, httpResponse.getStatusLine().getStatusCode());
    }

    @Test
    public void testDeleteExistingEpisode() throws Exception {
        long episodeId = 1;
        HttpClient client = HttpClients.createDefault();
        HttpDelete httpRequest = new HttpDelete("http://localhost:8080/de/fhws/fiw/fds/sutton/AdministrationSystem/episode" + "/" + episodeId);
        httpRequest.setHeader("Accept", "application/json");
        HttpResponse httpResponse = client.execute(httpRequest);
        assertEquals("already deleted data", 204, httpResponse.getStatusLine().getStatusCode());

    }
}
