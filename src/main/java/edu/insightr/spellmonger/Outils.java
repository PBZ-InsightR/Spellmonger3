package edu.insightr.spellmonger;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.*;

public class Outils {

    // TODO : utils class should be moved into another package if there's no interaction with others classes of the actual package

    // donner le login et true si il a gagné, false sinon et la fonction mis a jour le fichier Json des scores
    public static void updateJsonFile(String Login, boolean isWinner) {
        String filepath = System.getProperty("user.dir") + "/src/main/resources/scores.json";
        Map<String, User> m = Outils.readFileToMap(filepath);
        User p = m.get(Login);
        if (p != null) {
            double pourcentage;
            double tmp = Double.parseDouble(p.getScorePercent()) / 100 * (Double.parseDouble(p.getNbPlay()));
            p.setNbPlay((Double.parseDouble(p.getNbPlay()) + 1) + "");
            if (isWinner)
                pourcentage = (tmp + 1) * 100 / Double.parseDouble(p.getNbPlay());
            else
                pourcentage = tmp * 100 / Double.parseDouble(p.getNbPlay());
            p.setScorePercent(pourcentage + "");
        } else {
            if (isWinner)
                m.put(Login, new User(Login, 1, 100));
            else
                m.put(Login, new User(Login, 1, 0));
        }
        Outils.createFilewithMap(m, filepath);
    }

    //créé un fichier Json en lui passant une map de données
    public static void createFilewithMap(Map<String, User> data, String filepath) {

        JSONParser parser = new JSONParser();
        try {

            JSONObject jsonObject = new JSONObject();

            JSONArray scoreList = new JSONArray();

            for (String s : data.keySet()) {
                JSONObject p = new JSONObject();
                p.put("Login", s);
                p.put("NbPlay", data.get(s).getNbPlay());
                p.put("PourcentageScore", data.get(s).getScorePercent());
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
    public static Map<String, User> readFileToMap(String filepath) {

        JSONParser parser = new JSONParser();
        Map<String, User> data = new HashMap<>();
        try {

            Object obj = parser.parse(new FileReader(filepath));
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray scoreList = (JSONArray) jsonObject.get("scores");

            Iterator<JSONObject> iterator = scoreList.iterator();
            while (iterator.hasNext()) {
                JSONObject it = iterator.next();
                String Login = (String) it.get("login");
                double NbPlay = Double.parseDouble((String) it.get("nbPlay"));
                double p = Double.parseDouble((String) it.get("scorePercent"));
                data.put(Login, new User(Login, NbPlay, p));

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return Outils.sortByComparator(data, false);
    }

    // Pas utile a comprendre pour le projet
    // trie croissant --> true , false sinon
    private static Map<String, User> sortByComparator(Map<String, User> unsortMap, final boolean order) {

        List<Map.Entry<String, User>> list = new LinkedList<Map.Entry<String, User>>(unsortMap.entrySet());

        // Sorting the list based on values
        Collections.sort(list, new Comparator<Map.Entry<String, User>>() {
            public int compare(Map.Entry<String, User> o1,
                               Map.Entry<String, User> o2) {
                if (order) {
                    return o1.getValue().compareTo(o2.getValue());
                } else {
                    return o2.getValue().compareTo(o1.getValue());

                }
            }
        });

        // Maintaining insertion order with the help of LinkedList
        Map<String, User> sortedMap = new LinkedHashMap<String, User>();
        for (Map.Entry<String, User> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        return sortedMap;
    }


}
