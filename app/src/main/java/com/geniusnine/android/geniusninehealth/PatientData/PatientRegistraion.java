package com.geniusnine.android.geniusninehealth.PatientData;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.SyncStateContract;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.geniusnine.android.geniusninehealth.AzureTable.PatientBasicDetails;
import com.geniusnine.android.geniusninehealth.Constants;
import com.geniusnine.android.geniusninehealth.DashBord.Patients;
import com.geniusnine.android.geniusninehealth.R;
import com.microsoft.windowsazure.mobileservices.MobileServiceClient;
import com.microsoft.windowsazure.mobileservices.http.OkHttpClientFactory;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;
import com.squareup.okhttp.OkHttpClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * Created by Dev on 17-02-2017.
 */

public class PatientRegistraion extends AppCompatActivity {
    EditText editTextPaitentFirstName,editTextPaitentLastName,editTextPaitentBirthDate,editTextPaitentGender,editTextPaitentHeight,editTextPaitentWeight,editTextPaitentBloodGroup,editTextPaitentUniqueIdNumber;
    Button buttonRegister;
    ImageView imageViewProfielPicture;
    private Uri mImageUrl;
    private MobileServiceClient mClient;
    private MobileServiceTable<PatientBasicDetails> mpatientBasicDetailsMobileServiceTable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_registraion);
        imageViewProfielPicture=(ImageView)findViewById(R.id.imageViewProfilePicture);
        editTextPaitentFirstName=(EditText)findViewById(R.id.editTextPaitentFirstName);
        editTextPaitentLastName=(EditText)findViewById(R.id.editTextPaitentLastName);
        editTextPaitentBirthDate=(EditText)findViewById(R.id.editTextPaitentBirthDate);
        editTextPaitentGender=(EditText)findViewById(R.id.editTextPaitentGender);
        editTextPaitentHeight=(EditText)findViewById(R.id.editTextPaitentHeight);
        editTextPaitentWeight=(EditText)findViewById(R.id.editTextPaitentWeight);
        editTextPaitentBloodGroup=(EditText)findViewById(R.id.editTextPaitentBloodGroup);
        editTextPaitentUniqueIdNumber=(EditText)findViewById(R.id.editTextPaitentUniqueIdNumber);
        buttonRegister=(Button)findViewById(R.id.buttonRegister);
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
        } catch (MalformedURLException e) {
            //createAndShowDialog(new Exception("There was an error creating the Mobile Service. Verify the URL"), "Error");
        } catch (Exception e) {
            // createAndShowDialog(e, "Error");
        }
imageViewProfielPicture.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        pickImage();
    }
});
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mClient == null) {
                    return;
                }
                // Create a new item
                final PatientBasicDetails item = new PatientBasicDetails();

                item.setFname(editTextPaitentFirstName.getText().toString());
                item.setLname(editTextPaitentLastName.getText().toString());
                item.setBirthdate(editTextPaitentBirthDate.getText().toString());
                item.setGender(editTextPaitentGender.getText().toString());
                item.setHeight(editTextPaitentHeight.getText().toString());
                item.setWeight(editTextPaitentWeight.getText().toString());
                item.setBloodgroup(editTextPaitentBloodGroup.getText().toString());
                item.setUidssn(editTextPaitentUniqueIdNumber.getText().toString());


                // Insert the new item
                AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>() {
                    @Override
                    protected Void doInBackground(Void... params) {
                        try {
                            mpatientBasicDetailsMobileServiceTable.insert(item).get();

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {

                                }
                            });
                        } catch (final Exception e) {

                        }
                        return null;
                    }
                };
                task.execute();

                editTextPaitentFirstName.setText("");
                editTextPaitentLastName.setText("");
                editTextPaitentBirthDate.setText("");
                editTextPaitentGender.setText("");
                editTextPaitentHeight.setText("");
                editTextPaitentWeight.setText("");
                editTextPaitentBloodGroup.setText("");
                editTextPaitentUniqueIdNumber.setText("");
                Toast.makeText(PatientRegistraion.this,"Patient Registred Successfully",Toast.LENGTH_LONG).show();
                Intent intent=new Intent(PatientRegistraion.this, Patients.class);
                finish();
                startActivity(intent);
            }
        });

    }
    protected void pickImage() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, 987);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        try {
            //handle result from gallary select
            if (requestCode == 987) {
                Uri currImageURI = data.getData();
                this.mImageUrl = currImageURI;
                //Set the image view's image by using imageUri
                imageViewProfielPicture.setImageURI(currImageURI);
                saveImage();
            }
        } catch (Exception ex) {
            Log.e("TodoDetailsActivity", "Error in onActivityResult: " + ex.getMessage());
        }
    }

    protected void saveImage() {
        String imageString = null;
        if (mImageUrl != null && !mImageUrl.equals("")) {
            try {
                Cursor cursor = getContentResolver().query(mImageUrl, null,
                        null, null, null);
                cursor.moveToFirst();

                int index = cursor
                        .getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                String absoluteFilePath = cursor.getString(index);
                FileInputStream fis = new FileInputStream(absoluteFilePath);

                int bytesRead = 0;
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                byte[] b = new byte[1024];
                while ((bytesRead = fis.read(b)) != -1) {
                    bos.write(b, 0, bytesRead);
                }
                byte[] bytes = bos.toByteArray();
                imageString = Base64.encodeToString(bytes, Base64.DEFAULT);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        new SaveTodoTask(this).execute("testing", imageString);
    }

    private class SaveTodoTask extends AsyncTask<String, Void, String> {

        private Activity mContext;

        public SaveTodoTask(Activity activity) {
            mContext = activity;
        }

        @Override
        protected String doInBackground(String... params) {
            String todoText = params[0];
            String imageData = null;
            if (params.length > 1)
                imageData = params[1];

            JSONObject jsonUrl = new JSONObject();
            try {
                jsonUrl.put("complete", "false");
                jsonUrl.put("text", todoText);
                if (imageData != null)
                    jsonUrl.put("profilepic", imageData);
            } catch (JSONException e) {
                Log.e("TodoDetailsActivity", "Error creating JSON object: " + e.getMessage());
            }
            Log.i("TodoDetailsActivity", "JSON: " + jsonUrl.toString());

            HttpURLConnection urlConnection = null;
            try {
                URL url = new URL(Constants.kAddTodoUrl);
                urlConnection = (HttpURLConnection) url//
                        .openConnection();
                urlConnection.setDoOutput(true);
                urlConnection.setDoInput(true);
                urlConnection.setRequestMethod("POST");
                urlConnection.addRequestProperty("Content-Type",
                        "application/json");
                urlConnection.addRequestProperty("ACCEPT", "application/json");
                urlConnection.addRequestProperty("X-ZUMO-APPLICATION", Constants.kMobileServiceAppId);
                // Write JSON to Server
                DataOutputStream wr = new DataOutputStream(
                        urlConnection.getOutputStream());
                wr.writeBytes(jsonUrl.toString());
                wr.flush();
                wr.close();
                // Get response code
                int response = urlConnection.getResponseCode();
                // Read response
                InputStream inputStream = new BufferedInputStream(
                        urlConnection.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(
                        new InputStreamReader(inputStream));
                StringBuilder stringBuilderResult = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilderResult.append(line);
                }
                //A successful response will have a 201 response code
                if (response == 201)
                    return "SUCCESS";
                return "FAIL";

            } catch (IOException e) {
                Log.e("TodoDetailsActivity", "IO Exeception: " + e.getMessage());
                e.printStackTrace();
                return "IOERROR";
            } finally {
                urlConnection.disconnect();
            }
        }

        @Override
        protected void onPostExecute(String status) {
            // Do something with result
            if (status.equals("SUCCESS")) {
                Toast.makeText(getApplicationContext(),
                        "Todo Created Successfully", Toast.LENGTH_SHORT).show();
                mContext.finishActivity(1);
                finish();
            } else {
                Toast.makeText(getApplicationContext(),
                        "There was an error creating the Todo: " + status,
                        Toast.LENGTH_SHORT).show();
            }
        }
    }
}