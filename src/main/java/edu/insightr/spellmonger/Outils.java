package edu.insightr.spellmonger;

import edu.insightr.sample.Personne;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.*;

public class Outils {


    // donner le login et true si il a gagné, false sinon et la fonction mis a jour le fichier Json des scores
    public static void updateJsonFile(String Login, boolean isWinner) {
        String filepath = System.getProperty("user.dir") + "/src/main/resources/scores.json";
        Map<String, Personne> m = Outils.readFileToMap(filepath);
        Personne p = m.get(Login);
        if (p != null) {
            double pourcentage;
            double tmp = Double.parseDouble(p.getPourcentageScore()) / 100 * (Double.parseDouble(p.getNbPlay()));
            p.setNbPlay((Double.parseDouble(p.getNbPlay()) + 1) + "");
            if (isWinner)
                pourcentage = (tmp + 1) * 100 / Double.parseDouble(p.getNbPlay());
            else
                pourcentage = tmp * 100 / Double.parseDouble(p.getNbPlay());
            p.setPourcentageScore(pourcentage + "");
        } else {
            if (isWinner)
                m.put(Login, new Personne(Login, 1, 100));
            else
                m.put(Login, new Personne(Login, 1, 0));
        }
        Outils.createFilewithMap(m, filepath);
    }

    //créé un fichier Json en lui passant une map de données
    public static void createFilewithMap(Map<String, Personne> data, String filepath) {

        JSONParser parser = new JSONParser();
        try {

            JSONObject jsonObject = new JSONObject();

            JSONArray scoreList = new JSONArray();

            for (String s : data.keySet()) {
                JSONObject p = new JSONObject();
                p.put("Login", s);
                p.put("NbPlay", data.get(s).getNbPlay());
                p.put("PourcentageScore", data.get(s).getPourcentageScore());
                scoreList.add(p);
            }

            jsonObject.put("scores", scoreList);
            FileWriter fs = new FileWriter(filepath);
            jsonObject.writeJSONString(fs);
            fs.flush();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //lit un fichier et renvoi une MAP des données des scores
    public static Map<String, Personne> readFileToMap(String filepath) {

        JSONParser parser = new JSONParser();
        Map<String, Personne> data = new HashMap<>();
        try {

            Object obj = parser.parse(new FileReader(filepath));
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray scoreList = (JSONArray) jsonObject.get("scores");

            Iterator<JSONObject> iterator = scoreList.iterator();
            while (iterator.hasNext()) {
                JSONObject it = iterator.next();
                String Login = (String) it.get("Login");
                double NbPlay = Double.parseDouble((String) it.get("NbPlay"));
                double p = Double.parseDouble((String) it.get("PourcentageScore"));
                data.put(Login, new Personne(Login, NbPlay, p));

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return Outils.sortByComparator(data, false);
    }

    // Pas utile a comprendre pour le projet
    // trie croissant --> true , false sinon
    private static Map<String, Personne> sortByComparator(Map<String, Personne> unsortMap, final boolean order) {

        List<Map.Entry<String, Personne>> list = new LinkedList<Map.Entry<String, Personne>>(unsortMap.entrySet());

        // Sorting the list based on values
        Collections.sort(list, new Comparator<Map.Entry<String, Personne>>() {
            public int compare(Map.Entry<String, Personne> o1,
                               Map.Entry<String, Personne> o2) {
                if (order) {
                    return o1.getValue().compareTo(o2.getValue());
                } else {
                    return o2.getValue().compareTo(o1.getValue());

                }
            }
        });

        // Maintaining insertion order with the help of LinkedList
        Map<String, Personne> sortedMap = new LinkedHashMap<String, Personne>();
        for (Map.Entry<String, Personne> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        return sortedMap;
    }


}
