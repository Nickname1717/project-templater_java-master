package com.zkh.service.loginService;

import com.zkh.entity.user.Module;

import java.util.List;

public interface ModuleService {
     List<Module> getMenusByUserName(String loginId);

     List<Module> selectModule()throws Exception;

    Integer insertModule(Module module) throws Exception;

    Integer updateModule(Module module)throws Exception;
}
