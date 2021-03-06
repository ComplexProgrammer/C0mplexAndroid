package complexprogrammer.uz.models;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

import complexprogrammer.uz.R;

public class ExpandListAdapter extends BaseExpandableListAdapter {
    private Context _context;
    private List<ExpandListModel> _listDataHeader; // header titles
    // child data in format of header title, child title
    private HashMap<ExpandListModel, List<ExpandListModel>> _listDataChild;

    public ExpandListAdapter(Context context, List<ExpandListModel> listDataHeader,
                             HashMap<ExpandListModel, List<ExpandListModel>> listChildData) {
        this._context = context;
        this._listDataHeader = listDataHeader;
        this._listDataChild = listChildData;
    }

    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .get(childPosititon);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        final ExpandListModel childText = (ExpandListModel) getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_submenu,null);
        }

        TextView txtListChild = (TextView) convertView
                .findViewById(R.id.submenu);

        txtListChild.setText(childText.getIconName());
        ImageView headerIcon = (ImageView) convertView.findViewById(R.id.iconimage);
        headerIcon.setImageResource(childText.getIconImg());
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        if (this._listDataChild.get(this._listDataHeader.get(groupPosition)) == null)
            return 0;
        else
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this._listDataHeader.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this._listDataHeader.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        ExpandListModel headerTitle = (ExpandListModel) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.listheader,null);
        }

        TextView lblListHeader = (TextView) convertView
                .findViewById(R.id.submenu);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle.getIconName());
        ImageView headerIcon = (ImageView) convertView.findViewById(R.id.iconimage);
        headerIcon.setImageResource(headerTitle.getIconImg());

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}