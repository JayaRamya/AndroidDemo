package com.abhan.example;

import java.util.ArrayList;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;

public class DragNDropListActivity extends ListActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);

		ArrayList<String> content = new ArrayList<String>(mListContent.length);
		for (int i = 0; i < mListContent.length; i++) {
			content.add(mListContent[i]);
		}

		setListAdapter(new DragNDropAdapter(this,
				new int[] { R.layout.rowitem }, new int[] { R.id.txtName },
				content));
		ListView listView = getListView();

		if (listView instanceof DragNDropListView) {
			((DragNDropListView) listView).setDropListener(mDropListener);
			((DragNDropListView) listView).setRemoveListener(mRemoveListener);
			((DragNDropListView) listView).setDragListener(mDragListener);
		}
	}

	private final DropListener mDropListener = new DropListener() {
		@Override
		public void onDrop(int from, int to) {
			ListAdapter adapter = getListAdapter();
			if (adapter instanceof DragNDropAdapter) {
				((DragNDropAdapter) adapter).onDrop(from, to);
				getListView().invalidateViews();
			}
		}
	};

	private final RemoveListener mRemoveListener = new RemoveListener() {
		@Override
		public void onRemove(int which) {
			ListAdapter adapter = getListAdapter();
			if (adapter instanceof DragNDropAdapter) {
				((DragNDropAdapter) adapter).onRemove(which);
				getListView().invalidateViews();
			}
		}
	};

	private final DragListener mDragListener = new DragListener() {

		int backgroundColor = 0xe0103010;
		int defaultBackgroundColor;

		@Override
		public void onDrag(int x, int y, ListView listView) {
		}

		@Override
		public void onStartDrag(View itemView) {
			itemView.setVisibility(View.INVISIBLE);
			defaultBackgroundColor = itemView.getDrawingCacheBackgroundColor();
			itemView.setBackgroundColor(backgroundColor);
			ImageView imageView = (ImageView) itemView
					.findViewById(R.id.holdImage);
			if (imageView != null)
				imageView.setVisibility(View.INVISIBLE);
		}

		@Override
		public void onStopDrag(View itemView) {
			itemView.setVisibility(View.VISIBLE);
			itemView.setBackgroundColor(defaultBackgroundColor);
			ImageView imageView = (ImageView) itemView
					.findViewById(R.id.holdImage);
			if (imageView != null)
				imageView.setVisibility(View.VISIBLE);
		}

	};

	private static String[] mListContent = { "Abhan 1", "Abhan 2", "Abhan 3",
			"Abhan 4", "Abhan 5", "Abhan 6", "Abhan 7", "Abhan 8", "Abhan 9",
			"Abhan 10", "Abhan 11", "Abhan 12", "Abhan 13", "Abhan 14",
			"Abhan 15", "Abhan 16", "Abhan 17", "Abhan 18", "Abhan 19",
			"Abhan 20", "Abhan 21" };
}