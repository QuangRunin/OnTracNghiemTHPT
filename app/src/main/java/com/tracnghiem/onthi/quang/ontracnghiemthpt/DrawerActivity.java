package com.tracnghiem.onthi.quang.ontracnghiemthpt;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.tracnghiem.onthi.quang.ontracnghiemthpt.database.DatabseHelper;
import com.tracnghiem.onthi.quang.ontracnghiemthpt.monontap.OntapFragment;
import com.tracnghiem.onthi.quang.ontracnghiemthpt.monthi.MonDiaLyFragment;
import com.tracnghiem.onthi.quang.ontracnghiemthpt.monthi.MonGDCDFragment;
import com.tracnghiem.onthi.quang.ontracnghiemthpt.monthi.MonSinhFragment;
import com.tracnghiem.onthi.quang.ontracnghiemthpt.monthi.MonSuFragment;
import com.tracnghiem.onthi.quang.ontracnghiemthpt.slide.SlideActivity;

import java.io.IOException;

public class DrawerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);
        drawerLayout = findViewById(R.id.drawer_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new OntapFragment()).commit();
        getSupportActionBar().setTitle("Kho Kiến Thức");
        drawerLayout.closeDrawers();
        DatabseHelper databseHelper = new DatabseHelper(this);
        databseHelper.deleteDataBase();
        Toast.makeText(this, "Xóa thành công", Toast.LENGTH_SHORT).show();
        try {
            databseHelper.createDataBase();
            Toast.makeText(this, "Cap nhat thanh cong", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(DrawerActivity.this);
            builder.setTitle("Thoát");
            builder.setMessage("Xác nhận thoát !");
            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                }
            });
            builder.show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.drawer, menu);
        return true;
    }
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_khokienthuc) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new OntapFragment()).commit();
            getSupportActionBar().setTitle("Kho Kiến Thức");
            drawerLayout.closeDrawers();
        }
        if (id == R.id.nav_toan) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new MonSuFragment()).commit();
            getSupportActionBar().setTitle("Môn Lịch Sử");
            drawerLayout.closeDrawers();
        }
        if (id == R.id.nav_dialy) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new MonDiaLyFragment()).commit();
            getSupportActionBar().setTitle("Môn Địa Lý");
            drawerLayout.closeDrawers();
        }
        if (id == R.id.nav_sinhhoc) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new MonSinhFragment()).commit();
            getSupportActionBar().setTitle("Môn Sinh Học");
            drawerLayout.closeDrawers();
        }
        if (id == R.id.nav_gdcd) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new MonGDCDFragment()).commit();
                getSupportActionBar().setTitle("Môn GDCD");
            drawerLayout.closeDrawers();
        }if (id == R.id.nav_thongke) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new ThongKeFragment()).commit();
            getSupportActionBar().setTitle("Lịch Sử Làm Bài");
            drawerLayout.closeDrawers();
        }
        if(id ==R.id.nav_thoat){
            AlertDialog.Builder builder = new AlertDialog.Builder(DrawerActivity.this);
            builder.setTitle("Thoát");
            builder.setMessage("Bạn có thực sự muốn thoát !");
            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                }
            });
            builder.show();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
