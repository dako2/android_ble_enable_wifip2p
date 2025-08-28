package androidx.core.animation;

import android.os.Looper;
import android.util.AndroidRuntimeException;
import android.util.Log;
import androidx.collection.SimpleArrayMap;
import androidx.core.animation.AnimationHandler;
import androidx.core.animation.Animator;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.cli.HelpFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes.dex */
public final class AnimatorSet extends Animator implements AnimationHandler.AnimationFrameCallback {
    private static final Comparator<AnimationEvent> EVENT_COMPARATOR = new Comparator<AnimationEvent>() { // from class: androidx.core.animation.AnimatorSet.3
        @Override // java.util.Comparator
        public int compare(AnimationEvent animationEvent, AnimationEvent animationEvent2) {
            long time = animationEvent.getTime();
            long time2 = animationEvent2.getTime();
            if (time == time2) {
                if (animationEvent2.mEvent + animationEvent.mEvent == 1) {
                    return animationEvent.mEvent - animationEvent2.mEvent;
                }
                return animationEvent2.mEvent - animationEvent.mEvent;
            }
            if (time2 == -1) {
                return -1;
            }
            return (time != -1 && time - time2 <= 0) ? -1 : 1;
        }
    };
    private static final String TAG = "AnimatorSet";
    private ArrayList<Node> mPlayingSet = new ArrayList<>();
    SimpleArrayMap<Animator, Node> mNodeMap = new SimpleArrayMap<>();
    private ArrayList<AnimationEvent> mEvents = new ArrayList<>();
    private ArrayList<Node> mNodes = new ArrayList<>();
    boolean mDependencyDirty = false;
    private boolean mStarted = false;
    long mStartDelay = 0;
    private ValueAnimator mDelayAnim = ValueAnimator.ofFloat(0.0f, 1.0f).setDuration(0L);
    private Node mRootNode = new Node(this.mDelayAnim);
    private long mDuration = -1;
    private Interpolator mInterpolator = null;
    private long mTotalDuration = 0;
    private long mLastFrameTime = -1;
    private long mFirstFrame = -1;
    private int mLastEventId = -1;
    boolean mReversing = false;
    private boolean mSelfPulse = true;
    private SeekState mSeekState = new SeekState();
    private boolean mChildrenInitialized = false;
    private long mPauseTime = -1;
    private AnimatorListenerAdapter mNoOpListener = new AnimatorListenerAdapter() { // from class: androidx.core.animation.AnimatorSet.1
        @Override // androidx.core.animation.AnimatorListenerAdapter, androidx.core.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            if (AnimatorSet.this.mNodeMap.get(animator) == null) {
                throw new AndroidRuntimeException("Error: animation ended is not in the node map");
            }
            AnimatorSet.this.mNodeMap.get(animator).mEnded = true;
        }
    };

    public AnimatorSet() {
        this.mNodeMap.put(this.mDelayAnim, this.mRootNode);
        this.mNodes.add(this.mRootNode);
    }

    public void playTogether(Animator... animatorArr) {
        if (animatorArr != null) {
            Builder builderPlay = play(animatorArr[0]);
            for (int i = 1; i < animatorArr.length; i++) {
                builderPlay.with(animatorArr[i]);
            }
        }
    }

    public void playTogether(Collection<Animator> collection) {
        if (collection == null || collection.size() <= 0) {
            return;
        }
        Builder builderPlay = null;
        for (Animator animator : collection) {
            if (builderPlay == null) {
                builderPlay = play(animator);
            } else {
                builderPlay.with(animator);
            }
        }
    }

    public void playSequentially(Animator... animatorArr) {
        if (animatorArr != null) {
            int i = 0;
            if (animatorArr.length == 1) {
                play(animatorArr[0]);
                return;
            }
            while (i < animatorArr.length - 1) {
                Builder builderPlay = play(animatorArr[i]);
                i++;
                builderPlay.before(animatorArr[i]);
            }
        }
    }

    public void playSequentially(List<Animator> list) {
        if (list == null || list.size() <= 0) {
            return;
        }
        int i = 0;
        if (list.size() == 1) {
            play(list.get(0));
            return;
        }
        while (i < list.size() - 1) {
            Builder builderPlay = play(list.get(i));
            i++;
            builderPlay.before(list.get(i));
        }
    }

    public ArrayList<Animator> getChildAnimations() {
        ArrayList<Animator> arrayList = new ArrayList<>();
        int size = this.mNodes.size();
        for (int i = 0; i < size; i++) {
            Node node = this.mNodes.get(i);
            if (node != this.mRootNode) {
                arrayList.add(node.mAnimation);
            }
        }
        return arrayList;
    }

    @Override // androidx.core.animation.Animator
    public void setTarget(Object obj) {
        int size = this.mNodes.size();
        for (int i = 1; i < size; i++) {
            Animator animator = this.mNodes.get(i).mAnimation;
            if (animator instanceof AnimatorSet) {
                animator.setTarget(obj);
            } else if (animator instanceof ObjectAnimator) {
                animator.setTarget(obj);
            }
        }
    }

    @Override // androidx.core.animation.Animator
    public void setInterpolator(Interpolator interpolator) {
        this.mInterpolator = interpolator;
    }

    @Override // androidx.core.animation.Animator
    public Interpolator getInterpolator() {
        return this.mInterpolator;
    }

    public Builder play(Animator animator) {
        return new Builder(animator);
    }

    @Override // androidx.core.animation.Animator
    public void cancel() {
        if (Looper.myLooper() == null) {
            throw new AndroidRuntimeException("Animators may only be run on Looper threads");
        }
        if (isStarted()) {
            if (this.mListeners != null) {
                ArrayList arrayList = (ArrayList) this.mListeners.clone();
                int size = arrayList.size();
                for (int i = 0; i < size; i++) {
                    ((Animator.AnimatorListener) arrayList.get(i)).onAnimationCancel(this);
                }
            }
            ArrayList arrayList2 = new ArrayList(this.mPlayingSet);
            int size2 = arrayList2.size();
            for (int i2 = 0; i2 < size2; i2++) {
                ((Node) arrayList2.get(i2)).mAnimation.cancel();
            }
            this.mPlayingSet.clear();
            endAnimation();
        }
    }

    @Override // androidx.core.animation.Animator
    public void end() {
        if (Looper.myLooper() == null) {
            throw new AndroidRuntimeException("Animators may only be run on Looper threads");
        }
        if (isStarted()) {
            if (this.mReversing) {
                int size = this.mLastEventId;
                if (size == -1) {
                    size = this.mEvents.size();
                }
                this.mLastEventId = size;
                while (true) {
                    int i = this.mLastEventId;
                    if (i <= 0) {
                        break;
                    }
                    int i2 = i - 1;
                    this.mLastEventId = i2;
                    AnimationEvent animationEvent = this.mEvents.get(i2);
                    Animator animator = animationEvent.mNode.mAnimation;
                    if (!this.mNodeMap.get(animator).mEnded) {
                        if (animationEvent.mEvent == 2) {
                            animator.reverse();
                        } else if (animationEvent.mEvent == 1 && animator.isStarted()) {
                            animator.end();
                        }
                    }
                }
            } else {
                while (this.mLastEventId < this.mEvents.size() - 1) {
                    int i3 = this.mLastEventId + 1;
                    this.mLastEventId = i3;
                    AnimationEvent animationEvent2 = this.mEvents.get(i3);
                    Animator animator2 = animationEvent2.mNode.mAnimation;
                    if (!this.mNodeMap.get(animator2).mEnded) {
                        if (animationEvent2.mEvent == 0) {
                            animator2.start();
                        } else if (animationEvent2.mEvent == 2 && animator2.isStarted()) {
                            animator2.end();
                        }
                    }
                }
            }
            this.mPlayingSet.clear();
        }
        endAnimation();
    }

    @Override // androidx.core.animation.Animator
    public boolean isRunning() {
        if (this.mStartDelay == 0) {
            return this.mStarted;
        }
        return this.mLastFrameTime > 0;
    }

    @Override // androidx.core.animation.Animator
    public boolean isStarted() {
        return this.mStarted;
    }

    @Override // androidx.core.animation.Animator
    public long getStartDelay() {
        return this.mStartDelay;
    }

    @Override // androidx.core.animation.Animator
    public void setStartDelay(long j) {
        if (j < 0) {
            Log.w(TAG, "Start delay should always be non-negative");
            j = 0;
        }
        long j2 = j - this.mStartDelay;
        if (j2 == 0) {
            return;
        }
        this.mStartDelay = j;
        if (this.mDependencyDirty) {
            return;
        }
        int size = this.mNodes.size();
        int i = 0;
        while (true) {
            if (i >= size) {
                break;
            }
            Node node = this.mNodes.get(i);
            if (node == this.mRootNode) {
                node.mEndTime = this.mStartDelay;
            } else {
                node.mStartTime = node.mStartTime == -1 ? -1L : node.mStartTime + j2;
                node.mEndTime = node.mEndTime != -1 ? node.mEndTime + j2 : -1L;
            }
            i++;
        }
        long j3 = this.mTotalDuration;
        if (j3 != -1) {
            this.mTotalDuration = j3 + j2;
        }
    }

    @Override // androidx.core.animation.Animator
    public long getDuration() {
        return this.mDuration;
    }

    @Override // androidx.core.animation.Animator
    public AnimatorSet setDuration(long j) {
        if (j < 0) {
            throw new IllegalArgumentException("duration must be a value of zero or greater");
        }
        this.mDependencyDirty = true;
        this.mDuration = j;
        return this;
    }

    @Override // androidx.core.animation.Animator
    public void setupStartValues() {
        int size = this.mNodes.size();
        for (int i = 0; i < size; i++) {
            Node node = this.mNodes.get(i);
            if (node != this.mRootNode) {
                node.mAnimation.setupStartValues();
            }
        }
    }

    @Override // androidx.core.animation.Animator
    public void setupEndValues() {
        int size = this.mNodes.size();
        for (int i = 0; i < size; i++) {
            Node node = this.mNodes.get(i);
            if (node != this.mRootNode) {
                node.mAnimation.setupEndValues();
            }
        }
    }

    @Override // androidx.core.animation.Animator
    public void pause() {
        if (Looper.myLooper() == null) {
            throw new AndroidRuntimeException("Animators may only be run on Looper threads");
        }
        boolean z = this.mPaused;
        super.pause();
        if (z || !this.mPaused) {
            return;
        }
        this.mPauseTime = this.mLastFrameTime;
    }

    @Override // androidx.core.animation.Animator
    public void resume() {
        if (Looper.myLooper() == null) {
            throw new AndroidRuntimeException("Animators may only be run on Looper threads");
        }
        boolean z = this.mPaused;
        super.resume();
        if (!z || this.mPaused || this.mPauseTime < 0 || !this.mSelfPulse) {
            return;
        }
        addAnimationCallback(this);
    }

    @Override // androidx.core.animation.Animator
    public void start() {
        start(false, true);
    }

    @Override // androidx.core.animation.Animator
    void startWithoutPulsing(boolean z) {
        start(z, false);
    }

    private void initAnimation() {
        if (this.mInterpolator != null) {
            for (int i = 0; i < this.mNodes.size(); i++) {
                this.mNodes.get(i).mAnimation.setInterpolator(this.mInterpolator);
            }
        }
        updateAnimatorsDuration();
        createDependencyGraph();
    }

    private void start(boolean z, boolean z2) {
        if (Looper.myLooper() == null) {
            throw new AndroidRuntimeException("Animators may only be run on Looper threads");
        }
        this.mStarted = true;
        this.mSelfPulse = z2;
        this.mPaused = false;
        this.mPauseTime = -1L;
        int size = this.mNodes.size();
        for (int i = 0; i < size; i++) {
            this.mNodes.get(i).mEnded = false;
        }
        initAnimation();
        if (z && !canReverse()) {
            throw new UnsupportedOperationException("Cannot reverse infinite AnimatorSet");
        }
        this.mReversing = z;
        boolean zIsEmptySet = isEmptySet(this);
        if (!zIsEmptySet) {
            startAnimation();
        }
        if (this.mListeners != null) {
            ArrayList arrayList = (ArrayList) this.mListeners.clone();
            int size2 = arrayList.size();
            for (int i2 = 0; i2 < size2; i2++) {
                ((Animator.AnimatorListener) arrayList.get(i2)).onAnimationStart(this, z);
            }
        }
        if (zIsEmptySet) {
            end();
        }
    }

    private static boolean isEmptySet(AnimatorSet animatorSet) {
        if (animatorSet.getStartDelay() > 0) {
            return false;
        }
        for (int i = 0; i < animatorSet.getChildAnimations().size(); i++) {
            Animator animator = animatorSet.getChildAnimations().get(i);
            if (!(animator instanceof AnimatorSet) || !isEmptySet((AnimatorSet) animator)) {
                return false;
            }
        }
        return true;
    }

    private void updateAnimatorsDuration() {
        if (this.mDuration >= 0) {
            int size = this.mNodes.size();
            for (int i = 0; i < size; i++) {
                this.mNodes.get(i).mAnimation.setDuration(this.mDuration);
            }
        }
        this.mDelayAnim.setDuration(this.mStartDelay);
    }

    @Override // androidx.core.animation.Animator
    void skipToEndValue(boolean z) {
        if (this.mSelfPulse && !isInitialized()) {
            throw new UnsupportedOperationException("Children must be initialized.");
        }
        initAnimation();
        if (z) {
            for (int size = this.mEvents.size() - 1; size >= 0; size--) {
                if (this.mEvents.get(size).mEvent == 1) {
                    this.mEvents.get(size).mNode.mAnimation.skipToEndValue(true);
                }
            }
            return;
        }
        for (int i = 0; i < this.mEvents.size(); i++) {
            if (this.mEvents.get(i).mEvent == 2) {
                this.mEvents.get(i).mNode.mAnimation.skipToEndValue(false);
            }
        }
    }

    @Override // androidx.core.animation.Animator
    void animateBasedOnPlayTime(long j, long j2, boolean z) {
        if (j < 0 || j2 < 0) {
            throw new UnsupportedOperationException("Error: Play time should never be negative.");
        }
        if (z) {
            if (getTotalDuration() == -1) {
                throw new UnsupportedOperationException("Cannot reverse AnimatorSet with infinite duration");
            }
            long totalDuration = getTotalDuration() - this.mStartDelay;
            j = totalDuration - Math.min(j, totalDuration);
            j2 = totalDuration - j2;
            z = false;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < this.mEvents.size(); i++) {
            AnimationEvent animationEvent = this.mEvents.get(i);
            if (animationEvent.getTime() > j || animationEvent.getTime() == -1) {
                break;
            }
            if (animationEvent.mEvent == 1 && (animationEvent.mNode.mEndTime == -1 || animationEvent.mNode.mEndTime > j)) {
                arrayList.add(animationEvent.mNode);
            }
            if (animationEvent.mEvent == 2) {
                animationEvent.mNode.mAnimation.skipToEndValue(false);
            }
        }
        for (int i2 = 0; i2 < this.mEvents.size(); i2++) {
            AnimationEvent animationEvent2 = this.mEvents.get(i2);
            if (animationEvent2.getTime() > j && animationEvent2.mEvent == 1) {
                animationEvent2.mNode.mAnimation.skipToEndValue(true);
            }
        }
        for (int i3 = 0; i3 < arrayList.size(); i3++) {
            Node node = (Node) arrayList.get(i3);
            long playTimeForNode = getPlayTimeForNode(j, node, z);
            if (!z) {
                playTimeForNode -= node.mAnimation.getStartDelay();
            }
            node.mAnimation.animateBasedOnPlayTime(playTimeForNode, j2, z);
        }
    }

    @Override // androidx.core.animation.Animator
    boolean isInitialized() {
        boolean z = true;
        if (this.mChildrenInitialized) {
            return true;
        }
        int i = 0;
        while (true) {
            if (i >= this.mNodes.size()) {
                break;
            }
            if (!this.mNodes.get(i).mAnimation.isInitialized()) {
                z = false;
                break;
            }
            i++;
        }
        this.mChildrenInitialized = z;
        return z;
    }

    public void setCurrentPlayTime(long j) {
        if (this.mReversing && getTotalDuration() == -1) {
            throw new UnsupportedOperationException("Error: Cannot seek in reverse in an infinite AnimatorSet");
        }
        if ((getTotalDuration() != -1 && j > getTotalDuration() - this.mStartDelay) || j < 0) {
            throw new UnsupportedOperationException("Error: Play time should always be in between0 and duration.");
        }
        initAnimation();
        if (!isStarted() || isPaused()) {
            if (this.mReversing) {
                throw new UnsupportedOperationException("Error: Something went wrong. mReversing should not be set when AnimatorSet is not started.");
            }
            if (!this.mSeekState.isActive()) {
                findLatestEventIdForTime(0L);
                initChildren();
                this.mSeekState.setPlayTime(0L, this.mReversing);
            }
            animateBasedOnPlayTime(j, 0L, this.mReversing);
            this.mSeekState.setPlayTime(j, this.mReversing);
            notifyUpdateListeners();
            return;
        }
        this.mSeekState.setPlayTime(j, this.mReversing);
    }

    public long getCurrentPlayTime() {
        if (this.mSeekState.isActive()) {
            return this.mSeekState.getPlayTime();
        }
        if (this.mLastFrameTime == -1) {
            return 0L;
        }
        float durationScale = ValueAnimator.getDurationScale();
        if (durationScale == 0.0f) {
            durationScale = 1.0f;
        }
        if (this.mReversing) {
            return (long) ((this.mLastFrameTime - this.mFirstFrame) / durationScale);
        }
        return (long) (((this.mLastFrameTime - this.mFirstFrame) - this.mStartDelay) / durationScale);
    }

    private void initChildren() {
        if (isInitialized()) {
            return;
        }
        this.mChildrenInitialized = true;
        skipToEndValue(false);
    }

    @Override // androidx.core.animation.AnimationHandler.AnimationFrameCallback
    public boolean doAnimationFrame(long j) {
        float durationScale = ValueAnimator.getDurationScale();
        if (durationScale == 0.0f) {
            end();
            return true;
        }
        if (this.mFirstFrame < 0) {
            this.mFirstFrame = j;
        }
        if (this.mPaused) {
            if (this.mPauseTime == -1) {
                this.mPauseTime = j;
            }
            removeAnimationCallback();
            return false;
        }
        long j2 = this.mPauseTime;
        if (j2 > 0) {
            this.mFirstFrame += j - j2;
            this.mPauseTime = -1L;
        }
        if (this.mSeekState.isActive()) {
            this.mSeekState.updateSeekDirection(this.mReversing);
            if (this.mReversing) {
                this.mFirstFrame = j - ((long) (this.mSeekState.getPlayTime() * durationScale));
            } else {
                this.mFirstFrame = j - ((long) ((this.mSeekState.getPlayTime() + this.mStartDelay) * durationScale));
            }
            skipToEndValue(!this.mReversing);
            this.mPlayingSet.clear();
            for (int size = this.mNodes.size() - 1; size >= 0; size--) {
                this.mNodes.get(size).mEnded = false;
            }
            this.mLastEventId = -1;
            this.mSeekState.reset();
        }
        if (!this.mReversing && j < this.mFirstFrame + ((long) (this.mStartDelay * durationScale))) {
            return false;
        }
        long j3 = (long) ((j - this.mFirstFrame) / durationScale);
        this.mLastFrameTime = j;
        int iFindLatestEventIdForTime = findLatestEventIdForTime(j3);
        handleAnimationEvents(this.mLastEventId, iFindLatestEventIdForTime, j3);
        this.mLastEventId = iFindLatestEventIdForTime;
        for (int i = 0; i < this.mPlayingSet.size(); i++) {
            Node node = this.mPlayingSet.get(i);
            if (!node.mEnded) {
                pulseFrame(node, getPlayTimeForNode(j3, node));
            }
        }
        for (int size2 = this.mPlayingSet.size() - 1; size2 >= 0; size2--) {
            if (this.mPlayingSet.get(size2).mEnded) {
                this.mPlayingSet.remove(size2);
            }
        }
        boolean z = !this.mReversing ? !(this.mPlayingSet.isEmpty() && this.mLastEventId == this.mEvents.size() - 1) : !(this.mPlayingSet.size() == 1 && this.mPlayingSet.get(0) == this.mRootNode) && (!this.mPlayingSet.isEmpty() || this.mLastEventId >= 3);
        notifyUpdateListeners();
        if (!z) {
            return false;
        }
        endAnimation();
        return true;
    }

    private void notifyUpdateListeners() {
        if (this.mUpdateListeners != null) {
            for (int i = 0; i < this.mUpdateListeners.size(); i++) {
                this.mUpdateListeners.get(i).onAnimationUpdate(this);
            }
        }
    }

    @Override // androidx.core.animation.Animator
    boolean pulseAnimationFrame(long j) {
        return doAnimationFrame(j);
    }

    private void handleAnimationEvents(int i, int i2, long j) {
        if (!this.mReversing) {
            for (int i3 = i + 1; i3 <= i2; i3++) {
                AnimationEvent animationEvent = this.mEvents.get(i3);
                Node node = animationEvent.mNode;
                if (animationEvent.mEvent == 0) {
                    this.mPlayingSet.add(animationEvent.mNode);
                    if (node.mAnimation.isStarted()) {
                        node.mAnimation.cancel();
                    }
                    node.mEnded = false;
                    node.mAnimation.startWithoutPulsing(false);
                    pulseFrame(node, 0L);
                } else if (animationEvent.mEvent == 2 && !node.mEnded) {
                    pulseFrame(node, getPlayTimeForNode(j, node));
                }
            }
            return;
        }
        if (i == -1) {
            i = this.mEvents.size();
        }
        for (int i4 = i - 1; i4 >= i2; i4--) {
            AnimationEvent animationEvent2 = this.mEvents.get(i4);
            Node node2 = animationEvent2.mNode;
            if (animationEvent2.mEvent == 2) {
                if (node2.mAnimation.isStarted()) {
                    node2.mAnimation.cancel();
                }
                node2.mEnded = false;
                this.mPlayingSet.add(animationEvent2.mNode);
                node2.mAnimation.startWithoutPulsing(true);
                pulseFrame(node2, 0L);
            } else if (animationEvent2.mEvent == 1 && !node2.mEnded) {
                pulseFrame(node2, getPlayTimeForNode(j, node2));
            }
        }
    }

    private void pulseFrame(Node node, long j) {
        if (node.mEnded) {
            return;
        }
        float durationScale = ValueAnimator.getDurationScale();
        if (durationScale == 0.0f) {
            durationScale = 1.0f;
        }
        node.mEnded = node.mAnimation.pulseAnimationFrame((long) (j * durationScale));
    }

    private long getPlayTimeForNode(long j, Node node) {
        return getPlayTimeForNode(j, node, this.mReversing);
    }

    private long getPlayTimeForNode(long j, Node node, boolean z) {
        if (z) {
            return node.mEndTime - (getTotalDuration() - j);
        }
        return j - node.mStartTime;
    }

    private void startAnimation() {
        addNoOpListener();
        long playTime = 0;
        if (this.mSeekState.getPlayTimeNormalized() == 0 && this.mReversing) {
            this.mSeekState.reset();
        }
        if (isInitialized()) {
            skipToEndValue(!this.mReversing);
        } else if (this.mReversing) {
            initChildren();
            skipToEndValue(!this.mReversing);
        } else {
            for (int size = this.mEvents.size() - 1; size >= 0; size--) {
                if (this.mEvents.get(size).mEvent == 1) {
                    Animator animator = this.mEvents.get(size).mNode.mAnimation;
                    if (animator.isInitialized()) {
                        animator.skipToEndValue(true);
                    }
                }
            }
        }
        if (this.mReversing || this.mStartDelay == 0 || this.mSeekState.isActive()) {
            if (this.mSeekState.isActive()) {
                this.mSeekState.updateSeekDirection(this.mReversing);
                playTime = this.mSeekState.getPlayTime();
            }
            int iFindLatestEventIdForTime = findLatestEventIdForTime(playTime);
            handleAnimationEvents(-1, iFindLatestEventIdForTime, playTime);
            for (int size2 = this.mPlayingSet.size() - 1; size2 >= 0; size2--) {
                if (this.mPlayingSet.get(size2).mEnded) {
                    this.mPlayingSet.remove(size2);
                }
            }
            this.mLastEventId = iFindLatestEventIdForTime;
        }
        if (this.mSelfPulse) {
            addAnimationCallback(this);
        }
    }

    private void addNoOpListener() {
        for (int i = 1; i < this.mNodes.size(); i++) {
            this.mNodes.get(i).mAnimation.addListener(this.mNoOpListener);
        }
    }

    private void removeNoOpListener() {
        for (int i = 1; i < this.mNodes.size(); i++) {
            this.mNodes.get(i).mAnimation.removeListener(this.mNoOpListener);
        }
    }

    private int findLatestEventIdForTime(long j) {
        int size = this.mEvents.size();
        int i = this.mLastEventId;
        if (this.mReversing) {
            long totalDuration = getTotalDuration() - j;
            int i2 = this.mLastEventId;
            if (i2 != -1) {
                size = i2;
            }
            this.mLastEventId = size;
            for (int i3 = size - 1; i3 >= 0; i3--) {
                if (this.mEvents.get(i3).getTime() >= totalDuration) {
                    i = i3;
                }
            }
        } else {
            for (int i4 = i + 1; i4 < size; i4++) {
                AnimationEvent animationEvent = this.mEvents.get(i4);
                if (animationEvent.getTime() != -1 && animationEvent.getTime() <= j) {
                    i = i4;
                }
            }
        }
        return i;
    }

    private void endAnimation() {
        this.mStarted = false;
        this.mLastFrameTime = -1L;
        this.mFirstFrame = -1L;
        this.mLastEventId = -1;
        this.mPaused = false;
        this.mPauseTime = -1L;
        this.mSeekState.reset();
        this.mPlayingSet.clear();
        removeAnimationCallback();
        if (this.mListeners != null) {
            ArrayList arrayList = (ArrayList) this.mListeners.clone();
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                ((Animator.AnimatorListener) arrayList.get(i)).onAnimationEnd(this, this.mReversing);
            }
        }
        removeNoOpListener();
        this.mSelfPulse = true;
        this.mReversing = false;
    }

    private void removeAnimationCallback() {
        if (this.mSelfPulse) {
            AnimationHandler.getInstance().removeCallback(this);
        }
    }

    @Override // androidx.core.animation.Animator
    /* renamed from: clone */
    public AnimatorSet mo672clone() {
        final AnimatorSet animatorSet = (AnimatorSet) super.mo672clone();
        int size = this.mNodes.size();
        animatorSet.mStarted = false;
        animatorSet.mLastFrameTime = -1L;
        animatorSet.mFirstFrame = -1L;
        animatorSet.mLastEventId = -1;
        animatorSet.mPaused = false;
        animatorSet.mPauseTime = -1L;
        animatorSet.mSeekState = new SeekState();
        animatorSet.mSelfPulse = true;
        animatorSet.mPlayingSet = new ArrayList<>();
        animatorSet.mNodeMap = new SimpleArrayMap<>();
        animatorSet.mNodes = new ArrayList<>(size);
        animatorSet.mEvents = new ArrayList<>();
        animatorSet.mNoOpListener = new AnimatorListenerAdapter() { // from class: androidx.core.animation.AnimatorSet.2
            @Override // androidx.core.animation.AnimatorListenerAdapter, androidx.core.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                if (animatorSet.mNodeMap.get(animator) == null) {
                    throw new AndroidRuntimeException("Error: animation ended is not in the node map");
                }
                animatorSet.mNodeMap.get(animator).mEnded = true;
            }
        };
        animatorSet.mReversing = false;
        animatorSet.mDependencyDirty = true;
        HashMap map = new HashMap(size);
        for (int i = 0; i < size; i++) {
            Node node = this.mNodes.get(i);
            Node nodeM673clone = node.m673clone();
            nodeM673clone.mAnimation.removeListener(this.mNoOpListener);
            map.put(node, nodeM673clone);
            animatorSet.mNodes.add(nodeM673clone);
            animatorSet.mNodeMap.put(nodeM673clone.mAnimation, nodeM673clone);
        }
        Node node2 = (Node) map.get(this.mRootNode);
        animatorSet.mRootNode = node2;
        animatorSet.mDelayAnim = (ValueAnimator) node2.mAnimation;
        for (int i2 = 0; i2 < size; i2++) {
            Node node3 = this.mNodes.get(i2);
            Node node4 = (Node) map.get(node3);
            node4.mLatestParent = node3.mLatestParent == null ? null : (Node) map.get(node3.mLatestParent);
            int size2 = node3.mChildNodes == null ? 0 : node3.mChildNodes.size();
            for (int i3 = 0; i3 < size2; i3++) {
                node4.mChildNodes.set(i3, (Node) map.get(node3.mChildNodes.get(i3)));
            }
            int size3 = node3.mSiblings == null ? 0 : node3.mSiblings.size();
            for (int i4 = 0; i4 < size3; i4++) {
                node4.mSiblings.set(i4, (Node) map.get(node3.mSiblings.get(i4)));
            }
            int size4 = node3.mParents == null ? 0 : node3.mParents.size();
            for (int i5 = 0; i5 < size4; i5++) {
                node4.mParents.set(i5, (Node) map.get(node3.mParents.get(i5)));
            }
        }
        return animatorSet;
    }

    @Override // androidx.core.animation.Animator
    public boolean canReverse() {
        return getTotalDuration() != -1;
    }

    @Override // androidx.core.animation.Animator
    public void reverse() {
        start(true, true);
    }

    public String toString() {
        String str = "AnimatorSet@" + Integer.toHexString(hashCode()) + "{";
        int size = this.mNodes.size();
        for (int i = 0; i < size; i++) {
            str = str + "\n    " + this.mNodes.get(i).mAnimation.toString();
        }
        return str + "\n}";
    }

    private void createDependencyGraph() {
        if (!this.mDependencyDirty) {
            for (int i = 0; i < this.mNodes.size(); i++) {
                if (this.mNodes.get(i).mTotalDuration == this.mNodes.get(i).mAnimation.getTotalDuration()) {
                }
            }
            return;
        }
        this.mDependencyDirty = false;
        int size = this.mNodes.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.mNodes.get(i2).mParentsAdded = false;
        }
        for (int i3 = 0; i3 < size; i3++) {
            Node node = this.mNodes.get(i3);
            if (!node.mParentsAdded) {
                node.mParentsAdded = true;
                if (node.mSiblings != null) {
                    findSiblings(node, node.mSiblings);
                    node.mSiblings.remove(node);
                    int size2 = node.mSiblings.size();
                    for (int i4 = 0; i4 < size2; i4++) {
                        node.addParents(node.mSiblings.get(i4).mParents);
                    }
                    for (int i5 = 0; i5 < size2; i5++) {
                        Node node2 = node.mSiblings.get(i5);
                        node2.addParents(node.mParents);
                        node2.mParentsAdded = true;
                    }
                }
            }
        }
        for (int i6 = 0; i6 < size; i6++) {
            Node node3 = this.mNodes.get(i6);
            if (node3 != this.mRootNode && node3.mParents == null) {
                node3.addParent(this.mRootNode);
            }
        }
        ArrayList<Node> arrayList = new ArrayList<>(this.mNodes.size());
        this.mRootNode.mStartTime = 0L;
        this.mRootNode.mEndTime = this.mDelayAnim.getDuration();
        updatePlayTime(this.mRootNode, arrayList);
        sortAnimationEvents();
        ArrayList<AnimationEvent> arrayList2 = this.mEvents;
        this.mTotalDuration = arrayList2.get(arrayList2.size() - 1).getTime();
    }

    private void sortAnimationEvents() {
        boolean z;
        this.mEvents.clear();
        for (int i = 1; i < this.mNodes.size(); i++) {
            Node node = this.mNodes.get(i);
            this.mEvents.add(new AnimationEvent(node, 0));
            this.mEvents.add(new AnimationEvent(node, 1));
            this.mEvents.add(new AnimationEvent(node, 2));
        }
        Collections.sort(this.mEvents, EVENT_COMPARATOR);
        int size = this.mEvents.size();
        int i2 = 0;
        while (i2 < size) {
            AnimationEvent animationEvent = this.mEvents.get(i2);
            if (animationEvent.mEvent == 2) {
                if (animationEvent.mNode.mStartTime == animationEvent.mNode.mEndTime) {
                    z = true;
                } else if (animationEvent.mNode.mEndTime == animationEvent.mNode.mStartTime + animationEvent.mNode.mAnimation.getStartDelay()) {
                    z = false;
                }
                int i3 = i2 + 1;
                int i4 = size;
                int i5 = i4;
                for (int i6 = i3; i6 < size && (i4 >= size || i5 >= size); i6++) {
                    if (this.mEvents.get(i6).mNode == animationEvent.mNode) {
                        if (this.mEvents.get(i6).mEvent == 0) {
                            i4 = i6;
                        } else if (this.mEvents.get(i6).mEvent == 1) {
                            i5 = i6;
                        }
                    }
                }
                if (z && i4 == this.mEvents.size()) {
                    throw new UnsupportedOperationException("Something went wrong, no start isfound after stop for an animation that has the same start and endtime.");
                }
                if (i5 == this.mEvents.size()) {
                    throw new UnsupportedOperationException("Something went wrong, no startdelay end is found after stop for an animation");
                }
                if (z) {
                    this.mEvents.add(i2, this.mEvents.remove(i4));
                    i2 = i3;
                }
                this.mEvents.add(i2, this.mEvents.remove(i5));
                i2 += 2;
            }
            i2++;
        }
        if (!this.mEvents.isEmpty() && this.mEvents.get(0).mEvent != 0) {
            throw new UnsupportedOperationException("Sorting went bad, the start event should always be at index 0");
        }
        this.mEvents.add(0, new AnimationEvent(this.mRootNode, 0));
        this.mEvents.add(1, new AnimationEvent(this.mRootNode, 1));
        this.mEvents.add(2, new AnimationEvent(this.mRootNode, 2));
        ArrayList<AnimationEvent> arrayList = this.mEvents;
        if (arrayList.get(arrayList.size() - 1).mEvent != 0) {
            ArrayList<AnimationEvent> arrayList2 = this.mEvents;
            if (arrayList2.get(arrayList2.size() - 1).mEvent != 1) {
                return;
            }
        }
        throw new UnsupportedOperationException("Something went wrong, the last event is not an end event");
    }

    private void updatePlayTime(Node node, ArrayList<Node> arrayList) {
        int i = 0;
        if (node.mChildNodes == null) {
            if (node == this.mRootNode) {
                while (i < this.mNodes.size()) {
                    Node node2 = this.mNodes.get(i);
                    if (node2 != this.mRootNode) {
                        node2.mStartTime = -1L;
                        node2.mEndTime = -1L;
                    }
                    i++;
                }
                return;
            }
            return;
        }
        arrayList.add(node);
        int size = node.mChildNodes.size();
        while (i < size) {
            Node node3 = node.mChildNodes.get(i);
            node3.mTotalDuration = node3.mAnimation.getTotalDuration();
            int iIndexOf = arrayList.indexOf(node3);
            if (iIndexOf >= 0) {
                while (iIndexOf < arrayList.size()) {
                    arrayList.get(iIndexOf).mLatestParent = null;
                    arrayList.get(iIndexOf).mStartTime = -1L;
                    arrayList.get(iIndexOf).mEndTime = -1L;
                    iIndexOf++;
                }
                node3.mStartTime = -1L;
                node3.mEndTime = -1L;
                node3.mLatestParent = null;
                Log.w(TAG, "Cycle found in AnimatorSet: " + this);
            } else {
                if (node3.mStartTime != -1) {
                    if (node.mEndTime == -1) {
                        node3.mLatestParent = node;
                        node3.mStartTime = -1L;
                        node3.mEndTime = -1L;
                    } else {
                        if (node.mEndTime >= node3.mStartTime) {
                            node3.mLatestParent = node;
                            node3.mStartTime = node.mEndTime;
                        }
                        node3.mEndTime = node3.mTotalDuration == -1 ? -1L : node3.mStartTime + node3.mTotalDuration;
                    }
                }
                updatePlayTime(node3, arrayList);
            }
            i++;
        }
        arrayList.remove(node);
    }

    private void findSiblings(Node node, ArrayList<Node> arrayList) {
        if (arrayList.contains(node)) {
            return;
        }
        arrayList.add(node);
        if (node.mSiblings == null) {
            return;
        }
        for (int i = 0; i < node.mSiblings.size(); i++) {
            findSiblings(node.mSiblings.get(i), arrayList);
        }
    }

    @Override // androidx.core.animation.Animator
    public long getTotalDuration() {
        updateAnimatorsDuration();
        createDependencyGraph();
        return this.mTotalDuration;
    }

    Node getNodeForAnimation(Animator animator) {
        Node node = this.mNodeMap.get(animator);
        if (node == null) {
            node = new Node(animator);
            this.mNodeMap.put(animator, node);
            this.mNodes.add(node);
            if (animator instanceof AnimatorSet) {
                ((AnimatorSet) animator).mSelfPulse = false;
            }
        }
        return node;
    }

    private static class Node implements Cloneable {
        Animator mAnimation;
        ArrayList<Node> mParents;
        ArrayList<Node> mSiblings;
        ArrayList<Node> mChildNodes = null;
        boolean mEnded = false;
        Node mLatestParent = null;
        boolean mParentsAdded = false;
        long mStartTime = 0;
        long mEndTime = 0;
        long mTotalDuration = 0;

        Node(Animator animator) {
            this.mAnimation = animator;
        }

        /* renamed from: clone, reason: merged with bridge method [inline-methods] */
        public Node m673clone() {
            try {
                Node node = (Node) super.clone();
                node.mAnimation = this.mAnimation.mo672clone();
                if (this.mChildNodes != null) {
                    node.mChildNodes = new ArrayList<>(this.mChildNodes);
                }
                if (this.mSiblings != null) {
                    node.mSiblings = new ArrayList<>(this.mSiblings);
                }
                if (this.mParents != null) {
                    node.mParents = new ArrayList<>(this.mParents);
                }
                node.mEnded = false;
                return node;
            } catch (CloneNotSupportedException unused) {
                throw new AssertionError();
            }
        }

        void addChild(Node node) {
            if (this.mChildNodes == null) {
                this.mChildNodes = new ArrayList<>();
            }
            if (this.mChildNodes.contains(node)) {
                return;
            }
            this.mChildNodes.add(node);
            node.addParent(this);
        }

        public void addSibling(Node node) {
            if (this.mSiblings == null) {
                this.mSiblings = new ArrayList<>();
            }
            if (this.mSiblings.contains(node)) {
                return;
            }
            this.mSiblings.add(node);
            node.addSibling(this);
        }

        public void addParent(Node node) {
            if (this.mParents == null) {
                this.mParents = new ArrayList<>();
            }
            if (this.mParents.contains(node)) {
                return;
            }
            this.mParents.add(node);
            node.addChild(this);
        }

        public void addParents(ArrayList<Node> arrayList) {
            if (arrayList == null) {
                return;
            }
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                addParent(arrayList.get(i));
            }
        }
    }

    private static class AnimationEvent {
        static final int ANIMATION_DELAY_ENDED = 1;
        static final int ANIMATION_END = 2;
        static final int ANIMATION_START = 0;
        final int mEvent;
        final Node mNode;

        AnimationEvent(Node node, int i) {
            this.mNode = node;
            this.mEvent = i;
        }

        long getTime() {
            int i = this.mEvent;
            if (i == 0) {
                return this.mNode.mStartTime;
            }
            if (i != 1) {
                return this.mNode.mEndTime;
            }
            if (this.mNode.mStartTime == -1) {
                return -1L;
            }
            return this.mNode.mAnimation.getStartDelay() + this.mNode.mStartTime;
        }

        public String toString() {
            String str;
            int i = this.mEvent;
            if (i == 0) {
                str = "start";
            } else {
                str = i == 1 ? "delay ended" : "end";
            }
            return str + HelpFormatter.DEFAULT_LONG_OPT_SEPARATOR + this.mNode.mAnimation.toString();
        }
    }

    class SeekState {
        private long mPlayTime = -1;
        private boolean mSeekingInReverse = false;

        SeekState() {
        }

        void reset() {
            this.mPlayTime = -1L;
            this.mSeekingInReverse = false;
        }

        void setPlayTime(long j, boolean z) {
            if (AnimatorSet.this.getTotalDuration() != -1) {
                this.mPlayTime = Math.max(0L, Math.min(j, AnimatorSet.this.getTotalDuration() - AnimatorSet.this.mStartDelay));
            } else {
                this.mPlayTime = Math.max(0L, j);
            }
            this.mSeekingInReverse = z;
        }

        void updateSeekDirection(boolean z) {
            if (z && AnimatorSet.this.getTotalDuration() == -1) {
                throw new UnsupportedOperationException("Error: Cannot reverse infinite animator set");
            }
            if (this.mPlayTime < 0 || z == this.mSeekingInReverse) {
                return;
            }
            this.mPlayTime = (AnimatorSet.this.getTotalDuration() - AnimatorSet.this.mStartDelay) - this.mPlayTime;
            this.mSeekingInReverse = z;
        }

        long getPlayTime() {
            return this.mPlayTime;
        }

        long getPlayTimeNormalized() {
            if (AnimatorSet.this.mReversing) {
                return (AnimatorSet.this.getTotalDuration() - AnimatorSet.this.mStartDelay) - this.mPlayTime;
            }
            return this.mPlayTime;
        }

        boolean isActive() {
            return this.mPlayTime != -1;
        }
    }

    public class Builder {
        private Node mCurrentNode;

        Builder(Animator animator) {
            AnimatorSet.this.mDependencyDirty = true;
            this.mCurrentNode = AnimatorSet.this.getNodeForAnimation(animator);
        }

        public Builder with(Animator animator) {
            this.mCurrentNode.addSibling(AnimatorSet.this.getNodeForAnimation(animator));
            return this;
        }

        public Builder before(Animator animator) {
            this.mCurrentNode.addChild(AnimatorSet.this.getNodeForAnimation(animator));
            return this;
        }

        public Builder after(Animator animator) {
            this.mCurrentNode.addParent(AnimatorSet.this.getNodeForAnimation(animator));
            return this;
        }

        public Builder after(long j) {
            ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
            valueAnimatorOfFloat.setDuration(j);
            after(valueAnimatorOfFloat);
            return this;
        }
    }
}
