# Serveur Application Intagram 42

Contributeur :
- Mathieu TURELIER
- Kevin GUISTET
- Cédric ZOUAOUI
- Vincent CAUDRON

## Java Maven

Pour build le projet
```
$ mvn archetype:generate -DgroupId=com.td.insta -DartifactId=front-app -DarchetypeGroupId=org.wildfly.archetype -DarchetypeArtifactId=wildfly-javaee7-webapp-archetype -DarchetypeVersion=8.2.0.Final
$ mvn clean package
```

## Serveur Wildfly

Lancement du serveur Wildfly
```
$ cd docker
$ docker-compose up --build
```

Accessible port localhost:8080

## Base de donnée Redis



## PHP Redis Admin

Accessible port localhost:8090

## Serveur apache

Accessible port localhost:8070