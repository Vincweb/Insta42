FROM jboss/wildfly
COPY ./target/front-app.war /opt/jboss/wildfly/standalone/deployments/

USER root

CMD /opt/jboss/wildfly/bin/standalone.sh -DIMAGE_STORE_PATH=$IMAGE_STORE_PATH -b 0.0.0.0