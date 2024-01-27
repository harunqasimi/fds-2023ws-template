package de.fhws.fiw.fds.sutton.AdministrationSystem.models;

import com.owlike.genson.annotation.JsonConverter;
import de.fhws.fiw.fds.sutton.client.Link;
import de.fhws.fiw.fds.sutton.server.models.AbstractModel;
import de.fhws.fiw.fds.sutton.utils.JsonDateTimeConverter;
import org.glassfish.jersey.linking.InjectLink;

import java.io.Serializable;
import java.time.LocalDate;

public class Series extends AbstractModel implements Serializable {
    private String name;
    private String countryOfProduction;
    private String genre;
    private String lengthOfEpisode;
    private LocalDate startOfBroadcasting;


    @InjectLink(
            style = InjectLink.Style.ABSOLUTE,
            value = "/series/${instance.id}",
            rel = "self",
            title = "self",
            type = "application/json"
    )
    private Link selfLink;
    @InjectLink(
            style= InjectLink.Style.ABSOLUTE,
            value = "series/${instance.id}/season",
            rel = "getAllSeasonOfSeries",
            title = "season",
            type = "application/json"
    )
    private Link season;

    public Series(final String name, final String countryOfProduction, final String genre, final String lengthOfEpisode, final LocalDate startOfBroadcasting){
        this.name = name;
        this.countryOfProduction = countryOfProduction;
        this.genre = genre;
        this.lengthOfEpisode = lengthOfEpisode;
        this.startOfBroadcasting = startOfBroadcasting;
    }

    public String getName(){
        return name;
    }

    public void setName(final String name){
        this.name = name;
    }

    public String getCountryOfProduction(){
        return countryOfProduction;
    }

    public void setCountryOfProduction(final String countryOfProduction){
        this.countryOfProduction = countryOfProduction;
    }

    public String getGenre(){
        return genre;
    }

    public void setGenre(final String genre){
        this.genre = genre;
    }

    public String getLengthOfEpisode(){
        return lengthOfEpisode;
    }

    public void setLengthOfEpisode(final String lengthOfEpisode){
        this.lengthOfEpisode = lengthOfEpisode;
    }

    @JsonConverter( JsonDateTimeConverter.class )
    public LocalDate getStartOfBroadcasting(){
       return startOfBroadcasting;
    }

    @JsonConverter( JsonDateTimeConverter.class )
    public void setStartOfBroadcasting(final LocalDate startOfBroadcasting){
        this.startOfBroadcasting = startOfBroadcasting;
    }

    @Override public String toString( )
    {
        return "TelevisonSeries{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", countryOfProduction='" + countryOfProduction + '\'' +
                ", genre='" + genre + '\'' +
                ", lengthOfEp='" + lengthOfEpisode + '\'' +
                ", startOfBroad='" + startOfBroadcasting +
                '}';
    }
}
