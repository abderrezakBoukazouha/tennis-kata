Tennis kata :
- Chaque test decris un senario particulier : Jeu / Tie break / Set / Match
- La selection d'un vinqueur pour un point se fait de façon aleatoire , donc les jeux peuvent etre plus au moins court ou long (minimum 4 points, Max ? ça peut partir à l'infini).
- Toutes les reglèes du Tennis ont été respecté.

  Axes d'améliorations :
  - Plus de Stats concrètes.
  - Améliorer l'affichage du match.

___

### Remarques d'architecture
*Je remet pas en cause ton architecture, je te donne juste comment moi je l'aurai vu et donc comment j'aurai organisé le code* 

Une partie de tennis se joue entre 2 joueurs et se compose de Match, Set et Jeu \
Un match se compose de 2 à 3 sets \
Un set se compose de 6 à 13 jeux (6 jeux gagnants pour chaque joueur + un jeu "tiebreak") \
Un jeu a entre 4 et ***infini*** points

En suivant cette logique, je me serai attendu à ce qu'un Match initialise par lui-même ses Sets,
qu'un Set initialise par lui-même ses Jeux (et donc optionnellement un TieBreak selon les conditions) 
et qu'un jeu compte par lui-même ses Points. \
Dans ta structure actuelle, tu dois tout initialiser au plus haut niveau puis les injecter les uns dans les autres
et ils se réinitialise mutuellement par la suite suivant l'évolution du match.

Pour aller plus loin : les jeux, sets et matchs ont des règles très similaires :
- une règle pour gagner un point
- une règle pour gagner un jeu/set/match
- 