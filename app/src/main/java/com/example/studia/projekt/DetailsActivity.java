package com.example.studia.projekt;

import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import com.example.studia.projekt.db.AppDatabase;
import com.example.studia.projekt.db.model.FieldRecord;

public class DetailsActivity extends AppCompatActivity {

  private int id;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_details);

    AppDatabase database = AppDatabase.getInstance(getApplicationContext());

    int fieldId = getIntent().getExtras().getInt("fieldId");
    id = getIntent().getExtras().getInt("userId");

    final TextView substanceTextView = findViewById(R.id.textViewSubstance);
    final TextView dateTextView = findViewById(R.id.textViewDate);
    final TextView plantTextView = findViewById(R.id.textViewPlant);
    final TextView areaTextView = findViewById(R.id.textViewArea);
    final TextView usageReasonTextView = findViewById(R.id.textViewUsageReason);
    final TextView doseTextView = findViewById(R.id.textViewDose);

    database.fieldDao().getFieldRecordById(fieldId).observe(this,
        new Observer<FieldRecord>() {
          @Override
          public void onChanged(@Nullable FieldRecord record) {

            areaTextView.setText(record.getArea());
            plantTextView.setText(record.getPlant());
            substanceTextView.setText(record.getSubstance());
            dateTextView.setText(record.getDate());
            usageReasonTextView.setText(record.getUsageReason());
            doseTextView.setText(record.getDose());

          }
        });
  }

  ///////////////// intencja

  public void click(View view) {

    Intent intent = new Intent(this, MainActivity.class);
    intent.putExtra("userId", id);
    startActivity(intent);

  }
}
