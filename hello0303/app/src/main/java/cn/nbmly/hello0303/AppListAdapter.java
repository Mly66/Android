package cn.nbmly.hello0303;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class AppListAdapter extends BaseAdapter {

    private final Context context;
    private final List<ApplicationInfo> appList;
    private final PackageManager packageManager;
    private final List<Boolean> checkedStates;

    public AppListAdapter(Context context, List<ApplicationInfo> appList, PackageManager packageManager) {
        this.context = context;
        this.appList = appList;
        this.packageManager = packageManager;
        this.checkedStates = new ArrayList<>(appList.size());
        for (int i = 0; i < appList.size(); i++) {
            checkedStates.add(false);
        }
    }

    @Override
    public int getCount() {
        return appList.size();
    }

    @Override
    public Object getItem(int position) {
        return appList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.app_list_item, parent, false);
        }

        TextView appName = convertView.findViewById(R.id.app_name);
        CheckBox checkBox = convertView.findViewById(R.id.app_checkbox);

        final ApplicationInfo appInfo = appList.get(position);
        appName.setText(appInfo.loadLabel(packageManager));

        checkBox.setOnCheckedChangeListener(null);
        checkBox.setChecked(checkedStates.get(position));
        checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> checkedStates.set(position, isChecked));

        return convertView;
    }

    public List<ApplicationInfo> getSelectedApps() {
        List<ApplicationInfo> selectedApps = new ArrayList<>();
        for (int i = 0; i < appList.size(); i++) {
            if (checkedStates.get(i)) {
                selectedApps.add(appList.get(i));
            }
        }
        return selectedApps;
    }
}