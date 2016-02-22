package ca.lambton.c0661823.currencyconvertorjatinderbhola;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultLabel = (TextView) findViewById(R.id.resultLabel);
        seekValue = (TextView) findViewById(R.id.seekValue);
        cad = (EditText) findViewById(R.id.currencyCanada);
        rs = (EditText)findViewById(R.id.currencyRuppee);
        us = (EditText)findViewById(R.id.currencyUS);

        cad.setEnabled(false);
        rs.setEnabled(false);
        us.setEnabled(false);

        radiogroup = (RadioGroup)this.findViewById(R.id.radioGroup);

        uc = (RadioButton)this.findViewById(R.id.uc);
        cu = (RadioButton)this.findViewById(R.id.cu);
        ci = (RadioButton)this.findViewById(R.id.ci);
        ic = (RadioButton)this.findViewById(R.id.ic);

        calButton = (Button)this.findViewById(R.id.calButton);

        calButton.setOnClickListener(this);

        RadioGroup categoryGroup = (RadioGroup) findViewById(R.id.radioGroup);
        categoryGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId) {
                    case R.id.uc:
                        us.setEnabled(true); us.setText("");
                        cad.setEnabled(false); cad.setText("");
                        rs.setEnabled(false); rs.setText("");
                        us.setFocusable(true);
                        us.setFocusableInTouchMode(true);
                        seekBar.setProgress(0);
                        conversionRate = 1.25;
                        seekValue.setText(conversionRate+"");
                        break;
                    case R.id.cu:
                        us.setEnabled(false); us.setText("");
                        cad.setEnabled(true); cad.setText("");
                        rs.setEnabled(false); rs.setText("");
                        cad.setFocusable(true);
                        cad.setFocusableInTouchMode(true);
                        seekBar.setProgress(0);
                        conversionRate = 0.80;
                        seekValue.setText(conversionRate+"");
                        break;
                    case R.id.ci:
                        cad.setEnabled(true); cad.setText("");
                        rs.setEnabled(false); rs.setText("");
                        us.setEnabled(false); us.setText("");
                        cad.setFocusable(true);
                        cad.setFocusableInTouchMode(true);
                        seekBar.setProgress(0);
                        seekValue.setText(conversionRate+"");
                        conversionRate = 50.46;
                        break;
                    case R.id.ic:
                        rs.setEnabled(true);
                        rs.setText("");
                        cad.setEnabled(false);
                        cad.setText("");
                        us.setEnabled(false);
                        us.setText("");
                        rs.setFocusable(true);
                        rs.setFocusableInTouchMode(true);
                        seekBar.setProgress(0);
                        conversionRate = 0.020;
                        seekValue.setText(conversionRate+"");
                        break;
                    default:

                }
            }
        });

        seekBar = (SeekBar) findViewById(R.id.seekBar1);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                float value = (float) ((float)progress / 10.0);
                seekValue.setText(String.valueOf(value));
                conversionRate = progress/10;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        showAlert("Select the conversion first. Thanks.");
    }

    EditText cad, rs, us;
    RadioButton uc, cu, ci, ic;
    Button calButton;
    TextView resultLabel, seekValue;
    RadioGroup radiogroup;
    double unit, conversionRate;
    SeekBar seekBar;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        return true;
    }

    @Override

    public void onClick(View arg0) {
       if(uc.isChecked())
        {
            if(us.getText().toString().isEmpty())
            {
                showAlert("Enter Some value in USA field.");
            }
            else {
                double val = calculate(us.getText().toString());
                showAlert("The conversion result is: " + us.getText().toString() + " US Dollar/s = " + val + " CAD Dollar/s  \n" +
                        " with a conversion rate of :" + conversionRate);
            }
        }
        else if(cu.isChecked())
        {
            if(cad.getText().toString().isEmpty())
            {
                showAlert("Enter Some value in CAD field.");
            }
            else {
                double val = calculate(cad.getText().toString());
                showAlert("The conversion result is: " + cad.getText().toString() + " CAD Dollar/s = " + val + " US Dollar/s  \n" +
                        " with a conversion rate of :" + conversionRate);
            }
        }
        else if(ci.isChecked())
        {
            if(cad.getText().toString().isEmpty())
            {
                showAlert("Enter Some value in CAD field.");
            }
            else {
                double val = calculate(cad.getText().toString());
                showAlert("The conversion result is: " + cad.getText().toString() + " CAD Dollar/s = " + val + " Ruppee/s \n" +
                        " with a conversion rate of :" + conversionRate);
            }
        }
        else if(ic.isChecked())
        {
            if(rs.getText().toString().isEmpty())
            {
                showAlert("Enter Some value in INDIA field.");
            }
            else {
                double val = calculate(rs.getText().toString());
                showAlert("The conversion result is: " + rs.getText().toString() + " Ruppee/s = " + val + " CAD Dollar/s \n with a conversion" +
                        "rate of :" + conversionRate);
            }
        }
        else
       {
           showAlert("Check any of the radio box, first.");
       }

    }

    double calculate(String unit) {
        double val = Double.parseDouble(unit);

        resultLabel.setText(String.valueOf(val*conversionRate));
        return val*conversionRate;
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

    void calculateButton()
    {


    }

    void showAlert(String alertmsg)
    {
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();

        // Setting Dialog Title
        alertDialog.setTitle("Alert");

        // Setting Dialog Message
        alertDialog.setMessage(alertmsg);

        // Setting OK Button
        alertDialog.setButton("Agree", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // Write your code here to execute after dialog closed
                Toast.makeText(getApplicationContext(), "Agree", Toast.LENGTH_SHORT).show();
            }
        });

        // Showing Alert Message
        alertDialog.show();
    }
}
