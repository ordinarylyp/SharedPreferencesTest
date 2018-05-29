package lyp.com.sharedpreferencestest;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
    }
}
