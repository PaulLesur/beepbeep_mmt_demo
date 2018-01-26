# beepbeep_mmt_demo
## Librairies nécessaires (incluses)
- beepbeep3.jar : Coeur de BeepBeep3
- net.jar : Palette BB3 pour manipuler et capturer des paquets réseau.
- tuples.jar : Palette BB3 pour manipuler des tuples.
- JFreeChart : permet de générer des graphiques à partir de sets de données.

## Pour faire fonctionner la palette Net
- Télécharger JNetPcap : http://jnetpcap.com/
- Ajouter le repertoire où se trouve les librairies systèmes à `LD_LIBRARY_PATH`
- Se documenter sur les interweb... J'ai galéré à mettre ça en place et ça semble lié à mon système donc vous n'aurez surement pas les mêmes problèmes... 


## Exemples présents sur le dépôt
- [x] Chaîne 1 : Cluster en fonction des durées de connexion
- [x] Chaîne 2 : Cluster en fonction des heures de la journée 
- [ ] Chaîne 3 : Cluster en fonction des données échangées et des adresses IP sources et destination 
- [x] Chaîne 4 : Distribution des connexions sur la journée
- [x] Chaîne 5 : Distribution des connexions en fonction du port

## Données 
Les données utilisées pour les différents exemples sont extraites des [benchmarks effectués](http://www.takakura.com/Kyoto_data/BenchmarkData-Description-v5.pdf) par l'Université de Kyoto.
