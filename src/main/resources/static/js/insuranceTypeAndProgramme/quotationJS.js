function getProgrammeId() {
  var name = $.query.get("programmeId");
  return name;
}

$(function() {
  var protocol = window.location.protocol;
  var host = window.location.hostname;
  var port = window.location.port;

  var programmeTypeVUE = new Vue({
    el: "#programme",
    created: function() {
      //1判断登录以及页面跳转并且拿登录用户Id？
      this.judgeLogin();
      //2查询第一页数据
      this.getProgrammeList(1, this.page.pageSize);
    },
    data: {
      /* 页码信息数据 */
      page: {
        pageIndex: 1,
        pageSize: 10,
        totalPage: 0,
        totalCount: 0
      },
      /* 获取的险种集合 */
      dataList: [],
      /* 条件查询数据 */
      condition: {
        qProgrammeId: '', //报价方案id
        qPercent: '', //百分比
        qBasicsMoney: '' //基础保费
      },
      /* 添加的报价信息 */
      addProgramme: {
        programmeId: '', //报价方案id
        percent: '', //百分比
        basicsMoney: '' //基础保费
      }
    },
    methods: {
      getProgrammeList: function(pageIndex, pageSize) {
        var _this = this;
        _this.page.pageIndex = pageIndex;
        _this.page.pageSize = pageSize;
        var params = {
          "page": _this.page.pageIndex,
          "size": _this.page.pageSize,
          "qProgrammeId": _this.condition.qProgrammeId,
          "qPercent": _this.condition.qPercent,
          "qBasicsMoney": _this.condition.qBasicsMoney
        };
        this.$http.post(protocol + "//" + host + ":" + port + "/programme/programmeList.action", params, {
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
              this.demand.loginEmpId = this.loginEmp.empId;
              //注意：这里由于使用两个ajax会异步进行，所以要使其有先后顺序，在这里调用
              this.getProgrammeList(1, this.page.pageSize);
            }
          },
          function(res) {
            alert("错了");
            console.log(res);
          });
      },
      addProgrammeInfo: function(addProgramme) {
        var addFlag = confirm("您确定要添加该条信息吗？");
        if (addFlag) {
          var _this = this;
          _this.addProgramme = addProgramme;
          var params = {
            "programmeId": _this.addProgramme.programmeId,
            "percent": _this.addProgramme.percent,
            "basicsMoney": _this.addProgramme.basicsMoney
          };
          this.$http.post(protocol + "//" + host + ":" + port + "/programme/addProgramme.action", params, {
            emulateJSON: true
          }).then(
            function(res) {
              var addStatus = res.data.data;
              if (addStatus > 0) {
                alert("报价方案添加成功！");
                location.href = 'view-quotation-plan.html';
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
      toUpdatePage: function(programmeId) {
        location.href = 'modify-quotation-plan.html?programmeId=' + programmeId;
      },
      exportExcel:function(){
        location.href = protocol+"//"+host+":"+port+"/programme/exportExcel.action?page=0&size=0";
      }
    }
  });


  var programmeId = "0";
  programmeId = getProgrammeId();
  var vueProgramme = new Vue({
    el: ".programme",
    data: {
      updateProgrammeId: 0,
      updateProgramme: {
        programmeId: 0,
        percent: 0,
        basicsMoney: 0.0
      }
    },
    created: function() {
      this.getProgrammeId();
    },
    methods: {
      getProgrammeId: function() {
        var _this = this;
        _this.updateProgrammeId = programmeId;
        var params = {
          "programmeId": _this.updateProgrammeId
        };
        _this.$http.post(protocol + "//" + host + ":" + port + "/programme/toUpdate.action", params, {
          emulateJSON: true
        }).then(
          function(res) {
            var foundFlag = res.data.msg;
            if (foundFlag == 'success') {
              this.updateProgramme = res.data.data;
            } else {
              alert("查询失败！请刷新页面！");
            }
          },
          function(res) {
            alert("系统正忙，请稍后！");
            console.log(res);
          });
      },
      updateProgrammeInfo: function(updateProgramme) {
        var updateFlag = confirm('您确定提交对该条信息的更新吗?');
        if (updateFlag) {
          var _this = this;
          _this.updateProgramme = updateProgramme;
          var params = {
            "programmeId": _this.updateProgramme.programmeId,
            "percent": _this.updateProgramme.percent,
            "basicsMoney": _this.updateProgramme.basicsMoney
          };
          this.$http.post(protocol + "//" + host + ":" + port + "/programme/updateProgramme.action", params, {
            emulateJSON: true
          }).then(
            function(res) {
              var updateFlag = res.data.data;
              var updateMsg = res.data.msg;
              if (updateFlag > 0) {
                alert("报价信息修改成功！");
                location.href = 'view-quotation-plan.html';
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
      }
    }
  });
});
