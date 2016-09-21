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
    "group": "E__work_JianGuo_server_JG_server_doc_main_js",
    "groupTitle": "E__work_JianGuo_server_JG_server_doc_main_js",
    "name": ""
  },
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
    "filename": "./node_modules/grunt-apidoc/node_modules/apidoc/template/main.js",
    "group": "E__work_JianGuo_server_JG_server_node_modules_grunt_apidoc_node_modules_apidoc_template_main_js",
    "groupTitle": "E__work_JianGuo_server_JG_server_node_modules_grunt_apidoc_node_modules_apidoc_template_main_js",
    "name": ""
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
            "description": "<p>Users phone</p>"
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
    "filename": "./src/com/jianguo/merchant/login/LoginServlet.java",
    "groupTitle": "login",
    "sampleRequest": [
      {
        "url": "http://192.168.42.177:8080/LoginServlet/"
      }
    ]
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
    "filename": "./src/com/jianguo/merchant/login/QuickSmsServlet.java",
    "groupTitle": "login",
    "sampleRequest": [
      {
        "url": "http://192.168.42.177:8080/QuickSmsServlet/"
      }
    ]
  }
] });