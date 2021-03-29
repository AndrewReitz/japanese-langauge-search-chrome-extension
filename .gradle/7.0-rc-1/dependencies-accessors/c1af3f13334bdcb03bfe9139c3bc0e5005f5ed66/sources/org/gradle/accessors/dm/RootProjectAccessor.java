package org.gradle.accessors.dm;

import org.gradle.api.NonNullApi;
import org.gradle.api.artifacts.ProjectDependency;
import org.gradle.api.internal.artifacts.dependencies.ProjectDependencyInternal;
import org.gradle.api.internal.artifacts.DefaultProjectDependencyFactory;
import org.gradle.api.internal.artifacts.dsl.dependencies.ProjectFinder;
import org.gradle.api.internal.catalog.DelegatingProjectDependency;
import org.gradle.api.internal.catalog.TypeSafeProjectDependencyFactory;
import javax.inject.Inject;

@NonNullApi
public class RootProjectAccessor extends TypeSafeProjectDependencyFactory {


    @Inject
    public RootProjectAccessor(DefaultProjectDependencyFactory factory, ProjectFinder finder) {
        super(factory, finder);
    }

    /**
     * Creates a project dependency on the project at path ":"
     */
    public JapaneseLanguageSearchProjectDependency getJapaneseLanguageSearch() { return new JapaneseLanguageSearchProjectDependency(getFactory(), create(":")); }

    /**
     * Creates a project dependency on the project at path ":background"
     */
    public BackgroundProjectDependency getBackground() { return new BackgroundProjectDependency(getFactory(), create(":background")); }

    /**
     * Creates a project dependency on the project at path ":jisho"
     */
    public JishoProjectDependency getJisho() { return new JishoProjectDependency(getFactory(), create(":jisho")); }

}