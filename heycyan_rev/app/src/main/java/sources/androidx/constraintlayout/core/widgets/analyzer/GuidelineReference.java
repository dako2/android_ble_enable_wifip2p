package androidx.constraintlayout.core.widgets.analyzer;

import androidx.constraintlayout.core.widgets.ConstraintWidget;
import androidx.constraintlayout.core.widgets.Guideline;

/* loaded from: classes.dex */
class GuidelineReference extends WidgetRun {
    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    boolean supportsWrapComputation() {
        return false;
    }

    GuidelineReference(ConstraintWidget constraintWidget) {
        super(constraintWidget);
        constraintWidget.mHorizontalRun.clear();
        constraintWidget.mVerticalRun.clear();
        this.orientation = ((Guideline) constraintWidget).getOrientation();
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    void clear() {
        this.start.clear();
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    void reset() {
        this.start.resolved = false;
        this.end.resolved = false;
    }

    private void addDependency(DependencyNode dependencyNode) {
        this.start.mDependencies.add(dependencyNode);
        dependencyNode.mTargets.add(this.start);
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun, androidx.constraintlayout.core.widgets.analyzer.Dependency
    public void update(Dependency dependency) {
        if (this.start.readyToSolve && !this.start.resolved) {
            this.start.resolve((int) ((this.start.mTargets.get(0).value * ((Guideline) this.mWidget).getRelativePercent()) + 0.5f));
        }
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    void apply() {
        Guideline guideline = (Guideline) this.mWidget;
        int relativeBegin = guideline.getRelativeBegin();
        int relativeEnd = guideline.getRelativeEnd();
        guideline.getRelativePercent();
        if (guideline.getOrientation() == 1) {
            if (relativeBegin != -1) {
                this.start.mTargets.add(this.mWidget.mParent.mHorizontalRun.start);
                this.mWidget.mParent.mHorizontalRun.start.mDependencies.add(this.start);
                this.start.mMargin = relativeBegin;
            } else if (relativeEnd != -1) {
                this.start.mTargets.add(this.mWidget.mParent.mHorizontalRun.end);
                this.mWidget.mParent.mHorizontalRun.end.mDependencies.add(this.start);
                this.start.mMargin = -relativeEnd;
            } else {
                this.start.delegateToWidgetRun = true;
                this.start.mTargets.add(this.mWidget.mParent.mHorizontalRun.end);
                this.mWidget.mParent.mHorizontalRun.end.mDependencies.add(this.start);
            }
            addDependency(this.mWidget.mHorizontalRun.start);
            addDependency(this.mWidget.mHorizontalRun.end);
            return;
        }
        if (relativeBegin != -1) {
            this.start.mTargets.add(this.mWidget.mParent.mVerticalRun.start);
            this.mWidget.mParent.mVerticalRun.start.mDependencies.add(this.start);
            this.start.mMargin = relativeBegin;
        } else if (relativeEnd != -1) {
            this.start.mTargets.add(this.mWidget.mParent.mVerticalRun.end);
            this.mWidget.mParent.mVerticalRun.end.mDependencies.add(this.start);
            this.start.mMargin = -relativeEnd;
        } else {
            this.start.delegateToWidgetRun = true;
            this.start.mTargets.add(this.mWidget.mParent.mVerticalRun.end);
            this.mWidget.mParent.mVerticalRun.end.mDependencies.add(this.start);
        }
        addDependency(this.mWidget.mVerticalRun.start);
        addDependency(this.mWidget.mVerticalRun.end);
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    public void applyToWidget() {
        if (((Guideline) this.mWidget).getOrientation() == 1) {
            this.mWidget.setX(this.start.value);
        } else {
            this.mWidget.setY(this.start.value);
        }
    }
}
