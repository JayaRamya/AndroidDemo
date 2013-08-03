package com.abhan.example;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.view.ActionProvider;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.SubMenu;
import android.view.View;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;

public class AbhanActivity extends SherlockFragmentActivity implements
		CallbackListener, ActivityChangeCallback {
	
	private DrawerLayout mDrawerLayout;
	private ActionBarDrawerToggle mDrawerToggle;
	private FragmentManager fragmentManager;
	private LeftFragment leftFragment;
	private String title = "Abhan";
	public static final String ARG_NAME = "selected_name";
	public static final String ARG_DESG = "selected_desg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abhan);
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

		final ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(false);
		actionBar.setHomeButtonEnabled(true);
		actionBar.setBackgroundDrawable(new ColorDrawable(Color
				.parseColor("#08A3F5")));
		actionBar.setLogo(R.drawable.menu_dark);

		fragmentManager = getSupportFragmentManager();
		leftFragment = (LeftFragment) fragmentManager
				.findFragmentById(R.id.fragment_drawer);
		mDrawerToggle = new ActionBarDrawerToggle(
				this,
				mDrawerLayout,
				R.drawable.ic_drawer,
				R.string.drawer_open,
				R.string.drawer_close
				) {
					public void onDrawerClosed(View view) {
						supportInvalidateOptionsMenu();
						if (leftFragment != null && leftFragment.isInLayout()) {
							setBarTitle();
							leftFragment.getSelectedValues();
						}
					}
					
					public void onDrawerOpened(View drawerView) {
						setBarTitle();
						supportInvalidateOptionsMenu();
					}
				};
		mDrawerLayout.setDrawerListener(mDrawerToggle);
		if (savedInstanceState == null) {
			setBarTitle();
			selectedItem("", "");
		}
	}
	
	private void setBarTitle() {
		getSupportActionBar().setTitle(title);
	}
	
	@Override
	public void optionSelected(String selectedMonths, String selectedDesignation) {
		selectedItem(selectedMonths, selectedDesignation);
	}
	
	@Override
	public void changeActivity(String name) {
		Fragment fragment = new RightFragmentNew();
		Bundle args = new Bundle();
		args.putString(ARG_NAME, name);
		fragment.setArguments(args);
		fragmentManager
				.beginTransaction()
				.replace(R.id.content_frame, fragment)
				.addToBackStack(null)
				.commit();
	}

	private void selectedItem(String selectedMonths, String selectedDesignation) {
		fragmentManager.popBackStack(null,
				FragmentManager.POP_BACK_STACK_INCLUSIVE);

		Fragment fragment = new RightFragment();
		Bundle args = new Bundle();
		args.putString(ARG_NAME, selectedMonths);
		args.putString(ARG_DESG, selectedDesignation);
		fragment.setArguments(args);
		fragmentManager
				.beginTransaction()
				.replace(R.id.content_frame, fragment)
				.commit();
    }
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getSupportMenuInflater();
		inflater.inflate(R.menu.main, menu);
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		menu.findItem(R.id.action_websearch).setVisible(false);
		return super.onPrepareOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(final MenuItem item) {
		if (mDrawerToggle.onOptionsItemSelected(getMenuItem(item))) { return true; }
		switch (item.getItemId()) {
			case R.id.action_websearch:
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}
	
	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		mDrawerToggle.syncState();
	}
	
	private android.view.MenuItem getMenuItem(final MenuItem item) {
		return new android.view.MenuItem() {
			
			@Override
			public int getItemId() {
				return item.getItemId();
			}
			
			public boolean isEnabled() {
				return true;
			}
			
			@Override
			public boolean collapseActionView() {
				return false;
			}
			
			@Override
			public boolean expandActionView() {
				return false;
			}
			
			@Override
			public ActionProvider getActionProvider() {
				return null;
			}
			
			@Override
			public View getActionView() {
				return null;
			}
			
			@Override
			public char getAlphabeticShortcut() {
				return 0;
			}
			
			@Override
			public int getGroupId() {
				return 0;
			}
			
			@Override
			public Drawable getIcon() {
				return null;
			}
			
			@Override
			public Intent getIntent() {
				return null;
			}
			
			@Override
			public ContextMenuInfo getMenuInfo() {
				return null;
			}
			
			@Override
			public char getNumericShortcut() {
				return 0;
			}
			
			@Override
			public int getOrder() {
				return 0;
			}
			
			@Override
			public SubMenu getSubMenu() {
				return null;
			}
			
			@Override
			public CharSequence getTitle() {
				return null;
			}
			
			@Override
			public CharSequence getTitleCondensed() {
				return null;
			}
			
			@Override
			public boolean hasSubMenu() {
				return false;
			}
			
			@Override
			public boolean isActionViewExpanded() {
				return false;
			}
			
			@Override
			public boolean isCheckable() {
				return false;
			}
			
			@Override
			public boolean isChecked() {
				return false;
			}
			
			@Override
			public boolean isVisible() {
				return false;
			}
			
			@Override
			public android.view.MenuItem setActionProvider(
					ActionProvider actionProvider) {
				return null;
			}
			
			@Override
			public android.view.MenuItem setActionView(View view) {
				return null;
			}
			
			@Override
			public android.view.MenuItem setActionView(int resId) {
				return null;
			}
			
			@Override
			public android.view.MenuItem setAlphabeticShortcut(char alphaChar) {
				return null;
			}
			
			@Override
			public android.view.MenuItem setCheckable(boolean checkable) {
				return null;
			}
			
			@Override
			public android.view.MenuItem setChecked(boolean checked) {
				return null;
			}
			
			@Override
			public android.view.MenuItem setEnabled(boolean enabled) {
				return null;
			}
			
			@Override
			public android.view.MenuItem setIcon(Drawable icon) {
				return null;
			}
			
			@Override
			public android.view.MenuItem setIcon(int iconRes) {
				return null;
			}
			
			@Override
			public android.view.MenuItem setIntent(Intent intent) {
				return null;
			}
			
			@Override
			public android.view.MenuItem setNumericShortcut(char numericChar) {
				return null;
			}
			
			@Override
			public android.view.MenuItem setOnActionExpandListener(
					OnActionExpandListener listener) {
				return null;
			}
			
			@Override
			public android.view.MenuItem setOnMenuItemClickListener(
					OnMenuItemClickListener menuItemClickListener) {
				return null;
			}
			
			@Override
			public android.view.MenuItem setShortcut(char numericChar,
					char alphaChar) {
				return null;
			}
			
			@Override
			public void setShowAsAction(int actionEnum) {}
			
			@Override
			public android.view.MenuItem setShowAsActionFlags(int actionEnum) {
				return null;
			}
			
			@Override
			public android.view.MenuItem setTitle(CharSequence title) {
				return null;
			}
			
			@Override
			public android.view.MenuItem setTitle(int title) {
				return null;
			}
			
			@Override
			public android.view.MenuItem setTitleCondensed(CharSequence title) {
				return null;
			}
			
			@Override
			public android.view.MenuItem setVisible(boolean visible) {
				return null;
			}
		};
	}
}