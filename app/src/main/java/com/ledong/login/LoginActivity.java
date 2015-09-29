/*****************************************************************************
 * LoginActivity 用户登录页面
 *
 *****************************************************************************
 * Copyright (C) 2015-2016
 *
 * Authors: ChrisZhang <zhangyanlongcodec@gmail.com>
 *
 *
 */
package com.ledong.login;


import android.os.Bundle;

import com.ledong.app.BaseActivity;

import ly.ledong.com.ylandroidmodule.R;

public class LoginActivity extends BaseActivity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //
        initView();

        //
        setContentView(R.layout.user_login);

    }

    /**
     * 初始化组件
     */
    private void initView(){

    }

}
