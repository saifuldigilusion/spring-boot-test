package hk.com.nexify.cmn.mapper;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import hk.com.nexify.entity.cmn.NafCmnEntity;
import hk.com.nexify.entity.cmn.pojo.NafPageList;

public final class MapperUtils {

	private static final Logger log = LogManager.getLogger(MapperUtils.class);
	private static final ObjectMapper mapper = new ObjectMapper()
			.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
			.setSerializationInclusion(Include.NON_NULL);
	private static final Object EMPTY_JSON = mapper.createObjectNode();
	
	public static <RT,T> List<RT> convertToModelList(List<T> objectList, Class<RT> classType){
		List<RT> res= new ArrayList<RT>();
		for (Object object:objectList) {
			res.add((RT) convertToModel(object,classType));
		}
		return res;
	}
	
	public static <RT,T> RT convertToModel(T object, Class<RT> classType){
		if (object==null) {
			return null;
		}
		return mapper.convertValue(object, classType);
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T getEmptyJsonObject() {
		return (T) EMPTY_JSON;
	}
	

	public static <RT,T extends NafCmnEntity> NafPageList<RT> convertSearchModel(NafPageList<T> nafPageList,Class<RT> classType) {
		NafPageList<RT> res = new NafPageList<RT>();
		res.setPaging(nafPageList.getPaging());
		res.setTotalRecordNo(nafPageList.getTotalRecordNo());
		List<RT> resultList = MapperUtils.convertToModelList(nafPageList.getResultList(), classType);
		res.setResultList(resultList);
		return res;
	}
	
	public static <RT> RT readJsonFromBytes(byte[] bytes, Class<RT> targetClass) throws Exception  {
		return (RT) mapper.readValue(bytes, targetClass);
	}
	
	public static String writeAsString(Object obj) throws JsonProcessingException {
		return mapper.writeValueAsString(obj);
	}
	
	public static ObjectMapper getObjectMapper() {
		return mapper;
	}
	
}
