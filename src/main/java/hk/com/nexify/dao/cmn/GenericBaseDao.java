/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hk.com.nexify.dao.cmn;

import java.io.Serializable;

import com.googlecode.genericdao.dao.hibernate.GenericDAO;

import hk.com.nexify.entity.cmn.pojo.NafFilter;
import hk.com.nexify.entity.cmn.pojo.NafPageList;
import hk.com.nexify.entity.cmn.pojo.NafPaging;

/**
 *
 * @author Administrator
 * @param <T>
 * @param <ID>
 */
public interface GenericBaseDao<T extends Object, ID extends Serializable> extends GenericDAO<T, ID> {

    public NafPageList<T> searchPageList(NafPaging pageInfo, NafFilter filter);
}
