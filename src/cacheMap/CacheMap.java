package cacheMap;

import java.util.Map;

/**
 * @author Yury Khodanitcky
 */
public interface CacheMap <K,V> extends Map<K,V> {

    /**
     * Set lifetime of key-value mappings.
     *
     * @param lifeTime the lifetime of key-value mappings.
     */
    void setLifeTime(long lifeTime);

    /**
     * Returns lifetime of key-value mappings.
     *
     * @return lifetime of key-value mappings.
     */
    long getLifeTime();

    /**
     * Removes all expired mappings from this map.
     */
    void clearExpired();
}
