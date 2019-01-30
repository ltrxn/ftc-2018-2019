package ftcrobotcontroller;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.qualcomm.ftcrobotcontroller.R;

/**
 * Created by Trxn on 11/25/2018.
 */

public class FieldPositionActivity extends Activity {


    private TextView chosenText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_field_position);
        Button buttonRedBottom = (Button) findViewById(R.id.button_red_bottom);
        Button buttonRedTop = (Button) findViewById(R.id.button_red_top);
        Button buttonBlueTop = (Button) findViewById(R.id.button_blue_top);
        Button buttonBlueBottom = (Button) findViewById(R.id.button_blue_bottom);

        Button buttonSet = (Button)findViewById(R.id.button_set);

        buttonSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("POSITION", FieldPositonData.fieldPostion);
                setResult(Activity.RESULT_OK,resultIntent);
                finish();

            }
        });

        chosenText = (TextView) findViewById(R.id.selectedText2);

        buttonRedBottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FieldPositonData.fieldPostion = FieldPositonData.FieldPosition.RED_BOTTOM;
                updateChosenText();
            }
        });
        buttonRedTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FieldPositonData.fieldPostion = FieldPositonData.FieldPosition.RED_TOP;
                updateChosenText();
            }
        });
        buttonBlueTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FieldPositonData.fieldPostion = FieldPositonData.FieldPosition.BLUE_TOP;
                updateChosenText();
            }
        });
        buttonBlueBottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FieldPositonData.fieldPostion = FieldPositonData.FieldPosition.BLUE_BOTTOM;
                updateChosenText();
            }
        });


        updateChosenText();
    }


    private void updateChosenText(){

        switch(FieldPositonData.fieldPostion){
            case RED_BOTTOM:
                chosenText.setText("Red Bottom");
                chosenText.setTextColor(Color.RED);
                break;
            case RED_TOP:
                chosenText.setText("Red Top");
                chosenText.setTextColor(Color.RED);
                break;
            case BLUE_BOTTOM:
                chosenText.setText("Blue Bottom");
                chosenText.setTextColor(Color.BLUE);
                break;
            case BLUE_TOP:
                chosenText.setText("Blue Top");
                chosenText.setTextColor(Color.BLUE);
                break;

        }

    }

}
