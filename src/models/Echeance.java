package models;



    public class Echeance {
        private int numero;
        private double mensualite;
        private double interets;
        private double capital;
        private double restant;

        public Echeance(int numero, double mensualite, double interets, double capital, double restant) {
            this.numero = numero;
            this.mensualite = mensualite;
            this.interets = interets;
            this.capital = capital;
            this.restant = restant;
        }

        public int getNumero() { return numero; }
        public double getMensualite() { return mensualite; }
        public double getInterets() { return interets; }
        public double getCapital() { return capital; }
        public double getRestant() { return restant; }

        @Override
        public String toString() {
            return String.format("Mois %d | Mensualité: %.2f | Intérêts: %.2f | Capital: %.2f | Restant: %.2f",
                    numero, mensualite, interets, capital, restant);
        }
}


