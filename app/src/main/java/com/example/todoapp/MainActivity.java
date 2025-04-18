//package com.example.todoapp;
//
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//
//import androidx.activity.EdgeToEdge;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.core.graphics.Insets;
//import androidx.core.view.ViewCompat;
//import androidx.core.view.WindowInsetsCompat;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.todoapp.Adapter.ToDoAdapter;
//import com.example.todoapp.Model.ToDoModel;
//import com.example.todoapp.Utils.DataBaseHelper;
//import com.google.android.material.floatingactionbutton.FloatingActionButton;
//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//
//public class MainActivity extends AppCompatActivity {
//
//    private RecyclerView mRecyclerview;
//    private FloatingActionButton fab;
//    private DataBaseHelper myDB;
//    private List<ToDoModel> mList;
//    private ToDoAdapter adapter;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        setContentView(R.layout.activity_main);
//
//        mRecyclerview = findViewById(R.id.recyclerView);
//        fab = findViewById(R.id.fab);
//        myDB = new DataBaseHelper(MainActivity.this);
//        mList = new ArrayList<>();
//        adapter = new ToDoAdapter(myDB , MainActivity.this);
//
//        mRecyclerview.setHasFixedSize(true);
//        mRecyclerview.setLayoutManager(new LinearLayoutManager(this));
//        mRecyclerview.setAdapter(adapter);
//
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                AddNewTask.newInstance().show(getSupportFragmentManager(),AddNewTask.TAG);
//            }
//        });
//
//
//
//    }
//
//    public  void onDialogClose(DialogInterface dialogInterface){
//        mList = myDB.getAllTasks();
//        Collections.reverse(mList);
//        adapter.setTasks(mList);
//        adapter.notifyDataSetChanged();
//
//
//    }
//}

//package com.example.todoapp;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.ItemTouchHelper;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import android.content.DialogInterface;
//import android.os.Bundle;
//import android.view.View;
//
//import com.example.todoapp.Adapter.ToDoAdapter;
//import com.example.todoapp.Model.ToDoModel;
//import com.example.todoapp.Utils.DataBaseHelper;
//import com.google.android.material.floatingactionbutton.FloatingActionButton;
//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//
//public class MainActivity extends AppCompatActivity implements OnDialogCloseListener {
//
//    private RecyclerView mRecyclerview;
//    private FloatingActionButton fab;
//    private DataBaseHelper myDB;
//    private List<ToDoModel> mList;
//    private ToDoAdapter adapter;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        mRecyclerview = findViewById(R.id.recyclerView);
//        fab = findViewById(R.id.fab);
//        myDB = new DataBaseHelper(MainActivity.this);
//        mList = new ArrayList<>();
//        adapter = new ToDoAdapter(myDB , MainActivity.this);
//
//        mRecyclerview.setHasFixedSize(true);
//        mRecyclerview.setLayoutManager(new LinearLayoutManager(this));
//        mRecyclerview.setAdapter(adapter);
//
//        mList = myDB.getAllTasks();
//        Collections.reverse(mList);
//        adapter.setTasks(mList);
//
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                AddNewTask.newInstance().show(getSupportFragmentManager() , AddNewTask.TAG);
//            }
//        });
//        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new RecyclerViewTouchHelper(adapter));
//        itemTouchHelper.attachToRecyclerView(mRecyclerview);
//    }
//
//    @Override
//    public void onDialogClose(DialogInterface dialogInterface) {
//        mList = myDB.getAllTasks();
//        Collections.reverse(mList);
//        adapter.setTasks(mList);
//        adapter.notifyDataSetChanged();
//    }
//}

package com.example.todoapp;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todoapp.Adapter.ToDoAdapter;
import com.example.todoapp.Model.ToDoModel;
import com.example.todoapp.Utils.DataBaseHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnDialogCloseListener {

    private RecyclerView mRecyclerview;
    private FloatingActionButton fab;
    private DataBaseHelper myDB;
    private List<ToDoModel> mList;
    private ToDoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerview = findViewById(R.id.recyclerView);
        fab = findViewById(R.id.fab);
        myDB = new DataBaseHelper(MainActivity.this);
        mList = new ArrayList<>();
        adapter = new ToDoAdapter(myDB, MainActivity.this);

        mRecyclerview.setHasFixedSize(true);
        mRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerview.setAdapter(adapter);

        // Load and display all tasks
        mList = myDB.getAllTasks();
        Collections.reverse(mList);
        adapter.setTasks(mList);

        // FAB to open Add Task Dialog
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddNewTask.newInstance().show(getSupportFragmentManager(), AddNewTask.TAG);
            }
        });

        // Swipe-to-delete functionality
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new RecyclerViewTouchHelper(adapter));
        itemTouchHelper.attachToRecyclerView(mRecyclerview);
    }

    @Override
    public void onDialogClose(DialogInterface dialogInterface) {
        mList = myDB.getAllTasks();
        Collections.reverse(mList);
        adapter.setTasks(mList);
        adapter.notifyDataSetChanged();
    }
}
