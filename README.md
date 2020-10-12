# formation-back-apside

Ce projet utilise :

- Java 11
- Maven 3.6.3
- Spring boot 2.3.4
- PostgreSQL 12.4

A installer sur votre ordinateur si ce n'est pas déjà fait :

- Java 11
- Maven 3.6.3
- PostgreSQL 12.4
- Intellij ou Eclipse IDE for Enterprise Java Developers
- Un éditeur pour intéragir avec Postgres. Pour ma part, j'utilise pgAdmin 4 (qui, je crois doit se télécharger automatiquement en même temps que la base sous Windows. Mais à vérifier)
- Postman pour pouvoir tester les routes

Pour faire fonctionner le back :

- Télécharger le projet
- Créer une base de donnée PostgreSQL
- Allez dans le fichier application.properties du projet :
    - spring.datasource.url contient le lien vers le jdbc postgresql (le module permettant la liaison avec la base de données) et l'url où se trouve votre base de données. Remplacer "localhost:5432" par le localhost de votre base de données et "formation2" par le nom de votre base de données
    - spring.datasource.username contient votre identifiant pour vous connecter à votre base de données. Remplacer "postgres" par votre propre identifiant
    - spring.datasource.password contient votre mot de passe pour accéder à votre base de données. Remplacer les étoiles par votre propre mot de passe (quand vous push sur Git ou autre, pensez bien à l'effacer)

Authentification :

- Pour inscrire un nouvel utilisateur :

POST localhost:8080/auth/signup

Body à renseigner pour créer un utilisateur

{
"username": "your email",
"password" "your password",
"email": "your email"
}

Réponse reçue si la création est un succès : 
"User successfully registred"

- Pour se connecter à son compte :

POST localhost:8080/auth/signin

Body à renseigner pour se connecter :

{
"username": "your email",
"password": "your password"
}

Réponse reçu si la requête à réussi :

{
    "jwt": "user jwt",
    "message": "Login is successful."
}

Pour tester que l'API fonctionne bien  :

- GET localhost:8080/test/home
route accessible à tous 

Réponse attendue:
Welcome

- GET localhost:8080/test/user
route uniquement accessible aux utilisateurs connecter => Faire requête signin avant d'accéder à cette route

Réponse attendue :
Welcome user

Si vous ne vous êtes pas identifié, l'API vous retourne une erreur forbidden, access denied.

tutoriels utilisés pour réaliser ce projet (en anglais) :

- Spring security et JWT : https://www.youtube.com/watch?v=X80nJ5T7YpE&list=PLqq-6Pq4lTTYTEooakHchTGglSvkZAjnE&index=12
- Spring security avec authentification JPA : https://www.youtube.com/watch?v=TNt3GHuayXs&list=PLqq-6Pq4lTTYTEooakHchTGglSvkZAjnE&index=8

Ressource supplémentaire (en anglais):

- Tutoriel Spring security avec authentification JWT: https://bezkoder.com/spring-boot-jwt-authentication/









