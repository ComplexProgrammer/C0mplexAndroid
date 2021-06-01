package complexprogrammer.uz;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;

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
    public class MainAdapter extends BaseExpandableListAdapter {
        ArrayList<String> listGroup;
        HashMap<String,ArrayList<String>> listChild;
        public MainAdapter(ArrayList<String> listGroup,HashMap<String,ArrayList<String>> listChild){
            this.listGroup=listGroup;
            this.listChild=listChild;
        }

        @Override
        public int getGroupCount() {
            return listGroup.size();
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            return listChild.get(listGroup.get(groupPosition)).size();
        }

        @Override
        public Object getGroup(int groupPosition) {
            return listGroup.get(groupPosition);
        }

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return listChild.get(listGroup.get(groupPosition)).get(childPosition);
        }

        @Override
        public long getGroupId(int groupPosition) {
            return 0;
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return 0;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            convertView= LayoutInflater.from(parent.getContext())
                    .inflate(android.R.layout.simple_expandable_list_item_1,parent,false);
            TextView textView=convertView.findViewById(android.R.id.text1);
            String sGroup=String.valueOf(getGroup(groupPosition));
            textView.setText(sGroup);
            textView.setTypeface(null, Typeface.BOLD);
            textView.setTextColor(Color.BLUE);
            return convertView;
        }

        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            convertView=LayoutInflater.from(parent.getContext())
                    .inflate(android.R.layout.simple_selectable_list_item,parent,false);
            TextView textView=convertView.findViewById(android.R.id.text1);
            String sChild=String.valueOf(getChild(groupPosition,childPosition));
            textView.setText(sChild);
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(parent.getContext(),sChild,Toast.LENGTH_SHORT).show();
                }
            });
            return convertView;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return false;
        }
    }
}
