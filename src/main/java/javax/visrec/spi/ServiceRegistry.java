/*
 * Copyright 2019 Zoran.
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
package javax.visrec.spi;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Registry for all ML algorithms provided by this implementation.
 * 
 * @author Zoran Sevarac
 */
public class ServiceRegistry {
    
    private final Map<Class<?>, Object> providers = new HashMap();

    public <T> void registerServiceProvider(Class<T> service, T provider) {
        providers.put(service, provider);
    }

    public <T> void deregisterServiceProvider(Class<T> service, T provider) {
        providers.remove(service, provider);
    }

    public Iterator<?> getAllProviders() {
        return providers.values().iterator();
    }

    public <T> T getProvider(Class<T> service) {
        return service.cast(providers.get(service));
    }
}
