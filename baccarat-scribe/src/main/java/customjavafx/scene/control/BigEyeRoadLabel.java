package customjavafx.scene.control;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ObjectPropertyBase;
import javafx.css.PseudoClass;
import javafx.scene.control.Label;

public class BigEyeRoadLabel extends Label {

    public BigEyeRoadLabel() {
        this.getStyleClass().add("BigEyeRoadLabel");
    }

    public BigEyeRoadLabel(BigEyeRoadResult res) {
        this.getStyleClass().add("BigEyeRoadLabel");
        setResult(res);
    }

    //Add States
    private static final PseudoClass PSEUDO_CLASS_RED = PseudoClass.getPseudoClass("red");
    private static final PseudoClass PSEUDO_CLASS_BLUE = PseudoClass.getPseudoClass("blue");

    //Private object property
    private ObjectProperty<BigEyeRoadResult> result = new ObjectPropertyBase<BigEyeRoadResult>() {
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
                    break;
            }
        }

        @Override
        public Object getBean() {
            return BigEyeRoadLabel.this;
        }

        @Override
        public String getName() {
            return "BigEyeRoadLabel";
        }
    };

    //Getters & Setters for the object property
    public BigEyeRoadResult getResult() {
        return result.get();
    }

    public ObjectProperty<BigEyeRoadResult> resultProperty() {
        return result;
    }

    public void setResult(BigEyeRoadResult result) {
        this.result.set(result);
    }
}
