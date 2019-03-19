package customjavafx.scene.control;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ObjectPropertyBase;
import javafx.css.PseudoClass;
import javafx.scene.control.Label;

public class BeadRoadLabel extends Label {

    public BeadRoadLabel() {
        this.getStyleClass().add("BeadRoadLabel");
    }

    public BeadRoadLabel(BeadRoadResult result) {
        this.getStyleClass().add("BeadRoadLabel");
        this.setResult(result);
    }

    //Add the Individual States
    private static final PseudoClass PSEUDO_CLASS_BANKER_WIN = PseudoClass.getPseudoClass("bankerWin");
    private static final PseudoClass PSEUDO_CLASS_BANKER_WIN_BANKER_PAIR = PseudoClass.getPseudoClass("bankerWinBankerPair");
    private static final PseudoClass PSEUDO_CLASS_BANKER_WIN_PLAYER_PAIR = PseudoClass.getPseudoClass("bankerWinPlayerPair");
    private static final PseudoClass PSEUDO_CLASS_BANKER_WIN_BOTH_PAIR = PseudoClass.getPseudoClass("bankerWinBothPair");

    private static final PseudoClass PSEUDO_CLASS_PLAYER_WIN = PseudoClass.getPseudoClass("playerWin");
    private static final PseudoClass PSEUDO_CLASS_PLAYER_WIN_BANKER_PAIR = PseudoClass.getPseudoClass("playerWinBankerPair");
    private static final PseudoClass PSEUDO_CLASS_PLAYER_WIN_PLAYER_PAIR = PseudoClass.getPseudoClass("playerWinPlayerPair");
    private static final PseudoClass PSEUDO_CLASS_PLAYER_WIN_BOTH_PAIR = PseudoClass.getPseudoClass("playerWinBothPair");

    private static final PseudoClass PSEUDO_CLASS_TIE_WIN = PseudoClass.getPseudoClass("tieWin");
    private static final PseudoClass PSEUDO_CLASS_TIE_WIN_BANKER_PAIR = PseudoClass.getPseudoClass("tieWinBankerPair");
    private static final PseudoClass PSEUDO_CLASS_TIE_WIN_PLAYER_PAIR = PseudoClass.getPseudoClass("tieWinPlayerPair");
    private static final PseudoClass PSEUDO_CLASS_TIE_WIN_BOTH_PAIR = PseudoClass.getPseudoClass("tieWinBothPair");

    private ObjectProperty<BeadRoadResult> result = new ObjectPropertyBase<BeadRoadResult>() {
        @Override
        protected void invalidated() {
            switch (get()) {
                case EMPTY:
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BOTH_PAIR, false);

                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BOTH_PAIR, false);

                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_WIN_BOTH_PAIR, false);
                    break;

                case BANKER_WIN:
                case BANKER_WIN_NATURAL:
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN, true);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BOTH_PAIR, false);

                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BOTH_PAIR, false);

                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_WIN_BOTH_PAIR, false);
                    break;

                case BANKER_WIN_BANKER_PAIR:
                case BANKER_WIN_BANKER_PAIR_NATURAL:
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BANKER_PAIR, true);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BOTH_PAIR, false);

                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BOTH_PAIR, false);

                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_WIN_BOTH_PAIR, false);
                    break;

                case BANKER_WIN_PLAYER_PAIR:
                case BANKER_WIN_PLAYER_PAIR_NATURAL:
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_PLAYER_PAIR, true);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BOTH_PAIR, false);

                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BOTH_PAIR, false);

                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_WIN_BOTH_PAIR, false);
                    break;
                case BANKER_WIN_BOTH_PAIR:
                case BANKER_WIN_BOTH_PAIR_NATURAL:
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BOTH_PAIR, true);

                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BOTH_PAIR, false);

                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_WIN_BOTH_PAIR, false);
                    break;

                case PLAYER_WIN:
                case PLAYER_WIN_NATURAL:
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BOTH_PAIR, false);

                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN, true);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BOTH_PAIR, false);

                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_WIN_BOTH_PAIR, false);
                    break;
                case PLAYER_WIN_BANKER_PAIR:
                case PLAYER_WIN_BANKER_PAIR_NATURAL:
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BOTH_PAIR, false);

                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BANKER_PAIR, true);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BOTH_PAIR, false);

                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_WIN_BOTH_PAIR, false);
                    break;
                case PLAYER_WIN_PLAYER_PAIR:
                case PLAYER_WIN_PLAYER_PAIR_NATURAL:
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BOTH_PAIR, false);

                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_PLAYER_PAIR, true);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BOTH_PAIR, false);

                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_WIN_BOTH_PAIR, false);
                    break;
                case PLAYER_WIN_BOTH_PAIR:
                case PLAYER_WIN_BOTH_PAIR_NATURAL:
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BOTH_PAIR, false);

                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BOTH_PAIR, true);

                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_WIN_BOTH_PAIR, false);
                    break;

                case TIE_WIN:
                case TIE_WIN_NATURAL:
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BOTH_PAIR, false);

                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BOTH_PAIR, false);

                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_WIN, true);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_WIN_BOTH_PAIR, false);
                    break;
                case TIE_WIN_BANKER_PAIR:
                case TIE_WIN_BANKER_PAIR_NATURAL:
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BOTH_PAIR, false);

                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BOTH_PAIR, false);

                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_WIN_BANKER_PAIR, true);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_WIN_BOTH_PAIR, false);
                    break;
                case TIE_WIN_PLAYER_PAIR:
                case TIE_WIN_PLAYER_PAIR_NATURAL:
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BOTH_PAIR, false);

                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BOTH_PAIR, false);

                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_WIN_PLAYER_PAIR, true);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_WIN_BOTH_PAIR, false);
                    break;
                case TIE_WIN_BOTH_PAIR:
                case TIE_WIN_BOTH_PAIR_NATURAL:
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BOTH_PAIR, false);

                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BOTH_PAIR, false);

                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_WIN_BOTH_PAIR, true);
                    break;
                default:
            }
        }

        @Override
        public Object getBean() {
            return BeadRoadLabel.this;
        }

        @Override
        public String getName() {
            return "BeadRoadLabel";
        }
    };

    public BeadRoadResult getResult() {
        return result.get();
    }

    public void setResult(BeadRoadResult result) {
        this.result.set(result);
    }

    public ObjectProperty<BeadRoadResult> resultProperty() {
        return result;
    }
}
