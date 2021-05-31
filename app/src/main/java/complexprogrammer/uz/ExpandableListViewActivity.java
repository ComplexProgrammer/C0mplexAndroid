package complexprogrammer.uz;

import android.os.Bundle;
import android.widget.ExpandableListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;

import complexprogrammer.uz.models.MainAdapter;

public class ExpandableListViewActivity extends AppCompatActivity {

    ExpandableListView expandableListView;
    ArrayList<String> listGroup=new ArrayList<>();
    HashMap<String,ArrayList<String>> listChild=new HashMap<>();
    MainAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expandable_list_view);
        expandableListView=findViewById(R.id.exp_list_view);
        for(int g=0;g<10;g++){
            listGroup.add("Menu"+g);
            ArrayList<String> arrayList=new ArrayList<>();
            for(int c=0;c<5;c++){
                arrayList.add("subMenu"+c);
            }
            listChild.put(listGroup.get(g),arrayList);
        }
        adapter=new MainAdapter(listGroup,listChild);
        expandableListView.setAdapter(adapter);
    }
}