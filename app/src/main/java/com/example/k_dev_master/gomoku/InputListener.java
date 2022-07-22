package com.example.k_dev_master.gomoku;

import android.view.MotionEvent;
import android.view.View;

public class InputListener implements View.OnTouchListener{
    private final GomokuView mView;


    public InputListener(GomokuView view) {
        super();
        this.mView = view;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            mView.userX = (int) event.getX();
            mView.userY = (int) event.getY();

            System.out.println("USER_X: " + mView.userX);
            System.out.println("USER_Y: " + mView.userY);

            mView.game.start = true;

            for(int i = 0; i < mView.game.numSquaresX; i++) {
                for (int j = 0; j < mView.game.numSquaresY; j++) {
                    double boardWidth = mView.width * (69.1 / 1328.0);

                    int lowerRange_x = (int) ((43 * boardWidth / 69.1) + boardWidth * i - (boardWidth / 2));
                    int higherRange_x = (int) (lowerRange_x + boardWidth);
                    int lowerRange_y = (int) (((42 * boardWidth / 69.1) + 600) + boardWidth * j - (boardWidth / 2));
                    int higherRange_y = (int) (lowerRange_y + boardWidth);


                    if (mView.InRange(lowerRange_x, higherRange_x, mView.userX)
                            && mView.InRange(lowerRange_y, higherRange_y, mView.userY)) {
                        mView.game.stone_x = i;
                        mView.game.stone_y = j;
                    }
                }
            }

            mView.game.board_x = mView.userX;
            mView.game.board_y = mView.userY;

            if (mView.game.board.getStone(mView.game.stone_x, mView.game.stone_y) == null) {
                mView.game.addStone();
                mView.game.turn++;
            }
        }
        return true;
    }
}