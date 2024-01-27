package de.fhws.fiw.fds.sutton.client.testCases;

import com.owlike.genson.Genson;
import de.fhws.fiw.fds.sutton.AdministrationSystem.models.Actor;
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

public class ActorTest {
    @Test
    public void testCreateAnActor() throws Exception {
        Genson genson = new Genson();
        Actor actor = new Actor("Jackie", "Chan", "Jacky", "Chen");
        String json = genson.serialize(actor);
        HttpClient client = HttpClients.createDefault();
        HttpPost httpRequest = new HttpPost("http://localhost:8080/de/fhws/fiw/fds/sutton/AdministrationSystem/actor");
        httpRequest.setHeader("Content-type", "application/json");
        httpRequest.setEntity(new StringEntity(json));
        HttpResponse httpResponse = client.execute(httpRequest);
        assertEquals("The status should be 201", 201, httpResponse.getStatusLine().getStatusCode());
    }

    @Test
    public void testLoadAnExistingActor() throws Exception {
        Actor actor = new Actor("Jackie", "Chan", "Jacky", "Chen");
        long actorId = actor.getId();
        HttpClient client = HttpClients.createDefault();
        HttpGet httpRequest = new HttpGet("http://localhost:8080/de/fhws/fiw/fds/sutton/AdministrationSystem/actor" + "/" + actorId);
        HttpResponse httpResponse = client.execute(httpRequest);
        assertEquals(200, httpResponse.getStatusLine().getStatusCode());
    }

    @Test
    public void testDeleteExistingActor() throws Exception {
        long actorId = 1;
        HttpClient client = HttpClients.createDefault();
        HttpDelete httpRequest = new HttpDelete("http://localhost:8080/de/fhws/fiw/fds/sutton/AdministrationSystem/actor" + "/" + actorId);
        httpRequest.setHeader("Accept", "application/json");
        HttpResponse httpResponse = client.execute(httpRequest);
        assertEquals("already deleted data", 204, httpResponse.getStatusLine().getStatusCode());

    }
}
