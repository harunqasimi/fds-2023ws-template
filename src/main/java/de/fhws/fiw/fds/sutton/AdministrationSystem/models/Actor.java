package de.fhws.fiw.fds.sutton.AdministrationSystem.models;

import de.fhws.fiw.fds.sutton.client.Link;
import de.fhws.fiw.fds.sutton.server.models.AbstractModel;
import org.glassfish.jersey.linking.InjectLink;

import java.io.Serializable;

public class Actor extends AbstractModel implements Serializable {
    private String firstname;
    private String lastname;
    private String characterFirstname;
    private String characterLastname;

    @InjectLink(
            style = InjectLink.Style.ABSOLUTE,
            value = "episode/{instance.id}/actor/{instance.id}",
            rel = "self",
            title = "self",
            type = "application/json",
            condition = "${instance.primaryId != 0}"
    )
    private Link SelfLinkOnSecond;

    @InjectLink(
            style = InjectLink.Style.ABSOLUTE,
            value = "actor/{instance.id}",
            rel = "self",
            title = "self",
            type = "application/json",
            condition = "${instance.primaryId != 0}"
    )
    private Link selfLinkPrimary;

    public Actor(final String firstname, final String lastname, final String characterFirstname, final String characterLastname) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.characterFirstname = characterFirstname;
        this.characterLastname = characterLastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(final String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(final String lastname) {
        this.lastname = lastname;
    }

    public String getCharacterFirstname() {
        return characterFirstname;
    }

    public void setCharacterFirstname(final String characterFirstname) {
        this.characterFirstname = characterFirstname;
    }

    public String getCharacterLastname() {
        return characterLastname;
    }

    public void setCharacterLastname(final String characterLastname) {
        this.characterLastname = characterLastname;
    }

    @Override public String toString( )
    {
        return "Actor{" +
                ", id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", characterFirstname='" + characterFirstname + '\'' +
                ", characterLastname='" + characterLastname + '\'' +
                '}';
    }
}
