package org.sample.calcsha1;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CalcSHA1 extends Activity implements OnClickListener {
	Button calcButton;
	EditText pathText;
	TextView outputView;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        calcButton = (Button)findViewById(R.id.button1);
        pathText = (EditText)findViewById(R.id.editText1);
        outputView = (TextView)findViewById(R.id.textView1);
        
        calcButton.setOnClickListener(this);
    }

	@Override
	public void onClick(View v) {
		//outputView.setText(pathText.getText().toString());
		try {
			String filePath = pathText.getText().toString();
			String output = digest2String(getFileDigest(filePath));
			outputView.setText(output);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public byte[] getFileDigest(String filename) throws NoSuchAlgorithmException, IOException
	{
		MessageDigest md = MessageDigest.getInstance("SHA1");
		FileInputStream in = new FileInputStream(filename);
		byte[] data = new byte[256];
		int len;
		while((len = in.read(data) ) >= 0)
		{
			md.update(data,0,len);
		}
		in.close();
		return md.digest();
	}
	public String digest2String(byte[] digest)
	{
		StringBuffer dec = new StringBuffer("");
		for(int i=0;i<digest.length;i++)
		{
			int val = digest[i] & 0xFF;
			if(val < 16)
			{
				dec.append("0");
			}
			//dec.append(Integer.toString(val, 16)).append(" ");
			dec.append(Integer.toString(val, 16));
		}
		return dec.toString();
	}
}