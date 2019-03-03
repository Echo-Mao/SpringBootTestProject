/*
 * 后台需要编写的内容：
 * 1judgeLogin判断是否登录，到请求中的cookie中获取登录empId值，没有则返回失败状态。
 * 2getEmpList查询数据
 * 
 * 
 *前台页面的流程和跳转
 *1页面加载以及实例创建完成，去后台判断有没有登录，没登录就跳转到登录页面
 *2到后台去查询第一页的数据，
 *
 *
 *
 */

function getInsuranceId() {
  var name = $.query.get("insuranceId");
  return name;
}

$(function() {
  var protocol = window.location.protocol;
  var host = window.location.hostname;
  var port = window.location.port;

  var insuranceTypeVUE = new Vue({
    el: "#page",
    created: function() {
      //1判断登录以及页面跳转并且拿登录用户Id？
      this.judgeLogin();
      //2查询第一页数据
      if (this.page.pageSize !== 0) {
        this.getInsuranceTypeList(1, this.page.pageSize);
      }
      this.getProgrammeList();

    },
    data: {
      /* 页码信息数据 */
      page: {
        pageIndex: 1,
        pageSize: 10,
        totalPage: 0,
        totalCount: 0
      },
      /* 条件查询数据 */
      condition: {
        qInsuranceName: "", //模糊查询险种名
        qStartDate: "", //开始查询日期
        qEndDate: "", //结束查询日期
        qStatus: -1, //上线状态
        qProgrammeId: '', //报价方案id
      },
      /* 获取的险种集合 */
      dataList: [],
      /* 添加的险种信息 */
      addInsurance: {
        insuranceName: "", //险种名称
        example: "", //案例
        notice: "", //注意事项
        applicable: "", //适用人群
        buyPercent: "", //购买指数
        programmeId: -1, //报价方案id
        programmeList: []
      },
      /* 删除 */
      deleteIds: [],
    },
    methods: {
      getInsuranceTypeList: function(pageIndex, pageSize) {
        var _this = this;
        _this.page.pageIndex = pageIndex;
        _this.page.pageSize = pageSize;
        _this.checkData = [];
        var params = {
          "page": _this.page.pageIndex,
          "size": _this.page.pageSize,
          "qInsuranceName": this.condition.qInsuranceName,
          "qStartDate": this.condition.qStartDate,
          "qEndDate": _this.condition.qEndDate,
          "qStatus": _this.condition.qStatus,
          "qProgrammeId": _this.condition.qProgrammeId
        };
        this.$http.post(protocol + "//" + host + ":" + port + "/insuranceType/insuranceTypeList.action",
          params, {
            emulateJSON: true
          }).then(
          function(res) {
            _this.dataList = res.data.data.list; //返回的是RetResult
            _this.page.totalCount = res.data.data.total;
            _this.page.totalPage = res.data.data.pages;
          },
          function(res) {
            alert("您输入的参数有误！");
            console.log(res);
          });
      },
      judgeLogin: function() {
        this.$http.post(protocol + "//" + host + ":" + port + "/getLoginEmp.action").then(
          function(res) {
            this.loginEmp = res.data.loginEmp;
            if (this.loginEmp == null) { //没有登录
              window.parent.location.href = "/background/background-login.html";
            } else {
              //注意：这里由于使用两个ajax会异步进行，所以要使其有先后顺序，在这里调用
              this.getInsuranceTypeList(1, this.page.pageSize);
            }
          },
          function(res) {
            alert("错了");
            console.log(res);
          });
      },
      deleteInsuranceType: function(deleteIds) {
        var delFlag = confirm("您确定要下线该险种吗？");
        if (delFlag) {
          var params = {
            "ids": deleteIds
          };
          this.$http.post(protocol + "//" + host + ":" + port + "/insuranceType/deleteInsuranceType.action",
            params, {
              emulateJSON: true
            }).then(
            function(res) {
              var deleteFlag = res.data.data;
              var deleteMsg = res.data.msg;
              if (deleteFlag > 0) {
                alert("该险种已成功下线！");
                console.log(deleteMsg);
                this.getInsuranceTypeList(1, this.page.pageSize);
              } else {
                alert("下线险种失败！");
                console.log(deleteMsg);
              }
            },
            function(res) {
              var deleteMsg = res.data.msg;
              alert("系统正忙，请稍后！");
              console.log(deleteMsg);
            });
        }
      },
      addInsuranceType: function(addInsurance) {
        var addFlag = confirm("您确定要添加该条信息吗？");
        if (addFlag) {
          var _this = this;
          _this.addInsurance = addInsurance;
          var params = {
            /* "insuranceId": _this.addInsurance.insuranceId, */
            "insuranceName": _this.addInsurance.insuranceName,
            "example": _this.addInsurance.example,
            "notice": _this.addInsurance.notice,
            "applicable": _this.addInsurance.applicable,
            "buyPercent": _this.addInsurance.buyPercent,
            "programmeId": _this.addInsurance.programmeId,
          };
          this.$http.post(protocol + "//" + host + ":" + port + "/insuranceType/addInsuranceType.action",
            params, {
              emulateJSON: true
            }).then(
            function(res) {
              var addStatus = res.data.data;
              if (addStatus > 0) {
                alert("险种添加成功！");
                location.href = 'view-insurance-type.html';
              } else {
                alert("添加失败，请检查网络连接！");
              }
            },
            function(res) {
              alert("系统正忙，请稍后！");
              console.log(res);
            });
        }
      },
      getProgrammeList: function() {
        var _this = this;
        this.$http.post(protocol + "//" + host + ":" + port + "/programme/findAllProgramme.action", {
          emulateJSON: true
        }).then(
          function(res) {
            _this.addInsurance.programmeList = res.data.data; //返回的是RetResult
          },
          function(res) {
            alert("读取报价方案列表失败！请联系网站管理员！");
            console.log(res);
          });
      },
      toDetailPage: function(insuranceId) {
        location.href = 'view-insurance-type-details.html?insuranceId=' + insuranceId;
      },
      toUpdatePage: function(insuranceId) {
        location.href = 'modify-insurance-type.html?insuranceId=' + insuranceId;
      },
      exportExcel:function(){
        location.href = protocol+"//"+host+":"+port+"/insuranceType/exportExcel.action?page=0&size=0";
      }
    }
  });


  var insuranceId = "0";
  insuranceId = getInsuranceId();

  var vueInsurance = new Vue({
    el: ".page",
    data: {
      detailInsuranceId: "",
      detailInsurance: {
        insuranceId: "",
        insuranceName: "",
        example: "",
        notice: "",
        applicable: "",
        buyPercent: "",
        programmeId: "",
        viewState: 0
      },
      programmeList: []
    },
    created: function() {
      this.getProgrammeList();
      this.getInsuranceId();
    },
    methods: {
      getInsuranceId: function() {
        var _this = this;
        _this.detailInsuranceId = insuranceId;
        var params = {
          "insuranceId": _this.detailInsuranceId
        };
        _this.$http.post(protocol + "//" + host + ":" + port + "/insuranceType/findInsuranceById.action",
          params, {
            emulateJSON: true
          }).then(
          function(res) {
            var foundFlag = res.data.msg;
            if (foundFlag == 'success') {
              this.detailInsurance = res.data.data;
            } else {
              alert("查询失败！请刷新页面！");
            }
          },
          function(res) {
            alert("系统正忙，请稍后！");
            console.log(res);
          });
      },
      updateInsuranceType: function(detailInsurance) {
        var updateFlag = confirm('您确定提交对该条信息的更新吗?');
        if (updateFlag) {
          var _this = this;
          _this.detailInsurance = detailInsurance;
          var params = {
            "insuranceId": _this.detailInsurance.insuranceId,
            /* "insuranceName": _this.detailInsurance.insuranceName, */
            "example": _this.detailInsurance.example,
            "notice": _this.detailInsurance.notice,
            "applicable": _this.detailInsurance.applicable,
            "buyPercent": _this.detailInsurance.buyPercent,
            "programmeId": _this.detailInsurance.programmeId,
            "viewState": _this.detailInsurance.viewState
          };
          this.$http.post(protocol + "//" + host + ":" + port + "/insuranceType/updateInsuranceType.action",
            params, {
              emulateJSON: true
            }).then(
            function(res) {
              var updateFlag = res.data.data;
              var updateMsg = res.data.msg;
              if (updateFlag > 0) {
                alert("险种信息修改成功！");
                location.href = 'view-insurance-type.html';
                console.log(updateMsg);
              } else {
                alert("修改失败，请检查网络连接！");
                console.log(updateMsg);
              }
            },
            function(res) {
              alert("系统正忙，请稍后！");
              console.log(res);
            });
        }
      },
      getProgrammeList: function() {
        /* var _this = this; */
        this.$http.post(protocol + "//" + host + ":" + port + "/programme/findAllProgramme.action", {
          emulateJSON: true
        }).then(
          function(res) {
            this.programmeList = res.data.data; //返回的是RetResult
          },
          function(res) {
            alert("读取报价方案列表失败！请联系网站管理员！");
            console.log(res);
          });
      }
    }
  });





  /* 该功能已弃用
  //批量删除时获取被选中记录的id数组,并跳转至删除模块
  $("#deleteRecord").click(function() {
    var arr = [];
    $("input[name='ids']:checked").each(function() {
      arr.push($(this).val());
    });
    if (arr.length > 0) {
      insuranceTypeVUE.deleteInsuranceType(arr);
    } else {
      alert("错误 - 013 : 删除失败!未选中任何记录!");
    }
  }); */
});
