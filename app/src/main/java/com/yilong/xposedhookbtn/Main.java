package com.yilong.xposedhookbtn;

import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.lang.reflect.Field;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;

import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;


public class Main implements IXposedHookLoadPackage {

    private static final String PNAME = "com.android.mms";

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

                            }
                        }
//
                    } catch (Exception E) {

                    }
                }

            });


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

//        if (lpparam.packageName.equals(PNAME)) {
//
//            final Class<?> clazz = XposedHelpers.findClass(
//                    "com.android.mms.data.WorkingMessage", lpparam.classLoader);
//
//            XposedHelpers.findAndHookMethod(clazz, "send", String.class,
//                    new XC_MethodHook() {
//                        @Override
//                        protected void beforeHookedMethod(MethodHookParam param)
//                                throws Throwable {
//
//                            XposedBridge.log("----开始拦截send方法-------");
//                            Field f = XposedHelpers.findField(clazz, "mText");
//                            SpannableStringBuilder text = (SpannableStringBuilder) f.get(param.thisObject);
//                            String origMsg = text.toString();
//
//                            Log.i("ssssssss", " origMsg  = " + origMsg);
//                        }
//                    });
//
//            XposedHelpers.findAndHookMethod("com.android.internal.telephony.gsm" + ".SmsMessage$PduParser",
//                    lpparam.classLoader, "getUserDataUCS2", int.class,
//                    new XC_MethodHook() {
//                        @Override
//                        protected void afterHookedMethod(MethodHookParam param)
//                                throws Throwable {
//                            try {
//                                String strMms = (String) param.getResult();
//                                XposedBridge.log("=========before:" + strMms);
//                                //String after="666666666666666";
//                                char[] recvArray = strMms.toCharArray();
//
//                            } catch (Exception e) {
//                                XposedBridge.log(e);
//                            }
//
//                        }
//                    });
//
//        }

    }
}
