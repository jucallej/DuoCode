/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author jcarlos
 */
@javax.ws.rs.ApplicationPath("rest")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(com.test.CandidatosResource.class);
        resources.add(com.test.EjerciciosResource.class);
        resources.add(com.test.EnunciadosResource.class);
        resources.add(com.test.EnviosResource.class);
        resources.add(com.test.HolaMundoResource.class);
        resources.add(com.test.LeccionesResource.class);
        resources.add(com.test.LenguajesResource.class);
        resources.add(com.test.TemasResource.class);
        resources.add(com.test.UsuariosResource.class);
    }
    
}
