package model;

import java.util.HashMap;

/**
 * Classe modèle pour l'objet Formation
 *
 * @author Yassine Doghri
 */
public class Formation {

    private Integer id;
    private String nom;
    private int dureeSceance;
    private Planning planning;
    private HashMap<Integer, Module> listeModules = new HashMap();

    public Formation(Integer id, String nom, int dureeSceance, Planning planning, HashMap<Integer, Module> listeModules) {
        this.id = id;
        this.nom = nom;
        this.dureeSceance = dureeSceance;
        this.planning = planning;
        this.listeModules = listeModules;
    }

    public Formation() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getDureeSceance() {
        return dureeSceance;
    }

    public void setDureeSceance(int dureeSceance) {
        this.dureeSceance = dureeSceance;
    }

    public Planning getPlanning() {
        return planning;
    }

    public void setPlanning(Planning planning) {
        this.planning = planning;
    }

    public HashMap<Integer, Module> getListeModules() {
        return listeModules;
    }

    public Module getModule(Integer key) {
        return listeModules.get(key);
    }

    public Module getModuleByNom(String nom) {
        for (HashMap.Entry<Integer, Module> entry : listeModules.entrySet()) {
            if (entry.getValue().getNom().equals(nom)) {
                return entry.getValue();
            }
        }
        return null;
    }

    public void setListeModules(HashMap<Integer, Module> listeModules) {
        this.listeModules = listeModules;
    }

    public void addModule(Integer key, Module module) {
        this.listeModules.put(key, module);
    }

    public float getHeureTotalFormation() {
        int i = 0;
        for (HashMap.Entry<Integer, Module> unModule : this.getListeModules().entrySet()) {
            Module leModule = unModule.getValue();
            i += leModule.getListeSeances().size() * this.getDureeSceance();
        }
        return (float) i / 60;
    }

    @Override
    public String toString() {
        String string = "<html><h3>Formation : " + nom + " [" + this.getHeureTotalFormation() + "h]<h3>"
                + "<p>Duree type /séance : " + (float) dureeSceance / 60 + "h</p>";

        if (listeModules.size() > 0) {
            string += "<p>Modules :</p><ul>";
            for (HashMap.Entry<Integer, Module> entry : listeModules.entrySet()) {
                string += "<li>" + entry.getValue() + "</li>";
            }
            string += "</ul>";
        } else {
            string += "<em>Aucun Module</em>";
        }
        return string + "</html>";
    }

}
