# T-DEV-700-T-DEV-700_msc2023_group-17


## Lancement Projet

Lancer le docker-compose pour avoir une base de données fonctionnelles

Étants donné que le docker n'est pas terminé, il faut lancer le back et le front manuellement

Le back est facilement lançable avec intelliji tout d'abord faire un maven clean install (Ligne de commande : mvn package) puis run le projet

Le front, il est possible de crée une apk avec la commande flutter build apk --split-per-abi

Pour cela, il faut d'abord installer flutter, la documentation étant très complète sur leur site

Une fois flutter installé il est également très simple de le lancer sur vscode avec le plugin flutter en ouvrant seulement le dossier app_mobile

## Définitions routes back

#### Comptes

###### Route de récupération de compte

GET [localhost:8080/compte/{idCompte}]()

##### Paramètres
_idCompte_ : id du compte à récupérer

Pas d'objet JSON à envoyer. L'id est a insérer dans l'URL directement

Objet renvoyé par le back (exemple pour le compte 1 si existant)
```json
{
    "id": 1,
    "solde": 109.07
}
```

###### Route de création de compte

POST [localhost:8080/compte]()

#####Paramètres

Objet JSON de type compte à envoyer. Pas nécessaire d'indiquer l'id (créé par la BDD à l'insertion)

```json
{
    "solde": 550
}
```

Objet de type compte avec ID renvoyé par le back (exemple création compte 2)
```json
{
    "id": 2,
    "solde": 550
}
```

###### Route de création de compte

PUT [localhost:8080/compte/payment/{idCompte}]()

#####Paramètres

_idCompte_ : id du compte à utiliser pour le paiement. Défini par le QR code où la puce NFC.

Objet JSON de type cart à envoyer.
Seul l'id du cart est nécessaire, le back s'occupe de récupérer le cart correspondant et de calculer la possibilité de paiement du compte.

```json
{
    "id": 2
}
```

Objet de type compte avec ID renvoyé par le back (exemple création compte 2)
```json
{
    "id": 2,
    "solde": 550
}
```

#### Produits
[localhost:8080/products/]()
```json
{
  
}
```

#### Cart
[localhost:8080/cart/]()
```json
{
  
}
```