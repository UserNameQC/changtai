package com.changtai.Utils;


import android.util.Log;

public class HelloWorldController {

	public byte[] helloWorld() {
		return GetPass(1234567890L);
	}
	
	private byte Ddd2(byte add1,byte add2) {
		int sum = (add1+add2)%256;
		return (byte)sum;
	}
	

    private byte[] Dessming(byte[] source)
    {
        byte[] key = { 0x22, 0x55, 0x44, 0x33 };//新系统密钥22554433

        byte x0, x1, x2, x3, y0, y1, y2, y3, m, n, add1, add2;

        x0 = key[0];
        x1 = key[1];
        x2 = key[2];
        x3 = key[3];

        y0 = source[0];
        y1 = source[1];
        y2 = source[2];
        y3 = source[3];

        m = (byte)(x0 ^ x1);
        add1 = m;
        add2 = y2;
        m = Ddd2(add1, add2);
        add1 = x0;
        add2 = x1;
        n = Ddd2(add1, add2);
        add1 = n;
        add2 = y3;
        n = Ddd2(add1, add2);
        m = (byte)(m ^ y0);
        n = (byte)(n ^ y1);
        y0 = m;
        y1 = n;

        add1 = m;
        add2 = (byte)(x1 ^ x2);
        m = Ddd2(add1, add2);
        add1 = x1;
        add2 = x2;
        add1 = Ddd2(add1, add2);
        add2 = n;
        n = Ddd2(add1, add2);
        m = (byte)(m ^ y2);
        n = (byte)(n ^ y3);
        y2 = m;
        y3 = n;

        add1 = y1;
        add2 = (byte)(x2 ^ x3);
        m = Ddd2(add1, add2);
        add1 = x2;
        add2 = x3;
        add1 = Ddd2(add1, add2);
        add2 = y0;
        n = Ddd2(add1, add2);
        m = (byte)(m ^ y3);
        n = (byte)(n ^ y2);
        y2 = m;
        y3 = n;

        add1 = m;
        add2 = (byte)(x0 ^ x3);
        m = Ddd2(add1, add2);
        add1 = x0;
        add2 = x3;
        add1 = Ddd2(add1, add2);
        add2 = n;
        n = Ddd2(add1, add2);
        m = (byte)(m ^ y1);
        n = (byte)(n ^ y0);
        y0 = m;
        y1 = n;

        add1 = y3;
        add2 = (byte)(x0 ^ x3);
        m = Ddd2(add1, add2);
        add1 = x0;
        add2 = x3;
        add1 = Ddd2(add1, add2);
        add2 = y2;
        n = Ddd2(add1, add2);
        m = (byte)(m ^ y1);
        n = (byte)(n ^ y0);
        y0 = m;
        y1 = n;


        add1 = m;
        add2 = (byte)(x2 ^ x3);
        m = Ddd2(add1, add2);
        add1 = x2;
        add2 = x3;
        add1 = Ddd2(add1, add2);
        add2 = n;
        n = Ddd2(add1, add2);
        m = (byte)(m ^ y3);
        n = (byte)(n ^ y2);
        y2 = m;
        y3 = n;

        add1 = y1;
        add2 = (byte)(x1 ^ x2);
        m = Ddd2(add1, add2);
        add1 = x1;
        add2 = x2;
        add1 = Ddd2(add1, add2);
        add2 = y0;
        n = Ddd2(add1, add2);
        m = (byte)(m ^ y3);
        n = (byte)(n ^ y2);
        y2 = m;
        y3 = n;

        add1 = m;
        add2 = (byte)(x0 ^ x1);
        m = Ddd2(add1, add2);
        add1 = x0;
        add2 = x1;
        add1 = Ddd2(add1, add2);
        add2 = n;
        n = Ddd2(add1, add2);
        m = (byte)(m ^ y1);
        n = (byte)(n ^ y0);
        y0 = m;
        y1 = n;

        byte[] value = { y0, y1, y2, y3 };
        return value;
    }
    
    private byte[] int2byte(Long res) {
        byte[] targets = new byte[4];
        targets[0] = (byte) (res & 0xff);// 最低位
        targets[1] = (byte) ((res >> 8) & 0xff);// 次低位
        targets[2] = (byte) ((res >> 16) & 0xff);// 次高位
        targets[3] = (byte) (res >>> 24);// 最高位,无符号右移。
        return targets;
    }
    
    public byte[] GetPass(Long cardsn)
    {
        byte[] mia = int2byte(cardsn);
        byte[] mib = int2byte(~cardsn);
        mia = Dessming(mia);
        mib = Dessming(mib);
//        long value0=mia[0] << 40 ;
//        long value1=mia[1] << 32;
//        long value2=mia[2] << 24;
//        long value3=mia[3] << 16;
//        long value4=mib[0] << 8 ;
//        long value5=mib[1];
//
//        long value6 = value0| value1 |value2 | value3 | value4| value5;
//        long value = ((long) (value6)) & 0xFFFFFFFFL;
//        return Integer.toHexString((int)value);
        byte[] c ={mia[0], mia[1], mia[2], mia[3], mib[0], mib[1]};
        //byte[] d = {mib[1], mib[0], mia[3], mia[2], mia[1], mia[0]};
        //return c;
        String str = String.format("%02x%02x%02x%02x%02x%02x",mia[0],mia[1],mia[2],mia[3],mib[0],mib[1]);
        Log.e("getPass", str);

        return c;
    }
    
}
