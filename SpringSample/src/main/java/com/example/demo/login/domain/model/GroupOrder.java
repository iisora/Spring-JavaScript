package com.example.demo.login.domain.model;

import javax.validation.GroupSequence;

/* 実行順序を設定するアノテーション（左から順番に) */
@GroupSequence({ ValidGroup1.class, ValidGroup2.class, ValidGroup3.class })
public interface GroupOrder {

}