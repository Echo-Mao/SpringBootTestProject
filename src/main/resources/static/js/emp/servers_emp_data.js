var commonData = {
		/* 页码信息数据 */
		page:{
			pageIndex:1,
			pageSize:10,
			totalPage:0,
			totalCount:0,
		},
		/* 全选信息数据 */
		checkData: [],
        allLength:10,
        /* 条件查询数据 */
		demand:{
			empName:"",
			loginId:"",
			age:"",//年龄
			provinceId:-1,//所在地区的省份
			cityId:-1,//所在地区的城市
			phoneNum:"",
			roleId:-1,
			incumbency:-1,
/*  注意	*/		loginEmpId:85  /*  注意	这里是用于测试用的，实际需要从检测登录中获取*/	
		},
		/*获取的员工集合  */
		dataList:[],
		/* 省份、城市集合  用于选择省市 */
		provinceList:[],
		cityList:[],
		/* 职位集合 用于条件查询选择职位*/
		roleList:[],
		/* 要删除的员工id组合的字符串 */
		deleteIds:"",
		/*修改模块的的信息*/
		updateRole:{
			empId:"",
			loginId:-1,
			empName:"",
			roleId:-1
		},
		loginEmp:{},
		flag:1
	}