package customjavafx.scene.control;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ObjectPropertyBase;
import javafx.css.PseudoClass;
import javafx.scene.control.Label;

public class CockroachRoadLabel extends Label {


    public CockroachRoadLabel() {
        this.getStyleClass().add("CockroachRoadLabel");
    }

    public CockroachRoadLabel(CockroachRoadResult res) {
        this.getStyleClass().add("CockroachRoadLabel");
        setResult(res);
    }

    //Add States
    private static final PseudoClass PSEUDO_CLASS_RED = PseudoClass.getPseudoClass("red");
    private static final PseudoClass PSEUDO_CLASS_BLUE = PseudoClass.getPseudoClass("blue");

    //Private object property
    private ObjectProperty<CockroachRoadResult> result = new ObjectPropertyBase<CockroachRoadResult>() {
        @Override
        protected void invalidated() {
            switch (get()) {
                case RED:
                    pseudoClassStateChanged(PSEUDO_CLASS_RED, true);
                    pseudoClassStateChanged(PSEUDO_CLASS_BLUE, false);
                    break;
                case BLUE:
                    pseudoClassStateChanged(PSEUDO_CLASS_RED, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BLUE, true);
                    break;
                case EMPTY:
                    pseudoClassStateChanged(PSEUDO_CLASS_RED, false);
                    pseudoClassStateChanged(PSEUDO_CLASS_BLUE, false);
                    break;
                default:
            }
        }

        @Override
        public Object getBean() {
            return CockroachRoadLabel.this;
        }

        @Override
        public String getName() {
            return "CockroachRoadLabel";
        }
    };

    //Getters & Setters for the object property
    public CockroachRoadResult getResult() {
        return result.get();
    }

    public ObjectProperty<CockroachRoadResult> resultProperty() {
        return result;
    }

    public void setResult(CockroachRoadResult result) {
        this.result.set(result);
    }
}
