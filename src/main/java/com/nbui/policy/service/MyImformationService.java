package com.nbui.policy.service;

import com.nbui.entity.UserInfo;
import com.nbui.policy.entity.UserInfoBrief;

public interface MyImformationService {

	void UpdateUserInfo(UserInfo userInfo);

	UserInfoBrief showUserByLoginId(Integer loginId);

}
