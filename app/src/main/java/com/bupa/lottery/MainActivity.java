package com.bupa.lottery;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.bupa.lottery.base.BaseActivity;
import com.bupa.lottery.view.LuckyMonkeyPanelView;
import com.jaeger.library.StatusBarUtil;

import java.util.Random;

import cn.carbs.android.library.MDDialog;

public class MainActivity extends BaseActivity {


    private LuckyMonkeyPanelView lucky_panel;
    private Button btn_action;
    private MDDialog mMdDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StatusBarUtil.setTranslucent(this, 0);
        lucky_panel = (LuckyMonkeyPanelView) findViewById(R.id.lucky_panel);
        btn_action = (Button) findViewById(R.id.btn_action);

        btn_action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!lucky_panel.isGameRunning()) {
                    lucky_panel.startGame();
                } else {

                    final int stayIndex = new Random().nextInt(8);
                    Log.e("LuckyMonkeyPanelView", "====stay===" + stayIndex);
                    lucky_panel.tryToStop(stayIndex);
                    new Thread() {
                        @Override
                        public void run() {
                            try {
                                Thread.sleep(2000);
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        switch (stayIndex) {
                                            case 0:
                                                dialogShow(new String[]{"恭喜阁下中得:北京一套房!", "稍后我们将尽快与您取得联系,请保持手机通讯畅通谢谢!"}, "确定");
                                                break;
                                            case 1:
                                                dialogShow(new String[]{"恭喜阁下中得:上海一套房!", "稍后我们将尽快与您取得联系,请保持手机通讯畅通谢谢!"}, "确定");
                                                break;
                                            case 2:
                                                dialogShow(new String[]{"恭喜阁下中得:3个亿(纸币)!", "稍后我们将尽快与您取得联系,请保持手机通讯畅通谢谢!"}, "确定");
                                                break;
                                            case 3:
                                                dialogShow(new String[]{"恭喜阁下中得:深圳豪华别墅!", "稍后我们将尽快与您取得联系,请保持手机通讯畅通谢谢!"}, "确定");
                                                break;
                                            case 4:
                                                dialogShow(new String[]{"恭喜阁下中得:吃霸王餐一年!", "稍后我们将尽快与您取得联系,请保持手机通讯畅通谢谢!"}, "确定");
                                                break;
                                            case 5:
                                                dialogShow(new String[]{"恭喜阁下中得:话费1个亿!", "稍后我们将尽快与您取得联系,请保持手机通讯畅通谢谢!"}, "确定");
                                                break;
                                            case 6:
                                                dialogShow(new String[]{"恭喜阁下中得:宝马一辆!", "稍后我们将尽快与您取得联系,请保持手机通讯畅通谢谢!"}, "确定");
                                                break;
                                            case 7:
                                                dialogShow(new String[]{"恭喜阁下中得:女朋友一个(公司配送)!", "稍后我们将尽快与您取得联系,请保持手机通讯畅通谢谢!"}, "确定");
                                                break;
                                        }
                                    }
                                });
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }.start();

                }
            }
        });
    }

    private void dialogShow(String[] arr, String btnMsg) {
        mMdDialog = new MDDialog.Builder(this)
                .setMessages(arr)
                .setTitle("温馨提示")
                .setIcon(getResources().getDrawable(R.mipmap.gift_box_32px))
                .setPositiveButton(btnMsg, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mMdDialog.dismiss();
                    }
                }).create();
        mMdDialog.show();
    }
}
