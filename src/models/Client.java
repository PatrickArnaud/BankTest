package models;


    public class Client {
        private int id;        // correspond à client.id en base
        private String nom;

        // Constructeur pour créer un client avant insertion (id pas encore défini)
        public Client(String nom) {
            this.nom = nom;
        }

        // Constructeur pour récupérer un client depuis la DB
        public Client(int id, String nom) {
            this.id = id;
            this.nom = nom;
        }

        public int getId() { return id; }
        public String getNom() { return nom; }

        @Override
        public String toString() {
            return id + " - " + nom;
        }
    }


