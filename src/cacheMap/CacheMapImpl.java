package cacheMap;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Yury Khodanitcky
 * @see CacheMapTest - a JUnit test for this class
 */
public class CacheMapImpl<K,V> implements CacheMap<K,V> {

    /**
     * The default lifetime of key-value mappings.
     */
    final long DEFAULT_LIFETIME = 1000;
    private long lifeTime;
    private final Map<K, V> map = new HashMap<>();
    private final Map<K, Long> expiredQuery = new HashMap<>();

    /**
     * Constructs an empty <tt>CacheMapImpl</tt> with the default initial
     * lifetime of key-value mappings.
     */
    public CacheMapImpl() {
        lifeTime = DEFAULT_LIFETIME;
    }

    /**
     * Constructs an empty <tt>CacheMapImpl</tt> with the specified initial
     * lifetime of key-value mappings.
     *
     * @param lifeTime the initial lifetime of key-value mappings.
     */
    public CacheMapImpl(long lifeTime) {
        this.lifeTime = lifeTime;
    }

    /**
     * Set lifetime of key-value mappings.
     *
     * @param lifeTime the lifetime of key-value mappings.
     */
    public void setLifeTime(long lifeTime) {
        this.lifeTime = lifeTime;
    }

    /**
     * Returns lifetime of key-value mappings.
     *
     * @return lifetime of key-value mappings.
     */
    public long getLifeTime() {
        return lifeTime;
    }

    /**
     * Associates the specified value with the specified key in this map.
     * If the map previously contained a mapping for the key, the old
     * value is replaced.
     *
     * @param key key with which the specified value is to be associated
     * @param value value to be associated with the specified key
     * @return the previous value associated with <tt>key</tt>, or
     *         <tt>null</tt> if there was no mapping for <tt>key</tt>.
     *         (A <tt>null</tt> return can also indicate that the map
     *         previously associated <tt>null</tt> with <tt>key</tt>.)
     */
    @Override
    public V put(K key, V value) {
        if (key == null) return null;
        if (value == null) {
            expiredQuery.remove(key);
            map.remove(key);
        }
        if (expiredQuery.get(key) == null) {
            expiredQuery.put(key, System.currentTimeMillis());
        }
        map.put(key, value);
        clearExpired();
        return map.get(key);
    }

    /**
     * Removes all expired mappings from this map.
     */
    @Override
    public void clearExpired() {
        for (Entry<K, Long> entry : expiredQuery.entrySet()) {
            Long aliveTime = expiredQuery.get(entry.getKey());
            if (aliveTime != null && aliveTime < System.currentTimeMillis() - lifeTime) {
                map.remove(entry.getKey());
            }
        }
    }

    /**
     * Removes all of the mappings from this map.
     * The map will be empty after this call returns.
     */
    @Override
    public void clear() {
        map.clear();
        expiredQuery.clear();
    }

    /**
     * Returns a {@link Set} view of the keys contained in this map.
     * The set is backed by the map, so changes to the map are
     * reflected in the set, and vice-versa.  If the map is modified
     * while an iteration over the set is in progress (except through
     * the iterator's own <tt>remove</tt> operation), the results of
     * the iteration are undefined.  The set supports element removal,
     * which removes the corresponding mapping from the map, via the
     * <tt>Iterator.remove</tt>, <tt>Set.remove</tt>,
     * <tt>removeAll</tt>, <tt>retainAll</tt>, and <tt>clear</tt>
     * operations.  It does not support the <tt>add</tt> or <tt>addAll</tt>
     * operations.
     *
     * @return a set view of the keys contained in this map
     */
    @Override
    public Set<K> keySet() {
        clearExpired();
        Set<K> keySet = map.keySet();
        return keySet;
    }

    /**
     * Returns a {@link Collection} view of the values contained in this map.
     * The collection is backed by the map, so changes to the map are
     * reflected in the collection, and vice-versa.  If the map is
     * modified while an iteration over the collection is in progress
     * (except through the iterator's own <tt>remove</tt> operation),
     * the results of the iteration are undefined.  The collection
     * supports element removal, which removes the corresponding
     * mapping from the map, via the <tt>Iterator.remove</tt>,
     * <tt>Collection.remove</tt>, <tt>removeAll</tt>,
     * <tt>retainAll</tt> and <tt>clear</tt> operations.  It does not
     * support the <tt>add</tt> or <tt>addAll</tt> operations.
     *
     * @return a view of the values contained in this map
     */
    @Override
    public Collection<V> values() {
        clearExpired();
        Collection<V> values = map.values();
        return values;
    }

    /**
     * Returns a {@link Set} view of the mappings contained in this map.
     * The set is backed by the map, so changes to the map are
     * reflected in the set, and vice-versa.  If the map is modified
     * while an iteration over the set is in progress (except through
     * the iterator's own <tt>remove</tt> operation, or through the
     * <tt>setValue</tt> operation on a map entry returned by the
     * iterator) the results of the iteration are undefined.  The set
     * supports element removal, which removes the corresponding
     * mapping from the map, via the <tt>Iterator.remove</tt>,
     * <tt>Set.remove</tt>, <tt>removeAll</tt>, <tt>retainAll</tt> and
     * <tt>clear</tt> operations.  It does not support the
     * <tt>add</tt> or <tt>addAll</tt> operations.
     *
     * @return a set view of the mappings contained in this map
     */
    @Override
    public Set<Entry<K, V>> entrySet() {
        clearExpired();
        return map.entrySet();
    }

    /**
     * Returns <tt>true</tt> if this map contains a mapping for the
     * specified key.
     *
     * @param   key   The key whose presence in this map is to be tested
     * @return <tt>true</tt> if this map contains a mapping for the specified
     * key.
     */
    @Override
    public boolean containsKey(Object key) {
        clearExpired();
        return map.containsKey(key);
    }

    /**
     * Returns <tt>true</tt> if this map maps one or more keys to the
     * specified value.
     *
     * @param value value whose presence in this map is to be tested
     * @return <tt>true</tt> if this map maps one or more keys to the
     *         specified value
     */
    @Override
    public boolean containsValue(Object value) {
        clearExpired();
        return map.containsValue(value);
    }

    /**
     * Returns the value to which the specified key is mapped,
     * or {@code null} if this map contains no mapping for the key.
     */
    @Override
    public V get(Object key) {
        clearExpired();
        return map.get(key);
    }

    /**
     * Returns <tt>true</tt> if this map contains no key-value mappings.
     *
     * @return <tt>true</tt> if this map contains no key-value mappings.
     */
    @Override
    public boolean isEmpty() {
        clearExpired();
        return map.isEmpty();
    }

    /**
     * Removes the mapping for the specified key from this map if present.
     *
     * @param  key key whose mapping is to be removed from the map
     * @return the previous value associated with <tt>key</tt>, or
     *         <tt>null</tt> if there was no mapping for <tt>key</tt>.
     *         (A <tt>null</tt> return can also indicate that the map
     *         previously associated <tt>null</tt> with <tt>key</tt>.)
     */
    @Override
    public V remove(Object key) {
        expiredQuery.remove(key);
        return map.remove(key);
    }

    /**
     * Copies all of the mappings from the specified map to this map.
     * These mappings will replace any mappings that this map had for
     * any of the keys currently in the specified map.
     *
     * @param m mappings to be stored in this map
     */
    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        for (Entry<? extends K, ? extends V> entry : m.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    /**
     * Returns the number of key-value mappings in this map.
     *
     * @return the number of key-value mappings in this map
     */
    @Override
    public int size() {
        clearExpired();
        return map.size();
    }
}