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
    "title": "�����̼���Ϣ����",
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
            "description": "<p>�̼�������Ϣjson����</p>"
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
            "description": "<p>�����ύ�ɹ���</p>"
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
            "description": "<p>������æ�����Ժ�����</p>"
          },
          {
            "group": "Error 400",
            "type": "String",
            "optional": false,
            "field": "codeError",
            "description": "<p>�����������飨���ڲ����ԣ���������ʹ�ã�</p>"
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
            "description": "<p>��������������</p>"
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
            "description": "<p>ǩ��У������</p>"
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
            "description": "<p>ȱ��������Ϣ</p>"
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
    "url": "UpLogoServlet/",
    "title": "上传头像",
    "name": "UpLogoServlet",
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
            "field": "logoUrl",
            "description": "<p>User 用户头像</p>"
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
        ]
      }
    },
    "version": "0.0.0",
    "filename": "./src/com/jianguo/merchant/certification/UpLogoServlet.java",
    "groupTitle": "certification"
  },
  {
    "type": "post",
    "url": "AutoLoginServlet/",
    "title": "�Զ���¼",
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
            "description": "<p>��¼�ɹ���</p>"
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
            "description": "<p>0 (�̼�Ȩ�ޣ�1���ⲿ�̼ң�2�Ǹ����̻���0���ڲ���)</p>"
          },
          {
            "group": "Success 200",
            "type": "int",
            "optional": false,
            "field": "payStatus",
            "description": "<p>0  (֧�������Ƿ����� 0δ����1������)</p>"
          },
          {
            "group": "Success 200",
            "type": "int",
            "optional": false,
            "field": "resumeStatus",
            "description": "<p>0 (�̼���Ϣ�Ƿ���д 0δ��д 1����д������ 2����ͨ��)</p>"
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
            "description": "<p>������æ�����Ժ�����</p>"
          },
          {
            "group": "Error 400",
            "type": "String",
            "optional": false,
            "field": "codeError",
            "description": "<p>�����������飨���ڲ����ԣ���������ʹ�ã�</p>"
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
            "description": "<p>��������������</p>"
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
            "description": "<p>ǩ��У������</p>"
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
            "description": "<p>�ֻ����벻����</p>"
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
    "title": "���ٵ�¼",
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
            "description": "<p>��¼�ɹ���</p>"
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
            "description": "<p>0 (�̼�Ȩ�ޣ�1���ⲿ�̼ң�2�Ǹ����̻���0���ڲ���)</p>"
          },
          {
            "group": "Success 200",
            "type": "int",
            "optional": false,
            "field": "payStatus",
            "description": "<p>0  (֧�������Ƿ����� 0δ����1������)</p>"
          },
          {
            "group": "Success 200",
            "type": "int",
            "optional": false,
            "field": "resumeStatus",
            "description": "<p>0 (�̼���Ϣ�Ƿ���д 0δ��д 1����д������ 2����ͨ��)</p>"
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
            "description": "<p>������æ�����Ժ�����</p>"
          },
          {
            "group": "Error 400",
            "type": "String",
            "optional": false,
            "field": "codeError",
            "description": "<p>�����������飨���ڲ����ԣ���������ʹ�ã�</p>"
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
            "description": "<p>��������������</p>"
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
            "description": "<p>��֤������</p>"
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
            "description": "<p>��֤���ѹ���</p>"
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
    "title": "������¼",
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
            "description": "<p>��¼�ɹ���</p>"
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
            "description": "<p>0 (�̼�Ȩ�ޣ�1���ⲿ�̼ң�2�Ǹ����̻���0���ڲ���)</p>"
          },
          {
            "group": "Success 200",
            "type": "int",
            "optional": false,
            "field": "payStatus",
            "description": "<p>0  (֧�������Ƿ����� 0δ����1������)</p>"
          },
          {
            "group": "Success 200",
            "type": "int",
            "optional": false,
            "field": "resumeStatus",
            "description": "<p>0 (�̼���Ϣ�Ƿ���д 0δ��д 1����д������ 2����ͨ��)</p>"
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
            "description": "<p>������æ�����Ժ�����</p>"
          },
          {
            "group": "Error 400",
            "type": "String",
            "optional": false,
            "field": "codeError",
            "description": "<p>�����������飨���ڲ����ԣ���������ʹ�ã�</p>"
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
            "description": "<p>��������������</p>"
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
            "description": "<p>��������</p>"
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
            "description": "<p>�ֻ����벻����</p>"
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
            "description": "<p>��δ�������룬���ŵ�¼�û�</p>"
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
    "title": "���ٵ�¼��֤��",
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
            "description": "<p>�������ͣ���ѡ�ֶΣ����������е���֤��������ע�ᴫ0���������봫1�����಻�����ֶΣ�</p>"
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
            "description": "<p>������æ�����Ժ����ԣ���sql����IO����,���û���ʾ������æ��</p>"
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
            "description": "<p>������֤����������Ƶ�������Ժ����ԣ�</p>"
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
            "description": "<p>�������������£��ֻ��˺Ų����ڣ�</p>"
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
            "description": "<p>ע�������£��ֻ��˺��Ѿ����ڣ�</p>"
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
    "title": "�̼�ע��",
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
            "description": "<p>��¼�ɹ���</p>"
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
            "description": "<p>������æ�����Ժ�����</p>"
          },
          {
            "group": "Error 400",
            "type": "String",
            "optional": false,
            "field": "codeError",
            "description": "<p>�����������飨���ڲ����ԣ���������ʹ�ã�</p>"
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
            "description": "<p>��������������</p>"
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
            "description": "<p>��֤������</p>"
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
            "description": "<p>��֤���ѹ���</p>"
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
            "description": "<p>�ֻ������Ѿ�����</p>"
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
    "title": "��������",
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
            "description": "<p>�޸ĳɹ�</p>"
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
            "description": "<p>������æ�����Ժ�����</p>"
          },
          {
            "group": "Error 400",
            "type": "String",
            "optional": false,
            "field": "codeError",
            "description": "<p>�����������飨���ڲ����ԣ���������ʹ�ã�</p>"
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
            "description": "<p>��������������</p>"
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
            "description": "<p>��֤������</p>"
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
            "description": "<p>��֤���ѹ���</p>"
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
            "description": "<p>�ֻ������Ѿ�����</p>"
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
