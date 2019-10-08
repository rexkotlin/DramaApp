package com.example.dramaapp.main.drama.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.dramaapp.R;
import com.example.dramaapp.base.BaseActivity;
import com.example.dramaapp.dto.Dto.response.DramaResponse;
import com.example.dramaapp.main.drama.adapter.DramaAdapter;
import com.example.dramaapp.main.drama.presenter.DramaPresenter;

public class DramaDetailedActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dramadetail);

		Bundle bundle = getIntent().getExtras();
		String name = bundle.getString("name");
		int rating = bundle.getInt("rating");
		String created_at = bundle.getString("created_at");
		String thumb = bundle.getString("thumb");
		int total_views = bundle.getInt("total_views");

		TextView nameLabel = findViewById(R.id.nameLabel);
		nameLabel.setText("" + name);
		TextView ratingLabel = findViewById(R.id.ratingLabel);
		ratingLabel.setText(""+rating);
		TextView createdatLabel = findViewById(R.id.createdatLabel);
		createdatLabel.setText(""+created_at);
		TextView totalviewsLabel = findViewById(R.id.totalviewsLabel);
		totalviewsLabel.setText(""+total_views);

		ImageView dramaImg = findViewById(R.id.dramaImg);
		Glide.with(this).load(thumb).into(dramaImg);


	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}
}
