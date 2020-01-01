//package com.example.week1;
//
//import androidx.appcompat.app.AlertDialog;
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.content.DialogInterface;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//import android.widget.Toast;
//
//import com.example.week1.Adapter.AutoScrollAdapter;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import cn.trinea.android.view.autoscrollviewpager.AutoScrollViewPager;
//
//public class TripActivity extends AppCompatActivity {
//
//    AutoScrollViewPager autoViewPager;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        ArrayList<String> data = new ArrayList<>(); //이미지 url를 저장하는 arraylist
//        data.add("https://postfiles.pstatic.net/MjAxOTEyMzFfMjM5/MDAxNTc3NzY3NjQ0OTM0.GrhlCfxi-dILyccEgKmBLGVI-KN0pHg8Qv7BlBu9pdAg.ZzXGv4eI9ZrGbmKFE1734n5Dqtl_4eivWrAI25IBj_wg.PNG.otl0711/%EC%84%9C%EC%9A%B8.png?type=w580");
//        data.add("https://postfiles.pstatic.net/MjAxOTEyMzFfMjEw/MDAxNTc3NzY3NjQ3NTEx.NyXbKU_xr3a0pT1GTuQ4wzHtr68nXw2Emzto-p1klBsg.v1VJipbb2YWJT280fdcVKL01Qpsrs-HNTR05Wq5ZYZkg.PNG.otl0711/%EB%B6%80%EC%82%B0.png?type=w580");
//        data.add("https://postfiles.pstatic.net/MjAxOTEyMzFfMTI2/MDAxNTc3NzcyNTI2ODM2.Tlht_Y-I9iGsZIpRiCC771fgIVZq9_ovT_2HYFxCpiIg.qwoydP9o86RNOS1Aoq7H1kFmcRy_XArer0nB_EFW-OMg.PNG.otl0711/%EB%8C%80%EC%A0%84.png?type=w580");
//        data.add("https://postfiles.pstatic.net/MjAxOTEyMzFfMjc3/MDAxNTc3NzY3NjQ4OTM4.AaAzBjo1ENt6tG4P2oafCCByoDjUB2RZWcntw2W5IG4g.gsP_IlkylQFK7Y4GKYWvevigZF5ez5m9y-UUCZs5000g.PNG.otl0711/%EA%B2%BD%EC%A3%BC.png?type=w580");
//        data.add("https://postfiles.pstatic.net/MjAxOTEyMzFfODUg/MDAxNTc3NzY4NTI0ODMw.r1kdaUw08GHNo49zOW7Ucd-wvlRHjJkH2tPrc0xTIzkg.Pc59j7Ex2gRwO1z3-njRBF2T7Y3TrjOXHlA1Q06fpHQg.PNG.otl0711/%EC%A0%9C%EC%A3%BC.png?type=w580");
//
//        autoViewPager = (AutoScrollViewPager)findViewById(R.id.autoViewPager);
//        AutoScrollAdapter scrollAdapter = new AutoScrollAdapter(this, data);
//        autoViewPager.setAdapter(scrollAdapter); //Auto Viewpager에 Adapter 장착
//        autoViewPager.setInterval(1900); // 페이지 넘어갈 시간 간격 설정
//        autoViewPager.startAutoScroll(); //Auto Scroll 시작
//
//        Button button = (Button)findViewById(R.id.choosearea);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                show();
//            }
//        });
//
//
//        Button button1 = (Button) findViewById(R.id.content);
//        button1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.d("t", "--------------------------------------------");
//                showcnt();
//            }
//        });
//    }
//
//    void show()
//    {
//        final List<String> ListItems = new ArrayList<>();
//        ListItems.add("서울");ListItems.add("인천");ListItems.add("대전");ListItems.add("대구");ListItems.add("광주");ListItems.add("부산");
//        ListItems.add("울산");ListItems.add("세종");ListItems.add("경기");ListItems.add("강원");ListItems.add("충북");
//        ListItems.add("충남");ListItems.add("전북");ListItems.add("전남");ListItems.add("경북");ListItems.add("경남");
//        ListItems.add("제주");
//        final CharSequence[] items =  ListItems.toArray(new String[ ListItems.size()]);
//
//        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle("지역을 선택하세요");
//        builder.setItems(items, new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialog, int pos) {
//                String selectedText = items[pos].toString();
//                Toast.makeText(TripActivity.this, selectedText+" (으)로 이동합니다!", Toast.LENGTH_SHORT).show();
//            }
//        });
//        builder.show();
//    }
//
//    void showcnt()
//    {
//        final List<String> ListItems = new ArrayList<>();
//        ListItems.add("관광지");ListItems.add("문화시설");ListItems.add("축제/공연/행사");ListItems.add("여행코스");
//        ListItems.add("레포츠");ListItems.add("숙박");ListItems.add("쇼핑");ListItems.add("음식");
//        final CharSequence[] items =  ListItems.toArray(new String[ ListItems.size()]);
//
//        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle("컨텐츠를 선택하세요");
//        builder.setItems(items, new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialog, int pos) {
//                String selectedText = items[pos].toString();
//                Log.d("cat11", "-----------------------------------------"+pos);
//                Toast.makeText(TripActivity.this, selectedText+" (으)로 이동합니다!", Toast.LENGTH_SHORT).show();
//                if(pos == 0){
//                    final List<String> Cat1 = new ArrayList<>();
//                    Cat1.add("자연"); Cat1.add("인문(문화/예술/역사)");
//                    final CharSequence[] cat1items = Cat1.toArray(new String[Cat1.size()]);
//                    final AlertDialog.Builder buildercat1 = new AlertDialog.Builder(getApplicationContext());
//                    buildercat1.setTitle("대분류");
//                    buildercat1.setItems(cat1items, new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            String selectedText = cat1items[which].toString();
//                            Toast.makeText(TripActivity.this, selectedText+" (으)로 이동합니다!", Toast.LENGTH_SHORT).show();
//                        }
//                    });
//                }
//            }
//        });
//        builder.show();
//    }
//}
