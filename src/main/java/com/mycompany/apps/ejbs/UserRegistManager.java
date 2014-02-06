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

import com.mycompany.apps.entities.Grouptable;
import com.mycompany.apps.entities.GrouptablePK;
import com.mycompany.apps.entities.Usertable;
import com.mycompany.apps.util.SHA256Encoder;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author stnetadmin
 */
@Stateless
public class UserRegistManager {

    @PersistenceContext
    EntityManager em;

    /**
     * 指定したユーザ、メールアドレス、パスワード、グループ名で DB へ登録
     *
     * @param username
     * @param mailaddress
     * @param password
     * @param groupname
     */
    public void createUserAndGroup(String username, String mailaddress, String password, String groupname) {
        Usertable user = new Usertable();
        user.setUsername(username);
        user.setMailaddress(mailaddress);

        SHA256Encoder encoder = new SHA256Encoder();
        String encodedPassword = encoder.encodePassword(password);
        user.setPassword(encodedPassword);

        Grouptable group = new Grouptable();
        group.setGrouptablePK(new GrouptablePK(username, groupname));
        group.setUsertable(user);

        em.persist(user);
        em.persist(group);
    }

    /**
     * DB から指定したユーザの削除
     *
     * @param username
     */
    public void removeUser(String username) {
        Usertable user = em.find(Usertable.class, username);
        em.remove(user);
    }

    /**
     * DB から指定したユーザの検索
     *
     * @param username
     * @return Usertable
     */
    public Usertable findUser(String username) {
        Usertable user = em.find(Usertable.class, username);
        return user;
    }
}
