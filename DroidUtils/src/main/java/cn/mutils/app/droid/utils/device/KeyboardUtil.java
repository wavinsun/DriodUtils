package cn.mutils.app.droid.utils.device;

import android.content.Context;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * 键盘工具
 */
public class KeyboardUtil {

    /**
     * 显示键盘
     *
     * @param editText 输入组件
     * @return 是否成功
     */
    public static boolean showKeyboard(EditText editText) {
        if (editText == null) {
            return false;
        }
        Context context = editText.getContext();
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm == null) {
            return false;
        }
        editText.setFocusableInTouchMode(true);
        editText.requestFocus();
        editText.setImeOptions(EditorInfo.IME_FLAG_NO_EXTRACT_UI);
        return imm.showSoftInput(editText, 0);
    }

    /**
     * 影藏键盘
     *
     * @param editText 输入组件
     */
    public static void hideKeyboard(EditText editText) {
        Context context = editText.getContext();
        editText.setFocusableInTouchMode(false);
        editText.clearFocus();
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (null != imm && imm.isActive()) {
            imm.hideSoftInputFromWindow(editText.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    /**
     * 强制影藏键盘
     *
     * @param editText 输入组件
     */
    public static void forceHideKeyboard(EditText editText) {
        if (editText.getWindowToken() == null) {
            return;
        }
        editText.setFocusableInTouchMode(false);
        editText.clearFocus();
        Context context = editText.getContext();
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (null != imm && imm.isActive()) {
            imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
        }
    }

}
