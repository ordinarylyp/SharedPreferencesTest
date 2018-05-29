# SharedPreferencesTest
对SharePreferences存储的学习
### 1.存储

要想使用 SharedPreferences 来存储数据，首先需要获取到 SharedPreferences 对象。Android 提供了三种方法得到 SharedPreferences 对象：

###### Context 类中的 getSharedPreferences()方法
  此方法接收两个参数，第一个参数指定 SharedPreferences 文件的名称，第二个参数指定操作模式，目前只有 MODE_PRIVATE 一种模式，和直接传入 0 效果相同。
  其他几种模式已被废弃。

###### Activity 类中的 getPreferences()方法
  此方法和上面的方法相似，但只接收一个操作模式参数，使用这个方法时会自动将当前活动的类名作为 SharedPreferences 的文件名。

###### PreferenceManager 类中的 getDefaultSharedPreferences()方法
  这是一个静态方法，它接收一个 Context 参数，并自动使用当前应用程序的包名作为前缀来命名 SharedPreferences 文件。

###### 得到了 SharedPreferences 对象之后，分为三步实现向 SharedPreferences 文件中存储数据：
  （1）调用 SharedPreferences 对象的 edit()方法来获取一个 SharedPreferences.Editor 对象。
  
  （2）向 SharedPreferences.Editor 对象中添加数据，如添加一个布尔型数据使用 putBoolean 方法，添加一个字符串使用 putString()方法，以此类推。
  
  （3） 调用 apply()方法将添加的数据提交，完成数据存储。

可见[MainActivity.java](/app/src/main/java/lyp/com/sharedpreferencestest/MainActivity.java)中的代码：
```Java
        Button saveData = findViewById(R.id.save_data);
        saveData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //指定文件名为data，并得到一个SharedPreferences.Editor对象
                SharedPreferences.Editor editor=getSharedPreferences("data",MODE_PRIVATE).edit();
                //添加数据
                editor.putString("name","Tom");
                editor.putInt("age",28);
                editor.putBoolean("married",false);
                //提交数据
                editor.apply();
            }
        });
```
保存后的文件：

![save](/img/save.png "save")
![data_save](/img/datasave.png "data_save")
### 2.读取
SharedPreferences 对象中提供了一系列的 get 方法用于对存储的数据进行读取，每种 get 方法都对应了 SharedPreferences. Editor 
中的一种 put 方法。这些 get 方法接收两个参数，第一个参数是键，即传入存储数据时使用的键；第二个参数是默认值，即当传入的键找不到对
应的值时，返回默认值。
可见[MainActivity.java](/app/src/main/java/lyp/com/sharedpreferencestest/MainActivity.java)中的代码：
```Java
        final TextView showData=findViewById(R.id.show_data);
        Button restoreData=findViewById(R.id.restore_data);
        restoreData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获得一个SharedPreferences对象
                SharedPreferences preferences=getSharedPreferences("data",MODE_PRIVATE);
                //获取数据
                String name=preferences.getString("name","");
                int age=preferences.getInt("age",0);
                boolean married=preferences.getBoolean("married",false);
                //显示数据
                showData.setText("name:"+name+",age:"+age+"married is "+married);

            }
        });
```
最终运行结果：

![restore_data](/img/restoredata.png "restore_data")

[布局文件](/app/src/main/res/layout/activity_main.xml)自行查看代码。
