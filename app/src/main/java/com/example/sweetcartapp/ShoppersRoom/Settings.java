package com.example.sweetcartapp.ShoppersRoom;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.sweetcartapp.R;


public class Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);

        setUpToolbar();
        settingfragmentforSetting();
        //TODO: Use SharedPreferences to getDefault file and change SETTINGS using keys defined in setting_preferences.xml

    }

    private void settingfragmentforSetting() {
        getFragmentManager().beginTransaction().replace(R.id.settingsFragmLayout, new MyPrefenceFragment()).commit();
    }


    private void setUpToolbar() {
        Toolbar toolbar = findViewById(R.id.settings_app_bar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @SuppressLint("ValidFragment")
    private static class MyPrefenceFragment extends PreferenceFragment {

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.settings_preferences);
            Preference myPref = (Preference) findPreference("logout_button_preference");
            myPref.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {
                    //TODO: Perform logout operations here
                    Toast.makeText(getActivity(), "Logout Successfully", Toast.LENGTH_SHORT).show();
                    return true;
                }
            });

        }
    }

}