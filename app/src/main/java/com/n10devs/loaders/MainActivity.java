package com.n10devs.loaders;

import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;

import com.activeandroid.content.ContentProvider;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    @Bind(R.id.spinner)
    Spinner mSpinner;

    @Bind(R.id.editTextName)
    EditText mEditTextName;

    @Bind(R.id.editTextAge)
    EditText mEditTextAge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);

        mSpinner.setAdapter(new SimpleCursorAdapter(this,
                android.R.layout.simple_expandable_list_item_2,
                null,
                new String[]{Person.COLUMN_NAME, Person.COLUMN_AGE},
                new int[]{android.R.id.text1, android.R.id.text2},
                0));

        getLoaderManager().initLoader(0, null, this);
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

    @OnClick(R.id.fab)
    public void add(View view) {
        String name = mEditTextName.getText().toString();
        String age = mEditTextAge.getText().toString();

        Person person = new Person(name, Integer.parseInt(age));
        person.save();

        mEditTextName.getText().clear();
        mEditTextAge.getText().clear();
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(this,
                ContentProvider.createUri(Person.class, null), null, null, null, Person.COLUMN_AGE
        );
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        ((SimpleCursorAdapter) mSpinner.getAdapter()).swapCursor(cursor);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        ((SimpleCursorAdapter) mSpinner.getAdapter()).swapCursor(null);
    }
}
