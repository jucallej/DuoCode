/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Julián
 */
@XmlRootElement
public class ErrorYID {
    @XmlElement(name="error")
    private String error;
    @XmlElement(name="id")
    private int id;

    public ErrorYID() {
    }

    public ErrorYID(String error, int id) {
        this.error = error;
        this.id = id;
    }
    
    public ErrorYID(int id) {
        this.id = id;
        if (id >=0)
            this.error = "no";
        else
            this.error = "si";
    }

    public String getError() {
        return error;
    }

    public int getId() {
        return id;
    }
}
