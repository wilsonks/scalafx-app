package customjavafx.scene.layout;


import customjavafx.scene.control.BeadRoadLabel;
import customjavafx.scene.control.BeadRoadResult;
import customjavafx.scene.control.LastWinResult;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.layout.TilePane;

import java.util.ArrayList;

public class BeadRoadTilePane extends TilePane {

    private int column = 0;
    private int row = -1;


    private IntegerProperty count = new SimpleIntegerProperty(0);

    public IntegerProperty getCountProperty() {
        return count;
    }

    private IntegerProperty playerWinCount = new SimpleIntegerProperty(0);

    public IntegerProperty getPlayerWinCount() {
        return playerWinCount;
    }


    private IntegerProperty bankerWinCount = new SimpleIntegerProperty(0);

    public IntegerProperty getBankerWinCount() {
        return bankerWinCount;
    }


    private IntegerProperty tieWinCount = new SimpleIntegerProperty(0);

    public IntegerProperty getTieWinCount() {
        return tieWinCount;
    }


    private IntegerProperty playerPairCount = new SimpleIntegerProperty(0);

    public IntegerProperty getPlayerPairCount() {
        return playerPairCount;
    }

    private IntegerProperty bankerPairCount = new SimpleIntegerProperty(0);

    public IntegerProperty getBankerPairCount() {
        return bankerPairCount;
    }

    private IntegerProperty naturalCount = new SimpleIntegerProperty(0);

    public IntegerProperty getNaturalCount() {
        return naturalCount;
    }

    private ListProperty<BeadRoadResult> beadRoadList = new SimpleListProperty<BeadRoadResult>(FXCollections.observableList(new ArrayList<BeadRoadResult>()));
    public ListProperty<BeadRoadResult> getBeadRoadListProperty() {
        return beadRoadList;
    }

    public BeadRoadTilePane() {
        setOrientation(Orientation.VERTICAL);
        setAlignment(Pos.TOP_LEFT);
    }

    private int sizeLimit() {
        return (getPrefColumns() * getPrefRows());
    }

    private int getSize() {
        return getPosition() + 1;
    }

    private int getPosition() {
        return (column * getPrefRows()) + row;
    }

    private boolean childrenLimitReached() {
        return (getChildren().size() == sizeLimit());
    }

    private void ResultAdded(BeadRoadResult res) {
        count.setValue(count.getValue() + 1);
        beadRoadList.add(res);
        switch (res) {
            case BANKER_WIN:
                bankerWinCount.setValue(bankerWinCount.getValue() + 1);
                break;
            case BANKER_WIN_BANKER_PAIR:
                bankerPairCount.setValue(bankerPairCount.getValue() + 1);
                bankerWinCount.setValue(bankerWinCount.getValue() + 1);
                break;
            case BANKER_WIN_PLAYER_PAIR:
                playerPairCount.setValue(playerPairCount.getValue() + 1);
                bankerWinCount.setValue(bankerWinCount.getValue() + 1);
                break;

            case BANKER_WIN_BOTH_PAIR:
                bankerPairCount.setValue(bankerPairCount.getValue() + 1);
                playerPairCount.setValue(playerPairCount.getValue() + 1);
                bankerWinCount.setValue(bankerWinCount.getValue() + 1);
                break;

            case PLAYER_WIN:
                playerWinCount.setValue(playerWinCount.getValue() + 1);
                break;
            case PLAYER_WIN_BANKER_PAIR:
                bankerPairCount.setValue(bankerPairCount.getValue() + 1);
                playerWinCount.setValue(playerWinCount.getValue() + 1);
                break;
            case PLAYER_WIN_PLAYER_PAIR:
                playerPairCount.setValue(playerPairCount.getValue() + 1);
                playerWinCount.setValue(playerWinCount.getValue() + 1);
                break;

            case PLAYER_WIN_BOTH_PAIR:
                bankerPairCount.setValue(bankerPairCount.getValue() + 1);
                playerPairCount.setValue(playerPairCount.getValue() + 1);
                playerWinCount.setValue(playerWinCount.getValue() + 1);
                break;
            case TIE_WIN:
                tieWinCount.setValue(tieWinCount.getValue() + 1);
                break;
            case TIE_WIN_BANKER_PAIR:
                bankerPairCount.setValue(bankerPairCount.getValue() + 1);
                tieWinCount.setValue(tieWinCount.getValue() + 1);
                break;
            case TIE_WIN_PLAYER_PAIR:
                playerPairCount.setValue(playerPairCount.getValue() + 1);
                tieWinCount.setValue(tieWinCount.getValue() + 1);
                break;

            case TIE_WIN_BOTH_PAIR:
                bankerPairCount.setValue(bankerPairCount.getValue() + 1);
                playerPairCount.setValue(playerPairCount.getValue() + 1);
                tieWinCount.setValue(tieWinCount.getValue() + 1);
                break;

            case BANKER_WIN_NATURAL:
                bankerWinCount.setValue(bankerWinCount.getValue() + 1);
                naturalCount.setValue(naturalCount.getValue() + 1);
                break;
            case BANKER_WIN_BANKER_PAIR_NATURAL:
                bankerPairCount.setValue(bankerPairCount.getValue() + 1);
                bankerWinCount.setValue(bankerWinCount.getValue() + 1);
                naturalCount.setValue(naturalCount.getValue() + 1);

                break;
            case BANKER_WIN_PLAYER_PAIR_NATURAL:
                playerPairCount.setValue(playerPairCount.getValue() + 1);
                bankerWinCount.setValue(bankerWinCount.getValue() + 1);
                naturalCount.setValue(naturalCount.getValue() + 1);
                break;

            case BANKER_WIN_BOTH_PAIR_NATURAL:
                bankerPairCount.setValue(bankerPairCount.getValue() + 1);
                playerPairCount.setValue(playerPairCount.getValue() + 1);
                bankerWinCount.setValue(bankerWinCount.getValue() + 1);
                naturalCount.setValue(naturalCount.getValue() + 1);

                break;

            case PLAYER_WIN_NATURAL:
                playerWinCount.setValue(playerWinCount.getValue() + 1);
                naturalCount.setValue(naturalCount.getValue() + 1);
                break;
            case PLAYER_WIN_BANKER_PAIR_NATURAL:
                bankerPairCount.setValue(bankerPairCount.getValue() + 1);
                playerWinCount.setValue(playerWinCount.getValue() + 1);
                naturalCount.setValue(naturalCount.getValue() + 1);

                break;
            case PLAYER_WIN_PLAYER_PAIR_NATURAL:
                playerPairCount.setValue(playerPairCount.getValue() + 1);
                playerWinCount.setValue(playerWinCount.getValue() + 1);
                naturalCount.setValue(naturalCount.getValue() + 1);
                break;

            case PLAYER_WIN_BOTH_PAIR_NATURAL:
                bankerPairCount.setValue(bankerPairCount.getValue() + 1);
                playerPairCount.setValue(playerPairCount.getValue() + 1);
                playerWinCount.setValue(playerWinCount.getValue() + 1);
                naturalCount.setValue(naturalCount.getValue() + 1);
                break;
            case TIE_WIN_NATURAL:
                tieWinCount.setValue(tieWinCount.getValue() + 1);
                naturalCount.setValue(naturalCount.getValue() + 1);
                break;
            case TIE_WIN_BANKER_PAIR_NATURAL:
                bankerPairCount.setValue(bankerPairCount.getValue() + 1);
                tieWinCount.setValue(tieWinCount.getValue() + 1);
                naturalCount.setValue(naturalCount.getValue() + 1);
                break;
            case TIE_WIN_PLAYER_PAIR_NATURAL:
                playerPairCount.setValue(playerPairCount.getValue() + 1);
                tieWinCount.setValue(tieWinCount.getValue() + 1);
                naturalCount.setValue(naturalCount.getValue() + 1);
                break;
            case TIE_WIN_BOTH_PAIR_NATURAL:
                bankerPairCount.setValue(bankerPairCount.getValue() + 1);
                playerPairCount.setValue(playerPairCount.getValue() + 1);
                tieWinCount.setValue(tieWinCount.getValue() + 1);
                naturalCount.setValue(naturalCount.getValue() + 1);
                break;

            default:
                break;
        }
    }

    private boolean isCurrentWinRed() {
        switch (((BeadRoadLabel) getChildren().get(getPosition())).getResult()) {
            case BANKER_WIN:
            case BANKER_WIN_BANKER_PAIR:
            case BANKER_WIN_PLAYER_PAIR:
            case BANKER_WIN_BOTH_PAIR:
            case BANKER_WIN_NATURAL:
            case BANKER_WIN_BANKER_PAIR_NATURAL:
            case BANKER_WIN_PLAYER_PAIR_NATURAL:
            case BANKER_WIN_BOTH_PAIR_NATURAL:
                return true;
            default:
                return false;
        }
    }

    private boolean isCurrentWinBlue() {
        switch (((BeadRoadLabel) getChildren().get(getPosition())).getResult()) {
            case PLAYER_WIN:
            case PLAYER_WIN_BANKER_PAIR:
            case PLAYER_WIN_PLAYER_PAIR:
            case PLAYER_WIN_BOTH_PAIR:
            case PLAYER_WIN_NATURAL:
            case PLAYER_WIN_BANKER_PAIR_NATURAL:
            case PLAYER_WIN_PLAYER_PAIR_NATURAL:
            case PLAYER_WIN_BOTH_PAIR_NATURAL:
                return true;
            default:
                return false;
        }
    }

    private void ResultRemoved(BeadRoadResult res) {
        count.setValue(count.getValue() - 1);
        beadRoadList.remove(beadRoadList.size() - 1);
        switch (res) {
            case BANKER_WIN:
                bankerWinCount.setValue(bankerWinCount.getValue() - 1);
                break;
            case BANKER_WIN_BANKER_PAIR:
                bankerPairCount.setValue(bankerPairCount.getValue() - 1);
                bankerWinCount.setValue(bankerWinCount.getValue() - 1);
                break;
            case BANKER_WIN_PLAYER_PAIR:
                playerPairCount.setValue(playerPairCount.getValue() - 1);
                bankerWinCount.setValue(bankerWinCount.getValue() - 1);
                break;

            case BANKER_WIN_BOTH_PAIR:
                bankerPairCount.setValue(bankerPairCount.getValue() - 1);
                playerPairCount.setValue(playerPairCount.getValue() - 1);
                bankerWinCount.setValue(bankerWinCount.getValue() - 1);
                break;

            case PLAYER_WIN:
                playerWinCount.setValue(playerWinCount.getValue() - 1);
                break;
            case PLAYER_WIN_BANKER_PAIR:
                bankerPairCount.setValue(bankerPairCount.getValue() - 1);
                playerWinCount.setValue(playerWinCount.getValue() - 1);
                break;
            case PLAYER_WIN_PLAYER_PAIR:
                playerPairCount.setValue(playerPairCount.getValue() - 1);
                playerWinCount.setValue(playerWinCount.getValue() - 1);
                break;

            case PLAYER_WIN_BOTH_PAIR:
                bankerPairCount.setValue(bankerPairCount.getValue() - 1);
                playerPairCount.setValue(playerPairCount.getValue() - 1);
                playerWinCount.setValue(playerWinCount.getValue() - 1);
                break;
            case TIE_WIN:
                tieWinCount.setValue(tieWinCount.getValue() - 1);
                break;
            case TIE_WIN_BANKER_PAIR:
                bankerPairCount.setValue(bankerPairCount.getValue() - 1);
                tieWinCount.setValue(tieWinCount.getValue() - 1);
                break;
            case TIE_WIN_PLAYER_PAIR:
                playerPairCount.setValue(playerPairCount.getValue() - 1);
                tieWinCount.setValue(tieWinCount.getValue() - 1);
                break;

            case TIE_WIN_BOTH_PAIR:
                bankerPairCount.setValue(bankerPairCount.getValue() - 1);
                playerPairCount.setValue(playerPairCount.getValue() - 1);
                tieWinCount.setValue(tieWinCount.getValue() - 1);
                break;

            case BANKER_WIN_NATURAL:
                bankerWinCount.setValue(bankerWinCount.getValue() - 1);
                naturalCount.setValue(naturalCount.getValue() - 1);
                break;
            case BANKER_WIN_BANKER_PAIR_NATURAL:
                bankerPairCount.setValue(bankerPairCount.getValue() - 1);
                bankerWinCount.setValue(bankerWinCount.getValue() - 1);
                naturalCount.setValue(naturalCount.getValue() - 1);
                break;
            case BANKER_WIN_PLAYER_PAIR_NATURAL:
                playerPairCount.setValue(playerPairCount.getValue() - 1);
                bankerWinCount.setValue(bankerWinCount.getValue() - 1);
                naturalCount.setValue(naturalCount.getValue() - 1);
                break;

            case BANKER_WIN_BOTH_PAIR_NATURAL:
                bankerPairCount.setValue(bankerPairCount.getValue() - 1);
                playerPairCount.setValue(playerPairCount.getValue() - 1);
                bankerWinCount.setValue(bankerWinCount.getValue() - 1);
                naturalCount.setValue(naturalCount.getValue() - 1);
                break;

            case PLAYER_WIN_NATURAL:
                playerWinCount.setValue(playerWinCount.getValue() - 1);
                naturalCount.setValue(naturalCount.getValue() - 1);
                break;
            case PLAYER_WIN_BANKER_PAIR_NATURAL:
                bankerPairCount.setValue(bankerPairCount.getValue() - 1);
                playerWinCount.setValue(playerWinCount.getValue() - 1);
                naturalCount.setValue(naturalCount.getValue() - 1);
                break;
            case PLAYER_WIN_PLAYER_PAIR_NATURAL:
                playerPairCount.setValue(playerPairCount.getValue() - 1);
                playerWinCount.setValue(playerWinCount.getValue() - 1);
                naturalCount.setValue(naturalCount.getValue() - 1);
                break;

            case PLAYER_WIN_BOTH_PAIR_NATURAL:
                bankerPairCount.setValue(bankerPairCount.getValue() - 1);
                playerPairCount.setValue(playerPairCount.getValue() - 1);
                playerWinCount.setValue(playerWinCount.getValue() - 1);
                naturalCount.setValue(naturalCount.getValue() - 1);
                break;
            case TIE_WIN_NATURAL:
                tieWinCount.setValue(tieWinCount.getValue() - 1);
                naturalCount.setValue(naturalCount.getValue() - 1);
                break;
            case TIE_WIN_BANKER_PAIR_NATURAL:
                bankerPairCount.setValue(bankerPairCount.getValue() - 1);
                tieWinCount.setValue(tieWinCount.getValue() - 1);
                naturalCount.setValue(naturalCount.getValue() - 1);
                break;
            case TIE_WIN_PLAYER_PAIR_NATURAL:
                playerPairCount.setValue(playerPairCount.getValue() - 1);
                tieWinCount.setValue(tieWinCount.getValue() - 1);
                naturalCount.setValue(naturalCount.getValue() - 1);
                break;

            case TIE_WIN_BOTH_PAIR_NATURAL:
                bankerPairCount.setValue(bankerPairCount.getValue() - 1);
                playerPairCount.setValue(playerPairCount.getValue() - 1);
                tieWinCount.setValue(tieWinCount.getValue() - 1);
                naturalCount.setValue(naturalCount.getValue() - 1);
                break;

            default:
                break;
        }
    }


    private void Update(BeadRoadResult res) {
        MovePositionFront();
        ((BeadRoadLabel) getChildren().get(getPosition())).setResult(res);
    }

    private void Insert() {
        BeadRoadLabel temp = new BeadRoadLabel(BeadRoadResult.EMPTY);
        temp.setResult(BeadRoadResult.EMPTY);
        getChildren().add(temp);
    }

    private void MovePositionFront() {
        if (row == (getPrefRows() - 1)) {
            column++;
            row = 0;
        } else {
            row++;
        }

    }

    private void MovePostionBack() {
        if (row == 0) {
            column--;
            row = getPrefRows() - 1;
        } else {
            row--;
        }

    }

    public void RemoveElement() {
        if (getPosition() >= 0) {
            BeadRoadResult tmp = ((BeadRoadLabel) getChildren().get(getPosition())).getResult();
            ((BeadRoadLabel) getChildren().get(getPosition())).setResult(BeadRoadResult.EMPTY);
            MovePostionBack();
            ResultRemoved(tmp);
        }
    }

    public String LastWinAudio() {
        if (isCurrentWinRed()) return "/sounds/banker.mp3";
        else if (isCurrentWinBlue()) return "/sounds/player.mp3";
        else {
            return "/sounds/tie.mp3";
        }
    }

    public BeadRoadResult LastWinResult() {
        return ((BeadRoadLabel) getChildren().get(getPosition())).getResult();
    }

    public String LastWin() {
        if (isCurrentWinRed()) return "bWin";
        else if (isCurrentWinBlue()) return "pWin";
        else {
            return "tWin";
        }
    }

    public void AddElement(BeadRoadResult res) {
        if (getSize() == sizeLimit()) {
            Reset();
        }
        Update(res);
        ResultAdded(res);
    }

    public void Initialize(int row, int column) {
        this.setPrefRows(row);
        this.setPrefColumns(column);
        while (!childrenLimitReached()) {
            Insert();
        }
        this.column = 0;
        this.row = -1;
    }

    public ArrayList<BeadRoadResult> getElements() {
        ArrayList<BeadRoadResult> list = new ArrayList<BeadRoadResult>();
        getChildren().stream().map(t -> ((BeadRoadLabel) t).getResult()).forEach(t -> {
            if (t != BeadRoadResult.EMPTY) list.add(t);
        });
        return list;
    }


    public void Reset() {
        getChildren().stream().map(t -> (BeadRoadLabel) t).forEach(t -> {
            t.setResult(BeadRoadResult.EMPTY);
        });
        beadRoadList.clear();
        bankerWinCount.setValue(0);
        tieWinCount.setValue(0);
        playerWinCount.setValue(0);
        bankerPairCount.setValue(0);
        playerPairCount.setValue(0);
        naturalCount.setValue(0);
        count.setValue(0);
        column = 0;
        row = -1;
    }


    @Override
    protected void layoutChildren() {
        super.layoutChildren();
    }

}
