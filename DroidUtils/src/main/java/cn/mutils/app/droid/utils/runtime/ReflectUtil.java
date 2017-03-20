package cn.mutils.app.droid.utils.runtime;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 反射工具
 */
public class ReflectUtil {

    private ReflectUtil() {

    }

    /**
     * 获取静态成员变量的值
     *
     * @param clazz     类
     * @param fieldName 静态成员变量名称
     * @return 值
     */
    public static Object getFieldValue(Class<?> clazz, String fieldName)
            throws NoSuchFieldException, IllegalAccessException {
        return getDeclaredField(clazz, fieldName).get(null);
    }

    /**
     * 设置静态成员变量的值
     *
     * @param clazz     类
     * @param fieldName 静态成员变量名称
     * @param value     值
     */
    public static void setFieldValue(Class<?> clazz, String fieldName, Object value)
            throws NoSuchFieldException, IllegalAccessException {
        getDeclaredField(clazz, fieldName).set(null, value);
    }

    /**
     * 获取对象成员变量的值
     *
     * @param obj       对象
     * @param fieldName 成员变量名称
     * @return 值
     */
    public static Object getFieldValue(Object obj, String fieldName)
            throws NoSuchFieldException, IllegalAccessException {
        return getDeclaredField(obj.getClass(), fieldName).get(obj);
    }

    /**
     * 设置对象成员变量的值
     *
     * @param obj       对象
     * @param fieldName 成员变量名称
     * @param value     值
     */
    public static void setFieldValue(Object obj, String fieldName, Object value)
            throws NoSuchFieldException, IllegalAccessException {
        getDeclaredField(obj.getClass(), fieldName).set(obj, value);
    }

    /**
     * 根据成员变量名字获取成员变量反射描述
     *
     * @param c         类
     * @param fieldName 成员变量描述
     */
    public static Field getDeclaredField(Class c, String fieldName)
            throws NoSuchFieldException {
        while (c != null) {
            try {
                Field f = c.getDeclaredField(fieldName);
                f.setAccessible(true);
                return f;
            } catch (Exception e) {
            } finally {
                c = c.getSuperclass();
            }
        }
        throw new NoSuchFieldException();
    }

    /**
     * 动态调用成员方法
     *
     * @param clazz      类
     * @param object     对象
     * @param methodName 方法名称
     * @param argTypes   方法参数类型
     * @param args       方法参数
     * @return 返回值
     */
    public static Object invokeMethod(Class<?> clazz, Object object, String methodName, Class<?>[] argTypes,
                                      Object[] args) throws Exception {
        Method method = getDeclaredMethod(clazz, methodName, argTypes);
        if (null == args) {
            return method.invoke(object);
        } else {
            return method.invoke(object, args);
        }
    }

    /**
     * 动态调用对象方法
     *
     * @param object     对象
     * @param methodName 方法名称
     * @param argTypes   方法参数类型
     * @param args       方法参数
     * @return 返回值
     */
    public static Object invokeMethod(Object object, String methodName, Class<?>[] argTypes,
                                      Object[] args) throws Exception {
        return invokeMethod(object.getClass(), object, methodName, argTypes, args);
    }

    /**
     * 动态调用静态方法
     *
     * @param clazz      类
     * @param methodName 方法名称
     * @param argTypes   方法参数类型
     * @param args       方法参数
     * @return 返回值
     */
    public static Object invokeMethod(Class<?> clazz, String methodName, Class<?>[] argTypes,
                                      Object[] args) throws Exception {
        return invokeMethod(clazz, null, methodName, argTypes, args);
    }

    /**
     * 根据成员变量名字获取成员变量反射描述
     *
     * @param c          类
     * @param methodName 成员变量描述
     */
    public static Method getDeclaredMethod(Class<?> c, String methodName, Class<?>[] argTypes)
            throws NoSuchMethodException {
        while (c != null) {
            try {
                Method m = c.getDeclaredMethod(methodName, argTypes);
                m.setAccessible(true);
                return m;
            } catch (Exception e) {
            } finally {
                c = c.getSuperclass();
            }
        }
        throw new NoSuchMethodException();
    }

}
