public class EffectCard extends ColoredCard {
    private String effect;

    public EffectCard(String effect, char color) {
        super(color);
        if (effectCheck(effect))
            this.effect = effect;
        else
            System.out.println("invalid effect");
    }

    private boolean effectCheck(String effect) {
        String[] effectList = {"ban", "switchTurn", "draw"};
        for (String s : effectList) {
            if (s.equals(effect))
                return true;
        }
        return false;
    }

    /**
     * A method to return effect of the card
     *
     * @return the effect of the card
     */
    public String getEffect() {
        return effect;
    }

    @Override
    public void printCard() {
        if (super.getFaceDown())
            super.printCard();
        else {
            String[] effectList = {"ban", "switchTurn", "draw"};
            int i = 0;
            for (int j = 0; j < 3; j++) {
                if (effectList[j].equals(effect))
                    i = j;
            }
            switch (i) {
                case (0): {
                    System.out.println(colorCheck() + "|X          X|");
                    System.out.println("|       " + super.getColor() + "    |");
                    System.out.println("|X          X|" + ANSI_RESET);
                    break;
                }
                case (1): {
                    System.out.println(colorCheck() + "|->        ->|");
                    System.out.println("|      " + super.getColor() + "     |");
                    System.out.println("|<-        <-|" + ANSI_RESET);
                    break;
                }
                case (2): {
                    System.out.println(colorCheck() + "[2+" + "        " + getColor() + "|");
                    System.out.println("|     2+" + "    |");
                    System.out.println("|" + getColor() + "        " + "2+]" + ANSI_RESET);
                    break;
                }
                default:
                    System.out.println("invalid effect in EffectCard");
            }
        }
    }

    private String colorCheck() {
        switch (getColor()) {
            case ('r'): {
                return ANSI_RED;
            }
            case ('b'): {
                return ANSI_BLUE;
            }
            case ('y'): {
                return ANSI_YELLOW;
            }
            case ('g'): {
                return ANSI_GREEN;
            }
            default:
                return ANSI_RESET;
        }
    }
}
