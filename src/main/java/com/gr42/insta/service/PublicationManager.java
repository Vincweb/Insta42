package com.gr42.insta.service;

import com.gr42.insta.model.Member;
import com.gr42.insta.model.Publication;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Collection;
import java.util.logging.Logger;


@Stateless
public class PublicationManager {

    @Inject
    private Logger log;

    @Inject
    private EntityManager em;

    @Inject
    private Event<Member> memberEventSrc;

    public long store(Publication pub) throws Exception {
        log.info("Storing Publication");
        em.persist(pub);
        return pub.getId();
    }

    public void updateImageName (Publication pub){
        Publication newpub =em.find(Publication.class,pub.getId());
        newpub.setImageName(pub.getImageName());
        newpub.setImage(pub.getImage());
        em.merge(newpub);
        log.info("Storing Publication");

    }

    public Collection<Publication> findAllPublication() {
        Query query = em.createQuery("SELECT p FROM Publication p");
        return (Collection<Publication>) query.getResultList();
    }
}


