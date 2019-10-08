package com.example.dramaapp.main.drama.adapter;

import android.app.Activity;

import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.dramaapp.R;
import com.example.dramaapp.dto.Dto.response.DramaResponse;
import com.example.dramaapp.dto.Dto.response.DramaResponse.DataBean;
import com.example.dramaapp.main.drama.adapter.DramaAdapter.DramaViewHolder;
import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.subjects.PublishSubject;
import java.util.ArrayList;
import java.util.List;

public class DramaAdapter extends RecyclerView.Adapter<DramaViewHolder> {

	private Activity activity;
	private List<DramaResponse.DataBean> agentList = new ArrayList<>();

	private PublishSubject<DramaResponse.DataBean> agentCallback = PublishSubject.create();
	public Observable<DataBean> getAgentCallback() {
		return agentCallback;
	}

	private PublishSubject<Pair<Integer, DramaResponse.DataBean>> itemClick = PublishSubject.create();
	public Observable<Pair<Integer, DramaResponse.DataBean>> getItemClick() {
		return itemClick;
	}

	public DramaAdapter(Activity activity) {
		this.activity = activity;
	}

	public void updateData(List<DramaResponse.DataBean> list) {
		Log.d("dsfsdf", list.get(0).getName());
		this.agentList = list;
		this.notifyDataSetChanged();
	}

	@NonNull
	@Override
	public DramaViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
		return new DramaViewHolder(viewGroup);
	}

	@Override
	public void onBindViewHolder(@NonNull DramaViewHolder dramaViewHolder, int position) {
		dramaViewHolder.bind(position, this.agentList.get(position));
	}

	@Override
	public int getItemCount() {
		return agentList.size();
	}

	class DramaViewHolder extends RecyclerView.ViewHolder {

		DramaViewHolder(ViewGroup viewGroup) {
			super(LayoutInflater.from(viewGroup.getContext())
					.inflate(R.layout.item_drama, viewGroup, false));
		}

		public void bind(int position, DramaResponse.DataBean data) {
			itemView.setOnClickListener(v -> itemClick.onNext(new Pair<>(position, data)));
			TextView dramaNameLabel = itemView.findViewById(R.id.dramaNameLabel);
			dramaNameLabel.setText("" + data.getName());
			TextView dramaRatingLabel = itemView.findViewById(R.id.dramaRatingLabel);
			dramaRatingLabel.setText(""+data.getRating());
			TextView dramaCreatedatLabel = itemView.findViewById(R.id.dramaCreatedatLabel);
			dramaCreatedatLabel.setText(""+data.getCreated_at());

			ImageView dramaImg = itemView.findViewById(R.id.dramaImg);
			Glide.with(activity).load(data.getThumb()).into(dramaImg);
		}
	}
}
