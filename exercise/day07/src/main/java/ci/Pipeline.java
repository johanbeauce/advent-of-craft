package ci;

import ci.dependencies.Config;
import ci.dependencies.Emailer;
import ci.dependencies.Logger;
import ci.dependencies.Project;

public class Pipeline {
    private final Config config;
    private final Emailer emailer;
    private final Logger log;

    public Pipeline(Config config, Emailer emailer, Logger log) {
        this.config = config;
        this.emailer = emailer;
        this.log = log;
    }

    public void run(Project project) {

        testProject(project);

        deployProject(project);
    }

    private void deployProject(Project project) {
        if (projectHasNotTestOrRunSuccessFully(project)) {
            if (project.isDeploySuccessFully()) {
                log.info("Deployment successful");
                sendEmailSummary("Deployment completed successfully");
            } else {
                log.error("Deployment failed");
                sendEmailSummary("Deployment failed");
            }
        } else {
            sendEmailSummary("Tests failed");
        }

        if (!config.sendEmailSummary()) {
            log.info("Email disabled");
        }
    }

    private void testProject(Project project) {
        if (project.hasTests()) {
            if (project.isRunTestsSuccessFully()) {
                log.info("Tests passed");
            } else {
                log.error("Tests failed");
            }
        } else {
            log.info("No tests");
        }
    }

    private boolean projectHasNotTestOrRunSuccessFully(Project project) {
        return !project.hasTests() || project.isRunTestsSuccessFully();
    }

    public void sendEmailSummary(String message) {
        if (config.sendEmailSummary()) {
            log.info("Sending email");
            emailer.send(message);
        }
    }
}
