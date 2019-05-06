package com.example.nice.everywhere.ui.main.fragment;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nice.everywhere.R;
import com.example.nice.everywhere.widget.IdentifyingCodeView;

/**
 * A simple {@link Fragment} subclass.
 */
public class CodeLodingFragment extends Fragment {


    private ImageView iv_back;
    private TextView tv_send_again;
    private IdentifyingCodeView icv;
    private TextView tv_wait;
    private int count = 60;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                if (count > 0) {
                    tv_send_again.setText("重新发送"+"("+count+ "s)");
                    count--;
                    handler.sendEmptyMessageDelayed(1, 1000);
                } else {

                }
            }

        }
    };

    public CodeLodingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_code_loding, container, false);
        initView(inflate);
        return inflate;
    }

    private void initView(View inflate) {
        iv_back = (ImageView) inflate.findViewById(R.id.iv_back);
        tv_send_again = (TextView) inflate.findViewById(R.id.tv_send_again);
        icv = (IdentifyingCodeView) inflate.findViewById(R.id.icv);
        tv_wait = (TextView) inflate.findViewById(R.id.tv_wait);

        handler.sendEmptyMessageDelayed(1, 1000);   //倒计时监听

        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = manager.beginTransaction();
                //添加到回退栈
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.add(R.id.frag,new CodeFragment()).commit();
            }
        });
    }
}
