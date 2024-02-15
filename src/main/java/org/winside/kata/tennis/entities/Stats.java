package org.winside.kata.tennis.entities;

import java.util.ArrayList;
import java.util.List;

// Un record est censé être par définition "immutable", or tu utilises ici une liste qui par définition ne l'est pas
// Il faudrait donc pour bien respécter le record créer une copie défensive de la liste passé dans le constructeur puis également dans le getter
// Dans ton cas, cette classe n'est pas très utile, tu aurais pu te contenter d'avoir une "List<String> globalScore" dans "Set" avec la méthode printFinalMatchScore
public record Stats(List<String> globalScore) {

    // Exemple de copie défensive : (manque le traitement du equals et hashcode)
    public Stats {
        globalScore = new ArrayList<>(globalScore);
    }

    @Override
    public List<String> globalScore() {
        return new ArrayList<>(globalScore);
    }

    public void printFinalMatchScore() {
        for (String setScore : globalScore) {
            System.out.print(setScore + "   ");

        }
    }
}
