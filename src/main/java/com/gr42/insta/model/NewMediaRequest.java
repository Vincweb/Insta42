package com.gr42.insta.model;

import javax.xml.bind.annotation.XmlRootElement;
import javax.ws.rs.FormParam;
import org.jboss.resteasy.annotations.providers.multipart.PartType;

@XmlRootElement
public class NewMediaRequest {
    private String comment;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    private byte[] image;

    public byte[] getImage() {
        return image;
    }
    @FormParam("uploadedImage")
    @PartType("application/octet-stream")
    public void setImage(byte[] image) {
        this.image = image;
    }
}

