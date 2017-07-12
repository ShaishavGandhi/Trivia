package com.shaishavgandhi.trivia.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.shaishavgandhi.trivia.Constants;
import com.shaishavgandhi.trivia.Listeners;
import com.shaishavgandhi.trivia.R;
import com.shaishavgandhi.trivia.adapter.RecyclerCategoriesAdapter;
import com.shaishavgandhi.trivia.data.models.Category;
import com.shaishavgandhi.trivia.data.repository.CategoriesRepository;
import com.shaishavgandhi.trivia.databinding.ActivityMainBinding;
import com.shaishavgandhi.trivia.impl.CategoryListPresenterImpl;
import com.shaishavgandhi.trivia.presenters.CategoryListPresenter;
import com.shaishavgandhi.trivia.views.CategoryListView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MainActivity extends AppCompatActivity implements CategoryListView, Listeners {

    CategoryListPresenterImpl mPresenter;
    ActivityMainBinding mBinding;
    RecyclerView mRecyclerView;
    RecyclerCategoriesAdapter mAdapter;
    ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setSupportActionBar(mBinding.toolbar);
        mRecyclerView = mBinding.content.recyclerView;
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mAdapter = new RecyclerCategoriesAdapter(getApplicationContext(), this);
        mRecyclerView.setAdapter(mAdapter);

        mPresenter = new CategoryListPresenterImpl(this, CategoriesRepository.getInstance(getApplicationContext()));
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mPresenter.onStop();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
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

    @Override
    public void hideProgressDialog() {
        if (mProgressDialog != null) {
            mProgressDialog.hide();
        }
    }

    @Override
    public void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setMessage("Loading");
        }
        mProgressDialog.show();
    }

    @Override
    public void setData(@NotNull List<Category> data) {
        mAdapter.setData(data);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onCategoryClick(@NotNull Category category) {
        Intent intent = new Intent(this, QuizActivity.class);
        intent.putExtra(Constants.INSTANCE.getCATEGORY_NAME(), category.getName());
        intent.putExtra(Constants.INSTANCE.getCATEGORY_ID(), category.getEnum());
        startActivity(intent);
    }
}
