package org.gradle.accessors.dm;

import org.gradle.api.NonNullApi;
import org.gradle.api.artifacts.MinimalExternalModuleDependency;
import org.gradle.api.artifacts.ExternalModuleDependencyBundle;
import org.gradle.api.artifacts.MutableVersionConstraint;
import org.gradle.api.provider.Provider;
import org.gradle.api.provider.ProviderFactory;
import org.gradle.api.internal.catalog.AbstractExternalDependencyFactory;
import org.gradle.api.internal.catalog.DefaultVersionCatalog;
import java.util.Map;
import javax.inject.Inject;

/**
 * A catalog of dependencies accessible via the `libs` extension.
*/
@NonNullApi
public class LibrariesForLibs extends AbstractExternalDependencyFactory {

    private final LibrariesForLibsVersions versions;
    private final LibrariesForLibsBundles bundles;

    @Inject
    public LibrariesForLibs(DefaultVersionCatalog config, ProviderFactory providers) {
        super(config, providers);
        this.versions = new LibrariesForLibsVersions(providers, config);
        this.bundles = new LibrariesForLibsBundles(providers, config);
    }

        /**
         * Creates a dependency provider for coroutines (org.jetbrains.kotlinx:kotlinx-coroutines-core-js)
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getCoroutines() { return create("coroutines"); }

        /**
         * Returns the available bundles for this model.
         */
        public LibrariesForLibsBundles getBundles() { return bundles; }

    public static class LibrariesForLibsBundles extends BundleFactory {

        public LibrariesForLibsBundles(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

    }

        /**
         * Returns the available versions for this model.
         */
        public LibrariesForLibsVersions getVersions() { return versions; }

    public static class LibrariesForLibsVersions extends VersionFactory {

        public LibrariesForLibsVersions(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Returns the version associated to this alias: kotlin
         * If the version is a rich version and that its not expressable as a
         * single version string, then an empty string is returned.
        * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getKotlin() { return getVersion("kotlin"); }

    }

}
