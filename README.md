# Les Amis de l'Escalde

Cette application est un site communautaire autour de l'escalade. Elle a été réalisé dans le cadre d'un parcours de formation Java sur le site Openclassrooms pour reconversion professionnelle.

### Pré-requis

Vous avez besoin d'installer:
•JDK 13  
•BDD Mysql  
•Maven  

### Installation

1 - Initialiser la base de données avec comme nom : ladedb (port 3306 - sinon à modifier dans application.properties une fois le code cloné)  
2 - Cloner le projet depuis le repository  
3 - Générer le war avec la commande suivante :  
```
mvn clean install
```
4 - Déployer le war sur un serveur Tomcat

Pour la base de données vous pouvez :

•la laisser déployer ses tables automatiquement au lancement de l'application grâce à hibernate et ensuite si vous le souhaitez, déployer le fichier: "ladedb - données seules.sql"

•déployer le schéma de BDD avec le fichier: "ladedb - structure seule.sql" et ensuite si vous le souhaitez, déployer le fichier: "ladedb - données seules.sql"

•déployer schéma et jeux de données avec le fichier : "ladedb - structure et données.sql" (avant de lancer l'application pour la première fois)

Si vous utilisez le jeux de données:

Plusieurs utilisateurs ont été créés :

Un administrateur:   
•Identifiant : admin Mot de passe : admin

Un Utilisateur:  
•Identifiant : util Mot de passe : util

Si vous n'utilisez pas le jeux de données:  
Vous pouvez inscrire un nouvel utilisateur une fois l'application lancée par le module d'inscription du site.  
Vous pourrez ensuite faire une élévation de privilèges directement en BDD.  
Grâce à l'id de l'utilisateur nouvellement créé, vous devez ajouter dans la table role une ligne avec la commande suivante:  
```
INSERT INTO `role`(`id_utilisateur`, `role`) VALUES ( id_util ,'ADMINISTRATOR')
```
## Construit avec:
* [Maven](https://maven.apache.org/)
* [SpringBoot](https://spring.io/projects/spring-boot)

## Auteur
Ludovic SOUPLET - Pour reconversion professionnelle via OpenclassRooms
