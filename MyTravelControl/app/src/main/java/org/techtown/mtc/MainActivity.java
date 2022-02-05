package org.techtown.mtc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    ImageButton iBtn, kBtn, kmapBtn, nmapBtn, tranBtn;
    private final String katalkNames = "com.kakao.talk";
    private final String kmapNames = "net.daum.android.map";
    private final String nmapNames = "com.nhn.android.nmap";
    private final String instaNames = "com.instagram.android";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("내가 만든 여행 관리앱");

        iBtn = findViewById(R.id.instaBtn);
        kBtn = findViewById(R.id.katalkBtn);
        kmapBtn = findViewById(R.id.kakaoMapBtn);
        nmapBtn = findViewById(R.id.naverMapBtn);
        tranBtn = findViewById(R.id.transBtn);

        iBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onApp(instaNames);
            }
        });

        kBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onApp(katalkNames);
            }
        });

        kmapBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onApp(kmapNames);
            }
        });

        nmapBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onApp(nmapNames);
            }
        });

        tranBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, trans.class);
                startActivity(intent);
            }
        });

    }

    public void onApp(String names) {
//        앱이 있을 경우 해당 앱을 실행
        if(getPackageList(names)) {
            Intent intent = getPackageManager().getLaunchIntentForPackage(names);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
//            앱이 없을 경우 설치하도록 스토어 경로로 이동
        }else{
            String url = "market://details?id="+names;
            Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(i);
        }
    }

    public boolean getPackageList(String names) {
        boolean isExist = false;

        PackageManager pkgMgr = getPackageManager();
        List<ResolveInfo> mApps;
        Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        mApps = pkgMgr.queryIntentActivities(mainIntent, 0);

        try {
            for (int i=0; i<mApps.size(); i++){
//                앱이 있을 경우 true 반환
                if(mApps.get(i).activityInfo.packageName.startsWith(names)){
                    isExist = true;
                    break;
                }
            }
        }
        catch (Exception e){
//            앱이 없을 경우 false 반환
            isExist = false;
        }
        return isExist;
    }
}