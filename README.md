# beepbeep_mmt_demo
## Librairies nécessaires (incluses)
- beepbeep3.jar : Coeur de BeepBeep3
- net.jar : Palette BB3 pour manipuler et capturer des paquets réseau.
- tuples.jar : Palette BB3 pour manipuler des tuples.

## Pour faire fonctionner la palette Net
- Télécharger JNetPcap : http://jnetpcap.com/
- Ajouter le repertoire où se trouve les librairies systèmes à `LD_LIBRARY_PATH`
- Se documenter sur les interweb... J'ai galéré à mettre ça en place et ça semble lié à mon système donc vous n'aurez surement pas les mêmes problèmes... 

# Liste des choses à faire 
## Lire des données depuis les traces de l'université de Kyoto
[x] Faire un parser permettant de transformer une ligne d'une trace en un objet Java.
[x] Construire un processeur qui reçoit une ligne et construit une `Connection`

## Exemples de chaines à implémenter
[ ] Chaine 1 : Bande passante cumulative (Exemple demandé par Sylvain )
[ ] Chaine 2 : Cluster en fonction des durées de connexion
[ ] Chaîne 3 : Cluster en fonction des heures de la journée 

## Élements importants à implémenter
[ ] Un processeur qui reçoit les données clusterisées (Help massiva) et qui génère une image
[ ] Un GroupProcessor K-means qui reçoit juste les connexions et les paramètres et qui génère les clusters
[ ] ...
