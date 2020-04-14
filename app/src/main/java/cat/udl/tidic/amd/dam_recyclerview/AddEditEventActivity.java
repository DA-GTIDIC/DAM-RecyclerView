package cat.udl.tidic.amd.dam_recyclerview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddEditEventActivity extends AppCompatActivity {

    public static final String EXTRA_ID =
            "cat.udl.tidic.amd.dam_recyclerview.EXTRA_ID";
    public static final String EXTRA_DESCRIPTION =
            "cat.udl.tidic.amd.dam_recyclerview.EXTRA_DESCRIPTION";
    public static final String EXTRA_TITLE =
            "cat.udl.tidic.amd.dam_recyclerview.EXTRA_TITLE";
    public static final String EXTRA_AVALUATION=
            "cat.udl.tidic.amd.dam_recyclerview.EXTRA_AVALUATION";
    public static final String EXTRA_USERID =
            "cat.udl.tidic.amd.dam_recyclerview.EXTRA_USERID";
    public static final String EXTRA_START =
            "cat.udl.tidic.amd.dam_recyclerview.EXTRA_START";
    public static final String EXTRA_END =
            "cat.udl.tidic.amd.dam_recyclerview.EXTRA_END";

    private final static String TAG = "AddEditForm";

    private EditText editTextTitle;
    private EditText editTextEnd;
    private EditText editTextStart;
    private EditText editTextDescription;
    private RatingBar ratingBarAvaluation;
    private Button savaButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_event);

        editTextTitle = findViewById(R.id.edit_text_event_title);
        editTextDescription = findViewById(R.id.edit_text_event_description);
        editTextStart = findViewById(R.id.edit_text_event_start);
        editTextEnd = findViewById(R.id.edit_text_event_end);
        ratingBarAvaluation = findViewById(R.id.ratingBar_event);
        savaButton = findViewById(R.id.button_event_save);


        Intent intent = getIntent();

        if (intent.hasExtra(EXTRA_ID)) {
            setTitle("Edit Event");
            DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            editTextTitle.setText(intent.getStringExtra(EXTRA_TITLE));
            editTextDescription.setText(intent.getStringExtra(EXTRA_DESCRIPTION));
            Date dateStart =  (Date)intent.getSerializableExtra(EXTRA_START);

            editTextStart.setText( formatter.format(dateStart));

            Date dateEnd =  (Date)intent.getSerializableExtra(EXTRA_END);
            editTextEnd.setText(formatter.format(dateEnd));
            ratingBarAvaluation.setRating(intent.getFloatExtra(EXTRA_AVALUATION,1));

        } else {
            setTitle("Add Event");
        }

        savaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveEvent();
            }
        });


    }


    private void saveEvent() {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String title = editTextTitle.getText().toString();
        String description = editTextDescription.getText().toString();
        String start = editTextStart.getText().toString();
        Date dataStart = null;
        Date dataEnd = null;

        try {
            dataStart = df.parse(start);
        } catch (ParseException e) {
            e.printStackTrace();
            Toast.makeText(this,
                    "Error en el formato de la fecha inicial", Toast.LENGTH_SHORT).show();
        }
        String end = editTextEnd.getText().toString();
        try {
            dataEnd = df.parse(end);
        } catch (ParseException e) {
            e.printStackTrace();
            Toast.makeText(this,
                    "Error en la fecha final", Toast.LENGTH_SHORT).show();
        }
        float avaluation = ratingBarAvaluation.getRating();

        Log.d(TAG, "" +avaluation);

        if (title.trim().isEmpty() || description.trim().isEmpty()) {
            Toast.makeText(this,
                    "Please insert a title and description", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent data = new Intent();
        data.putExtra(EXTRA_TITLE, title);
        data.putExtra(EXTRA_DESCRIPTION, description);
        data.putExtra(EXTRA_START, dataStart);
        data.putExtra(EXTRA_END, dataEnd);
        data.putExtra(EXTRA_AVALUATION, avaluation);

        int id = getIntent().getIntExtra(EXTRA_ID, -1);
        if (id != -1) {
            data.putExtra(EXTRA_ID, id);
        }

        setResult(RESULT_OK, data);
        finish();
    }





}
