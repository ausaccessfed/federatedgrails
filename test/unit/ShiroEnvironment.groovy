import org.apache.shiro.SecurityUtils
import org.apache.shiro.UnavailableSecurityManagerException
import org.apache.shiro.mgt.SecurityManager
import org.apache.shiro.subject.Subject
import org.apache.shiro.subject.support.SubjectThreadState
import org.apache.shiro.util.LifecycleUtils
import org.apache.shiro.util.ThreadState

class ShiroEnvironment {

  private static ThreadState subjectThreadState

    void setSubject(Subject subject) {
        clearSubject()
        subjectThreadState = createThreadState(subject)
        subjectThreadState.bind()
    }

    Subject getSubject() {
        return SecurityUtils.getSubject()
    }

    ThreadState createThreadState(Subject subject) {
        return new SubjectThreadState(subject)
    }

    void clearSubject() {
        doClearSubject()
    }

    private static void doClearSubject() {
        if (subjectThreadState != null) {
            subjectThreadState.clear()
            subjectThreadState = null
        }
    }

    static void setSecurityManager(SecurityManager securityManager) {
        SecurityUtils.setSecurityManager(securityManager)
    }

    static SecurityManager getSecurityManager() {
        return SecurityUtils.getSecurityManager()
    }

    void tearDownShiro() {
        doClearSubject()
        try {
            SecurityManager securityManager = getSecurityManager()
            LifecycleUtils.destroy(securityManager)
        } catch (UnavailableSecurityManagerException e) {
            //we don't care about this when cleaning up the test environment
            //(for example, maybe the subclass is a unit test and it didn't
            // need a SecurityManager instance because it was using only
            // mock Subject instances)
        }
        setSecurityManager(null)
    }
}
