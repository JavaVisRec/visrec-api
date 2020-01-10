package javax.visrec.spi;

import java.util.*;

/*
 * Special thanks to Werner Keil and Martin Desruisseaux for the proven design of a service provider.
 * The ServiceProvider of JSR381 is heavily inspired by JSR 363/385.
 */

/**
 * The ServiceProvider is the centralized provider to provide API scoped services.
 *
 * @author Werner Keil
 * @author Martin Desruisseaux
 * @author Kevin Berendsen
 * @since 1.0
 */
public abstract class ServiceProvider {

    /** The lock to populate and mutate the providers list. */
    private static final Object LOCK = new Object();

    private static List<ServiceProvider> providers;

    protected ServiceProvider() {
        // Prevent instantiation, only allowed by subclasses.
    }

    /**
     * If multiple implementations of the {@link ServiceProvider} are found on the classpath, then the
     * {@link ServiceProvider} with the highest value of priority will be used by default.
     * @return The priority (default = 0)
     */
    public int getPriority() {
        return 0;
    }

    /**
     * Get the {@link BuilderService}
     * @return builder service.
     * @Deprecated for removal
     */
    @Deprecated
    public abstract BuilderService getBuilderService();

    /**
     * Get the {@link ClassifierCreatorService}
     * @return classifier creator service
     */
    public ClassifierCreatorService getClassifierCreatorService() {
        return ClassifierCreatorService.getInstance();
    }

    /**
     * Get the {@link ImageFactoryService}
     * @return image factory service.
     */
    public abstract ImageFactoryService getImageFactoryService();

    /**
     * Get the {@link ImplementationService}
     * @return implementation service.
     */
    public abstract ImplementationService getImplementationService();

    /**
     * Get the current {@link ServiceProvider}
     * @return service provider.
     * @throws IllegalStateException If there are no service providers found.
     */
    public static ServiceProvider current() {
        if (available().size() == 0) {
            throw new IllegalStateException("No service provider found");
        }
        return available().get(0);
    }

    /**
     * Set the current {@link ServiceProvider}
     * @param provider The {@link ServiceProvider} to be set as current.
     * @throws IllegalStateException If there are no service providers found.
     * @throws IllegalArgumentException If the {@link ServiceProvider} given by the parameters is not known
     * in the existing list of providers.
     */
    public static void setCurrent(final ServiceProvider provider) {
        Objects.requireNonNull(provider);

        synchronized (LOCK) {
            final List<ServiceProvider> foundProviders = available();
            if (foundProviders.isEmpty()) {
                throw new IllegalStateException("No providers found.");
            }
            if (!foundProviders.contains(provider)) {
                throw new IllegalArgumentException("ServiceProvider given through the parameters is not known.");
            }

            // Copying list, removes the provider from the arguments and prepends it upfront
            // on the copied list.
            final ArrayList<ServiceProvider> copiedProviders = new ArrayList<>();
            Collections.copy(copiedProviders, foundProviders);
            copiedProviders.remove(provider);
            copiedProviders.add(0, provider);
            copiedProviders.trimToSize();

            // Make the list unmodifiable to prevent illegal modification
            providers = Collections.unmodifiableList(copiedProviders);
        }
    }

    /**
     * Gets a list of all available {@link ServiceProvider}s.
     * @return service providers.
     */
    public static List<ServiceProvider> available() {
        if (Objects.isNull(providers)) {
            synchronized(LOCK) {
                if (Objects.nonNull(providers)) {
                    return providers;
                }

                // Searches for implementations of the ServiceProvider using the ServiceLoader API
                final ServiceLoader<ServiceProvider> serviceLoader = ServiceLoader.load(ServiceProvider.class);
                final ArrayList<ServiceProvider> localProviders = new ArrayList<>();
                for (ServiceProvider provider : serviceLoader) {
                    localProviders.add(provider);
                }
                localProviders.trimToSize();

                // Sort the list based on priority. Highest priority comes first.
                localProviders.sort((p1, p2) -> p2.getPriority() - p1.getPriority());

                // Make the list unmodifiable to prevent illegal modification
                providers = Collections.unmodifiableList(localProviders);
            }
        }
        return providers;
    }

}
