package hk.com.nexify.cmn.util;

/**
 * For a method parameter that would be part of the key of a cache, the parameter class should implement this interface.
 * 
 * For example: If class ParameterA implements NafCachableParam, toString() value of instances of ParameterA will then be part of cache key.
 *
 */
public interface NafCachableParam {

}
