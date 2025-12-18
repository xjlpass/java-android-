package com.example.lib.serializable;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/*序列化作用：
       1、 持久化存储对象
            可以将对象的状态写入文件、数据库或 SharedPreferences（通过字节流或 Base64）
            下次程序启动可以通过反序列化恢复对象状态
       用途： 缓存用户配置、游戏存档、HUD 状态
             将复杂对象保存到磁盘，而不是单独保存每个字段
       2、网络传输 / RPC
            将对象转换成字节流，通过网络发送给远程系统
            对方接收后反序列化为对象
       用途：
           跨设备通信
           远程调用（RMI）
       3、跨进程传递对象（IPC）
               Android 中 Binder / AIDL 可以传递序列化对象（虽然 Parcelable 更高效）
               适用于同一设备上不同 app 或 service 间的对象传输
**/
public class SerializableDemo {
    public static void main(String[] args) throws IOException {
        User1 user = new User1("Alice", 123);

        //  序列化
        try {
            // user.dat 在根目录下
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("user.dat"));
            // 将对象及其状态写入流
            out.writeObject(user);
            System.out.println("对象已序列化到 user.dat");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 反序列化
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("user.dat"));
            //  返回的是 Object 类型，Java 编译器不知道它具体是 User
            User1 newUser = (User1) in.readObject();
            System.out.println("反序列化对象：" + newUser);
        }catch(Exception e){
            e.printStackTrace();
        }
        //
//        对象已序列化到 user.dat
//        反序列化对象：User{name='Alice', age=123}

    }
}
