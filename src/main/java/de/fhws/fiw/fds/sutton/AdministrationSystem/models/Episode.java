package de.fhws.fiw.fds.sutton.AdministrationSystem.models;

import com.owlike.genson.annotation.JsonConverter;
import de.fhws.fiw.fds.sutton.client.Link;
import de.fhws.fiw.fds.sutton.server.models.AbstractModel;
import de.fhws.fiw.fds.sutton.utils.JsonDateTimeConverter;
import org.glassfish.jersey.linking.InjectLink;

import java.io.Serializable;
import java.time.LocalDate;

public class Episode extends AbstractModel implements Serializable {
    private int number;
    private String title;
    private String director;
    private LocalDate dateOfBroad;

    @InjectLink(
            style = InjectLink.Style.ABSOLUTE,
            value = "/episode/${instance.id}",
            rel = "self",
            title = "self",
            type = "application/json"
    )
    private Link selfLink;
    @InjectLink(
            style= InjectLink.Style.ABSOLUTE,
            value = "episode/${instance.id}/actor/${instance.id}",
            rel = "getAllActorOfEpisode",
            title = "actor",
            type = "application/json"
    )
    private Link season;

    public Episode(final int number, final String title, final String director, final LocalDate dateOfBroad){
        this.number = number;
        this.title = title;
        this.director = director;
        this.dateOfBroad = dateOfBroad;
    }

    public int getNumber()   {
        return number;
    }

    public void setNumber(final int number){
        this.number = number;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(final String title){
        this.title = title;
    }

    public String getDirector(){
        return director;
    }

    public void setDirector(final String director){
        this.director = director;
    }

    @JsonConverter( JsonDateTimeConverter.class )
    public LocalDate getDateOfBroad(){
        return dateOfBroad;
    }

    @JsonConverter( JsonDateTimeConverter.class )
    public void setDateOfBroad(final LocalDate dateOfBroad){
        this.dateOfBroad = dateOfBroad;
    }

    @Override public String toString( )
    {
        return "Episode{" +
                "id=" + id +
                ", number='" + number +
                ", title='" + title + '\'' +
                ", director='" + director + '\'' +
                ", dateOfBroad='" + dateOfBroad +
                '}';
    }
}
