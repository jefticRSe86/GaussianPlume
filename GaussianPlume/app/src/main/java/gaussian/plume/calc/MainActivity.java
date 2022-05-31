package gaussian.plume.calc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String PI = "3.141592653589793";
    private EditText edtMassFlow;
    private EditText edtWindSpeed;
    private EditText edtSigmaY;
    private EditText edtSigmaZ;
    private EditText edtPollutionHeightH;
    private EditText edtPollutionAxisZ;
    private EditText edtPollutionAxisY;
    private TextView txtResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        edtMassFlow = (EditText) findViewById(R.id.editText);
        edtWindSpeed = (EditText) findViewById(R.id.editText2);
        edtSigmaY = (EditText) findViewById(R.id.editText3);
        edtSigmaZ = (EditText) findViewById(R.id.editText4);
        edtPollutionHeightH = (EditText) findViewById(R.id.editText6);
        edtPollutionAxisZ = (EditText) findViewById(R.id.editText7);
        edtPollutionAxisY = (EditText) findViewById(R.id.editText5);
        txtResults = (TextView) findViewById(R.id.textView8);
        TextView txtUnit = (TextView) findViewById(R.id.textView10);
        TextView txtSigmaY = (TextView) findViewById(R.id.textView3);
        TextView txtSigmaZ = (TextView) findViewById(R.id.textView4);
        txtUnit.setText(Html.fromHtml("μg/m<sup>3</sup>"));
        txtSigmaY.setText(Html.fromHtml("σ<sub>y</sub>"));
        txtSigmaZ.setText(Html.fromHtml("σ<sub>z</sub>"));

    }

    public void calculateGauss(View view) {

        try {

            float massFlow = Float.parseFloat(edtMassFlow.getText().toString());
            float windSpeed = Float.parseFloat(edtWindSpeed.getText().toString());
            float sigmaY = Float.parseFloat(edtSigmaY.getText().toString());
            float sigmaZ = Float.parseFloat(edtSigmaZ.getText().toString());
            float pollutionHeightH = Float.parseFloat(edtPollutionHeightH.getText().toString());
            float pollutionAxisZ = Float.parseFloat(edtPollutionAxisZ.getText().toString());
            float pollutionAxisY = Float.parseFloat(edtPollutionAxisY.getText().toString());
            float pI = Float.parseFloat (PI);

            float gaussValue = calculateGauss(massFlow, windSpeed, sigmaY, sigmaZ, pollutionHeightH, pollutionAxisZ, pollutionAxisY, pI);

            txtResults.setText(gaussValue + "");

            Toast.makeText(getApplicationContext(), R.string.confirmation, Toast.LENGTH_SHORT).show();
        }
        catch(Exception e){
            Toast.makeText(getApplicationContext(), R.string.calculationError, Toast.LENGTH_SHORT).show();
        }

    }
    private float calculateGauss (float massFlow, float windSpeed, float sigmaY, float sigmaZ, float pollutionHeightH, float pollutionAxisZ, float pollutionAxisY, float pI){

        return (float) ((massFlow/(2*pI*windSpeed*sigmaY*sigmaZ)) * (Math.exp(-1*Math.pow(pollutionAxisZ - pollutionHeightH,2) / (2*Math.pow(sigmaZ,2))) +
                Math.exp(-1*Math.pow(pollutionAxisZ + pollutionHeightH,2) / (2*Math.pow(sigmaZ,2)))) * (Math.exp(-Math.pow(pollutionAxisY,2) / (2*Math.pow(sigmaY,2))))*1000000);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                Intent aa = new Intent(android.provider.Settings.ACTION_SETTINGS);
                startActivityForResult(aa,0);
                break;
            case R.id.display:
                Intent ea = new Intent(android.provider.Settings.ACTION_DISPLAY_SETTINGS);
                startActivityForResult(ea,0);
                break;
            case R.id.about1:
                Intent eb1 = new Intent(getApplicationContext(),AboutActivity.class);
                startActivityForResult(eb1,0);
                break;
            case R.id.about:
                Intent eb = new Intent(getApplicationContext(),SecondActivity.class);
                startActivityForResult(eb,0);
                break;



            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }
}




