package de.fhws.fiw.fds.sutton.AdministrationSystem.models;

import de.fhws.fiw.fds.sutton.client.Link;
import de.fhws.fiw.fds.sutton.server.models.AbstractModel;
import org.glassfish.jersey.linking.InjectLink;

import java.io.Serializable;

public class Season extends AbstractModel implements Serializable {
    private int number;
    private int yearOfBroadcasting;

    @InjectLink(
            style = InjectLink.Style.ABSOLUTE,
            value = "/season/${instance.id}",
            rel = "self",
            title = "self",
            type = "application/json"
    )
    private Link selfLink;
    @InjectLink(
            style= InjectLink.Style.ABSOLUTE,
            value = "season/${instance.id}/episode",
            rel = "getAllEpisodeOfSeason",
            title = "episode",
            type = "application/json"
    )
    private Link episode;

    public Season(final int number, final int yearOfBroadcasting){
        this.number = number;
        this.yearOfBroadcasting = yearOfBroadcasting;
    }

    public void setNumber(final int number) {
        this.number = number;
    }

    public int getYearOfBroadcasting() {
        return yearOfBroadcasting;
    }

    public void setYearOfBroadcasting(final int yearOfBroadcasting) {
        this.yearOfBroadcasting = yearOfBroadcasting;
    }

    public int getNumber() {
        return number;
    }

    @Override public String toString( )
    {
        return "Season{" +
                "id=" + id +
                ", number='" + number +
                ", yearOfBroad='" + yearOfBroadcasting + '\'' +
                '}';
    }
}
