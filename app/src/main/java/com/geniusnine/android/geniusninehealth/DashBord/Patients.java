package com.geniusnine.android.geniusninehealth.DashBord;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import com.geniusnine.android.geniusninehealth.Adapters.PatientAdapter;
import com.geniusnine.android.geniusninehealth.AzureTable.PatientBasicDetails;
import com.geniusnine.android.geniusninehealth.PatientData.PatientRegistraion;
import com.geniusnine.android.geniusninehealth.R;
import com.microsoft.windowsazure.mobileservices.MobileServiceClient;
import com.microsoft.windowsazure.mobileservices.http.OkHttpClientFactory;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;
import com.squareup.okhttp.OkHttpClient;

import java.net.MalformedURLException;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Dev on 17-02-2017.
 */

public class Patients extends AppCompatActivity {

    Button buttonaddpatient;
    private ListView listViewPatient;
    private PatientAdapter mAdapter;
    private MobileServiceClient mClient;
    private MobileServiceTable<PatientBasicDetails> mpatientBasicDetailsMobileServiceTable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patients);
        buttonaddpatient=(Button)findViewById(R.id.buttonaddpatient);
        buttonaddpatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Patients.this, PatientRegistraion.class);
               startActivity(intent);
            }
        });
        try {
            // Create the Mobile Service Client instance, using the provided

            // Mobile Service URL and key
            mClient = new MobileServiceClient(
                    "https://doctorfe.azurewebsites.net",
                    this);

            // Extend timeout from default of 10s to 20s
            mClient.setAndroidHttpClientFactory(new OkHttpClientFactory() {
                @Override
                public OkHttpClient createOkHttpClient() {
                    OkHttpClient client = new OkHttpClient();
                    client.setReadTimeout(20, TimeUnit.SECONDS);
                    client.setWriteTimeout(20, TimeUnit.SECONDS);
                    return client;
                }
            });

            // Get the Mobile Service Table instance to use

            mpatientBasicDetailsMobileServiceTable = mClient.getTable(PatientBasicDetails.class);
            // Create an adapter to bind the items with the view
            mAdapter = new PatientAdapter(this, R.layout.row_list_patient);
            listViewPatient = (ListView) findViewById(R.id.listViewPatient);
            listViewPatient.setAdapter(mAdapter);


            // Load the items from the Mobile Service
            showAll();
        } catch (MalformedURLException e) {
            //createAndShowDialog(new Exception("There was an error creating the Mobile Service. Verify the URL"), "Error");
        } catch (Exception e) {
            // createAndShowDialog(e, "Error");
        }
    }
    public void showAll() {

        AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                try {
                    final List<PatientBasicDetails> results = mpatientBasicDetailsMobileServiceTable.execute().get();
                    runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            mAdapter.clear();
                            for (PatientBasicDetails item : results) {
                                mAdapter.add(item);
                            }
                        }
                    });
                } catch (Exception exception) {
                    //createAndShowDialog(exception, "Error");
                }
                return null;
            }
        };
        //runAsyncTask(task);
        task.execute();
    }

    public void deletepatient(final PatientBasicDetails item) {
        if (mClient == null) {
            return;
        }
        AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                try {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            mAdapter.remove(item);
                            mpatientBasicDetailsMobileServiceTable.delete(item);

                        }
                    });
                } catch (final Exception e) {
                }

                return null;
            }
        };
        task.execute();
    }

    public void Updatepatient(final PatientBasicDetails item,String fname,String lname,String birthdate,String gender,String height,String weight,String bloodgroup,String uniqueid) {
        if (mClient == null) {
            return;
        }
        item.setFname(fname);
        item.setLname(lname);
        item.setBirthdate(birthdate);
        item.setGender(gender);
        item.setHeight(height);
        item.setWeight(weight);
        item.setBloodgroup(bloodgroup);
        item.setUidssn(uniqueid);

        // Insert the new item
        AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                try {

                    final PatientBasicDetails entity = mpatientBasicDetailsMobileServiceTable.update(item).get();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mpatientBasicDetailsMobileServiceTable.update(entity);
                            mAdapter.remove(entity);

                        }
                    });
                } catch (final Exception e) {
                }
                return null;
            }
        };
        task.execute();

        showAll();
    }
}