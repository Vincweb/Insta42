/*
 * JBoss, Home of Professional Open Source
 * Copyright 2013, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.gr42.insta.rest;


import com.gr42.insta.model.Publication;
import com.gr42.insta.service.PublicationManager;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.Validator;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.File;
import java.io.InputStream;
import java.util.Collection;
import java.util.logging.Logger;

import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

/**
 * JAX-RS Example
        * <p/>
        * This class produces a RESTful service to read/write the contents of the members table.
        */
@Path("/publications")
@RequestScoped
public class PublicationResourceRESTService {

    @Inject
    private Logger log;

    @Inject
    private Validator validator;

    @Inject
    PublicationManager publications;

    private String IMAGE_STORAGE_FILE = "C:\\Users\\Turel\\front-app\\target\\front-app.war";


    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Publication createPublication(@MultipartForm MultipartFormDataInput request) throws Exception {
        Publication pub = new Publication();
        byte[] img = null;
        try {
            pub.setComment(request.getFormDataMap().get("comment").get(0).getBodyAsString());
            long id = publications.store(pub);
            InputStream inputStream = request.getFormDataMap().get("image").get(0).getBody(InputStream.class, null);
            if (inputStream.available() != 0)
                img = IOUtils.toByteArray(inputStream);
            String imageName = "publication-" + id + ".jpg";
            pub.setImageName(imageName);
            pub.setImage("localhost:8080//front-app//"+ imageName);
            FileUtils.writeByteArrayToFile(new File(imageName), img);
            publications.updateImageName(pub);
            String pathName = System.getProperty("IMAGE_STORAGE_FILE", IMAGE_STORAGE_FILE);
        } finally {
        }
        return pub;
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Publication> listAllPublications() {
        return publications.findAllPublication();
    }

}
