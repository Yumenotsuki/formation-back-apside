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
    

Base de données :

Importer les tables dofus et pokemons se trouvant dans "formation-back-apside/src/main/resources/" dans PostgreSQL

Authentification :

- Pour inscrire un nouvel utilisateur :

POST localhost:8080/auth/signup

Body à renseigner pour créer un utilisateur

{
"username": "your username",
"password" "your password",
"email": "your email"
}

Réponse reçue si la création est un succès : 
"User successfully registred"

- Pour se connecter à son compte :

POST localhost:8080/auth/signin

Body à renseigner pour se connecter :

{
"username": "your username",
"password": "your password"
}

Réponse reçue si la requête à réussi :

{
    "jwt": "user jwt",
    "message": "Login is successful."
}

POST localhost:80800/auth/updatePassword

Requête permettant de changer son mot de passe.

Body à renseigner pour la requête :

{
"password": "your password"
}

Réponse attendue :

Si la requête a réussi, elle renvoie : "Your password has been successfully updated."
Sinon, elle envoie : "An error appears during the password update process".

GET/POST localhost:8080/auth/confirm-account

Lors de son inscription, l'utilisateur reçoit un email avec un lien permettant l'activation de son compte. Cette requête permet d'activer le compte de l'utilisateur

POST localhost:8080/auth/sendActivationMail

Envoie une requête envoyant un mail contenant un lien pour activer le compte de l'utilisateur.
Pour que l'envoi de mail fonctionne, aller dans le fichier application.properties et ajouter un identifiant gmail pour spring.mail.username et le mot de passe de l'adresse gmail pour spring.mail.password. Vous devez également autoriser la réception de mail des applications moins sécurisé dans votre application email recevant le mail envoyé. Pour finir, dans le dossier controllers, composant AuthController dans la requête sign up dans la partie permettant d'envoyer le mail d'activation vous devez modifier l'adresse email écrite par votre adresse email gmail précédemment ajouter dans le fichier application.properties.

Body à renseigner pour la requête :

{
"email": "your email"
}

Réponse attendue :

Sous Postman, le message : "An activate account link has been sent to you." s'affiche et vous devez récupérer un mail contenant un lien pour activer votre compte dans votre boîte email

Si erreur :

Utilisateur introuvable : le message "User not found" s'affiche
Si l'utilisateur est trouvé mais que l'envoie du mail ne marche pas, le message "An error appears during the activation process" s'affiche.


- Requêtes autres que l'authentification :

GET localhost:8080/api/dofus
Récupère des données provenant de l'api Dofus, route monsters

Réponse attendue si la requête réussie

[
    {
        "id": 31,
        "name": "Larve Bleue",
        "type": "Larves",
        "imgUrl": "lien image",
        "url": "https://www.dofus.com/fr/mmorpg/encyclopedie/monstres/31-larve-bleue"
    },
    {
        "id": 34,
        "name": "Larve Verte",
        "type": "Larves",
        "imgUrl": "lien image"null,
        "url": "https://www.dofus.com/fr/mmorpg/encyclopedie/monstres/34-larve-verte"
    },
    {
        "id": 46,
        "name": "Larve Orange",
        "type": "Larves",
        "imgUrl": "lien image",
        "url": "https://www.dofus.com/fr/mmorpg/encyclopedie/monstres/46-larve-orange"
    },
    {
        "id": 47,
        "name": "Abraknyde",
        "type": "Abraknydiens",
        "imgUrl": "lien image",
        "url": "https://www.dofus.com/fr/mmorpg/encyclopedie/monstres/47-abraknyde"
    },
    {
        "id": 48,
        "name": "Tournesol Sauvage",
        "type": "Plantes des champs",
        "imgUrl": "lien image",
        "url": "https://www.dofus.com/fr/mmorpg/encyclopedie/monstres/48-tournesol-sauvage"
    },
    {
        "id": 52,
        "name": "Arakne",
        "type": "Vermines des champs",
        "imgUrl": "lien image",
        "url": "https://www.dofus.com/fr/mmorpg/encyclopedie/monstres/52-arakne"
    },
    {
        "id": 53,
        "name": "Bwork Mage",
        "type": "Bworks",
        "imgUrl": "lien image",
        "url": "https://www.dofus.com/fr/mmorpg/encyclopedie/monstres/53-bwork-mage"
    },
    {
        "id": 54,
        "name": "Chafer",
        "type": "Chafers",
        "imgUrl": "lien image",
        "url": "https://www.dofus.com/fr/mmorpg/encyclopedie/monstres/54-chafer"
    },
    {
        "id": 55,
        "name": "Gelée Bleue",
        "type": "Gelées",
        "imgUrl": "lien image",
        "url": "https://www.dofus.com/fr/mmorpg/encyclopedie/monstres/55-gelee-bleue"
    },
    {
        "id": 56,
        "name": "Gelée Menthe",
        "type": "Gelées",
        "imgUrl": "lien image",
        "url": "https://www.dofus.com/fr/mmorpg/encyclopedie/monstres/56-gelee-menthe"
    },
    {
        "id": 57,
        "name": "Gelée Fraise",
        "type": "Gelées",
        "imgUrl": "lien image",
        "url": "https://www.dofus.com/fr/mmorpg/encyclopedie/monstres/57-gelee-fraise"
    },
    {
        "id": 58,
        "name": "Gelée Royale Bleue",
        "type": "Gelées",
        "imgUrl": "lien image",
        "url": "https://www.dofus.com/fr/mmorpg/encyclopedie/monstres/58-gelee-royale-bleue"
    },
    {
        "id": 59,
        "name": "Champ Champ",
        "type": "Vermines des champs",
        "imgUrl": "lien image",
        "url": "https://www.dofus.com/fr/mmorpg/encyclopedie/monstres/59-champ-champ"
    },
    {
        "id": 61,
        "name": "Moskito",
        "type": "Vermines des champs",
        "imgUrl": "lien image",
        "url": "https://www.dofus.com/fr/mmorpg/encyclopedie/monstres/61-moskito"
    }
]

Si une erreur survient, l'api renvoie le message "Error when fetching Dofus monsters" ou une erreur Forbidden, access denied si l'utilisateur n'est pas connecté

GET localhost:8080/api/pokemon
Récupère des données provenant de l'api tcg pokemon, route cards

Réponse attendue si la requête réussie

[
    {
        "id": "xy7-10",
        "name": "Vespiquen",
        "imageUrl": "lien image"
    },
    {
        "id": "dp6-90",
        "name": "Cubone",
        "imageUrl": "lien image"
    },
    {
        "id": "pl2-103",
        "name": "Alakazam 4",
        "imageUrl": "lien image"
    },
    {
        "id": "ex8-100",
        "name": "Hariyama ex",
        "imageUrl": "lien image"
    },
    {
        "id": "xy7-4",
        "name": "Bellossom",
        "imageUrl": "lien image"
    },
    {
        "id": "ex16-1",
        "name": "Aggron",
        "imageUrl": "lien image"
    },
    {
        "id": "xy11-41",
        "name": "Joltik",
        "imageUrl": "lien image"
    },
    {
        "id": "pl2-104",
        "name": "Floatzel GL",
        "imageUrl": "lien image"
    },
    {
        "id": "dp6-107",
        "name": "Misdreavus",
        "imageUrl": "lien image"
    },
    {
        "id": "xy0-14",
        "name": "Greninja",
        "imageUrl": "lien image"
    }
]

Si une erreur survient le message "Error when fetching pokemons" s'affiche ou bien une erreur forbidden, access denied si l'utilisateur n'est pas conencté à son compte.

GET localhost:8080/api/profile/{username}

Réponse attendue

{
"username": "your username",
"email": "your email"
}

S'il y a une erreur (hors authentification), le message "This user profile does not exist" s'affiche. Si un utilisateur essaie de modifier un profil n'étant pas le sien, le message d'erreur "Error. You do not have access to these datas or problem happen when trying to retrieve your data" s'affiche

PUT localhost:8080/api/profile/{username}

Pour l'instant, l'adresse email est l'unique champ modifiable. 

Body à renseigner pour la requête :

{
"email": "your new email"
}

Réponse attendue :

Si la requête est un succès, le message "user updated" s'affiche. Si un utilisateur essaie de modifier un profil qui n'est pas le sienne, le message d'erreur "Error.You can only update your profile" s'affiche. Sinon, s'il y a une erreur (hor authentification), le message "Error when updating this profile. Please try again" s'affiche.



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
- Spring, send an email with activation link once user is registred: https://www.pixeltrice.com/send-an-activation-link-to-email-for-the-new-user-registration-using-spring-boot-application/

Ressource supplémentaire (en anglais):

- Tutoriel Spring security avec authentification JWT: https://bezkoder.com/spring-boot-jwt-authentication/

Liens vers les API utilisées :

- https://docs.pokemontcg.io/
- https://dofapi.fr/











