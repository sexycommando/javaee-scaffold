/*
 * Copyright 2014 stnetadmin.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.mycompany.apps.cdis.login;

import com.mycompany.apps.ejbs.RoleCheckLogic;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 * ホームページの ManagedBean クラス
 *
 * @author stnetadmin
 */
@Named(value = "homePage")
@SessionScoped
public class HomePage implements Serializable {

    /**
     * Creates a new instance of HomePage
     */
    public HomePage() {
    }

    @EJB
    RoleCheckLogic roleCheckLogic;

    private String roleChekerString;

    /**
     * ログインユーザのロールをチェックします。
     *
     * @return ロールを表す文字列
     */
    public String getRoleChekerString() {
        if (isUserInRole("admin")) {
            String adminRoleString = roleCheckLogic.executableByAdmin();
            roleChekerString = adminRoleString;
        }
        if (isUserInRole("user")) {
            String userRoleString = roleCheckLogic.executableByUser();
            roleChekerString = userRoleString;
        }
        return roleChekerString;
    }

    /**
     * ログインしたユーザが、引数で指定した役割（ロール）を持つユーザか否かを検証します。
     *
     * @param role ロール
     * @return 指定されたロールを持っているとき true, そうでないとき false
     */
    public boolean isUserInRole(String role) {
        return FacesContext.getCurrentInstance().getExternalContext().isUserInRole(role);
    }
}
