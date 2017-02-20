package com.geniusnine.android.geniusninehealth.Adapters;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.geniusnine.android.geniusninehealth.AzureTable.PatientBasicDetails;
import com.geniusnine.android.geniusninehealth.DashBord.Patients;
import com.geniusnine.android.geniusninehealth.MainActivity;
import com.geniusnine.android.geniusninehealth.R;

/**
 * Created by AndriodDev8 on 15-02-2017.
 */

public class PatientAdapter extends ArrayAdapter<PatientBasicDetails> {

    Context mContext;

    /**
     * Adapter View layout
     */
    int mLayoutResourceId;

    public PatientAdapter(Context context, int resource) {
        super(context, resource);
        mContext = context;
        mLayoutResourceId = resource;
    }

    @Override
    public View getView(int position, final View convertView, ViewGroup parent) {
        View row = convertView;

        final PatientBasicDetails currentItem = getItem(position);

        if (row == null) {
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            row = inflater.inflate(R.layout.row_list_patient, parent, false);
        }

        row.setTag(currentItem);

        final TextView textViewPatientName = (TextView) row.findViewById(R.id.textViewPatientName);
        textViewPatientName.setText(currentItem.getFname() + " " + currentItem.getLname());
        final Button buttondelete = (Button) row.findViewById(R.id.btndelete);
        buttondelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setMessage("Are You Sure Want To Delete Patient")
                        .setTitle("Delete Patient");
               // Add the buttons
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User clicked OK button
                        Patients activity = (Patients) mContext;
                        activity.deletepatient(currentItem);
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });


               // Create the AlertDialog
                AlertDialog dialog = builder.create();
                dialog.show();

            }
        });
        final Button buttonupdate = (Button) row.findViewById(R.id.btnupdate);
        buttonupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final  EditText editTextPaitentFirstName,editTextPaitentLastName,editTextPaitentBirthDate,editTextPaitentGender,editTextPaitentHeight,editTextPaitentWeight,editTextPaitentBloodGroup,editTextPaitentUniqueIdNumber;
                final Button buttonupdate;
                final LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                final View alertLayout = inflater.inflate(R.layout.activity_patient_registraion, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
                alertDialogBuilder.setTitle("Patient Update");
                editTextPaitentFirstName = (EditText) alertLayout.findViewById(R.id.editTextPaitentFirstName);
                editTextPaitentFirstName.setText(currentItem.getFname());
                editTextPaitentLastName = (EditText) alertLayout.findViewById(R.id.editTextPaitentLastName);
                editTextPaitentLastName.setText(currentItem.getLname());
                editTextPaitentBirthDate=(EditText)alertLayout.findViewById(R.id.editTextPaitentBirthDate);
                editTextPaitentBirthDate.setText(currentItem.getBirthdate());
                editTextPaitentGender=(EditText)alertLayout.findViewById(R.id.editTextPaitentGender);
                editTextPaitentGender.setText(currentItem.getGender());
                editTextPaitentHeight=(EditText)alertLayout.findViewById(R.id.editTextPaitentHeight);
                editTextPaitentHeight.setText(currentItem.getHeight());
                editTextPaitentWeight=(EditText)alertLayout.findViewById(R.id.editTextPaitentWeight);
                editTextPaitentWeight.setText(currentItem.getWeight());
                editTextPaitentBloodGroup=(EditText)alertLayout.findViewById(R.id.editTextPaitentBloodGroup);
                editTextPaitentBloodGroup.setText(currentItem.getBloodgroup());
                editTextPaitentUniqueIdNumber=(EditText)alertLayout.findViewById(R.id.editTextPaitentUniqueIdNumber);
                editTextPaitentUniqueIdNumber.setText(currentItem.getUidssn());
                buttonupdate=(Button)alertLayout.findViewById(R.id.buttonRegister);
                buttonupdate.setVisibility(View.INVISIBLE);
                alertDialogBuilder.setView(alertLayout);
                alertDialogBuilder.setPositiveButton("Update",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                String fname,lname,birthdate,gender,height,weight,bloodgroup,uniqueid;
                                fname = editTextPaitentFirstName.getText().toString().trim();
                                lname = editTextPaitentLastName.getText().toString().trim();
                                birthdate=editTextPaitentBirthDate.getText().toString().trim();
                                gender = editTextPaitentGender.getText().toString().trim();
                                height=editTextPaitentHeight.getText().toString().trim();
                                weight = editTextPaitentWeight.getText().toString().trim();
                                bloodgroup=editTextPaitentBloodGroup.getText().toString().trim();
                                uniqueid = editTextPaitentUniqueIdNumber.getText().toString().trim();

                                Patients activity = (Patients) mContext;
                                activity.Updatepatient(currentItem,fname,lname,birthdate,gender,height,weight,bloodgroup,uniqueid);
                                Toast.makeText(getContext(),"Patient Updated",Toast.LENGTH_LONG).show();
                            }
                        });
                alertDialogBuilder.setNegativeButton("Close",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                            }
                        });
                final AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();


            }


        });


        return row;
    }

}

