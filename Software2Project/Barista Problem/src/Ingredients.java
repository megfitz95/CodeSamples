public enum Ingredients {
    COCOA {
        public double getCost() {
            return 0.90;
        }

        @Override
        public String toString() {
            return "Cocoa";
        }
    },
    COFFEE {
        public double getCost(){
            return 0.75;
        }

        @Override
        public String toString() {
            return "Coffee";
        }
    },
    CREAM {
        public double getCost() {
            return 0.25;
        }

        @Override
        public String toString() {
            return "Cream";
        }
    },
    DECAF_COFFEE {
        public double getCost() {
            return 0.75;
        }

        @Override
        public String toString() {
            return "Decaf Coffee";
        }
    },
    ESPRESSO {
        public double getCost() {
            return 1.10;
        }

        @Override
        public String toString() {
            return "Espresso";
        }
    },
    FOAMED_MILK {
        public double getCost() {
            return 0.35;
        }

        @Override
        public String toString() {
            return "Foamed Milk";
        }
    },
    STEAMED_MILK {
        public double getCost() {
            return 0.35;
        }

        @Override
        public String toString() {
            return "Steamed Milk";
        }
    },
    SUGAR {
        public double getCost() {
            return 0.25;
        }

        @Override
        public String toString() {
            return "Sugar";
        }
    },
    WHIPPED_CREAM {
        public double getCost() {
            return 1.00;
        }

        @Override
        public String toString() {
            return "Whipped Cream";
        }
    };

    public abstract double getCost();

}
