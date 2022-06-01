package com.example.testlogproject.business.abstracts;

import javax.servlet.http.HttpServletRequest;

public interface RequestService {

    String getClientIp(HttpServletRequest request);

}
