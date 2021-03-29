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
public class JapaneseLanguageSearchProjectDependency extends DelegatingProjectDependency {

    @Inject
    public JapaneseLanguageSearchProjectDependency(TypeSafeProjectDependencyFactory factory, ProjectDependencyInternal delegate) {
        super(factory, delegate);
    }

    /**
     * Creates a project dependency on the project at path ":background"
     */
    public BackgroundProjectDependency getBackground() { return new BackgroundProjectDependency(getFactory(), create(":background")); }

    /**
     * Creates a project dependency on the project at path ":jisho"
     */
    public JishoProjectDependency getJisho() { return new JishoProjectDependency(getFactory(), create(":jisho")); }

}
