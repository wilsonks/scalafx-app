package customjavafx.scene.control;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ObjectPropertyBase;
import javafx.css.PseudoClass;
import javafx.scene.control.Label;

public class BigRoadLabel extends Label {

    public BigRoadLabel() {
        this.getStyleClass().add("BigRoadLabel");
    }

    public BigRoadLabel(BigRoadResult result) {
        this.getStyleClass().add("BigRoadLabel");
        this.setResult(result);
    }

    private int tieCount = 1;

    private static final PseudoClass PSEUDO_CLASS_TIE_WIN = PseudoClass.getPseudoClass("tieWin");

    private static final PseudoClass PSEUDO_CLASS_BANKER_WIN = PseudoClass.getPseudoClass("bankerWin");
    private static final PseudoClass PSEUDO_CLASS_BANKER_WIN_BANKER_PAIR = PseudoClass.getPseudoClass("bankerWinBankerPair");
    private static final PseudoClass PSEUDO_CLASS_BANKER_WIN_PLAYER_PAIR = PseudoClass.getPseudoClass("bankerWinPlayerPair");
    private static final PseudoClass PSEUDO_CLASS_BANKER_WIN_BOTH_PAIR = PseudoClass.getPseudoClass("bankerWinBothPair");

    private static final PseudoClass PSEUDO_CLASS_PLAYER_WIN = PseudoClass.getPseudoClass("playerWin");
    private static final PseudoClass PSEUDO_CLASS_PLAYER_WIN_BANKER_PAIR = PseudoClass.getPseudoClass("playerWinBankerPair");
    private static final PseudoClass PSEUDO_CLASS_PLAYER_WIN_PLAYER_PAIR = PseudoClass.getPseudoClass("playerWinPlayerPair");
    private static final PseudoClass PSEUDO_CLASS_PLAYER_WIN_BOTH_PAIR = PseudoClass.getPseudoClass("playerWinBothPair");

    private static final PseudoClass PSEUDO_CLASS_TIE_AFTER_BANKER_WIN = PseudoClass.getPseudoClass("bankerWinBeforeTie");
    private static final PseudoClass PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_BANKER_PAIR = PseudoClass.getPseudoClass("bankerWinBankerPairBeforeTie");
    private static final PseudoClass PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_PLAYER_PAIR = PseudoClass.getPseudoClass("bankerWinPlayerPairBeforeTie");
    private static final PseudoClass PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_BOTH_PAIR = PseudoClass.getPseudoClass("bankerWinBothPairBeforeTie");

    private static final PseudoClass PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN = PseudoClass.getPseudoClass("playerWinBeforeTie");
    private static final PseudoClass PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_BANKER_PAIR = PseudoClass.getPseudoClass("playerWinBankerPairBeforeTie");
    private static final PseudoClass PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_PLAYER_PAIR = PseudoClass.getPseudoClass("playerWinPlayerPairBeforeTie");
    private static final PseudoClass PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_BOTH_PAIR = PseudoClass.getPseudoClass("playerWinBothPairBeforeTie");

    private static final PseudoClass PSEUDO_CLASS_TIE_WIN_NATURAL = PseudoClass.getPseudoClass("tieWinNatural");

    private static final PseudoClass PSEUDO_CLASS_BANKER_WIN_NATURAL = PseudoClass.getPseudoClass("bankerWinNatural");
    private static final PseudoClass PSEUDO_CLASS_BANKER_WIN_BANKER_PAIR_NATURAL = PseudoClass.getPseudoClass("bankerWinBankerPairNatural");
    private static final PseudoClass PSEUDO_CLASS_BANKER_WIN_PLAYER_PAIR_NATURAL = PseudoClass.getPseudoClass("bankerWinPlayerPairNatural");
    private static final PseudoClass PSEUDO_CLASS_BANKER_WIN_BOTH_PAIR_NATURAL = PseudoClass.getPseudoClass("bankerWinBothPairNatural");

    private static final PseudoClass PSEUDO_CLASS_PLAYER_WIN_NATURAL = PseudoClass.getPseudoClass("playerWinNatural");
    private static final PseudoClass PSEUDO_CLASS_PLAYER_WIN_BANKER_PAIR_NATURAL = PseudoClass.getPseudoClass("playerWinBankerPairNatural");
    private static final PseudoClass PSEUDO_CLASS_PLAYER_WIN_PLAYER_PAIR_NATURAL = PseudoClass.getPseudoClass("playerWinPlayerPairNatural");
    private static final PseudoClass PSEUDO_CLASS_PLAYER_WIN_BOTH_PAIR_NATURAL = PseudoClass.getPseudoClass("playerWinBothPairNatural");

    private static final PseudoClass PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_NATURAL = PseudoClass.getPseudoClass("bankerWinBeforeTieNatural");
    private static final PseudoClass PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_BANKER_PAIR_NATURAL = PseudoClass.getPseudoClass("bankerWinBankerPairBeforeTieNatural");
    private static final PseudoClass PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_PLAYER_PAIR_NATURAL = PseudoClass.getPseudoClass("bankerWinPlayerPairBeforeTieNatural");
    private static final PseudoClass PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_BOTH_PAIR_NATURAL = PseudoClass.getPseudoClass("bankerWinBothPairBeforeTieNatural");

    private static final PseudoClass PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_NATURAL = PseudoClass.getPseudoClass("playerWinBeforeTieNatural");
    private static final PseudoClass PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_BANKER_PAIR_NATURAL = PseudoClass.getPseudoClass("playerWinBankerPairBeforeTieNatural");
    private static final PseudoClass PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_PLAYER_PAIR_NATURAL = PseudoClass.getPseudoClass("playerWinPlayerPairBeforeTieNatural");
    private static final PseudoClass PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_BOTH_PAIR_NATURAL = PseudoClass.getPseudoClass("playerWinBothPairBeforeTieNatural");


    private ObjectProperty<BigRoadResult> result = new ObjectPropertyBase<BigRoadResult>() {
        @Override
        protected void invalidated() {
            switch (get()) {
                case EMPTY:
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BOTH_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BOTH_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN, false);

                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_BOTH_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_BOTH_PAIR, false);

                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_WIN_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BANKER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_PLAYER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BOTH_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BANKER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_PLAYER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BOTH_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_NATURAL, false);

                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_BANKER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_PLAYER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_BOTH_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_BANKER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_PLAYER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_BOTH_PAIR_NATURAL, false);
                    break;
                case TIE_WIN:
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_WIN, true);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BOTH_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BOTH_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN, false);

                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_BOTH_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_BOTH_PAIR, false);

                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_WIN_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BANKER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_PLAYER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BOTH_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BANKER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_PLAYER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BOTH_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_NATURAL, false);

                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_BANKER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_PLAYER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_BOTH_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_BANKER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_PLAYER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_BOTH_PAIR_NATURAL, false);
                    break;
                case BANKER_WIN:
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN, true);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BOTH_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BOTH_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN, false);

                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_BOTH_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_BOTH_PAIR, false);

                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_WIN_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BANKER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_PLAYER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BOTH_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BANKER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_PLAYER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BOTH_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_NATURAL, false);

                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_BANKER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_PLAYER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_BOTH_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_BANKER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_PLAYER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_BOTH_PAIR_NATURAL, false);
                    break;
                case BANKER_WIN_BANKER_PAIR:
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BANKER_PAIR, true);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BOTH_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BOTH_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN, false);

                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_BOTH_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_BOTH_PAIR, false);

                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_WIN_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BANKER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_PLAYER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BOTH_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BANKER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_PLAYER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BOTH_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_NATURAL, false);

                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_BANKER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_PLAYER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_BOTH_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_BANKER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_PLAYER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_BOTH_PAIR_NATURAL, false);
                    break;
                case BANKER_WIN_PLAYER_PAIR:
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_PLAYER_PAIR, true);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BOTH_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BOTH_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN, false);

                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_BOTH_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_BOTH_PAIR, false);

                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_WIN_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BANKER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_PLAYER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BOTH_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BANKER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_PLAYER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BOTH_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_NATURAL, false);

                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_BANKER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_PLAYER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_BOTH_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_BANKER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_PLAYER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_BOTH_PAIR_NATURAL, false);
                    break;
                case BANKER_WIN_BOTH_PAIR:
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BOTH_PAIR, true);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BOTH_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN, false);

                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_BOTH_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_BOTH_PAIR, false);

                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_WIN_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BANKER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_PLAYER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BOTH_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BANKER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_PLAYER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BOTH_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_NATURAL, false);

                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_BANKER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_PLAYER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_BOTH_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_BANKER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_PLAYER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_BOTH_PAIR_NATURAL, false);
                    break;
                case PLAYER_WIN:
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BOTH_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN, true);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BOTH_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN, false);

                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_BOTH_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_BOTH_PAIR, false);

                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_WIN_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BANKER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_PLAYER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BOTH_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BANKER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_PLAYER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BOTH_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_NATURAL, false);

                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_BANKER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_PLAYER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_BOTH_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_BANKER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_PLAYER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_BOTH_PAIR_NATURAL, false);
                    break;
                case PLAYER_WIN_BANKER_PAIR:
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BOTH_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BANKER_PAIR, true);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BOTH_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN, false);

                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_BOTH_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_BOTH_PAIR, false);

                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_WIN_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BANKER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_PLAYER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BOTH_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BANKER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_PLAYER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BOTH_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_NATURAL, false);

                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_BANKER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_PLAYER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_BOTH_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_BANKER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_PLAYER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_BOTH_PAIR_NATURAL, false);
                    break;
                case PLAYER_WIN_PLAYER_PAIR:
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BOTH_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_PLAYER_PAIR, true);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BOTH_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN, false);

                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_BOTH_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_BOTH_PAIR, false);

                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_WIN_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BANKER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_PLAYER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BOTH_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BANKER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_PLAYER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BOTH_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_NATURAL, false);

                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_BANKER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_PLAYER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_BOTH_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_BANKER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_PLAYER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_BOTH_PAIR_NATURAL, false);
                    break;
                case PLAYER_WIN_BOTH_PAIR:
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BOTH_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BOTH_PAIR, true);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN, false);

                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_BOTH_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_BOTH_PAIR, false);

                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_WIN_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BANKER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_PLAYER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BOTH_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BANKER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_PLAYER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BOTH_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_NATURAL, false);

                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_BANKER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_PLAYER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_BOTH_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_BANKER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_PLAYER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_BOTH_PAIR_NATURAL, false);
                    break;
                case TIE_AFTER_BANKER_WIN:
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BOTH_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BOTH_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN, true);

                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_BOTH_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_BOTH_PAIR, false);

                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_WIN_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BANKER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_PLAYER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BOTH_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BANKER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_PLAYER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BOTH_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_NATURAL, false);

                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_BANKER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_PLAYER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_BOTH_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_BANKER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_PLAYER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_BOTH_PAIR_NATURAL, false);
                    break;
                case TIE_AFTER_BANKER_WIN_BANKER_PAIR:
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BOTH_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BOTH_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN, false);

                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_BANKER_PAIR, true);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_BOTH_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_BOTH_PAIR, false);

                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_WIN_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BANKER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_PLAYER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BOTH_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BANKER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_PLAYER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BOTH_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_NATURAL, false);

                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_BANKER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_PLAYER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_BOTH_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_BANKER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_PLAYER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_BOTH_PAIR_NATURAL, false);
                    break;
                case TIE_AFTER_BANKER_WIN_PLAYER_PAIR:
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BOTH_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BOTH_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN, false);

                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_PLAYER_PAIR, true);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_BOTH_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_BOTH_PAIR, false);

                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_WIN_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BANKER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_PLAYER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BOTH_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BANKER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_PLAYER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BOTH_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_NATURAL, false);

                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_BANKER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_PLAYER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_BOTH_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_BANKER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_PLAYER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_BOTH_PAIR_NATURAL, false);
                    break;
                case TIE_AFTER_BANKER_WIN_BOTH_PAIR:
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BOTH_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BOTH_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN, false);

                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_BOTH_PAIR, true);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_BOTH_PAIR, false);

                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_WIN_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BANKER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_PLAYER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BOTH_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BANKER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_PLAYER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BOTH_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_NATURAL, false);

                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_BANKER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_PLAYER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_BOTH_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_BANKER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_PLAYER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_BOTH_PAIR_NATURAL, false);
                    break;
                case TIE_AFTER_PLAYER_WIN:
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BOTH_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BOTH_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN, false);

                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_BOTH_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN, true);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_BOTH_PAIR, false);

                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_WIN_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BANKER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_PLAYER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BOTH_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BANKER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_PLAYER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BOTH_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_NATURAL, false);

                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_BANKER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_PLAYER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_BOTH_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_BANKER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_PLAYER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_BOTH_PAIR_NATURAL, false);
                    break;
                case TIE_AFTER_PLAYER_WIN_BANKER_PAIR:
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BOTH_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BOTH_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN, false);

                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_BOTH_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_BANKER_PAIR, true);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_BOTH_PAIR, false);

                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_WIN_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BANKER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_PLAYER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BOTH_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BANKER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_PLAYER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BOTH_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_NATURAL, false);

                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_BANKER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_PLAYER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_BOTH_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_BANKER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_PLAYER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_BOTH_PAIR_NATURAL, false);
                    break;
                case TIE_AFTER_PLAYER_WIN_PLAYER_PAIR:
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BOTH_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BOTH_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN, false);

                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_BOTH_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_PLAYER_PAIR, true);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_BOTH_PAIR, false);

                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_WIN_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BANKER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_PLAYER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BOTH_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BANKER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_PLAYER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BOTH_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_NATURAL, false);

                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_BANKER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_PLAYER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_BOTH_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_BANKER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_PLAYER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_BOTH_PAIR_NATURAL, false);
                    break;
                case TIE_AFTER_PLAYER_WIN_BOTH_PAIR:
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BOTH_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BOTH_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN, false);

                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_BOTH_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_BOTH_PAIR, true);

                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_WIN_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BANKER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_PLAYER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BOTH_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BANKER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_PLAYER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BOTH_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_NATURAL, false);

                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_BANKER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_PLAYER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_BOTH_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_BANKER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_PLAYER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_BOTH_PAIR_NATURAL, false);
                    break;
                case TIE_WIN_NATURAL:
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BOTH_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BOTH_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN, false);

                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_BOTH_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_BOTH_PAIR, false);

                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_WIN_NATURAL, true);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BANKER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_PLAYER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BOTH_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BANKER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_PLAYER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BOTH_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_NATURAL, false);

                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_BANKER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_PLAYER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_BOTH_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_BANKER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_PLAYER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_BOTH_PAIR_NATURAL, false);
                    break;
                case BANKER_WIN_NATURAL:
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BOTH_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BOTH_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN, false);

                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_BOTH_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_BOTH_PAIR, false);

                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_WIN_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_NATURAL, true);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BANKER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_PLAYER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BOTH_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BANKER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_PLAYER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BOTH_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_NATURAL, false);

                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_BANKER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_PLAYER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_BOTH_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_BANKER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_PLAYER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_BOTH_PAIR_NATURAL, false);
                    break;
                case BANKER_WIN_BANKER_PAIR_NATURAL:
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BOTH_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BOTH_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN, false);

                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_BOTH_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_BOTH_PAIR, false);

                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_WIN_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BANKER_PAIR_NATURAL, true);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_PLAYER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BOTH_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BANKER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_PLAYER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BOTH_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_NATURAL, false);

                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_BANKER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_PLAYER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_BOTH_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_BANKER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_PLAYER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_BOTH_PAIR_NATURAL, false);
                    break;
                case BANKER_WIN_PLAYER_PAIR_NATURAL:
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BOTH_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BOTH_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN, false);

                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_BOTH_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_BOTH_PAIR, false);

                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_WIN_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BANKER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_PLAYER_PAIR_NATURAL, true);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BOTH_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BANKER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_PLAYER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BOTH_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_NATURAL, false);

                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_BANKER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_PLAYER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_BOTH_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_BANKER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_PLAYER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_BOTH_PAIR_NATURAL, false);
                    break;
                case BANKER_WIN_BOTH_PAIR_NATURAL:
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BOTH_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BOTH_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN, false);

                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_BOTH_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_BOTH_PAIR, false);

                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_WIN_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BANKER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_PLAYER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BOTH_PAIR_NATURAL, true);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BANKER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_PLAYER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BOTH_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_NATURAL, false);

                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_BANKER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_PLAYER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_BOTH_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_BANKER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_PLAYER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_BOTH_PAIR_NATURAL, false);
                    break;
                case PLAYER_WIN_NATURAL:
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BOTH_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BOTH_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN, false);

                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_BOTH_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_BOTH_PAIR, false);

                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_WIN_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BANKER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_PLAYER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BOTH_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_NATURAL, true);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BANKER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_PLAYER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BOTH_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_NATURAL, false);

                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_BANKER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_PLAYER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_BOTH_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_BANKER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_PLAYER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_BOTH_PAIR_NATURAL, false);
                    break;
                case PLAYER_WIN_BANKER_PAIR_NATURAL:
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BOTH_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BOTH_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN, false);

                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_BOTH_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_BOTH_PAIR, false);

                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_WIN_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BANKER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_PLAYER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BOTH_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BANKER_PAIR_NATURAL, true);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_PLAYER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BOTH_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_NATURAL, false);

                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_BANKER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_PLAYER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_BOTH_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_BANKER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_PLAYER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_BOTH_PAIR_NATURAL, false);
                    break;
                case PLAYER_WIN_PLAYER_PAIR_NATURAL:
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BOTH_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_PLAYER_PAIR, true);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BOTH_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN, false);

                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_BOTH_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_BOTH_PAIR, false);

                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_WIN_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BANKER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_PLAYER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BOTH_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BANKER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_PLAYER_PAIR_NATURAL, true);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BOTH_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_NATURAL, false);

                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_BANKER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_PLAYER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_BOTH_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_BANKER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_PLAYER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_BOTH_PAIR_NATURAL, false);
                    break;
                case PLAYER_WIN_BOTH_PAIR_NATURAL:
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BOTH_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BOTH_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN, false);

                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_BOTH_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_BOTH_PAIR, false);

                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_WIN_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BANKER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_PLAYER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BOTH_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BANKER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_PLAYER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BOTH_PAIR_NATURAL, true);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_NATURAL, false);

                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_BANKER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_PLAYER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_BOTH_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_BANKER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_PLAYER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_BOTH_PAIR_NATURAL, false);
                    break;
                case TIE_AFTER_BANKER_WIN_NATURAL:
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BOTH_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BOTH_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN, false);

                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_BOTH_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_BOTH_PAIR, false);

                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_WIN_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BANKER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_PLAYER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BOTH_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BANKER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_PLAYER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BOTH_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_NATURAL, true);

                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_BANKER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_PLAYER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_BOTH_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_BANKER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_PLAYER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_BOTH_PAIR_NATURAL, false);
                    break;
                case TIE_AFTER_BANKER_WIN_BANKER_PAIR_NATURAL:
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BOTH_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BOTH_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN, false);

                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_BOTH_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_BOTH_PAIR, false);

                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_WIN_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BANKER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_PLAYER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BOTH_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BANKER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_PLAYER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BOTH_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_NATURAL, false);

                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_BANKER_PAIR_NATURAL, true);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_PLAYER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_BOTH_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_BANKER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_PLAYER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_BOTH_PAIR_NATURAL, false);
                    break;
                case TIE_AFTER_BANKER_WIN_PLAYER_PAIR_NATURAL:
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BOTH_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BOTH_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN, false);

                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_BOTH_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_BOTH_PAIR, false);

                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_WIN_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BANKER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_PLAYER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BOTH_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BANKER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_PLAYER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BOTH_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_NATURAL, false);

                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_BANKER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_PLAYER_PAIR_NATURAL, true);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_BOTH_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_BANKER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_PLAYER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_BOTH_PAIR_NATURAL, false);
                    break;
                case TIE_AFTER_BANKER_WIN_BOTH_PAIR_NATURAL:
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BOTH_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BOTH_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN, false);

                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_BOTH_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_BOTH_PAIR, false);

                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_WIN_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BANKER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_PLAYER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BOTH_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BANKER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_PLAYER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BOTH_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_NATURAL, false);

                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_BANKER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_PLAYER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_BOTH_PAIR_NATURAL, true);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_BANKER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_PLAYER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_BOTH_PAIR_NATURAL, false);
                    break;
                case TIE_AFTER_PLAYER_WIN_NATURAL:
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BOTH_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BOTH_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN, false);

                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_BOTH_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_BOTH_PAIR, false);

                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_WIN_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BANKER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_PLAYER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BOTH_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BANKER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_PLAYER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BOTH_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_NATURAL, false);

                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_BANKER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_PLAYER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_BOTH_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_NATURAL, true);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_BANKER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_PLAYER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_BOTH_PAIR_NATURAL, false);
                    break;
                case TIE_AFTER_PLAYER_WIN_BANKER_PAIR_NATURAL:
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BOTH_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BOTH_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN, false);

                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_BOTH_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_BOTH_PAIR, false);

                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_WIN_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BANKER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_PLAYER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BOTH_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BANKER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_PLAYER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BOTH_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_NATURAL, false);

                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_BANKER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_PLAYER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_BOTH_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_BANKER_PAIR_NATURAL, true);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_PLAYER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_BOTH_PAIR_NATURAL, false);
                    break;
                case TIE_AFTER_PLAYER_WIN_PLAYER_PAIR_NATURAL:
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BOTH_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BOTH_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN, false);

                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_BOTH_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_BOTH_PAIR, false);

                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_WIN_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BANKER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_PLAYER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BOTH_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BANKER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_PLAYER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BOTH_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_NATURAL, false);

                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_BANKER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_PLAYER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_BOTH_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_BANKER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_PLAYER_PAIR_NATURAL, true);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_BOTH_PAIR_NATURAL, false);
                    break;
                case TIE_AFTER_PLAYER_WIN_BOTH_PAIR_NATURAL:
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BOTH_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BOTH_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN, false);

                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_BOTH_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_BANKER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_PLAYER_PAIR, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_BOTH_PAIR, false);

                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_WIN_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BANKER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_PLAYER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN_BOTH_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BANKER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_PLAYER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN_BOTH_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_NATURAL, false);

                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_BANKER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_PLAYER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_BANKER_WIN_BOTH_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_BANKER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_PLAYER_PAIR_NATURAL, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_AFTER_PLAYER_WIN_BOTH_PAIR_NATURAL, true);
                    break;


                default:
            }
        }

        @Override
        public Object getBean() {
            return BigRoadLabel.this;
        }

        @Override
        public String getName() {
            return "BigRoadLabel";
        }

    };

    public int getTieCount() {
        return this.tieCount;
    }

    public void  setTieCount(int s){
        this.tieCount= s;
    }
    public void incTieCount(){
        this.tieCount++;
    }
    public BigRoadResult getResult() {
        return result.get();
    }

    public ObjectProperty<BigRoadResult> resultProperty() {
        return result;
    }

    public void setResult(BigRoadResult result) {
        this.result.set(result);
//        this.setText("");
    }
}
