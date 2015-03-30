package com.mohammed.processmanager;

import android.app.ActivityManager;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by mohammed on 29/3/15.
 */
public class ProcessAdapter  extends ArrayAdapter<ActivityManager.RunningAppProcessInfo> {

    public ProcessAdapter(Context context, List<ActivityManager.RunningAppProcessInfo> processList) {
        super(context,R.layout.listitems, processList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data from the position
        ActivityManager.RunningAppProcessInfo processInfo = getItem(position);

        // Use existing convert view otherwise create a new view
        if (convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.listitems, parent, false);

        // Get the text views
        TextView tvPid = (TextView)convertView.findViewById(R.id.tvPid);
        TextView tvProcessName = (TextView)convertView.findViewById(R.id.tvProcessName);

        // populate the data into the text views above
        tvPid.setText(Integer.toString(processInfo.pid));
        tvProcessName.setText(processInfo.processName);

        return convertView;
    }
}
