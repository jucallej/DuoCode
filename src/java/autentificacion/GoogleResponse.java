/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autentificacion;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Julián
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class GoogleResponse implements RespuestaServiciosLogin{
    @XmlElement(name="id")
    private String id;
    @XmlElement(name="name")
    private String name;
    @XmlElement(name="given_name")
    private String given_name;
    @XmlElement(name="family_name")
    private String family_name;
    @XmlElement(name="link")
    private String link;
    @XmlElement(name="picture")
    private String picture;
    @XmlElement(name="gender")
    private String gender;
    @XmlElement(name="locale")
    private String locale;

    public GoogleResponse() {
    }

    public GoogleResponse(String id, String name, String given_name, String family_name, String link, String picture, String gender, String locale) {
        this.id = id;
        this.name = name;
        this.given_name = given_name;
        this.family_name = family_name;
        this.link = link;
        this.picture = picture;
        this.gender = gender;
        this.locale = locale;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGiven_name() {
        return given_name;
    }

    public void setGiven_name(String given_name) {
        this.given_name = given_name;
    }

    public String getFamily_name() {
        return family_name;
    }

    public void setFamily_name(String family_name) {
        this.family_name = family_name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    @Override
    public String getPictureURL() {
        return this.picture;
    }
    
}
