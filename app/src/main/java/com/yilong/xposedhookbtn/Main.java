package com.yilong.xposedhookbtn;

import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;

import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;


public class Main implements IXposedHookLoadPackage {

    @Override
    public void handleLoadPackage(LoadPackageParam lpparam) throws Throwable {
//        XposedBridge.log("handleloadpackage 执行了");

        if (lpparam.packageName.equals("com.tencent.mm")) {

            findAndHookMethod(TextView.class, "setText", CharSequence.class, new XC_MethodHook() {

                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {

                    try {
                        String string = (String) param.args[0];

                        if (!TextUtils.isEmpty(string)) {
                            if (string.contains("微信已登录")) {
                                param.args[0] = "启动硬件加速";

                                TextView textView = (TextView) param.thisObject;

                                LinearLayout linearLayout = (LinearLayout) textView.getParent().getParent();

                                ViewGroup.LayoutParams linearParams = linearLayout.getLayoutParams(); //取控件textView当前的布局参数
                                linearParams.height = 0;

//                                Log.i("ssssssss", " textView  = " + textView + "   linearLayout = " + linearLayout);

                            }
                        }
//
                    } catch (Exception E) {

                    }
                }

            });

//
//            findAndHookConstructor(View.OnClickListener.class, new XC_MethodHook() {
//                @Override
//                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
//
//                    try {
//                        View view = (View) param.args[0];
//
//                        view.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View view) {
//
//                            }
//                        });
//                        Log.i("ssssssss", " id  = " + view.getId());
//                    } catch (Exception E) {
//
//                    }
//                }
//            });


//            findAndHookMethod(Activity.class, "startActivity", Intent.class, new XC_MethodHook() {
//                @Override
//                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
//                    try {
////                        Intent view = (Intent) param.args[0];
////
////                        Activity activity = (Activity) param.thisObject;
//
//
//                        Log.i("ssssssss", " startActivity class  = " + activity.getClass());
//                    } catch (Exception E) {
//                        Log.i("ssssssss", " tostring = " + E.toString());
//                    }
//                }
//            });


//            findAndHookMethod("com.tencent.mm.plugin.webwx.ui.WebWXLogoutUI", lpparam.classLoader, "onCreate", Bundle.class, new XC_MethodHook() {
//                @Override
//                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
//
//                    Log.i("ssssssss", " WebWXLogoutUI  = ");
//
//                    Activity activity = (Activity) param.thisObject;
//                    activity.finish();
//
//                }
//            });


        }

    }
}
