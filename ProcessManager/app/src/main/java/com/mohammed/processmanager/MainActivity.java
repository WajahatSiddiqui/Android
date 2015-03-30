package com.mohammed.processmanager;

import android.app.ActivityManager;
import android.app.ExpandableListActivity;
import android.app.ListActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ListActivity {

    List<ActivityManager.RunningAppProcessInfo> mRunningProcess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityManager manager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        mRunningProcess = manager.getRunningAppProcesses();
        if (mRunningProcess != null && mRunningProcess.size() > 0) {
            // Set data to the list adapter
            setListAdapter(new ProcessAdapter(this, mRunningProcess));
            registerForContextMenu(this.getListView());
        } else {
            // In case there are no processes running (not a chance :))
            Toast.makeText(getApplicationContext(), "No application is running", Toast.LENGTH_LONG).show();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Select Operation");
        menu.add(v.getId(), R.id.cmKillPid, 0, R.string.context_menu_kill_pid);
        menu.add(v.getId(), R.id.cmKillPid, 1, R.string.context_menu_more_info);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.cmKillPid:
                int pid = ((ActivityManager.RunningAppProcessInfo)getListAdapter().getItem(item.getGroupId())).pid;
                Process.killProcess(pid);
            case R.id.cmMoreInfo:
            default:
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
