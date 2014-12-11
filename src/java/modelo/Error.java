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
public class Error {
    @XmlElement(name="error")
    private String error;

    public Error() {
    }

    public Error(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }
}
