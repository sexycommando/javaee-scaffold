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
package com.mycompany.apps.ejbs;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;

/**
 * ロールチェック用クラス
 *
 * @author stnetadmin
 */
@Stateless
public class RoleCheckLogic {

    @RolesAllowed("admin")
    public String executableByAdmin() {
        return "管理者による実行が可能なロジック";
    }

    @RolesAllowed("user")
    public String executableByUser() {
        return "ユーザによる実行が可能なロジック";
    }

}
