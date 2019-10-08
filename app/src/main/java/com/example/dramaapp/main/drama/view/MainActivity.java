package com.example.dramaapp.main.drama.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.dramaapp.R;
import com.example.dramaapp.base.BaseActivity;
import com.example.dramaapp.dto.Dto.response.DramaResponse;
import com.example.dramaapp.dto.Dto.response.DramaResponse.DataBean;
import com.example.dramaapp.main.drama.adapter.DramaAdapter;
import com.example.dramaapp.main.drama.presenter.DramaPresenter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends BaseActivity {

	private DramaPresenter presenter;
	private RecyclerView recyclerView;
	private DramaAdapter dramaAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		//初始化UI
		initView();
		//初始化監聽員工資料
		initPresenter();
		//取得員工資料
		presenter.getDrama();

	}


	private void initView() {
		recyclerView = findViewById(R.id.recyclerView);
		recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
		dramaAdapter = new DramaAdapter(this);
		recyclerView.setAdapter(dramaAdapter);


		disposable.add(dramaAdapter.getItemClick().subscribe(pair -> {
			Intent it = new Intent(this,DramaDetailedActivity.class);
			Bundle bundle = new Bundle(); //new一個Bundle物件，並將要傳遞的資料傳入
			bundle.putString("name", pair.second.getName());
			bundle.putInt("rating", (int) pair.second.getRating());
			bundle.putString("created_at", pair.second.getCreated_at());
			bundle.putString("thumb", pair.second.getThumb());
			bundle.putInt("total_views", pair.second.getTotal_views());

			it.putExtras(bundle);//將Bundle物件傳給intent
			startActivity(it);
		}));

	}

	private void initPresenter() {
		presenter = new DramaPresenter(new DramaPresenter.DramaCallback() {
			@Override
			public void onDramaSuccess(DramaResponse dto) {
				SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
				SharedPreferences.Editor editor = prefs.edit();

				Gson gson = new Gson();
				List<List<DramaResponse.DataBean>> textList = new ArrayList<List<DramaResponse.DataBean>>();
				textList.addAll(Collections.singleton(dto.getData()));
				String jsonText = gson.toJson(textList);
				editor.putString("key", jsonText);
				editor.commit();

				dramaAdapter.updateData(dto.getData());
			}

			@Override
			public void onDramaFail(String errorMsg) {
				Toast.makeText(MainActivity.this,errorMsg,Toast.LENGTH_SHORT).show();
				try {
					Gson gson = new Gson();
					SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
					String json = prefs.getString("key", null);
					Type type = new TypeToken<List<DramaResponse.DataBean>>() {}.getType();
					List<DramaResponse.DataBean> a =  gson.fromJson(json, type);
					dramaAdapter.updateData(a);
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	@Override
	protected void onDestroy() {
		super.onDestroy();
		presenter.onDestroy();
	}
}
