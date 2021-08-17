package hk.com.nexify.dao.cmn.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.googlecode.genericdao.dao.hibernate.GenericDAOImpl;
import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;

import hk.com.nexify.cmn.util.CmnUtils;
import hk.com.nexify.dao.cmn.util.FilterInfoUtils;
import hk.com.nexify.dao.cmn.util.PageInfoUtils;
import hk.com.nexify.entity.cmn.pojo.NafFilter;
import hk.com.nexify.entity.cmn.pojo.NafPageList;
import hk.com.nexify.entity.cmn.pojo.NafPaging;

public abstract class GenericBaseDaoImpl<T, ID extends Serializable> extends GenericDAOImpl<T, ID> {

	private static final Logger log = LoggerFactory.getLogger(GenericBaseDaoImpl.class);

	public Session getSession() {
		return super.getSession();
	}

	@Autowired
	@Override
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	public NafPageList<T> searchPageList(NafPaging pageInfo, NafFilter filter) {

		Search search = new Search(persistentClass);

		if (filter != null) {
			search.addFilter(FilterInfoUtils.toGenericDaoFilter(filter));
		}

		NafPageList<T> list = searchPageListResult(search, pageInfo);

		return list;
	}

	protected NafPageList<T> searchPageListResult(Search search, NafPaging pageInfo) {
		NafPageList<T> pageList = new NafPageList<T>();

		try {
			if (pageInfo != null) {
				PageInfoUtils.setupPageSearch(search, pageInfo);

				SearchResult<T> searchResult = this.searchAndCount(search);
				pageList.setTotalRecordNo(searchResult.getTotalCount());
				pageList.setResultList(searchResult.getResult());
				pageList.setPaging(pageInfo);
			} else {
				List<T> results = search(search);
				pageList.setResultList(results);
				pageList.setTotalRecordNo(results.size());
			}
		} catch (Exception e) {
			log.warn(e.getMessage(), e);
		}

		return pageList;
	}

	protected NafPageList<T> doSearch(NafPaging nafPaging, NafFilter nafFilter) {
		NafPageList<T> nafPage = new NafPageList<T>();

		Search search = new Search();
		search.addFilter(FilterInfoUtils.toGenericDaoFilter(nafFilter));

		if (nafPaging != null) {
			nafPage.setPaging(nafPaging);

			if (nafPaging.getPageNo() > 0) {
				// Minus 1 for zero-based offset
				search.setPage(nafPaging.getPageNo() - 1);
			}

			if (nafPaging.getPageSize() > -1) {
				search.setMaxResults(nafPaging.getPageSize());
			}

			if (CmnUtils.isNotEmpty(nafPaging.getSortBy())) {
				search.addSort(nafPaging.getSortBy(), nafPaging.isSortDesc());
			}
		}

		SearchResult<T> searchResult = searchAndCount(search);
		nafPage.setTotalRecordNo(searchResult.getTotalCount());
		nafPage.setResultList(searchResult.getResult());

		return nafPage;
	}

	/*
	 * protected Class getEntityClass() {
	 * 
	 * Class<T> entityClass = (Class<T>) ((ParameterizedType)
	 * getClass().getGenericSuperclass()) .getActualTypeArguments()[0];
	 * 
	 * log.info("getEntityClass :{}, persistentClass :{}", entityClass,
	 * persistentClass); return entityClass; }
	 */
}
