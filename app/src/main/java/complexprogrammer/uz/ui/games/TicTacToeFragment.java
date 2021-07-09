package complexprogrammer.uz.ui.games;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import org.jetbrains.annotations.NotNull;

import complexprogrammer.uz.R;

public class TicTacToeFragment extends Fragment implements View.OnClickListener {

private Button[][] buttons=new Button[3][3];
private boolean player1Turn=true;
private int roundCount;
private int player1Points;
private int player2Points;
private TextView textViewPlayer1;
private TextView textViewPlayer2;
    private AdView mAdView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tic_tac_toe, container, false);
        mAdView = view.findViewById(R.id.adView1);
        AdRequest adRequest = new AdRequest.Builder()
                .build();
        mAdView.loadAd(adRequest);
        textViewPlayer1=view.findViewById(R.id.text_view_p1);
        textViewPlayer2=view.findViewById(R.id.text_view_p2);
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                String buttonID="button_"+i+j;
                int resID = getResources().getIdentifier(buttonID,"id",view.getContext().getPackageName());
                buttons[i][j]=view.findViewById(resID);
                buttons[i][j].setOnClickListener(this::onClick);

            }
        }
        Button buttonReset=view.findViewById(R.id.button_reset);
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame();

            }
        });
        return view;
    }

    @Override
    public void onClick(View v) {
        if(!((Button) v).getText().toString().equals("")){
            return;
        }
        if (player1Turn){
            ((Button) v).setText("X");
        }
        else{
            ((Button) v).setText("O");
        }
        roundCount++;
        if (checkForWin()){
            if(player1Turn){
                player1Wins();
            }
            else{
                player2Wins();
            }
        } else if(roundCount==9){
            draw();
        } else {
            player1Turn=!player1Turn;
        }

    }
    private boolean checkForWin(){
        String[][] field=new String[3][3];
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                field[i][j]=buttons[i][j].getText().toString();

            }
        }
        for(int i=0;i<3;i++){
            if(field[i][0].equals(field[i][1])
                    &&field[i][0].equals(field[i][2])
                    &&!field[i][0].equals("")){
                return true;
            }
        }
        for(int i=0;i<3;i++){
            if(field[0][i].equals(field[1][i])
                    &&field[0][i].equals(field[2][i])
                    &&!field[0][i].equals("")){
                return true;
            }
        }
        for(int i=0;i<3;i++){
            if(field[0][0].equals(field[1][1])
                    &&field[0][0].equals(field[2][2])
                    &&!field[0][0].equals("")){
                return true;
            }
        }
        for(int i=0;i<3;i++){
            if(field[0][2].equals(field[1][1])
                    &&field[0][2].equals(field[2][0])
                    &&!field[0][2].equals("")){
                return true;
            }
        }
        return false;

    }
    public void player1Wins(){
        player1Points++;
        Toast.makeText(getContext(),"1-o'yinchi g'alaba qozonadi!",Toast.LENGTH_SHORT).show();
        updatePointsText();
        resetBoard();
    }
    public void player2Wins(){
        player2Points++;
        Toast.makeText(getContext(),"2-o'yinchi g'alaba qozonadi!",Toast.LENGTH_SHORT).show();
        updatePointsText();
        resetBoard();

    }
    public void draw(){
        Toast.makeText(getContext(),"Draw!",Toast.LENGTH_SHORT).show();
        resetBoard();
    }
    private void updatePointsText(){
        textViewPlayer1.setText(getString(R.string.player_1) +player1Points);
        textViewPlayer2.setText(getString(R.string.player_2)+player2Points);
    }
    private void resetBoard(){
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                buttons[i][j].setText("");

            }
        }
        roundCount=0;
        player1Turn=true;
    }
    private void resetGame(){
        player1Points=0;
        player2Points=0;
        updatePointsText();
        resetBoard();
    }

    @Override
    public void onSaveInstanceState(@NonNull @NotNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("roundCount",roundCount);
        outState.putInt("player1Points",player1Points);
        outState.putInt("player2Points",player2Points);
        outState.putBoolean("player1Turn",player1Turn);
    }

//    @Override
//    public void onViewStateRestored(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
//        super.onViewStateRestored(savedInstanceState);
//        roundCount=savedInstanceState.getInt("roundCount");
//        player1Points=savedInstanceState.getInt("player1Points");
//        player2Points=savedInstanceState.getInt("player2Points");
//        player1Turn=savedInstanceState.getBoolean("player1Turn");
//    }

}