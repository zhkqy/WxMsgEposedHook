package com.yilong.xposedhookbtn;

import android.app.Activity;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.Editable;
import android.text.SpannableString;
import android.text.SpannedString;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;

import static android.widget.Toast.LENGTH_LONG;
import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;


public class Main implements IXposedHookLoadPackage {

    String preStr = "";

    @Override
    public void handleLoadPackage(LoadPackageParam lpparam) throws Throwable {
//        com.tencent.mm.ui.LauncherUI
        XposedBridge.log("handleloadpackage 执行了");
        if (lpparam.packageName.equals("com.tencent.mm")) {
//            XposedBridge.log("开始hook测试程序");
//
//            findAndHookMethod(TextView.class, "setText", SpannableString.class, new XC_MethodHook() {
//                @Override
//                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                    String string = (String) param.args[0];
//                    Log.i("ssssssss", " EditText string  = " + string);
//                }
//            });
//
//            TextView textView = null;
//            textView.setText(new SpannableString(""));


//            setText(CharSequence text, TextView.BufferType type,
//            boolean notifyBefore, int oldlen)

            findAndHookMethod(TextView.class, "setText",
                    CharSequence.class,
                    TextView.BufferType.class, new XC_MethodHook() {
                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {

                            try {
                                String string = (String) param.args[0];
                                Log.i("ssssssss", " EditText string  = " + string);
                            } catch (Exception E) {

                            }
                            try {
                                SpannableString string = (SpannableString) param.args[0];
                                Log.i("ssssssss", "------------ EditText string  = " + string.toString());
                            } catch (Exception E) {

                            }
                        }
                    });


//            findAndHookMethod(Canvas.class, "drawText",
//                    CharSequence.class,
//                    Integer.class,
//                    Integer.class,
//                    Float.class,
//                    Float.class,
//                    Paint.class, new XC_MethodHook() {
//                        @Override
//                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                            try {
//                                String string = (String) param.args[0];
//                                Log.i("ssssssss", " string 11111  = " + string);
//                            } catch (Exception E) {
//
//                            }
//                            try {
//                                SpannableString string = (SpannableString) param.args[0];
//                                Log.i("ssssssss", "------------ string 11111 = " + string.toString());
//                            } catch (Exception E) {
//
//                            }
//                        }
//                    });
//
////            drawText(String text, int start, int end, float x, float y, Paint paint)
//            findAndHookMethod(Canvas.class, "drawText",
//                    String.class,
//                    Integer.class,
//                    Integer.class,
//                    Float.class,
//                    Float.class,
//                    Paint.class, new XC_MethodHook() {
//                        @Override
//                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                            String string = (String) param.args[0];
//                            Log.i("ssssssss", " string 222222222 = " + string);
//                        }
//                    });
////
////            drawText(String text, float x, float y, Paint paint)
//            findAndHookMethod(Canvas.class, "drawText",
//                    String.class,
//                    Float.class,
//                    Float.class,
//                    Paint.class, new XC_MethodHook() {
//                        @Override
//                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                            String string = (String) param.args[0];
//                            Log.i("ssssssss", " string 333333333  = " + string);
//                        }
//                    });
//
////            drawText(char[] text, int index, int count, float x, float y, Paint paint)
//            findAndHookMethod(Canvas.class, "drawText",
//                    Character[].class,
//                    Integer.class,
//                    Integer.class,
//                    Float.class,
//                    Float.class,
//                    Paint.class, new XC_MethodHook() {
//                        @Override
//                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                            Character[] c = (Character[]) param.args[0];
//                            Log.i("ssssssss", " string 4444444 = " + String.valueOf(c));
//                        }
//                    });


//            findAndHookMethod("com.tencent.mm.ui.LauncherUI", lpparam.classLoader, "onCreate", Bundle.class, new XC_MethodHook() {
//                @Override
//                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                    Log.i("ssssssss", " afterHookedMethod  = " + param.args[0]);
//
//                    Activity activity = (Activity) param.thisObject;
//                    getMethodInfo(activity.getClass());
//
//                    Log.i("ssssssss", "----------------------");
//
//                    printAllFileds(activity.getClass());
//
//                    EditText editText = null;
//                    editText.getText().toString();
//
//
////                String wechatId = activity.getIntent().getStringExtra("RoomInfo_Id");
////                ClipboardManager cmb = (ClipboardManager) activity.getSystemService(Context.CLIPBOARD_SERVICE);
////                cmb.setText(wechatId);
////                Toast.makeText(activity, "微信ID:" + wechatId + "已复制到剪切板", LENGTH_LONG).show();
//                }
//            });


        }


    }


    /**
     * 获取并打印所有的字段名
     */
    private static void printAllFileds(Class cls) {
        Field[] field = cls.getDeclaredFields();
        Log.i("ssssssss", "getFields():获取所有权限修饰符修饰的字段");
        for (Field f : field) {
            Log.i("ssssssss", "Field Name = " + f.getName());
        }
        System.out.println();
    }


    /**
     * 传入全类名获得对应类中所有方法名和参数名
     */
    @SuppressWarnings("rawtypes")
    private static void getMethodInfo(Class clazz) {
        try {
            Method[] methods = clazz.getMethods();
            for (Method method : methods) {
                String methodName = method.getName();
                Log.i("ssssssss", "----方法名称:" + methodName);
                Class<?>[] parameterTypes = method.getParameterTypes();
                for (Class<?> clas : parameterTypes) {
                    String parameterName = clas.getName();
                    Log.i("ssssssss", "参数名称:" + parameterName);
                }
                Log.i("ssssssss", "*****************************");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
