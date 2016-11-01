define({ "api": [
  {
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "optional": false,
            "field": "varname1",
            "description": "<p>No type.</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "varname2",
            "description": "<p>With type.</p>"
          }
        ]
      }
    },
    "type": "",
    "url": "",
    "version": "0.0.0",
    "filename": "./doc/main.js",
    "group": "E__work_JianGuo_server_Server_jg_doc_main_js",
    "groupTitle": "E__work_JianGuo_server_Server_jg_doc_main_js",
    "name": ""
  },
  {
    "type": "post",
    "url": "CerficationServlet/",
    "title": "个人商家信息审核",
    "name": "CerficationServlet",
    "group": "certification",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "loginId",
            "description": "<p>User loginId</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "token",
            "description": "<p>User token</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "merchantId",
            "description": "<p>User merchantId</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "merchantInfo",
            "description": "<p>商家审核信息json对象</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "code",
            "description": "<p>200</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>审核提交成功！</p>"
          }
        ]
      }
    },
    "error": {
      "fields": {
        "Error 400": [
          {
            "group": "Error 400",
            "type": "String",
            "optional": false,
            "field": "code",
            "description": "<p>400</p>"
          },
          {
            "group": "Error 400",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>服务器忙，请稍后重试</p>"
          },
          {
            "group": "Error 400",
            "type": "String",
            "optional": false,
            "field": "codeError",
            "description": "<p>代码错误详情（供内部测试，查找问题使用）</p>"
          }
        ],
        "Error 401": [
          {
            "group": "Error 401",
            "type": "String",
            "optional": false,
            "field": "code",
            "description": "<p>401</p>"
          },
          {
            "group": "Error 401",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>参数错误请检查</p>"
          }
        ],
        "Error 402": [
          {
            "group": "Error 402",
            "type": "String",
            "optional": false,
            "field": "code",
            "description": "<p>402</p>"
          },
          {
            "group": "Error 402",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>签名校验错误</p>"
          }
        ],
        "Error 403": [
          {
            "group": "Error 403",
            "type": "String",
            "optional": false,
            "field": "code",
            "description": "<p>403</p>"
          },
          {
            "group": "Error 403",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>缺少审核信息</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "./src/com/jianguo/merchant/certification/CerficationServlet.java",
    "groupTitle": "certification"
  },
  {
    "type": "post",
    "url": "AutoLoginServlet/",
    "title": "自动登录",
    "name": "AutoLoginServlet",
    "group": "login",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "tel",
            "description": "<p>User phone</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "token",
            "description": "<p>User token</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "code",
            "description": "<p>200</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>登录成功！</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "tel",
            "description": "<p>18101050625</p>"
          },
          {
            "group": "Success 200",
            "type": "int",
            "optional": false,
            "field": "loginId",
            "description": "<p>10</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "password",
            "description": "<p>&quot;&quot;</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "token",
            "description": "<p>0a4148a32160ebfa78eff622357bda4e</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "permissions",
            "description": "<p>0 (商家权限（1是外部商家，2是个人商户，0是内部）)</p>"
          },
          {
            "group": "Success 200",
            "type": "int",
            "optional": false,
            "field": "payStatus",
            "description": "<p>0  (支付密码是否设置 0未设置1已设置)</p>"
          },
          {
            "group": "Success 200",
            "type": "int",
            "optional": false,
            "field": "resumeStatus",
            "description": "<p>0 (商家信息是否填写 0未填写 1已填写审核中 2审核通过)</p>"
          }
        ]
      }
    },
    "error": {
      "fields": {
        "Error 400": [
          {
            "group": "Error 400",
            "type": "String",
            "optional": false,
            "field": "code",
            "description": "<p>400</p>"
          },
          {
            "group": "Error 400",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>服务器忙，请稍后重试</p>"
          },
          {
            "group": "Error 400",
            "type": "String",
            "optional": false,
            "field": "codeError",
            "description": "<p>代码错误详情（供内部测试，查找问题使用）</p>"
          }
        ],
        "Error 401": [
          {
            "group": "Error 401",
            "type": "String",
            "optional": false,
            "field": "code",
            "description": "<p>401</p>"
          },
          {
            "group": "Error 401",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>参数错误请检查</p>"
          }
        ],
        "Error 402": [
          {
            "group": "Error 402",
            "type": "String",
            "optional": false,
            "field": "code",
            "description": "<p>402</p>"
          },
          {
            "group": "Error 402",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>签名校验错误</p>"
          }
        ],
        "Error 403": [
          {
            "group": "Error 403",
            "type": "String",
            "optional": false,
            "field": "code",
            "description": "<p>403</p>"
          },
          {
            "group": "Error 403",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>手机号码不存在</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "./src/com/jianguo/merchant/login/AutoLoginServlet.java",
    "groupTitle": "login"
  },
  {
    "type": "post",
    "url": "LoginServlet/",
    "title": "快速登录",
    "name": "LoginServlet",
    "group": "login",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "tel",
            "description": "<p>User phone</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "smsCode",
            "description": "<p>User smsCode</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "code",
            "description": "<p>200</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>登录成功！</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "tel",
            "description": "<p>18101050625</p>"
          },
          {
            "group": "Success 200",
            "type": "int",
            "optional": false,
            "field": "loginId",
            "description": "<p>10</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "password",
            "description": "<p>&quot;&quot;</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "token",
            "description": "<p>0a4148a32160ebfa78eff622357bda4e</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "permissions",
            "description": "<p>0 (商家权限（1是外部商家，2是个人商户，0是内部）)</p>"
          },
          {
            "group": "Success 200",
            "type": "int",
            "optional": false,
            "field": "payStatus",
            "description": "<p>0  (支付密码是否设置 0未设置1已设置)</p>"
          },
          {
            "group": "Success 200",
            "type": "int",
            "optional": false,
            "field": "resumeStatus",
            "description": "<p>0 (商家信息是否填写 0未填写 1已填写审核中 2审核通过)</p>"
          }
        ]
      }
    },
    "error": {
      "fields": {
        "Error 400": [
          {
            "group": "Error 400",
            "type": "String",
            "optional": false,
            "field": "code",
            "description": "<p>400</p>"
          },
          {
            "group": "Error 400",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>服务器忙，请稍后重试</p>"
          },
          {
            "group": "Error 400",
            "type": "String",
            "optional": false,
            "field": "codeError",
            "description": "<p>代码错误详情（供内部测试，查找问题使用）</p>"
          }
        ],
        "Error 401": [
          {
            "group": "Error 401",
            "type": "String",
            "optional": false,
            "field": "code",
            "description": "<p>401</p>"
          },
          {
            "group": "Error 401",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>参数错误请检查</p>"
          }
        ],
        "Error 402": [
          {
            "group": "Error 402",
            "type": "String",
            "optional": false,
            "field": "code",
            "description": "<p>402</p>"
          },
          {
            "group": "Error 402",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>验证码错误</p>"
          }
        ],
        "Error 403": [
          {
            "group": "Error 403",
            "type": "String",
            "optional": false,
            "field": "code",
            "description": "<p>403</p>"
          },
          {
            "group": "Error 403",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>验证码已过期</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "./src/com/jianguo/merchant/login/LoginServlet.java",
    "groupTitle": "login"
  },
  {
    "type": "post",
    "url": "PasswordLoginServlet/",
    "title": "密码登录",
    "name": "PasswordLoginServlet",
    "group": "login",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "tel",
            "description": "<p>User phone</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "password",
            "description": "<p>User password</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "code",
            "description": "<p>200</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>登录成功！</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "tel",
            "description": "<p>18101050625</p>"
          },
          {
            "group": "Success 200",
            "type": "int",
            "optional": false,
            "field": "loginId",
            "description": "<p>10</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "password",
            "description": "<p>&quot;&quot;</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "token",
            "description": "<p>0a4148a32160ebfa78eff622357bda4e</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "permissions",
            "description": "<p>0 (商家权限（1是外部商家，2是个人商户，0是内部）)</p>"
          },
          {
            "group": "Success 200",
            "type": "int",
            "optional": false,
            "field": "payStatus",
            "description": "<p>0  (支付密码是否设置 0未设置1已设置)</p>"
          },
          {
            "group": "Success 200",
            "type": "int",
            "optional": false,
            "field": "resumeStatus",
            "description": "<p>0 (商家信息是否填写 0未填写 1已填写审核中 2审核通过)</p>"
          }
        ]
      }
    },
    "error": {
      "fields": {
        "Error 400": [
          {
            "group": "Error 400",
            "type": "String",
            "optional": false,
            "field": "code",
            "description": "<p>400</p>"
          },
          {
            "group": "Error 400",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>服务器忙，请稍后重试</p>"
          },
          {
            "group": "Error 400",
            "type": "String",
            "optional": false,
            "field": "codeError",
            "description": "<p>代码错误详情（供内部测试，查找问题使用）</p>"
          }
        ],
        "Error 401": [
          {
            "group": "Error 401",
            "type": "String",
            "optional": false,
            "field": "code",
            "description": "<p>401</p>"
          },
          {
            "group": "Error 401",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>参数错误请检查</p>"
          }
        ],
        "Error 402": [
          {
            "group": "Error 402",
            "type": "String",
            "optional": false,
            "field": "code",
            "description": "<p>402</p>"
          },
          {
            "group": "Error 402",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>密码错误</p>"
          }
        ],
        "Error 403": [
          {
            "group": "Error 403",
            "type": "String",
            "optional": false,
            "field": "code",
            "description": "<p>403</p>"
          },
          {
            "group": "Error 403",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>手机号码不存在</p>"
          }
        ],
        "Error 405": [
          {
            "group": "Error 405",
            "type": "String",
            "optional": false,
            "field": "code",
            "description": "<p>405</p>"
          },
          {
            "group": "Error 405",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>尚未设置密码，短信登录用户</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "./src/com/jianguo/merchant/login/PasswordLoginServlet.java",
    "groupTitle": "login"
  },
  {
    "type": "post",
    "url": "QuickSmsServlet/",
    "title": "快速登录验证码",
    "name": "QuickSmsServlet",
    "group": "login",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "tel",
            "description": "<p>Users phone</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "type",
            "description": "<p>请求类型，可选字段（忘记密码中的验证码请求，注册传0，忘记密码传1，其余不传该字段）</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "code",
            "description": "<p>200</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>验证码已经发送，请注意查收！</p>"
          }
        ]
      }
    },
    "error": {
      "fields": {
        "Error 400": [
          {
            "group": "Error 400",
            "type": "String",
            "optional": false,
            "field": "code",
            "description": "<p>400</p>"
          },
          {
            "group": "Error 400",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>服务器忙，请稍后重试！（sql或者IO错误,给用户提示服务器忙）</p>"
          }
        ],
        "Error 401": [
          {
            "group": "Error 401",
            "type": "String",
            "optional": false,
            "field": "code",
            "description": "<p>401</p>"
          },
          {
            "group": "Error 401",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>您的验证码请求过于频繁，请稍后再试！</p>"
          }
        ],
        "Error 402": [
          {
            "group": "Error 402",
            "type": "String",
            "optional": false,
            "field": "code",
            "description": "<p>402</p>"
          },
          {
            "group": "Error 402",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>忘记密码情况下，手机账号不存在！</p>"
          }
        ],
        "Error 403": [
          {
            "group": "Error 403",
            "type": "String",
            "optional": false,
            "field": "code",
            "description": "<p>403</p>"
          },
          {
            "group": "Error 403",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>注册情况下，手机账号已经存在！</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "./src/com/jianguo/merchant/login/QuickSmsServlet.java",
    "groupTitle": "login"
  },
  {
    "type": "post",
    "url": "RegisterServlet/",
    "title": "商家注册",
    "name": "RegisterServlet",
    "group": "login",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "tel",
            "description": "<p>User phone</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "smsCode",
            "description": "<p>User smsCode</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "password",
            "description": "<p>User password</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "code",
            "description": "<p>200</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>登录成功！</p>"
          }
        ]
      }
    },
    "error": {
      "fields": {
        "Error 400": [
          {
            "group": "Error 400",
            "type": "String",
            "optional": false,
            "field": "code",
            "description": "<p>400</p>"
          },
          {
            "group": "Error 400",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>服务器忙，请稍后重试</p>"
          },
          {
            "group": "Error 400",
            "type": "String",
            "optional": false,
            "field": "codeError",
            "description": "<p>代码错误详情（供内部测试，查找问题使用）</p>"
          }
        ],
        "Error 401": [
          {
            "group": "Error 401",
            "type": "String",
            "optional": false,
            "field": "code",
            "description": "<p>401</p>"
          },
          {
            "group": "Error 401",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>参数错误请检查</p>"
          }
        ],
        "Error 402": [
          {
            "group": "Error 402",
            "type": "String",
            "optional": false,
            "field": "code",
            "description": "<p>402</p>"
          },
          {
            "group": "Error 402",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>验证码错误</p>"
          }
        ],
        "Error 403": [
          {
            "group": "Error 403",
            "type": "String",
            "optional": false,
            "field": "code",
            "description": "<p>403</p>"
          },
          {
            "group": "Error 403",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>验证码已过期</p>"
          }
        ],
        "Error 405": [
          {
            "group": "Error 405",
            "type": "String",
            "optional": false,
            "field": "code",
            "description": "<p>405</p>"
          },
          {
            "group": "Error 405",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>手机号码已经存在</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "./src/com/jianguo/merchant/login/RegisterServlet.java",
    "groupTitle": "login"
  },
  {
    "type": "post",
    "url": "ResetPasswordServlet/",
    "title": "重置密码",
    "name": "ResetPasswordServlet",
    "group": "login",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "tel",
            "description": "<p>User phone</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "smsCode",
            "description": "<p>User smsCode</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "password",
            "description": "<p>User password</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "code",
            "description": "<p>200</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>修改成功</p>"
          }
        ]
      }
    },
    "error": {
      "fields": {
        "Error 400": [
          {
            "group": "Error 400",
            "type": "String",
            "optional": false,
            "field": "code",
            "description": "<p>400</p>"
          },
          {
            "group": "Error 400",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>服务器忙，请稍后重试</p>"
          },
          {
            "group": "Error 400",
            "type": "String",
            "optional": false,
            "field": "codeError",
            "description": "<p>代码错误详情（供内部测试，查找问题使用）</p>"
          }
        ],
        "Error 401": [
          {
            "group": "Error 401",
            "type": "String",
            "optional": false,
            "field": "code",
            "description": "<p>401</p>"
          },
          {
            "group": "Error 401",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>参数错误请检查</p>"
          }
        ],
        "Error 402": [
          {
            "group": "Error 402",
            "type": "String",
            "optional": false,
            "field": "code",
            "description": "<p>402</p>"
          },
          {
            "group": "Error 402",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>验证码错误</p>"
          }
        ],
        "Error 403": [
          {
            "group": "Error 403",
            "type": "String",
            "optional": false,
            "field": "code",
            "description": "<p>403</p>"
          },
          {
            "group": "Error 403",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>验证码已过期</p>"
          }
        ],
        "Error 405": [
          {
            "group": "Error 405",
            "type": "String",
            "optional": false,
            "field": "code",
            "description": "<p>405</p>"
          },
          {
            "group": "Error 405",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>手机号码已经存在</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "./src/com/jianguo/merchant/login/ResetPasswordServlet.java",
    "groupTitle": "login"
  },
  {
    "type": "post",
    "url": "PayWageServlet/",
    "title": "�̼ҽ���",
    "name": "LoginServlet",
    "group": "wages",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "job_id",
            "description": "<p>��ְjobid</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "json",
            "description": "<p>Users info</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "code",
            "description": "<p>200</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>��֤���Ѿ����ͣ���ע�����գ�</p>"
          }
        ]
      }
    },
    "error": {
      "fields": {
        "Error 4xx": [
          {
            "group": "Error 4xx",
            "type": "String",
            "optional": false,
            "field": "code",
            "description": "<p>400</p>"
          },
          {
            "group": "Error 4xx",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": ""
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "./src/com/jianguo/merchant/wages/PayWageServlet.java",
    "groupTitle": "wages"
  }
] });
