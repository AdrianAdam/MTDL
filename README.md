A college project. Full text here:

                                RobotWorld - Control Interface for Educational Robot

Il y a un set des robots non-humanoïdes qui peuvent accomplir des tâches collaboratives dans un certain scénario a l’intérieur d’un bâtiment.   
 
Vous devez créer une application avec une interface graphique permettant la communication avec les robots. L'ordinateur sur lequel votre application est installée est considéré comme le serveur et chaque robot est considéré comme un client. Le serveur enverra aux robots la configuration initiale (position et caractéristiques) et des commandes simples (turn_right / turn_left / go_forward / go_backwards / stop) et sera en mesure de recevoir des clients leur statut actuel : working/stopped/moving.
L’application doit avoir les fonctionnalités suivantes :
Login/logout
Création du robot : id, nom, caractéristiques – type du robot (e.g. 4 roues, 2 roues, chenillé etc.), icône, image, connectivité (Bluetooth, IRDA etc.)  (Maximum 10 robots peuvent être créés) et sa sauvegarde dans la base de données
Editer le nom et les caractéristiques d’un robot
Effacer un robot de la base de données
La visualisation des robots dans un panel/liste/grille  
Emettre / recevoir des commandes simples pour un robot : turn_right / turn_left / go_forward / go_backwards / stop – écriture / lecture des textes simples
Recevoir le statut courant des clients - working / stopped / moving et l’affichage dans la liste des robots
Création de la configuration initiale d'un scénario d'interaction entre les robots: vous devez disposer d'un canevas, placer les robots sur l'écran (par exemple, drag & drop) et envoyer leurs positions et fonctionnalités du serveur à chaque client - robot_id, robot_features_coordinates; cela devrait être sauvegardé aussi dans un fichier local.
 La documentation doit inclure :
Identification des acteurs, opérations (fonctionnalités) du système, règles métier (business rules)
Diagrammes UML: cas d’utilisation, diagrammes d’activités (au moins 3), diagrammes de
séquence (au moins 3), diagramme de classes, diagramme de paquets
Description détaillée de chaque cas d’utilisation
Description du modèle architecturale (composantes, design patterns – au moins 2, diagramme de
classes et – si nécessaire – diagramme de composantes)
Diagramme (schéma) de la base de données
Votre application doit être une application Java desktop implémentée utilisant Java FX ou web-based avec Web sockets, incluant toutes les fonctionnalités identifiées dans la description et inclues dans les diagrammes. En plus, toutes les informations des robots doivent être sauvegardées dans une base de données. Utiliser au moins 3 tests unitaires pour vérifier des fonctionnalités de votre application !
