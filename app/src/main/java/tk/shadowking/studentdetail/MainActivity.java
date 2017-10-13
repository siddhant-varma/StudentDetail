package tk.shadowking.studentdetail;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText name, contact;
    TextView yr , error;
    Button submit;
    RadioGroup gender;
    RadioButton sex, year;
    CheckBox cb1, cb2, cb3;
    String errorMsg = "", lang = "";
    boolean selectedGender = false, selectedYear = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = (EditText) findViewById(R.id.name);
        contact = (EditText) findViewById(R.id.contact);
        yr = (TextView) findViewById(R.id.yr);
        error = (TextView) findViewById(R.id.error);
        submit = (Button) findViewById(R.id.button);
        gender = (RadioGroup) findViewById(R.id.gender);
        cb1 = (CheckBox) findViewById(R.id.cb1);
        cb2 = (CheckBox) findViewById(R.id.cb2);
        cb3 = (CheckBox) findViewById(R.id.cb3);

        gender.clearCheck();
        cb1.setChecked(false);
        cb2.setChecked(false);
        cb3.setChecked(false);
        /*gender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedGender = true;
                Toast.makeText(MainActivity.this, "Gender Selected", Toast.LENGTH_SHORT).show();
            }
        });*/
        gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){

            @Override
            public void onCheckedChanged(RadioGroup rg, int checked){
                RadioButton rb = rg.findViewById(checked);
                sex = rb;
                if(rb != null && checked > -1){
                    selectedGender = true;
                    //Toast.makeText(MainActivity.this, sex.getText().toString() + " Selected", Toast.LENGTH_SHORT).show();
                }

            }

        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                if(name.getText().toString().equals(""))
                    errorMsg += "PLease Enter Name.";
                if(contact.getText().toString().equals(""))
                    errorMsg += "\nPLease Enter Contact.";
                if(!selectedGender)
                    errorMsg += "\nSelect Gender";
                if(!selectedYear)
                    errorMsg += "\nSelect Year";

                if(cb1.isChecked())
                    lang += getString(R.string.cpp) + ", ";
                if(cb2.isChecked())
                    lang += getString(R.string.java) + ", ";
                if(cb3.isChecked())
                    lang += getString(R.string.python);
                if(lang.equals(""))
                    lang = "None";

                if(!errorMsg.equals("")){
                    error.setText(errorMsg);
                }
                else{
                    Toast.makeText(MainActivity.this, "Thank You.\nHave A Good Day!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), view.class)
                            .putExtra("Name", name.getText().toString())
                            .putExtra("Contact", contact.getText().toString())
                            .putExtra("Gender", sex.getText().toString())
                            .putExtra("Year", year.getText().toString())
                            .putExtra("Languages Known", lang)
                    );
                }

                errorMsg = "";
            }
        });
    }


    public void displayYr(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();
        selectedYear = true;
        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.yr1:
                if (checked){
                    year = (RadioButton) findViewById(R.id.yr1);
                    yr.setText(getString(R.string.greet1));
                }
                break;
            case R.id.yr2:
                if (checked){
                    yr.setText(getString(R.string.greet2));
                    year = (RadioButton) findViewById(R.id.yr2);
                }
                break;
            case R.id.yr3:
                if (checked){
                    yr.setText(getString(R.string.greet3));
                    year = (RadioButton) findViewById(R.id.yr3);
                }
                break;
        }
        //Toast.makeText(this, year.getText().toString() + " Selected", Toast.LENGTH_SHORT).show();

    }
}
