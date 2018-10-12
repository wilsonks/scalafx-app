package customjavafx.scene.control;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ObjectPropertyBase;
import javafx.css.PseudoClass;
import javafx.scene.control.Label;

public class LastWinLabel extends Label {


    public LastWinLabel() {
        this.getStyleClass().add("LastWinLabel");
    }

    public LastWinLabel(LastWinResult result) {
        this.getStyleClass().add("LastWinLabel");
        this.setResult(result);
    }

    //Add the Individual States
    private static final PseudoClass PSEUDO_CLASS_BANKER_WIN = PseudoClass.getPseudoClass("bankerWin");

    private static final PseudoClass PSEUDO_CLASS_PLAYER_WIN = PseudoClass.getPseudoClass("playerWin");

    private static final PseudoClass PSEUDO_CLASS_TIE_WIN = PseudoClass.getPseudoClass("tieWin");

    private ObjectProperty<LastWinResult> result = new ObjectPropertyBase<LastWinResult>() {
        @Override
        protected void invalidated() {
            switch (get()) {
                case EMPTY:
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_WIN, false);
                    break;

                case BANKER_WIN:
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN, true);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_WIN, false);
                    break;

                case PLAYER_WIN:
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN, true);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_WIN, false);
                    break;
                case TIE_WIN:
                    pseudoClassStateChanged(PSEUDO_CLASS_BANKER_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_PLAYER_WIN, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_TIE_WIN, true);
                    break;
                default:
            }
        }

        @Override
        public Object getBean() {
            return LastWinLabel.this;
        }

        @Override
        public String getName() {
            return "LastWinLabel";
        }
    };

    public LastWinResult getResult() {
        return result.get();
    }

    public void setResult(LastWinResult result) {
        this.result.set(result);
    }

    public ObjectProperty<LastWinResult> resultProperty() {
        return result;
    }
}
