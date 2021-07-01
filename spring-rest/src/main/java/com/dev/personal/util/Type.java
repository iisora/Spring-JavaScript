package com.dev.personal.util;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class Type {
	// htmlから直接アクセスできるようにstaticにする。
	public static final Map<Integer, String> GENDERS;
	/*
	 * static初期化ブロック(static initializer)は、以下の時に実行されます。
	 * staticメソッドやstatic変数に最初にアクセスした時。クラスのインスタンスを最初に生成する時。
	 */
	static {
		// 、LinkedHashMapクラスはHashMapクラスを継承している
		Map<Integer, String> genders = new LinkedHashMap<>();
		genders.put(0, "選択しない");
		genders.put(1, "男");
		genders.put(2, "女");
		genders.put(3, "その他");
		// Collectionを変更不可能にしている。
		GENDERS = Collections.unmodifiableMap(genders);
	}

}
