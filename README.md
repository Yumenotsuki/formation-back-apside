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

Code à entrer sous PgAdmin ou tout autre éditeur pour postgres :

- Pour avoir la table dofus :

CREATE TABLE dofus(
	_id int not null primary key,
	ankamaId int,
	name text,
	level int,
	type text,
	imgUrl varchar(500),
	url varchar(500)
);

with dofus_monsters (doc) as (values(
	'[
	{
		"_id": 31,
		"name": "Larve Bleue",
		"type": "Larves",
		"imgUrl": "https://s.ankama.com/www/static.ankama.com/dofus/www/game/monsters/200/31.png",
		"url": "https://www.dofus.com/fr/mmorpg/encyclopedie/monstres/31-larve-bleue"
	},
	{
		"_id": 34,
		"name": "Larve Verte",
		"type": "Larves",
		"imgUrl": "https://s.ankama.com/www/static.ankama.com/dofus/www/game/monsters/200/34.png",
		"url": "https://www.dofus.com/fr/mmorpg/encyclopedie/monstres/34-larve-verte"
	},
	{
		"_id": 46,
		"name": "Larve Orange",
		"type": "Larves",
		"imgUrl": "https://s.ankama.com/www/static.ankama.com/dofus/www/game/monsters/200/46.png",
		"url": "https://www.dofus.com/fr/mmorpg/encyclopedie/monstres/46-larve-orange"
	},
	{
		"_id": 47,
		"name": "Abraknyde",
		"type": "Abraknydiens",
		"imgUrl": "https://s.ankama.com/www/static.ankama.com/dofus/www/game/monsters/200/47.png",
		"url": "https://www.dofus.com/fr/mmorpg/encyclopedie/monstres/47-abraknyde"
	},
	{
		"_id": 48,
		"name": "Tournesol Sauvage",
		"type": "Plantes des champs",
		"imgUrl": "https://s.ankama.com/www/static.ankama.com/dofus/www/game/monsters/200/48.png",
		"url": "https://www.dofus.com/fr/mmorpg/encyclopedie/monstres/48-tournesol-sauvage"
	
	},
	{
		"_id": 52,
		"name": "Arakne",
		"type": "Vermines des champs",
		"imgUrl": "https://s.ankama.com/www/static.ankama.com/dofus/www/game/monsters/200/52.png",
		"url": "https://www.dofus.com/fr/mmorpg/encyclopedie/monstres/52-arakne"
	},
	{
		"_id": 53,
		"name": "Bwork Mage",
		"type": "Bworks",
		"imgUrl": "https://s.ankama.com/www/static.ankama.com/dofus/www/game/monsters/200/53.png",
		"url": "https://www.dofus.com/fr/mmorpg/encyclopedie/monstres/53-bwork-mage"
	},
	
	{
		"_id": 54,
		"name": "Chafer",
		"type": "Chafers",
		"imgUrl": "https://s.ankama.com/www/static.ankama.com/dofus/www/game/monsters/200/54.png",
		"url": "https://www.dofus.com/fr/mmorpg/encyclopedie/monstres/54-chafer"
	},
	{
		"_id": 55,
		"name": "Gelée Bleue",
		"type": "Gelées",
		"imgUrl": "https://s.ankama.com/www/static.ankama.com/dofus/www/game/monsters/200/55.png",
		"url": "https://www.dofus.com/fr/mmorpg/encyclopedie/monstres/55-gelee-bleue"
	},
	{
		"_id": 56,
		"name": "Gelée Menthe",
		"type": "Gelées",
		"imgUrl": "https://s.ankama.com/www/static.ankama.com/dofus/www/game/monsters/200/56.png",
		"url": "https://www.dofus.com/fr/mmorpg/encyclopedie/monstres/56-gelee-menthe"
	},
	{
		"_id": 57,
		"name": "Gelée Fraise",
		"type": "Gelées",
		"imgUrl": "https://s.ankama.com/www/static.ankama.com/dofus/www/game/monsters/200/57.png",
		"url": "https://www.dofus.com/fr/mmorpg/encyclopedie/monstres/57-gelee-fraise"
	},
	{
		"_id": 58,
		"name": "Gelée Royale Bleue",
		"type": "Gelées",
		"imgUrl": "https://s.ankama.com/www/static.ankama.com/dofus/www/game/monsters/200/58.png",
		"url": "https://www.dofus.com/fr/mmorpg/encyclopedie/monstres/58-gelee-royale-bleue"
	},
	{
		"_id": 59,
		"name": "Champ Champ",
		"type": "Vermines des champs",
		"imgUrl": "https://s.ankama.com/www/static.ankama.com/dofus/www/game/monsters/200/59.png",
		"url": "https://www.dofus.com/fr/mmorpg/encyclopedie/monstres/59-champ-champ"
	},
	{
		"_id": 61,
		"name": "Moskito",
		"type": "Vermines des champs",
		"imgUrl": "https://s.ankama.com/www/static.ankama.com/dofus/www/game/monsters/200/61.png",
		"url": "https://www.dofus.com/fr/mmorpg/encyclopedie/monstres/61-moskito"
	}
	]'::json))
	INSERT INTO dofus(_id, name, type, imgUrl, url)
	SELECT p.* FROM dofus_monsters l CROSS JOIN lateral
	json_populate_recordset(NULL::dofus, doc) AS p ON conflict(_id)
	do UPDATE SET name = excluded.name;


- Pour avoir la table pokemon :

CREATE TABLE pokemons(
	id text not null primary key,
	name text,
	imageUrl text
);

with pokemon (doc) as (values(
	'[
	 {
            "id": "xy7-10",
            "name": "Vespiquen",
            "imageUrl": "https://images.pokemontcg.io/xy7/10.png"
	},
	{
            "id": "dp6-90",
            "name": "Cubone",
            "imageUrl": "https://images.pokemontcg.io/dp6/90.png"
	},
	 {
            "id": "pl2-103",
            "name": "Alakazam 4",
            "imageUrl": "https://images.pokemontcg.io/pl2/103.png"
	},
	 {
            "id": "ex8-100",
            "name": "Hariyama ex",
            "imageUrl": "https://images.pokemontcg.io/ex8/100.png"
	},
	{
            "id": "xy7-4",
            "name": "Bellossom",
            "imageUrl": "https://images.pokemontcg.io/xy7/4.png"
	},
	{
            "id": "ex16-1",
            "name": "Aggron",
            "imageUrl": "https://images.pokemontcg.io/ex16/1.png"
	},
	{
            "id": "xy11-41",
            "name": "Joltik",
            "imageUrl": "https://images.pokemontcg.io/xy11/41.png"
	},
	{
            "id": "pl2-104",
            "name": "Floatzel GL",
            "imageUrl": "https://images.pokemontcg.io/pl2/104.png"
	},
	 {
            "id": "dp6-107",
            "name": "Misdreavus",
            "nationalPokedexNumber": 200,
            "imageUrl": "https://images.pokemontcg.io/dp6/107.png"
	},
	{
            "id": "xy0-14",
            "name": "Greninja",
            "nationalPokedexNumber": 658,
            "imageUrl": "https://images.pokemontcg.io/xy0/14.png"
	}
	]'::json))
	INSERT INTO pokemons(id, name, imageUrl)
	SELECT p.* FROM pokemon l CROSS JOIN lateral
	json_populate_recordset(NULL::pokemons, doc) AS p ON conflict(id)
	do UPDATE SET name = excluded.name;
  
  NB: Ne pas vous inquiéter si la colonne imgurl et imageurl sont "null". Il y a une manipulation à faire visiblement dans la base de données pour importer les liens des images qui n'ont pas un lien url mais je n'ai pas encore réussi à trouver la bonne manipulation. Je mettrais le README à jour lorsque j'aurais trouvé.

    

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

GET localhost:8080/api/dofus
Récupère des données provenant de l'api Dofus, route monsters
NB: le champs imgUrl est vide pour l'instant car je n'ai pas réussi à trouver comment importer les images qui ne sont pas des urls. Je ferai un fix quand j'aurais trouvé

Réponse attendue si la requête réussie

[
    {
        "id": 31,
        "name": "Larve Bleue",
        "type": "Larves",
        "imgUrl": null,
        "url": "https://www.dofus.com/fr/mmorpg/encyclopedie/monstres/31-larve-bleue"
    },
    {
        "id": 34,
        "name": "Larve Verte",
        "type": "Larves",
        "imgUrl": null,
        "url": "https://www.dofus.com/fr/mmorpg/encyclopedie/monstres/34-larve-verte"
    },
    {
        "id": 46,
        "name": "Larve Orange",
        "type": "Larves",
        "imgUrl": null,
        "url": "https://www.dofus.com/fr/mmorpg/encyclopedie/monstres/46-larve-orange"
    },
    {
        "id": 47,
        "name": "Abraknyde",
        "type": "Abraknydiens",
        "imgUrl": null,
        "url": "https://www.dofus.com/fr/mmorpg/encyclopedie/monstres/47-abraknyde"
    },
    {
        "id": 48,
        "name": "Tournesol Sauvage",
        "type": "Plantes des champs",
        "imgUrl": null,
        "url": "https://www.dofus.com/fr/mmorpg/encyclopedie/monstres/48-tournesol-sauvage"
    },
    {
        "id": 52,
        "name": "Arakne",
        "type": "Vermines des champs",
        "imgUrl": null,
        "url": "https://www.dofus.com/fr/mmorpg/encyclopedie/monstres/52-arakne"
    },
    {
        "id": 53,
        "name": "Bwork Mage",
        "type": "Bworks",
        "imgUrl": null,
        "url": "https://www.dofus.com/fr/mmorpg/encyclopedie/monstres/53-bwork-mage"
    },
    {
        "id": 54,
        "name": "Chafer",
        "type": "Chafers",
        "imgUrl": null,
        "url": "https://www.dofus.com/fr/mmorpg/encyclopedie/monstres/54-chafer"
    },
    {
        "id": 55,
        "name": "Gelée Bleue",
        "type": "Gelées",
        "imgUrl": null,
        "url": "https://www.dofus.com/fr/mmorpg/encyclopedie/monstres/55-gelee-bleue"
    },
    {
        "id": 56,
        "name": "Gelée Menthe",
        "type": "Gelées",
        "imgUrl": null,
        "url": "https://www.dofus.com/fr/mmorpg/encyclopedie/monstres/56-gelee-menthe"
    },
    {
        "id": 57,
        "name": "Gelée Fraise",
        "type": "Gelées",
        "imgUrl": null,
        "url": "https://www.dofus.com/fr/mmorpg/encyclopedie/monstres/57-gelee-fraise"
    },
    {
        "id": 58,
        "name": "Gelée Royale Bleue",
        "type": "Gelées",
        "imgUrl": null,
        "url": "https://www.dofus.com/fr/mmorpg/encyclopedie/monstres/58-gelee-royale-bleue"
    },
    {
        "id": 59,
        "name": "Champ Champ",
        "type": "Vermines des champs",
        "imgUrl": null,
        "url": "https://www.dofus.com/fr/mmorpg/encyclopedie/monstres/59-champ-champ"
    },
    {
        "id": 61,
        "name": "Moskito",
        "type": "Vermines des champs",
        "imgUrl": null,
        "url": "https://www.dofus.com/fr/mmorpg/encyclopedie/monstres/61-moskito"
    }
]

Si une erreur survient, l'api renvoie le message "Error when fetching Dofus monsters" ou une erreur Forbidden, access denied si l'utilisateur n'est pas connecté

GET localhost:8080/api/pokemon
Récupère des données provenant de l'api tcg pokemon, route cards
NB: le champs imageUrl est vide pour l'instant car je n'ai pas réussi à trouver comment importer les images qui ne sont pas des urls. Je ferai un fix quand j'aurais trouvé

Réponse attendue si la requête réussie

[
    {
        "id": "xy7-10",
        "name": "Vespiquen",
        "imageUrl": null
    },
    {
        "id": "dp6-90",
        "name": "Cubone",
        "imageUrl": null
    },
    {
        "id": "pl2-103",
        "name": "Alakazam 4",
        "imageUrl": null
    },
    {
        "id": "ex8-100",
        "name": "Hariyama ex",
        "imageUrl": null
    },
    {
        "id": "xy7-4",
        "name": "Bellossom",
        "imageUrl": null
    },
    {
        "id": "ex16-1",
        "name": "Aggron",
        "imageUrl": null
    },
    {
        "id": "xy11-41",
        "name": "Joltik",
        "imageUrl": null
    },
    {
        "id": "pl2-104",
        "name": "Floatzel GL",
        "imageUrl": null
    },
    {
        "id": "dp6-107",
        "name": "Misdreavus",
        "imageUrl": null
    },
    {
        "id": "xy0-14",
        "name": "Greninja",
        "imageUrl": null
    }
]

Si une erreur survient le message "Error when fetching pokemons" s'affiche ou bien une erreur forbidden, access denied si l'utilisateur n'est pas conencté à son compte.

/*Peut-être route à revoir, à voir*/

GET localhost:8080/api/profile

Réponse attendue

{
"username": "your username",
"email": "your email"
}

S'il y a une erreur (hors authentification), le message "This user profile does not exist" s'affiche


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

Liens vers les API utilisées :

- https://docs.pokemontcg.io/
- https://dofapi.fr/











