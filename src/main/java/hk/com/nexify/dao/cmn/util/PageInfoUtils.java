package hk.com.nexify.dao.cmn.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.googlecode.genericdao.search.Search;

import hk.com.nexify.cmn.util.CmnUtils;
import hk.com.nexify.entity.cmn.pojo.NafPaging;

public final class PageInfoUtils {

	private static final Logger log = LoggerFactory.getLogger(PageInfoUtils.class);

	public static int getFirstResult(NafPaging pi) {
		if (pi == null || pi.getPageNo() == 0 || pi.getPageNo() == 1) {
			return 0;
		}

		return pi.getPageSize() * (pi.getPageNo() - 1);
	}

	public static Search setupPageSearch(Search search, NafPaging pageInfo) {
		// removed, search not working
		// search.setPage(pageInfo.getPageNo());
		search.setFirstResult(PageInfoUtils.getFirstResult(pageInfo));

		if (CmnUtils.isNotEmpty(pageInfo.getSortBy())) {
			search.addSort(pageInfo.getSortBy(), pageInfo.isSortDesc());

			// to support sort with distinct
			if (pageInfo.getSortBy().contains(".")) {
				String mem = pageInfo.getSortBy().substring(0, pageInfo.getSortBy().lastIndexOf("."));
				log.info("setupPageSearch addFetch:{}", mem);
				search.addFetch(mem);

			}
		}

		search.setMaxResults(pageInfo.getPageSize());

		return search;
	}
}
