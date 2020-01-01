package com.example.week1.Fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.week1.Adapter.AutoScrollAdapter;
import com.example.week1.Data.APIConstant;
import com.example.week1.Data.UrlInfo;
import com.example.week1.MainActivity;
import com.example.week1.R;

import java.util.ArrayList;
import java.util.List;

import cn.trinea.android.view.autoscrollviewpager.AutoScrollViewPager;

public class EmptyFragement extends Fragment implements MainActivity.OnBackKeyPressedListener{
    private AutoScrollViewPager autoViewPager;
    private View v;
    private int selectedArea = -1;
    private int selectedContent = -1;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.trip_main, container, false);

        ArrayList<String> data = new ArrayList<>(); //이미지 url를 저장하는 arraylist
        data.add("https://postfiles.pstatic.net/MjAxOTEyMzFfMjM5/MDAxNTc3NzY3NjQ0OTM0.GrhlCfxi-dILyccEgKmBLGVI-KN0pHg8Qv7BlBu9pdAg.ZzXGv4eI9ZrGbmKFE1734n5Dqtl_4eivWrAI25IBj_wg.PNG.otl0711/%EC%84%9C%EC%9A%B8.png?type=w580");
        data.add("https://postfiles.pstatic.net/MjAxOTEyMzFfMjEw/MDAxNTc3NzY3NjQ3NTEx.NyXbKU_xr3a0pT1GTuQ4wzHtr68nXw2Emzto-p1klBsg.v1VJipbb2YWJT280fdcVKL01Qpsrs-HNTR05Wq5ZYZkg.PNG.otl0711/%EB%B6%80%EC%82%B0.png?type=w580");
        data.add("https://postfiles.pstatic.net/MjAxOTEyMzFfMTI2/MDAxNTc3NzcyNTI2ODM2.Tlht_Y-I9iGsZIpRiCC771fgIVZq9_ovT_2HYFxCpiIg.qwoydP9o86RNOS1Aoq7H1kFmcRy_XArer0nB_EFW-OMg.PNG.otl0711/%EB%8C%80%EC%A0%84.png?type=w580");
        data.add("https://postfiles.pstatic.net/MjAxOTEyMzFfMjc3/MDAxNTc3NzY3NjQ4OTM4.AaAzBjo1ENt6tG4P2oafCCByoDjUB2RZWcntw2W5IG4g.gsP_IlkylQFK7Y4GKYWvevigZF5ez5m9y-UUCZs5000g.PNG.otl0711/%EA%B2%BD%EC%A3%BC.png?type=w580");
        data.add("https://postfiles.pstatic.net/MjAxOTEyMzFfODUg/MDAxNTc3NzY4NTI0ODMw.r1kdaUw08GHNo49zOW7Ucd-wvlRHjJkH2tPrc0xTIzkg.Pc59j7Ex2gRwO1z3-njRBF2T7Y3TrjOXHlA1Q06fpHQg.PNG.otl0711/%EC%A0%9C%EC%A3%BC.png?type=w580");

        autoViewPager = (AutoScrollViewPager) v.findViewById(R.id.autoViewPager);
        AutoScrollAdapter scrollAdapter = new AutoScrollAdapter(getContext(), data);
        autoViewPager.setAdapter(scrollAdapter); //Auto Viewpager에 Adapter 장착
        autoViewPager.setInterval(1900); // 페이지 넘어갈 시간 간격 설정
        autoViewPager.startAutoScroll(); //Auto Scroll 시작

        Button buttongo = (Button) v.findViewById(R.id.go);
        buttongo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedArea != -1 && selectedContent != -1) {
                    UrlInfo.setAreaCode(selectedArea);
                    UrlInfo.setContentType(selectedContent);
                    UrlInfo.setCurrentPage(1);
                    UrlInfo.setMode(UrlInfo.SEARCH_AREA_CONTENT);
                    ((MainActivity)getActivity()).replaceFragment(new TripFragment());
                } else {
                    Toast.makeText(getContext(), "항목을 모두 선택해 주세요", Toast.LENGTH_SHORT).show();
                }
            }
        });


        Button button = (Button) v.findViewById(R.id.choosearea);
        final TextView area = v.findViewById(R.id.area);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show(area);
            }
        });

        Button button1 = (Button) v.findViewById(R.id.content);
        final TextView cnt = v.findViewById(R.id.cnt);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showcnt(cnt);
            }
        });

        return v;
    }

    void show(final TextView area)
    {
        final List<String> ListItems = new ArrayList<>();
        ListItems.add("서울");ListItems.add("인천");ListItems.add("대전");ListItems.add("대구");ListItems.add("광주");ListItems.add("부산");
        ListItems.add("울산");ListItems.add("세종");ListItems.add("경기");ListItems.add("강원");ListItems.add("충북");
        ListItems.add("충남");ListItems.add("경북");ListItems.add("경남");ListItems.add("전북");ListItems.add("전남");
        ListItems.add("제주");
        final CharSequence[] items =  ListItems.toArray(new String[ ListItems.size()]);

        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("지역을 선택해주세요");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int pos) {
                String selectedText = items[pos].toString();
                selectedArea = APIConstant.AREA_LIST[pos];
//                switch (selectedText) {
//                    case "서울":
//                        selectedArea = APIConstant.AREA_SEOUL;
//                        break;
//                    case "인천":
//                        selectedArea = APIConstant.AREA_INCHEON;
//                        break;
//                    case "대전":
//                        selectedArea = APIConstant.AREA_DAEJEON;
//                        break;
//                    case "대구":
//                        selectedArea = APIConstant.AREA_DAEGU;
//                        break;
//                    case "광주":
//                        selectedArea = APIConstant.AREA_GANGJU;
//                        break;
//                    case "부산":
//                        selectedArea = APIConstant.AREA_BUSAN;
//                        break;
//                    case "울산":
//                        selectedArea = APIConstant.AREA_ULSAN;
//                        break;
//                    case "세종":
//                        selectedArea = APIConstant.AREA_SAEJONG;
//                        break;
//                    case "경기":
//                        selectedArea = APIConstant.AREA_GYEONGGI;
//                        break;
//                    case "강원":
//                        selectedArea = APIConstant.AREA_GANGWON;
//                        break;
//                    case "충북":
//                        selectedArea = APIConstant.AREA_CHUNGBUK;
//                        break;
//                    case "충남":
//                        selectedArea = APIConstant.AREA_CHUNGNAM;
//                        break;
//                    case "전북":
//                        selectedArea = APIConstant.AREA_JEONBUK;
//                    case "전남":
//                        selectedArea = APIConstant.AREA_JEONNAM;
//                        break;
//                    case "경북":
//                        selectedArea = APIConstant.AREA_GYEONGBUK;
//                        break;
//                    case "경남":
//                        selectedArea = APIConstant.AREA_GYEONGNAM;
//                        break;
//                    case "제주":
//                        selectedArea = APIConstant.AREA_JEJU;
//                        break;
//                }
                Toast.makeText(getContext(), selectedText+"(으)로 이동합니다!", Toast.LENGTH_SHORT).show();
                area.setText(selectedText);
            }
        });
        builder.show();
    }

    void showcnt(final TextView cnt)
    {
        final List<String> ListItems = new ArrayList<>();
        ListItems.add("관광지");ListItems.add("문화시설");ListItems.add("축제/공연/행사");ListItems.add("여행코스");
        ListItems.add("레포츠");ListItems.add("숙박");ListItems.add("쇼핑");ListItems.add("음식");
        final CharSequence[] items =  ListItems.toArray(new String[ ListItems.size()]);

        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("컨텐츠를 선택하세요");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int pos) {
                String selectedText = items[pos].toString();
                selectedContent = APIConstant.CONTENT_LIST[pos];
                Toast.makeText(getContext(), selectedText+"(으)로 이동합니다!", Toast.LENGTH_SHORT).show();
                cnt.setText(selectedText);
            }
        });
        builder.show();
    }

    @Override
    public void onBack() {
        getFragmentManager().popBackStack();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        ((MainActivity) context).pushOnBackKeyPressedListener(this);
    }
}
